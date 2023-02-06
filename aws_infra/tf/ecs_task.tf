resource "aws_ecs_task_definition" "task_service" {
  family = "${var.app_name}-${var.app_environment}-task-fam"
  container_definitions = jsonencode([
    {
      name: "${var.app_name}-${var.app_environment}-container",
      image: aws_ecr_repository.ecr_repo.repository_url
      cpu: 10,
      memory: 512,
      links: [],
      portMappings : [
        {
          "hostPort": 8080,
          "containerPort": 8080,
          "protocol": "tcp"
        }
      ],
      secrets: []
      essential: true,
      entryPoint: [],
      command: [],
      environment: [],
      mountPoints: [],
      volumesFrom: []
    }
  ])

  volume {
    name      = "service-storage"
    host_path = "/ecs/${var.app_name}/${var.app_environment}/service"
  }


}