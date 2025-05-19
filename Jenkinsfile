pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                echo 'Building...'
                script {
                    def buildSuccess = true
                    if (!buildSuccess) {
                        error('Build failed!')
                    } else {
                        echo 'Build succeeded!'
                    }
                }
            }
        }

        stage('Test') {
            steps {
                echo 'Testing...'
            }
        }

        stage('Check Final Matching') {
            steps {
                script {
                    if (fileExists('Final Matching') && readFile('Final Matching').trim()) {
                        echo "'Final Matching' is not empty."
                        currentBuild.description = "Ready to merge"
                    } else {
                        echo "'Final Matching' is empty or missing."
                        error("Aborting: 'Final Matching' is empty.")
                    }
                }
            }
        }

        stage('Merge to Main') {
            when {
                expression {
                    return fileExists('Final Matching') && readFile('Final Matching').trim()
                }
            }
            steps {
                echo 'Merging to main...'
                script {
                    sh '''
                        git config user.name "joshFeenberg"
                        git config user.email "joshuafeenberg@gmail.com"
                        git checkout main
                        git merge dev
                        git push origin main
                    '''
                }
            }
        }

        stage('Deploy') {
            steps {
                echo 'Deploying...'
            }
        }
    }

    post {
        always {
            echo 'This will always run after the stages.'
        }
    }
}
