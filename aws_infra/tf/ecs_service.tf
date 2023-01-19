resource "aws_iam_service_linked_role" "ecs-service-role" {
  aws_service_name = "ecs.amazonaws.com"
}
resource "aws_ecs_capacity_provider" "capacity_provider" {
  name = "${aws_autoscaling_group.asg.name}_provider"
  auto_scaling_group_provider {
    auto_scaling_group_arn         = aws_autoscaling_group.asg.arn
    managed_termination_protection = "ENABLED"
    managed_scaling {
      maximum_scaling_step_size = 10
      minimum_scaling_step_size = 1
      target_capacity           = 100
    }
  }
  depends_on = [
    aws_iam_service_linked_role.ecs-service-role
  ]
}

resource "aws_ecs_service" "service" {
  name = "${var.app_name}-${var.app_environment}-service"
  cluster = aws_ecs_cluster.jhc_cluster.id
  task_definition = aws_ecs_task_definition.task_service.arn
  desired_count = 2
  iam_role = aws_iam_service_linked_role.ecs-service-role.arn
  launch_type = "EC2"
  load_balancer {
    target_group_arn = aws_lb_target_group.target_group.arn
    container_name   = "${var.app_name}-${var.app_environment}-container"
    container_port   = 8080
  }
  network_configuration {
    subnets = aws_subnet.public.*.id

    assign_public_ip = false
  }
  depends_on = [aws_alb.jhc_load_balancer]
}