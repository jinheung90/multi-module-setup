
# Create a VPC
resource "aws_vpc" "jhc_vpc" {
  cidr_block = var.vpc_cidr
  instance_tenancy = "default"
  enable_dns_support = "true"
  enable_dns_hostnames = "true"
  tags = {
    Name        = "${var.app_name}-vpc"
    Environment = var.app_environment
  }
}



# for 문으로 변경
resource "aws_subnet" "public" {
  vpc_id                  = aws_vpc.jhc_vpc.id
  cidr_block              = element(var.private_subnets, count.index)
  availability_zone       = element(var.availability_zones, count.index)
  count                   = length(var.private_subnets)
  map_public_ip_on_launch = true

  tags = {
    Name        = "${var.app_name}-subnet-public-${count.index + 1}"
    Environment = var.app_environment
  }
}



resource "aws_internet_gateway" "main_public_gateway" {
  vpc_id = aws_vpc.jhc_vpc.id
  tags = {
    Name        = "${var.app_name}-igw"
    Environment = var.app_environment
  }
}

resource "aws_route_table" "main_rt" {
  vpc_id = aws_vpc.jhc_vpc.id
  route {
    cidr_block = "0.0.0.0/0"
    gateway_id = aws_internet_gateway.main_public_gateway.id
  }
  tags = {
    Name = "main_rt"
  }
}


resource "aws_route_table_association" "aws_rta" {
  route_table_id          = aws_route_table.main_rt.id
  subnet_id               = element(aws_subnet.public.*.id, count.index)
  count                   = length(aws_subnet.public.*.id)
}

