node {
    properties([
        parameters([
            string(name: 'CUSTOMER_DOMAIN', defaultValue: 'Hello', description: '인사말'),
            string(name: 'OLD_SVR', defaultValue: 'World', description: '대상'),
            string(name: 'NEW_SVR', defaultValue: 'World', description: '대상')
        ])
    ])

    stage('Print params') {
        echo "GREETING = ${params.CUSTOMER_DOMAIN}"
        echo "TARGET   = ${params.OLD_SVR}"
        echo "NEW_SVR = ${params.NEW_SVR}!"
    }
}
