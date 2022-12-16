FROM openjdk:17-alpine
RUN apk update & apk upgrade && apk add netcat-openbsd
RUN mkdir -p /usr/local/ea-licensing-service
ADD @project.build.finalName@.jar /usr/local/ea-licensing-service
ADD run.sh run.sh
RUN chmod +x run.sh
CMD ./run.sh