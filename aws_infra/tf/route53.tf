resource "aws_route53_zone" "jhc_api" {
  name = "jhc90.com"
}

resource "aws_route53_record" "jhc_record" {
  zone_id = aws_route53_zone.jhc_api.zone_id
  name    = "${var.app_environment}.jhc90.com"
  type    = "A"
  alias {
    evaluate_target_health = true
    name                   = aws_alb.jhc_load_balancer.dns_name
    zone_id                = aws_alb.jhc_load_balancer.zone_id
    ttl                    = "30"
  }
  tags = {
    Environment = var.app_environment
  }


}
