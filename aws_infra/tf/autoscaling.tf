
resource "aws_iam_instance_profile" "ecs_agent" {
  name = "ecs-agent"
  role = aws_iam_role.ecs_agent.name
}

resource "aws_launch_configuration" "ecs_launch_config" {
  image_id      = "ami-0b6d6fc5fe3f750f1" #amazon ec2
  iam_instance_profile = aws_iam_instance_profile.ecs_agent.name
  security_groups      = [aws_security_group.load_balancer_security_group.id]
  user_data = <<EOF
        #!/bin/bash
        echo ECS_CLUSTER=${aws_ecs_cluster.jhc_cluster.name} >> /etc/ecs/ecs.config
        sudo mkdir /etc/ecs/service
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