pipeline {
    agent any

    environment {
        MAVEN_OPTS = '-Xmx1024m'
    }

    stages {
        stage('Checkout') {
            steps {
                // Projeyi GitHub'dan çek
                git url: 'https://github.com/techpro-aws-devops/qa-test.git', branch: 'main'
            }
        }
        
        stage('Build') {
            steps {
                // Maven projesini temizle ve derle
                sh 'mvn clean install -DskipTests'
            }
        }

        stage('Test') {
            steps {
                // Maven testlerini çalıştır
                sh 'mvn test'
            }
        }

        stage('Report') {
            steps {
                // Test raporlarını oluştur
                sh 'mvn verify'
            }
            post {
                always {
                    // Raporları Jenkins'e yükle
                    publishHTML([
                        reportDir: 'target/site/cucumber-reports',
                        reportFiles: 'index.html',
                        reportName: 'Cucumber HTML Report',
                        keepAll: true,
                        alwaysLinkToLastBuild: true
                    ])
                }
            }
        }
    }

    post {
        always {
            // İşlem sonrası işlemleri yap
            cleanWs()
        }
    }
}
