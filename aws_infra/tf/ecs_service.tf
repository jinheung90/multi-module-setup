variable "container_name" {
  type = string
  default = "jhc-container"
}

resource "aws_ecs_service" "service" {
  cluster                = aws_ecs_cluster.jhc_cluster.id                                 # ecs cluster id
  desired_count          = 1                                                         # no of task running
  launch_type            = "EC2"                                                     # Cluster type ECS OR FARGATE
  name                   = "spring-service"                                         # Name of service
  task_definition        = aws_ecs_task_definition.task_service.arn        # Attaching Task to service
  load_balancer {
    container_name       = var.container_name                       #"container_${var.component}_${var.environment}"
    container_port       = "8081"
    target_group_arn     = aws_lb_target_group.target_group.arn         # attaching load_balancer target group to ecs
  }
  depends_on              = [aws_lb.jhc_load_balancer]
}