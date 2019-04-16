FROM java:8
VOLUME /tmp
ADD  build/libs/TwoFactorAuthentication-0.0.1-SNAPSHOT.jar entry.jar
ENTRYPOINT ["java","-jar","entry.jar"]