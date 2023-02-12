
BASE_PORT=8080
STDOUT=/home/ec2-user/stdout.log
STDERR=/home/ec2-user/stderr.log
BASE_PATH=/home/ec2-user
CURRENT_DEPLOY_JAR=deploy.jar


sudo fuser -k $BASE_PORT/tcp
sudo nohup java -jar $CURRENT_DEPLOY_JAR 1>> $STDOUT 2>> $STDERR &

sleep 60

exit 0