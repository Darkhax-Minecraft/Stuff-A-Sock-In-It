#!/usr/bin/env groovy

pipeline {

    agent any
    
    stages {
    
        stage('Clean') {
        
            steps {
            
                echo 'Cleaning Project'
                sh 'chmod +x gradlew'
                sh './gradlew clean'
            }
        }
        
        stage('Build and Deploy') {
        
		    withCredentials([file(credentialsId: 'mod_build_secrets', variable: 'ORG_GRADLE_PROJECT_SECRET_FILE')]) {
			
			    echo 'Building and Deploying'
                sh './gradlew build publish curseforge'
			}
        }
    }
}