pipeline {
    agent any
    stages {
        stage('BUILD') {
          steps {
              git([url: 'https://github.com/BossMouseFire/ip-java.git', branch: 'lab-docker'])
              bat './gradlew build'
          }
        }
        stage('Test') {
            steps {
                bat './gradlew test'
            }
            post {
                always {
                  junit '**/build/test-results/test/TEST-*.xml'
                }
            }
        }
        stage ("DOCKER IMAGE") {
            steps {
               bat 'docker image build -t mouzzefire/lab .'
            }
        }
        stage("PUSH TO DOCKER HUB") {
            steps {
               withCredentials([string(credentialsId: 'dockerhub-pwd', variable: 'dockerhubpwd')]) {
                  bat 'echo ${dockerhubpwd}'
                  bat 'docker login --username mouzzefire --password ${dockerhubpwd}'
                  bat 'docker push mouzzefire/lab'
               }
            }
        }
    }
}