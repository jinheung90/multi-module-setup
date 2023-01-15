# 사실 ecr은 결국 스크립트를 도커로 올려야해서 그냥 aws console 에서 작업하는 것이 빠르다
# 어차피 푸시명령어(id) 들어가서 봐야하고 올리기도 해야하고..
resource "aws_ecr_repository" "spring_app_repo" {
  name = "spring_app_repo"
}

output "springApp-repo-URL" {
  value = aws_ecr_repository.spring_app_repo.repository_url
}

