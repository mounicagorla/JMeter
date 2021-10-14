#!/usr/bin/env groovy
node {

    stage('Initialise') {
        /* Checkout the scripts */
        checkout scm: [
                $class: 'GitSCM',
                userRemoteConfigs: [
                        [
                                url: "https://bitbucket.org/xxxxxx/performance-test-repo.git",
                               
                        ]
                ],
                branches: [[name: "master"]]
        ], poll: false
    }

    stage('Execute Performance Tests') {
        dir("${WORKSPACE}/scripts") {
            bat "c:/apache-jmeter/apache-jmeter/bin/jmeter.bat -n -t C:\Users\mgorla\workspacejm\Testplan\submitMaterialOrder.jmx -l C:\Users\mgorla\workspacejm\Testplan\testreport.jtl"
        }
    }

    stage('Analyse Results') {
        echo "Analyse results"
    }
}
