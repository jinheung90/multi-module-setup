
resource "aws_iam_instance_profile" "ecs_agent" {
  name = "ecs-agent-iam-instance-profile"
  role = aws_iam_role.ecs_agent.name
}


resource "aws_security_group" "ec2-sg" {
  vpc_id = aws_vpc.jhc_vpc.id

  ingress {
    from_port        = 443
    to_port          = 443
    protocol         = "tcp"
    cidr_blocks      = ["0.0.0.0/0"]
    ipv6_cidr_blocks = ["::/0"]
  }

  ingress {
    from_port        = 8080
    to_port          = 8080
    protocol         = "tcp"
    cidr_blocks      = ["0.0.0.0/0"]
    ipv6_cidr_blocks = ["::/0"]
  }

  ingress {
    from_port        = 22
    to_port          = 22
    protocol         = "tcp"
    cidr_blocks      = ["0.0.0.0/0"]
    ipv6_cidr_blocks = ["::/0"]
  }

  egress {
    from_port        = 0
    to_port          = 0
    protocol         = "-1"
    cidr_blocks      = ["0.0.0.0/0"]
    ipv6_cidr_blocks = ["::/0"]
  }
  tags = {
    Name        = "${var.app_name}-sg"
    Environment = var.app_environment
  }
}


resource "aws_launch_configuration" "ecs_launch_config" {
  image_id      = "ami-0b6d6fc5fe3f750f1" #amazon ec2
  iam_instance_profile = aws_iam_instance_profile.ecs_agent.name
  security_groups      = [aws_security_group.ec2-sg.id]
  user_data = <<EOF
        #!/bin/bash
        echo ECS_CLUSTER=${aws_ecs_cluster.jhc_cluster.name} >> /etc/ecs/ecs.config
        EOF
  instance_type        = "t3.small"
  name_prefix = "${var.app_name}-${var.app_environment}"
  key_name = "test_key"
}

resource "aws_autoscaling_group" "asg" {
  vpc_zone_identifier       = aws_subnet.public.*.id
  name                      = "${var.app_name}-${var.app_environment}-asg"
  max_size                  = 2
  min_size                  = 1
  health_check_grace_period = 300
  health_check_type         = "EC2"
  desired_capacity          = 2
  force_delete              = true #운영단에서는 false 취급한다
  target_group_arns = [aws_lb_target_group.target_group.arn]
  launch_configuration = aws_launch_configuration.ecs_launch_config.name
}

resource "aws_autoscaling_policy" "policy_up" {
  autoscaling_group_name = aws_autoscaling_group.asg.name
  name                   = "web_policy_up"
  adjustment_type = "ChangeInCapacity"
  cooldown = 300
  scaling_adjustment = 1
}

resource "aws_cloudwatch_metric_alarm" "web_cpu_alarm_up" {
  alarm_name          = "web_cpu_alarm_up"
  comparison_operator = "GreaterThanOrEqualToThreshold"
  evaluation_periods  = "2"
  metric_name = "${var.app_name}-${var.app_environment}-cpu"
  namespace = "AWS/EC2"
  period = 120
  statistic = "Average"
  threshold = 60
  dimensions = {
    AutoScalingGroupName = aws_autoscaling_group.asg.name
  }

  alarm_actions = [aws_autoscaling_policy.policy_up.arn]
}