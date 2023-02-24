
# 얘 디폴트 서울로
variable "aws_region" {
  type        = string
  default     = "ap-northeast-2"
}

variable "aws_cloudwatch_retention_in_days" {
  type        = number
  description = "AWS CloudWatch Logs Retention in Days"
  default     = 1
}

variable "app_name" {
  type        = string
  description = "Application Name"
}

variable "app_environment" {
  type        = string
  description = "Application Environment"
}
#
variable "vpc_cidr" {
  description = "The CIDR block for the VPC."
  default = "172.18.0.0/16"
}

variable "private_subnets" {
  type        = list(string)
  description = "List of private subnets"
  default     = ["172.18.16.0/20","172.18.32.0/20","172.18.48.0/20"]
}

variable "public_subnets" {
  type        = list(string)
  description = "List of private subnets"
  default     = ["172.18.64.0/20","172.18.80.0/20","172.18.96.0/20"]
}

# 고정
variable "availability_zones" {
  type        = list(string)
  description = "List of availability zones"
  default = ["ap-northeast-2a","ap-northeast-2b","ap-northeast-2c"]
}

variable "route53_domain_name" {
  description = "domain name"
}

variable "ecr_repo" {
  description = "ecr_repository name"
  default = "spring_boot_ecr"
}

variable "jhc_hosting_area" {
  description = "hosting_area_id"
}

variable "rds_password" {
  type = string
}

variable "key_pair" {
  type = string
}
