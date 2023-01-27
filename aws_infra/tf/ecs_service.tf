

resource "aws_ecs_service" "service" {
  name = "${var.app_name}-${var.app_environment}-service"
  cluster = aws_ecs_cluster.jhc_cluster.id
  task_definition = aws_ecs_task_definition.task_service.arn
  desired_count = 2

  launch_type = "EC2"
  load_balancer {
    target_group_arn = aws_lb_target_group.target_group.arn
    container_name   = "${var.app_name}-${var.app_environment}-container"
    container_port   = 8080
  }
#  network_configuration {
#    subnets = aws_subnet.public.*.id
#    assign_public_ip = false
#  }
  depends_on = [aws_lb.jhc_load_balancer]
}