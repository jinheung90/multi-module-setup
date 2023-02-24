#resource "aws_ami_from_instance" "example" {
#  name               = "terraform-example"
#  source_instance_id = "i-xxxxxxxx"
#}
#
#resource "aws_ami" "ec2_base" {
#  name                = "terraform_ami"
#  virtualization_type = "hvm"
#  root_device_name    = "/dev/xvda"
#  imds_support        = "v2.0" # Enforce usage of IMDSv2. You can safely remove this line if your application explicitly doesn't support it.
#  ebs_block_device {
#    device_name = "/dev/xvda"
#    snapshot_id = "snap-"
#    volume_size = 1
#  }
#}