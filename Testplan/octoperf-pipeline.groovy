#!/usr/bin/env groovy
node {

    stage('Initialise') {
        /* Checkout the scripts */
        checkout scm: [
                $class: 'GitSCM',
                userRemoteConfigs: [
                        [
                                url: "https://github.com/mounicagorla/JMeter.git",
                                credentialsId: "jmeter_user"
                        ]
                ],
                branches: [[name: "main"]]
        ], poll: false
    }

    stage('Execute Performance Tests') {
        bat "c:/apache-jmeter/apache-jmeter/bin/jmeter.bat -n -t C:\Users\mgorla\workspacejm\Testplan\submitMaterialOrder.jmx"
    }

    stage('Analyse Results') {
        echo "Analyse results"
    }
}
