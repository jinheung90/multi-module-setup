variable "environment" {
  description = "project-setup-prefix"
  type = string
}

variable "tags" {
  type = map(string)
}

locals {
  tags = merge(var.tags, {
    Module = "ec2-user"
  })
}