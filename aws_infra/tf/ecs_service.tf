resource "aws_ecs_service" "service" {
  name   = "${var.app_name}-${var.app_environment}-service"
  family = "${var.app_name}-${var.app_environment}-family"
  container_definitions = jsonencode([
    {
      name      = "first"
      image     = "service-first"
      cpu       = 2
      memory    = 256
      essential = true
      portMappings = [
        {
          containerPort = 80
          hostPort      = 80
        }
      ]
    }
  ])

  volume {
    name      = "${var.app_name}-${var.app_environment}-service"
  }

  load_balancer {
    target_group_arn = aws_alb_target_group.jhc_alb_target_group.arn
    container_name   = "${var.app_name}-${var.app_environment}-container"
    container_port   = 8080
  }
}