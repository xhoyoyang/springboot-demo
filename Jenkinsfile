pipeline {
    agent any
    stages {
        stage('git checkout') {
            steps {
                sh label: '', script: 'git checkout main && git pull'
            }
        }
        stage('mavne build') {
            steps {
                sh label: '', script: 'mvn clean package -Dmaven.skip.test=true'
            }
        }
        stage('docker build') {
            steps {
                sh label: '', script: 'sudo docker build -t watson/suth:0.0.1 .'
            }
        }
    }
}
