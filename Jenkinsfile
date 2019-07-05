pipeline {
    agent any 
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
                sh "docker tag testunited/testunited-core learnright-int:5000/testunited/testunited-core"
                sh "docker push learnright-int:5000/testunited/testunited-core"
            }
        }
        stage('Deploy') { 
            steps {
                sh "kubectl apply -f src/main/infra/int/testunited-core-deployment.yml"
            }
        }
    }
}