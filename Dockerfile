FROM java:8-alpine

ADD target/MusicMeterParser-1.0-SNAPSHOT.jar /root/

EXPOSE 8080
EXPOSE 8081

WORKDIR /root/

ENTRYPOINT java -jar MusicMeterParser-1.0-SNAPSHOT.jar server
