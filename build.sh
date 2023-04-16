#!/bin/bash
#read -p "请输入版本号: " version

version=$(cat /proc/sys/kernel/random/uuid | cksum | awk -F ' ' '{print $1}')

echo "build version:"$version

echo "start git pull"

git pull

echo "start maven build"

mvn clean package

echo "start docker build"

docker build -t 192.168.50.100:1180/watson/springboot-auth:$version .

echo "start docker login"

docker login 192.168.50.100:1180 -u admin -p Harbor12345

echo "start docker push"

docker push 192.168.50.100:1180/watson/springboot-auth:$version

echo "start k8s updateImageTag"

curl -X PUT \
    -H "content-type: application/json" \
    -H "Cookie: KuboardUsername=admin; KuboardAccessKey=fxdf8c5hbfdt.d8wbi8cn53ep7jbp4f72a8cyfhwah3ej" \
    -d '{"kind":"deployments","namespace":"default","name":"springboot-auth","images":{"192.168.50.100:1180/watson/springboot-auth":"192.168.50.100:1180/watson/springboot-auth:'$version'"}}' \
    "192.168.50.10:30080/kuboard-api/cluster/default/kind/CICDApi/admin/resource/updateImageTag"

echo "docker remove image"

docker rmi 192.168.50.100:1180/watson/springboot-auth:$version

echo 'successful'