pipeline{
    agent {
        docker {
//             image 'allbears/jenkins-android:1.0.3' //①
            image 'terasology/jenkins-android-agent:latest' //①
        }
    }
    stages {
        stage('Build'){
             steps {
                sh './gradlew clean && rm -rf ./app/build/' //②
                sh './gradlew assembleRelease'  //③
             }
        }

    }

}
