resource "aws_iam_instance_profile" "ecs_agent" {
  name = "ecs-agent"
  role = aws_iam_role.ecs-service-role.name
}

resource "aws_security_group" "ec2-security-group" {
  vpc_id = aws_vpc.jhc_vpc.id

  ingress {
    from_port        = 8081
    to_port          = 8081
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
    Name        = "${var.app_name}-spring-sg"
    Environment = var.app_environment
  }
}

resource "aws_launch_configuration" "ecs_launch_config" {
  image_id             = "ami-035233c9da2fabf52"
  iam_instance_profile = aws_iam_instance_profile.ecs_agent.name
  instance_type        = "t3.small"

  security_groups = [aws_security_group.load_balancer_security_group.id]
  name = "launch-config"
}

#resource "aws_placement_group" "cluster-place-group" {
#  name     = "${var.app_name}-pg"
#  strategy = "cluster"
#}


resource "aws_autoscaling_group" "asg" {
  name                      = "${var.app_name}-${var.app_environment}-asg"
  max_size                  = 2
  min_size                  = 1
  health_check_grace_period = 300
  health_check_type         = "ELB"
  desired_capacity          = 1
  force_delete              = true
  target_group_arns = [aws_lb_target_group.target_group.arn]
  vpc_zone_identifier = aws_subnet.public.*.id

  launch_configuration = aws_launch_configuration.ecs_launch_config.name
#  launch_template {
#    id      = aws_launch_template.l-template.id
#    version = "$Latest"
#  }
}