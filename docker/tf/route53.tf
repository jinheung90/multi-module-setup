resource "aws_route53_zone" "jhc_api" {
  name = "jhc90.com"
}

resource "aws_route53_record" "record_api" {
  zone_id = aws_route53_zone.jhc_api.zone_id
  name    = "api.jhc90.com"
  type    = "A"
  ttl     = "30"
  records = aws_route53_zone
}
