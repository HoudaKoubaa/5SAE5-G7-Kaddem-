pipeline {

	agent any

	stages {
		
		stage('Junit + Mockito Test') {
			steps {
				sh 'docker start cidb'
				sh 'mvn test'
			      } 
		}
		stage('Build Artifact - Maven') {
			steps {
				sh "mvn clean package -DskipTests=true"
				archive 'target/*.jar'
			      }
		}
		       
		/*stage('SonarQube + JacOcO Analysis') {
			steps {
				sh "mvn  sonar:sonar -Dsonar.projectKey=project-ci  -Dsonar.host.url=http://192.168.33.10:9000  -Dsonar.login=sqp_3fa1853e4657764cc9564759b04e3ba105f08b77"

			}
		        post {
				always {

					jacoco execPattern: 'target/jacoco.exec'

				       }    
			    } 

		 }*/  
		 stage('Sonatype/Nexus deploy') {
			steps {
				//sh 'mvn clean deploy -DskipTests'
				sh'mvn clean deploy -Dmaven.test.skip=true -Dresume=false'
			      }
		 } 
		/* stage('Docker Build and Push') {
                       steps {
                               withDockerRegistry([credentialsId: "docker-hub", url: ""]) {
         			  sh 'printenv'
        			  sh 'docker build -t onstb/onstb .'
	 			  sh 'docker tag onstb/onstb onstb/onstb:latest'
         			  sh 'docker push onstb/onstb:latest'
         			}
     			  }
    		}*/
		 stage('Docker compose') {
      		      steps {
	      sh 'docker stop cidb'
               sh 'docker compose up '
           
       }
		 }
           
		}
}
