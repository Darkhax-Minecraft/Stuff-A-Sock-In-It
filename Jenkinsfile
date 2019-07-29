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
        
            steps {
            
                echo 'Building and Deploying'
                sh './gradlew build publish curseforge'
            }
        }
    }
}