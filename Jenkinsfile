pipeline {
    agent any
    stages {
        stage('Git pull') {
            steps {
                echo '开始拉取代码 ...'
				sh 'git checkout main && git pull'
            }
        }
        stage('mavne build') {
            steps {
				echo '开始编译代码...'
                sh '/usr/local/maven/bin/mvn clean package -Dmaven.skip.test=true'
            }
        }
        stage('docker build') {
            steps {
                sh label: '', script: 'sudo docker build -t watson/suth:0.0.1 .'
            }
        }
    }
	 post {
        always {
            echo '清理工作区...'
            cleanWs()
        }
    }
}
