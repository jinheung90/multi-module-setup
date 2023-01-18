
#variable "aws_access_key" {
#  type        = string
#  description = "AWS Access Key"
#}
#
#variable "aws_secret_key" {
#  type        = string
#  description = "AWS Secret Key"
#}

#variable "aws_key_pair_name" {
#  type        = string
#  description = "AWS Key Pair Name"
#}

# variable "aws_key_pair_file" {
#   type = string
#   description = "AWS Key Pair File"
# }

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
  default     = "172.18.0.0/16"
  description = "The CIDR block for the VPC."
}

variable "private_subnets" {
  type        = list(string)
  description = "List of private subnets"
  default     = ["172.18.1.0/24","172.18.2.0/24"]
}

# 고정
variable "availability_zones" {
  type        = list(string)
  description = "List of availability zones"
  default = ["ap-northeast-2a","ap-northeast-2b"]
}

variable "route53_domain_name" {
  description = "domain name"
  default = "jhc90.com"
}


variable "jhc_gateway_ecr_repository" {
  description = "ecr_repository name"
  default = "jhc_app_ecr"
}

variable "jhc_hosting_area" {
  description = "hosting_area_id"
}