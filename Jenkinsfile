pipeline {
    agent any

    environment {
        STUDENT_NAME = 'Erbarak Omirzak'
        IMAGE        = 'omirzak-erbarak-student-api:v1.0-omirzak-erbarak-37403'
    }

    stages {
        stage('Checkout') {
            steps {
                echo "Student: ${STUDENT_NAME} (ID 37403) - checking out source"
                checkout scm
            }
        }
        stage('Build') {
            steps {
                sh 'chmod +x gradlew'
                sh './gradlew clean bootJar -x test --no-daemon'
            }
        }
        stage('Test') {
            steps {
                sh './gradlew test --no-daemon'
            }
            post {
                always {
                    junit allowEmptyResults: true, testResults: 'build/test-results/test/*.xml'
                }
            }
        }
        stage('Docker Build') {
            steps {
                sh 'docker build -t ${IMAGE} .'
            }
        }
        stage('Verify') {
            steps {
                sh 'docker image inspect ${IMAGE} --format "Image ${IMAGE} exists, size: {{.Size}} bytes"'
                echo "Pipeline for ${STUDENT_NAME} finished successfully"
            }
        }
    }

    post {
        failure {
            echo "Pipeline FAILED for ${STUDENT_NAME}"
        }
    }
}
