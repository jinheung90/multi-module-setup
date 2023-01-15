FROM amazoncorretto:11

ENV BASE_PORT 8081
ENV STDOUT /home/ec2-user/stdout.log
ENV STDERR /home/ec2-user/stderr.log
ENV BASE_PATH /home/ec2-user
ENV CURRENT_DEPLOY_JAR deploy.jar
ENV PRE_DEPLOY_JAR pre_deploy.jar
ENV NEXT_DEPLOY_JAR next_deploy.jar

# 현재를 이전으로 옮기고 다음을 현재로 옮긴다
CMD sudo cp $BASE_PATH/$CURRENT_DEPLOY_JAR $BASE_PATH/$PRE_DEPLOY_JAR
CMD sudo cp $BASE_PATH/$NEXT_DEPLOY_JAR $BASE_PATH/$CURRENT_DEPLOY_JAR

CMD sudo fuser -k $BASE_PORT/tcp

CMD sudo nohup java -jar $BASE_PATH/$CURRENT_DEPLOY_JAR 1>>$STDOUT 2>> $STDERR &

CMD sleep 90

HEALTHCHECK --interval=10s --timeout=3s CMD curl -f http://localhost:$BASE_PORT/actuator/heath || exit 1

# 실패 시 처리
# 이전 jar를 현재로 옮기고 재 실행

CMD sudo cp $BASE_PATH/$PRE_DEPLOY_JAR $BASE_PATH/$CURRENT_DEPLOY_JAR

CMD sudo fuser -k $BASE_PORT/tcp

CMD sudo nohup java -jar $BASE_PATH/$CURRENT_DEPLOY_JAR 1>>$STDOUT 2>> $STDERR &

exit 0





