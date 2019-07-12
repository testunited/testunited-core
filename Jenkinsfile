pipeline {
    agent any 
    environment {
       field = 'some'
   }
    stages {
        stage('Build') { 
            steps {
                sh "gradle build -x test"
            }
        }
        stage('Dev Test') { 
            steps {
                sh "gradle test"
            }
        }
        stage('Package') { 
            steps {
                sh "gradle jar docker"
                sh "docker tag testunited/testunited-core registry.minikube.local:80/testunited/testunited-core:${BUILD_NUMBER}"
                sh "docker push registry.minikube.local:80/testunited/testunited-core:${BUILD_NUMBER}"
            }
        }
        stage('Deploy') { 
            steps {
                sh "kubectl set image deployment/testunited-core testunited-core=registry.minikube.local:80/testunited/testunited-core:${BUILD_NUMBER}"
            }
        }
    }
}