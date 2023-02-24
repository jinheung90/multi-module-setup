#
#resource "aws_security_group" "nat_security_group" {
#  vpc_id = aws_vpc.vpc.id
#
#  ingress {
#    from_port        = 22
#    to_port          = 22
#    protocol         = "tcp"
#    cidr_blocks      = ["0.0.0.0/0"]
#  }
#
#  egress {
#    from_port        = 0
#    to_port          = 0
#    protocol         = "-1"
#    cidr_blocks      = ["0.0.0.0/0"]
#    ipv6_cidr_blocks = ["::/0"]
#  }
#  tags = {
#    Name        = "${var.app_name}-sg"
#    Environment = var.app_environment
#  }
#}
#resource "aws_eip" "nat_instance_eip" {
#  instance = aws_instance.nat-instance.id
#  vpc      = true
#}
#
#resource "aws_eip_association" "nat_eip_assoc" {
#  instance_id   = aws_instance.nat-instance.id
#  allocation_id = aws_eip.nat_eip.id
#}
#
#resource "aws_instance" "nat-instance" {
#  ami      = "ami-07d6ac6f14ccd308c"
#  instance_type = "t2.micro"
##  vpc_security_group_ids = [aws_security_group..id]
#  iam_instance_profile = aws_iam_instance_profile.ec2_agent.name
#  subnet_id = aws_subnet.public[0].id
#  key_name = ""
#  disable_api_termination = true
#  root_block_device {
#    volume_size = "30"
#    volume_type = "gp3"
#    delete_on_termination = true
#    tags = {
#      Name = "${var.app_name}-${var.app_environment}-nat-ec2"
#    }
#  }
#}