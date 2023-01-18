resource "aws_ecr_repository" "jhc_gateway_ecr_repository" {
  name = var.jhc_gateway_ecr_repository
}

output "springApp-repo-URL" {
  value = aws_ecr_repository.jhc_gateway_ecr_repository.repository_url
}

