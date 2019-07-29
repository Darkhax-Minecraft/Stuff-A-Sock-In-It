#!/usr/bin/env groovy

pipeline {

    agent any
    
    withCredentials([file(credentialsId: 'mod_build_secrets', variable: 'ORG_GRADLE_PROJECT_secretFile')]) {

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
                    sh './gradlew build publish curseforge --stacktrace --warn'
	    		}
            }
        }
	}
}