
resource "aws_lb" "jhc_load_balancer" {
  name               = "${var.app_name}-${var.app_environment}-alb"
  internal           = false
  load_balancer_type = "application"
  subnets            = aws_subnet.public.*.id
  security_groups    = [aws_security_group.load_balancer_security_group.id]

  tags = {
    Name        = "${var.app_name}-alb"
    Environment = var.app_environment
  }
}

resource "aws_security_group" "load_balancer_security_group" {
  vpc_id = aws_vpc.jhc_vpc.id

  ingress {
    from_port        = 443
    to_port          = 443
    protocol         = "tcp"
    cidr_blocks      = ["0.0.0.0/0"]
    ipv6_cidr_blocks = ["::/0"]
  }

  egress {
    from_port        = 0
    to_port          = 0
    protocol         = "-1"
    cidr_blocks      = ["0.0.0.0/0"]
    ipv6_cidr_blocks = ["::/0"]
  }
  tags = {
    Name        = "${var.app_name}-sg"
    Environment = var.app_environment
  }
}
# 기존에 만든것으로 대체 이건 인증서 생성용이다
#resource "aws_acm_certificate" "cert" {
#  domain_name = var.route53_domain_name
#  subject_alternative_names = ["*.${var.route53_domain_name}"]
#  validation_method = "DNS"
#}
#
#resource "aws_acm_certificate_validation" "cert" {
#  certificate_arn = aws_acm_certificate.cert.arn
#  validation_record_fqdns = [aws_route53_record.jhc_record.fqdn]
#}


resource "aws_lb_target_group" "target_group" {
  name        = "${var.app_name}-${var.app_environment}-tg"
  port        = 8080
  protocol    = "HTTP"
  target_type = "instance"
  vpc_id      = aws_vpc.jhc_vpc.id

  health_check {
    healthy_threshold   = "3"
    interval            = "300"
    protocol            = "HTTP"
    matcher             = "200"
    timeout             = "3"
    path                = "/actuator/health"
    unhealthy_threshold = "2"
  }

  tags = {
    Name        = "${var.app_name}-lb-tg"
    Environment = var.app_environment
  }
}


resource "aws_lb_listener" "jhc-ALB-Listener" {
  //depends_on = ["aws_lb.CF2TF-ALB.id", "aws_lb_target_group.CF2TF-TargetGroup.id"]
  load_balancer_arn = aws_lb.jhc_load_balancer.arn
  port              = "443"
  protocol          = "HTTPS"
  ssl_policy = "ELBSecurityPolicy-2016-08"
  certificate_arn = var.default_ssl_cert_arn
  default_action {
    target_group_arn = aws_lb_target_group.target_group.arn
    type             = "forward"
  }
}

