resource "aws_launch_template" "l-template" {
  name_prefix   = var.app_name
  image_id      = "ami-1a2b3c"
  instance_type = "t2.micro"
}

resource "aws_placement_group" "cluster-place-group" {
  name     = "${var.app_name}-pg"
  strategy = "cluster"
}

resource "aws_autoscaling_group" "bar" {
  availability_zones = var.availability_zones
  desired_capacity   = 2
  max_size           = 2
  min_size           = 1

  launch_template {
    id      = aws_launch_template.l-template.id
    version = "$Latest"
  }
}

resource "aws_autoscaling_group" "asg" {
  name                      = "foobar3-terraform-test"
  max_size                  = 2
  min_size                  = 1
  health_check_grace_period = 300
  health_check_type         = "ELB"
  desired_capacity          = 2
  force_delete              = true
  launch_template {
    id      = aws_launch_template.l-template.id
    version = "$Latest"
  }
}