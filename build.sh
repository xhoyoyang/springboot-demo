#!/bin/bash
#read -p "请输入版本号: " version

version=$(cat /proc/sys/kernel/random/uuid | cksum | awk -F ' ' '{print $1}')

echo "build version:"$version

echo"[step 1 -------------------------------------------git pull]"

git pull

echo"[step 2 -------------------------------------------maven build]"

mvn clean package

echo"[step 3 -------------------------------------------docker build]"

docker build -t 192.168.50.100:1180/watson/springboot-auth:$version .

echo"[step 4 -------------------------------------------docker login]"


docker login 192.168.50.100:1180 -u admin -p Harbor12345

echo"[step 5 -------------------------------------------docker push inamge]"

docker push 192.168.50.100:1180/watson/springboot-auth:$version

echo"[step 6 -------------------------------------------update container]"

echo "start k8s updateImageTag"

curl -X PUT \
    -H "content-type: application/json" \
    -H "Cookie: KuboardUsername=admin; KuboardAccessKey=fxdf8c5hbfdt.d8wbi8cn53ep7jbp4f72a8cyfhwah3ej" \
    -d '{"kind":"deployments","namespace":"default","name":"springboot-auth","images":{"192.168.50.100:1180/watson/springboot-auth":"192.168.50.100:1180/watson/springboot-auth:'$version'"}}' \
    "192.168.50.10:30080/kuboard-api/cluster/default/kind/CICDApi/admin/resource/updateImageTag"

echo"[step 7 -------------------------------------------docker remove image]"

docker rmi 192.168.50.100:1180/watson/springboot-auth:$version

echo"[step 8 -------------------------------------------done]"
