#!/usr/bin/env sh

/opt/java/openjdk/bin/java -Xmx256m -Xss512k -XX:-UseContainerSupport \
              -jar /apps/app.jar \
              --spring.datasource.username=root \
              --spring.datasource.password=root