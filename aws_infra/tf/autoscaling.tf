
resource "aws_iam_instance_profile" "ecs_agent" {
  name = "ecs-agent"
  role = aws_iam_role.ecs_agent.name
}

resource "aws_launch_configuration" "ecs_launch_config" {
  image_id      = "ami-035233c9da2fabf52" #amazon ec2
  iam_instance_profile = aws_iam_instance_profile.ecs_agent.name
  security_groups      = [aws_security_group.load_balancer_security_group.id]
  user_data            = "#!/bin/bash\necho ECS_CLUSTER=my-cluster >> /etc/ecs/ecs.config"
  instance_type        = "t3.small"
  name_prefix = "${var.app_name}-${var.app_environment}"
}


resource "aws_autoscaling_group" "asg" {
  vpc_zone_identifier       = aws_subnet.public.*.id
  name                      = "foobar3-terraform-test"
  max_size                  = 2
  min_size                  = 1
  health_check_grace_period = 300
  health_check_type         = "EC2"
  desired_capacity          = 2
  force_delete              = true
  target_group_arns = [aws_lb_target_group.target_group.arn]
  launch_configuration = aws_launch_configuration.ecs_launch_config.name
}