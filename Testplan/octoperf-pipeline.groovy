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
          bat  'C:/apache-jmeter-5.4.1/apache-jmeter-5.4.1/bin/jmeter.bat -n -t  Testplan/Controller.jmx -Jclientid=nike.niketech.edge-mgorla -Jclientsecret=T8sgW_b00dK2e1oEcQ2awYY-orID4mE3b7TByQxV5yjgj4iewL4euk2bNYd6brZO -l Testplan/testreport.jtl -e -o Testplan/HTMLreport'
    }
    
    stage('Publish Report') {
           
                perfReport filterRegex: '', sourceDataFiles: '**/*.jtl'
            
        }

    stage('Analyse Results') {
        echo "Analyse results"
    }
}
