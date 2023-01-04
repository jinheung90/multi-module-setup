STDOUT=/home/centos/stdout.log
STDERR=/home/centos/stderr.log

sudo nohup java -jar /home/centos/deploy.jar 1>>$STDOUT 2>> $STDERR &
exit 0;