// pipeline {
//     agent any
//     stages {
//         stage('Build') {
//             steps {
//                 sh './mvnw test'
//             }
//         }
//     }
// }

pipeline {
    agent any

    stages {
        stage('Setup Chrome and ChromeDriver') {
            steps {
                sh '''
                    # Update the package list
                    apt-get update
                    
                    # Install dependencies
                    apt-get install -y wget unzip curl apt-transport-https ca-certificates gnupg

                    # Add Google's official GPG key
                    curl -fsSL https://dl-ssl.google.com/linux/linux_signing_key.pub | apt-key add -
                    
                    # Add Google Chrome's repository to the sources list
                    sh -c 'echo "deb [arch=amd64] http://dl.google.com/linux/chrome/deb/ stable main" > /etc/apt/sources.list.d/google-chrome.list'
                    
                    # Update the package list again
                    apt-get update
                    
                    # Install Google Chrome
                    apt-get install -y google-chrome-stable

                    # Get the version of Chrome installed
                    CHROME_VERSION=$(google-chrome --version | awk '{print $3}')
                    
                    # Get the corresponding ChromeDriver version
                    CHROMEDRIVER_VERSION=$(wget -qO- "https://chromedriver.storage.googleapis.com/LATEST_RELEASE_${CHROME_VERSION%.*}")
                    
                    # Download ChromeDriver
                    wget -O /tmp/chromedriver.zip "https://chromedriver.storage.googleapis.com/${CHROMEDRIVER_VERSION}/chromedriver_linux64.zip"
                    
                    # Unzip and move ChromeDriver to /usr/local/bin/
                    unzip /tmp/chromedriver.zip -d /usr/local/bin/
                    
                    # Clean up
                    rm /tmp/chromedriver.zip
                    
                    # Make ChromeDriver executable
                    chmod +x /usr/local/bin/chromedriver
                '''
            }
        }
        
        stage('Build') {
            steps {
                sh './mvnw test'
            }
        }
    }
}

