#!/bin/bash
read -p "请输入版本号: ' version

echo 'start git pull'

git pull

echo 'start maven build\n'

mvn clean package

echo 'start docker build\n'

docker build -t 192.168.50.100:1180/watson/springboot-demo:$version .

echo 'start docker push\n'

docker login 192.168.50.100:1180 -u admin -p Harbor12345

docker push 192.168.50.100:1180/watson/springboot-demo:$version

echo 'successful'