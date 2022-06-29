#!/bin/bash
read -p "请输入版本号: ' version

echo "start git pull""

git pull

echo "start maven build"

mvn clean package

echo "start docker build"

docker build -t 192.168.50.100:1180/watson/springboot-demo:$version .

echo "start docker login"

docker login 192.168.50.100:1180 -u admin -p Harbor12345

echo "start docker push"

docker push 192.168.50.100:1180/watson/springboot-demo:$version

echo "start k8s updateImageTag"

curl -X PUT \
    -H "content-type: application/json" \
    -H "Cookie: KuboardUsername=admin; KuboardAccessKey=yx4wwakfd4xp.izddjf4fty65yd8m4i6ehwyf3xwezmik" \
    -d '{"kind":"deployments","namespace":"default","name":"springboot-demo","images":{"192.168.50.100:1180/watson/springboot-demo":"192.168.50.100:1180/watson/springboot-demo:$version"}}' \
    "192.168.50.10:30080/kuboard-api/cluster/default/kind/CICDApi/admin/resource/updateImageTag"

echo 'successful'