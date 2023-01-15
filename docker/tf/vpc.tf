
# Create a VPC
resource "aws_vpc" "jhc_api_vpc" {
  cidr_block = "172.18.0.0/16"
  instance_tenancy = "default"
  enable_dns_support = "true"
  enable_dns_hostnames = "true"
  tags = {
    Name = "$-vpc"
  }
}

resource "aws_subnet" "main-public-1" {
  vpc_id = aws_vpc.jhc_api_vpc.id
  cidr_block = "172.18.1.0/24"
  map_public_ip_on_launch = "true"
  availability_zone = "ap-northeast-2a"
  tags = {
    Name =  "main-public-1"
  }
}

resource "aws_subnet" "main-public-2" {
  vpc_id = aws_vpc.jhc_api_vpc.id
  cidr_block =  "172.18.2.0/24"
  map_public_ip_on_launch = "true"
  availability_zone = "ap-northeast-2b"
  tags = {
    Name =  "main-public-2"
  }
}

resource "aws_subnet" "main-public-3" {
  vpc_id = aws_vpc.jhc_api_vpc.id
  cidr_block =  "172.18.3.0/24"
  availability_zone = "ap-northeast-2c"
  tags = {
    Name =  "main-public-3"
  }
}
resource "aws_subnet" "main-public-4" {
  vpc_id = aws_vpc.jhc_api_vpc.id
  cidr_block = "172.18.4.0/24"
  availability_zone = "ap-northeast-2d"
  tags = {
    Name =  "main-public-4"
  }
}

resource "aws_internet_gateway" "main_public_gateway" {
  vpc_id = aws_vpc.jhc_api_vpc.id
  tags = {
    Name = "main_internet_gateway"
  }
}

resource "aws_route_table" "main_rt" {
  vpc_id = aws_vpc.jhc_api_vpc.id
  route {
    cidr_block = "0.0.0.0/0"
    gateway_id = aws_internet_gateway.main_public_gateway.id
  }
  tags = {
    Name = "main_rt"
  }
}

resource "aws_route_table_association" "main_rt_association-1a" {
  route_table_id = aws_route_table.main_rt.id
  subnet_id = aws_subnet.main-public-1.id
}

resource "aws_route_table_association" "main_rt_association-2b" {
  route_table_id = aws_route_table.main_rt.id
  subnet_id = aws_subnet.main-public-2.id
}

resource "aws_route_table_association" "main_rt_association-3c" {
  route_table_id = aws_route_table.main_rt.id
  subnet_id = aws_subnet.main-public-3.id
}

resource "aws_route_table_association" "main_rt_association-4d" {
  route_table_id = aws_route_table.main_rt.id
  subnet_id = aws_subnet.main-public-4.id
}

