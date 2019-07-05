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
                docker tag testunited/testunited-core learnright-int:5000/testunited/testunited-core
                docker push learnright-int:5000/testunited/testunited-core
            }
        }
    }
}