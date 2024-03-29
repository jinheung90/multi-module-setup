resource "aws_cloudwatch_log_group" "backend-log" {
  name              = "${var.app_name}-${var.app_environment}-backend-log-group"
  retention_in_days = 14   # 로그의 expire 기간
}

resource "aws_cloudwatch_log_stream" "backend-log-std-out" {
  name              = "stdout.log"
  log_group_name    = aws_cloudwatch_log_group.backend-log.name
}

resource "aws_cloudwatch_log_stream" "backend-log-std-err" {
  name              = "stderr.log"
  log_group_name    = aws_cloudwatch_log_group.backend-log.name
}

