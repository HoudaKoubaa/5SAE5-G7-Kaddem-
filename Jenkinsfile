pipeline {
    agent any

    stages{
        stage('Checkout GIT'){
            steps {
                echo 'Pulling..'
                    git branch: 'seifkorbi-5SAE5-G7',
                    url:'https://github.com/HoudaKoubaa/5SAE5-G7-Kaddem-'
            }
        }

        stage('GIT'){
            steps{
                echo "Getting project from Git";
            }
        }
        stage('Testing Maven'){
            steps {
                sh "mvn clean test"

            }
        }
        stage('Compiler Maven'){
            steps {
                sh "mvn compile"
            }
        }
        stage('Junit/Mokito tests') {
            steps {
                sh 'mvn clean test -Dtest=EquipeJUnitTest,EtudiantMockitoTest'
            }
        }
        stage('SonarQube Analysis') {
            steps {
                    sh 'mvn sonar:sonar -Dsonar.login=admin -Dsonar.password=sonar -Dmaven.test.skip=true';
            }
        }
        stage('Deploy artifact with Nexus ') {
             steps {
                 sh 'mvn deploy -DskipTests'
             }
         }
    }
}