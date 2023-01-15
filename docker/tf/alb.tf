
resource "aws_security_group" "alb-output-security-group" {

  name = "alb_output_sg"

  description = "alb_route_security"
  vpc_id      = aws_vpc.jhc_api_vpc.id

  ingress {
    description      = "TLS from VPC"
    from_port        = 443
    to_port          = 443
    protocol         = "tcp"
    cidr_blocks      = [aws_vpc.jhc_api_vpc.cidr_block]
    ipv6_cidr_blocks = [aws_vpc.jhc_api_vpc.ipv6_cidr_block]
  }

  egress {
    from_port        = 0
    to_port          = 0
    protocol         = "-1"
    cidr_blocks      = ["0.0.0.0/0"]
    ipv6_cidr_blocks = ["::/0"]
  }

  tags = {
    Name = "alb_route_security"
  }
}

resource "aws_s3_bucket" "springboot-alb-log-store" {

  bucket = "springboot-alb-log"

  tags = {
    Name = "springboot-alb-log"
    Environment = "production"
  }
}

resource "aws_lb" "springboot-alb" {
  name = "spring-app-alb"
  load_balancer_type = "application"

  subnets = [
    aws_subnet.main-public-1.id,
    aws_subnet.main-public-2.id,
    aws_subnet.main-public-3.id,
    aws_subnet.main-public-4.id
  ]

  security_groups = [aws_security_group.alb-output-security-group.id]

  cross_zone_load_balancing = true

  access_logs {
    bucket  = aws_s3_bucket.springboot-alb-log-store.id
    prefix  = "test-lb"
    enabled = true
  }

  tags = {
    Environment = "production"
  }
}

