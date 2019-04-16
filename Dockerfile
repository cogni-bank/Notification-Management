FROM java:8

ENV SMTP_USERNAME ${SMTP_USERNAME}
ENV SMTP_PASSWORD ${SMTP_PASSWORD}

VOLUME /tmp
ADD  build/libs/TwoFactorAuthentication-0.0.1-SNAPSHOT.jar entry.jar
ENTRYPOINT ["java","-jar","entry.jar"]

RUN echo ${SMTP_USERNAME}