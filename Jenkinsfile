pipeline {
    agent { label 'agent-jdk21' }

    tools {
        git 'Default'
    }

    stages {
        stage('Prepare Environment') {
            steps {
                sh 'chmod +x ./gradlew'
            }
        }
        stage('Check') {
            steps {
                sh './gradlew check'
            }
        }
        stage('Package') {
            steps {
                sh './gradlew build'
            }
        }
        stage('JaCoCo Report') {
            steps {
                sh './gradlew jacocoTestReport'
            }
        }
        stage('JaCoCo Verification') {
            steps {
                sh './gradlew jacocoTestCoverageVerification'
            }
        }
        stage('Docker Build') {
            steps {
                sh 'docker build -t job4j_devops .'
            }
        }
        stage('Check Git Tag') {
            steps {
                script {
                    def gitTag = sh(script: 'git describe --tags --exact-match', returnStdout: true).trim()
                    if (gitTag) {
                        echo "Tag found: ${gitTag}. Proceeding with Docker build."
                        sh "docker build -t job4j_devops:${gitTag} ."
                        sh "docker push job4j_devops:${gitTag}"
                    } else {
                        echo "No Git tag found. Skipping Docker build."
                    }
                }
            }
        }
    }

    post {
        always {
            script {
                def buildInfo = """
                    Build number: ${currentBuild.number}
                    Build status: ${currentBuild.currentResult}
                    Started at: ${new Date(currentBuild.startTimeInMillis)}
                    Duration: ${currentBuild.durationString}
                """
                telegramSend(message: buildInfo)
            }
        }
    }
}
