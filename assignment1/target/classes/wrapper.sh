#!/bin/sh
while ! exec 6<>/dev/tcp/${DATABASE_HOST}/${DATABASE_PORT}; do
    echo "Trying to connect to MYSQL at ${DATABASE_HOST}:${DATABASE_PORT}..."
    sleep 10
done
echo ">>Connected to MYSQL database!<<"
java -Djava.security.egd=file:/dev/./urandom -Dspring.profiles.active=container -jar project-assig1.jar