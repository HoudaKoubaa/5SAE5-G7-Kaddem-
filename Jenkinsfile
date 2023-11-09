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
        stage('Build docker image'){
            steps{
                script{
                    sh 'docker build -t korbiapp .'
                }
            }
        }
        stage('Push image to DockerHub') {
            steps {
                // login dockerhub
                sh 'docker login -u seifkorbi -p dockerhub123'
                // push image to dockerhub
                sh 'docker tag korbiapp:latest seifkorbi/korbiapp:v1'
                sh 'docker push seifkorbi/korbiapp:v1'
            }
        }
        stage('Run Docker Compose') {
             steps {
                // Exécutez docker-compose up
                sh 'docker compose up -d' // -d pour exécuter en arrière-plan
            }
        }
    }
}