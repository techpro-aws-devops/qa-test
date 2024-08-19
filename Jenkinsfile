pipeline {
    agent any

    environment {
        JAVA_HOME = "/usr/lib/jvm/java-1.8.0-openjdk-amd64"
        PATH = "${env.PATH}:${env.JAVA_HOME}/bin"
    }

    stages {
        stage('Setup') {
            steps {
                script {
                    if (isUnix()) {
                        sh 'sudo apt-get update'
                        sh 'sudo apt-get install -y xvfb'
                        sh 'Xvfb :99 -ac &'
                        env.DISPLAY = ':99'
                    }
                }
            }
        }

        stage('Install Dependencies') {
            steps {
                sh 'sudo apt-get update'
                sh 'sudo apt-get install -y wget unzip'
                // Google Chrome kurulumu
                sh '''
                    wget https://dl.google.com/linux/direct/google-chrome-stable_current_amd64.deb
                    sudo apt-get install -y ./google-chrome-stable_current_amd64.deb
                '''
                // ChromeDriver kurulumu
                sh '''
                    CHROME_DRIVER_VERSION=$(curl -sS chromedriver.storage.googleapis.com/LATEST_RELEASE)
                    wget -N https://chromedriver.storage.googleapis.com/$CHROME_DRIVER_VERSION/chromedriver_linux64.zip
                    sudo unzip chromedriver_linux64.zip -d /usr/local/bin/
                '''
            }
        }

        stage('Build') {
            steps {
                sh './mvnw clean test'
            }
        }
    }

    post {
        always {
            junit '**/target/surefire-reports/*.xml'
            archiveArtifacts artifacts: '**/target/*.html', allowEmptyArchive: true
        }

        failure {
            script {
                sh 'cat target/surefire-reports/*.txt || echo "No detailed test logs found"'
            }
        }
    }
}
