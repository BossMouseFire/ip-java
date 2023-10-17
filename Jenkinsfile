pipeline {
    agent any
    stages {
        stage('BUILD') {
          steps {
              git([url: 'https://github.com/BossMouseFire/ip-java.git', branch: 'lab-docker'])
              bash './gradlew build'
          }
        }
        stage('Test') {
            steps {
                bash './gradlew test'
            }
            post {
                always {
                  junit '**/build/test-results/test/TEST-*.xml'
                }
            }
        }
        stage ("DOCKER IMAGE") {
            steps {
               bash 'docker image build -t mouzzefire/lab .'
            }
        }
        stage("PUSH TO DOCKER HUB") {
            steps {
               withCredentials([string(credentialsId: 'dockerhub-pwd', variable: 'dockerhubpwd')]) {
                  bash 'echo ${dockerhubpwd}'
                  bash 'docker login --username mouzzefire --password ${dockerhubpwd}'
                  bash 'docker push mouzzefire/lab'
               }
            }
        }
    }
}