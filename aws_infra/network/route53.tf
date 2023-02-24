#resource "aws_route53_record" "jhc_record" {
#  zone_id = var.jhc_hosting_area
#  name    = "${var.app_environment}.jhc90.com"
#  type    = "A"
#  alias {
#    evaluate_target_health = true
#    name                   = aws_lb.load_balancer.dns_name
#    zone_id                = aws_lb.load_balancer.zone_id
#  }
#}
