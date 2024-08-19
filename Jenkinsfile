pipeline {
    agent any

    environment {
        JAVA_HOME = "/usr/lib/jvm/java-1.8.0-openjdk-amd64" // JAVA_HOME doğru olmalı
        PATH = "${env.PATH}:${env.JAVA_HOME}/bin"
    }

    stages {
        stage('Setup') {
            steps {
                script {
                    // Eğer ihtiyaç duyuluyorsa Xvfb başlatılıyor
                    if (isUnix()) {
                        sh 'Xvfb :99 -ac &'
                        env.DISPLAY = ':99'
                    }
                }
            }
        }

        stage('Install Dependencies') {
            steps {
                sh 'apt-get update'
                sh 'apt-get install -y wget unzip xvfb'
                // Google Chrome kurulumu
                sh '''
                    wget https://dl.google.com/linux/direct/google-chrome-stable_current_amd64.deb
                    apt-get install -y ./google-chrome-stable_current_amd64.deb
                '''
                // ChromeDriver kurulumu
                sh '''
                    CHROME_DRIVER_VERSION=$(curl -sS chromedriver.storage.googleapis.com/LATEST_RELEASE)
                    wget -N https://chromedriver.storage.googleapis.com/$CHROME_DRIVER_VERSION/chromedriver_linux64.zip
                    unzip chromedriver_linux64.zip -d /usr/local/bin/
                '''
            }
        }

        stage('Build') {
            steps {
                // Testleri çalıştırmak için Maven wrapper komutunu çalıştırıyor
                sh './mvnw clean test'
            }
        }
    }

    post {
        always {
            // Test sonuçlarını arşivleme
            junit '**/target/surefire-reports/*.xml'
            // Test sonuç raporlarını saklama
            archiveArtifacts artifacts: '**/target/*.html', allowEmptyArchive: true
        }

        failure {
            // Hata durumunda build loglarını göster
            script {
                sh 'cat target/surefire-reports/*.txt || echo "No detailed test logs found"'
            }
        }
    }
}
