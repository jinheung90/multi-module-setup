resource "aws_kms_key" "ecs_cluster_kms_key" {
  description             = "kms_7_days"
  deletion_window_in_days = 7
}

#resource "aws_cloudwatch_log_group" "ecs_cluster_log" {
#  name = "ecs_cluster_log"
#}
#
#resource "aws_cloudwatch_log_stream" "ecs_cluster_log_stream" {
#  log_group_name = aws_cloudwatch_log_group.ecs_cluster_log.name
#  name           = "${var.app_environment}-${var.app_name}"
#}
#


resource "aws_ecs_cluster_capacity_providers" "aws_ecs_cluster_cps" {
  cluster_name = aws_ecs_cluster.jhc_cluster.name

  capacity_providers = [aws_ecs_capacity_provider.jhc-ecs-cp.name]

  default_capacity_provider_strategy {
    base              = 1
    weight            = 100
    capacity_provider = aws_ecs_capacity_provider.jhc-ecs-cp.name
  }
}

resource "aws_ecs_capacity_provider" "jhc-ecs-cp" {
  name = "${var.app_name}-${var.app_environment}-ecs-cp"

  auto_scaling_group_provider {
    auto_scaling_group_arn = aws_autoscaling_group.asg.arn
  }
}

resource "aws_ecs_cluster" "jhc_cluster" {
  name = "${var.app_name}-${var.app_environment}-ecs-cluster"


}