#resource "aws_db_subnet_group" "" {
#  subnet_ids = []
#}
#
#resource "aws_db_instance" "mysql" {
#  identifier                = "mysql"
#  allocated_storage         = 5
#  backup_retention_period   = 2
#  backup_window             = "01:00-01:30"
#  maintenance_window        = "sun:03:00-sun:03:30"
#  multi_az                  = true
#  engine                    = "mysql"
#  engine_version            = "8.0.28"
#  instance_class            = "db.t2.micro"
#  username                  = "${var.app_name}-${var.app_environment}-name"
#  password                  = var.rds_password
#  port                      = "3306"
#  db_subnet_group_name      = aws_db_subnet_group.
#  vpc_security_group_ids    = []
#  skip_final_snapshot       = true
#  final_snapshot_identifier = "worker-final"
#  publicly_accessible       = true
#}