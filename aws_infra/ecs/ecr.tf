resource "aws_ecr_repository" "ecr_repo" {
  name = var.ecr_repo
}

output "springApp-repo-URL" {
  value = aws_ecr_repository.ecr_repo.repository_url
}

