pipeline {
    agent any
    stages {
        stage('Compile Stage') {
            steps {
            withMaven(mave : 'MAVEN_3_8_1')
                sh 'mvn clean compile'
            }
        }
        
        stage('Testing Stage') {
            steps {
            withMaven(mave : 'MAVEN_3_8_1')
                sh 'mvn test'
            }
        }
        
        stage('Deployment Stage') {
            steps {
            withMaven(mave : 'MAVEN_3_8_1')
                sh 'mvn deploy'
            }
        }
    }
}