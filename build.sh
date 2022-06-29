#!/bin/bash
read -p "请输入版本号: " version

echo "start git pull"

git pull

echo "版本号为：" $version

echo "start maven build"

mvn clean package -f springboot-demo/pom.xml

echo "start docker build"

docker build -t 192.168.50.100:1180/watson/springboot-demo:$version .

echo "start docker push"

docker login 192.168.50.100:1180 -u admin -p Harbor12345

docker push 192.168.50.100:1180/watson/springboot-demo:$version