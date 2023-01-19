#resource "aws_instance" "ec2_instance" {
#  ami                    = ""
#  subnet_id              = aws_subnet.public.id
#  instance_type          = "t2.micro"
#  iam_instance_profile   = "ecsInstanceRole" #CHANGE THIS
#  vpc_security_group_ids = [ aws_security_group.load_balancer_security_group.id] #CHANGE THIS
#  key_name               = "pnl-test" #CHANGE THIS
#  ebs_optimized          = "false"
#  source_dest_check      = "false"
#  user_data              = "${data.template_file.user_data.rendered}"
#  root_block_device = {
#    volume_type           = "gp2"
#    volume_size           = "30"
#    delete_on_termination = "true"
#  }
#
#  tags {
#    Name                   = "openapi-ecs-ec2_instance"
#  }
#
#  lifecycle {
#    ignore_changes         = ["ami", "user_data", "subnet_id", "key_name", "ebs_optimized", "private_ip"]
#  }
#}