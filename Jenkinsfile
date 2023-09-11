pipeline {
    agent any
    stages {
        stage('Git pull') {
            steps {
                echo '[INFO] 开始拉取代码 ...'
                sh 'git checkout ${branch} && git pull'
            }
        }
        stage('Build project') {
            steps {
                echo '[INFO] 开始maven编译代码...'
                sh '/usr/local/maven/bin/mvn clean package -Dmaven.skip.test=true'
            }
        }
        stage('Build image') {
            steps {
                echo '[INFO] 开始编译docker镜像...'
                sh 'sudo docker build -t watson/auth:0.0.1 .'
            }
        }
        stage('Deploy') {
            steps {
                echo '[INFO] 开始部署服务...'
                sh 'sudo docker stop auth'
                sh 'sudo docker rm auth'
                sh 'sudo docker run -d --name auth -p 18080:8080 watson/auth:0.0.1'
            }
        }
    }
    post {
        always {
            echo '[INFO] 清理工作区...'
            cleanWs()
        }
    }
}
