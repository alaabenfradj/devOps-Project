pipeline {
//alaabenfradj
    agent any
    environment{
        github=credentials('dca79545-e90f-4e61-9bb5-270f245fa154')
        dockerCredentials = 'dockerhub'
        NEXUS_VERSION = "nexus3"
        NEXUS_PROTOCOL = "http"
        NEXUS_URL = "192.168.149.48:8081"
        NEXUS_REPOSITORY = "alaa-repo"
        NEXUS_CREDENTIAL_ID = "nexus-user-credentials"
        SONAR_LOGIN="admin"
        SONAR_PWD="alaa"
    }
    stages {
    	stage('clean'){
    	   	steps{
    	   	     sh '''
                 mvn clean
                 pwd
                '''
    	   	}
    	}
   	    stage('test'){
    	   	steps{
    	   	     sh '''
                 mvn test
                '''
    	   	}
    	}
    	stage('package'){
    	   	steps{
    	   	     sh '''
                 mvn package
                '''
    	   	}
    	}
        stage('install') {
            steps{
                 sh '''
                 mvn install
                 '''
            }
        }
        stage('sonar') {
            steps{
            	 sh '''

                 mvn sonar:sonar -Dsonar.login=${SONAR_LOGIN} -Dsonar.password=${SONAR_PWD}
                 '''
            	 }
        }

        stage("nexus") {
            steps {
                script {
                    pom = readMavenPom file: "pom.xml";
                    filesByGlob = findFiles(glob: "target/*.${pom.packaging}");
                    echo "${filesByGlob[0].name} ${filesByGlob[0].path} ${filesByGlob[0].directory} ${filesByGlob[0].length} ${filesByGlob[0].lastModified}"
                    artifactPath = filesByGlob[0].path;
                    artifactExists = fileExists artifactPath;
                    if(artifactExists) {
                        echo "*** File: ${artifactPath}, group: ${pom.groupId}, packaging: ${pom.packaging}, version ${pom.version}";
                        nexusArtifactUploader(
                            nexusVersion: NEXUS_VERSION,
                            protocol: NEXUS_PROTOCOL,
                            nexusUrl: NEXUS_URL,
                            groupId: pom.groupId,
                            version: pom.version,
                            repository: NEXUS_REPOSITORY,
                            credentialsId: NEXUS_CREDENTIAL_ID,
                            artifacts: [
                                [artifactId: pom.artifactId,
                                classifier: '',
                                file: artifactPath,
                                type: pom.packaging],
                                [artifactId: pom.artifactId,
                                classifier: '',
                                file: "pom.xml",
                                type: "pom"]
                            ]
                        );
                    } else {
                        error "*** File: ${artifactPath}, could not be found";
                    }
                }
            }
        }

        stage('build_push_image') {
              steps {
                        script {
                              def dockerImage = docker.build("alaabenfradj/project-devops")
                              docker.withRegistry('',dockerCredentials){
                                         dockerImage.push("latest")
                               }
                        }
              }
        }


        stage('docker_run') {
                           	steps{
                    	   	     sh '''
                                   docker-compose up -d
                                   '''
                    	   	}

          }


 //stages end
   }



   post {
         always {
              emailext body: " Project DevOps ALAA ben Fradj /n Build result : ${currentBuild.currentResult}",recipientProviders: [[$class: 'DevelopersRecipientProvider'],[$class: 'RequesterRecipientProvider']],subject: "jenkins build:${currentBuild.currentResult}"
                 }
       }




 //pipeline end
}
