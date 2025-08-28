pipeline {
  agent any

  parameters {
    file(name: 'UPLOAD_FILE', description: '업로드할 파일')
    string(name: 'SERVER_IP',  defaultValue: '192.168.0.10', description: '리눅스 서버 IP')
    string(name: 'REMOTE_DIR', defaultValue: '/tmp', description: '리눅스 서버 저장 경로')
  }

  environment {
    SSH_CRED    = 'local_vm'   // Jenkins Credentials ID (SSH Private Key)
    REMOTE_USER = 'root'       // 리눅스 접속 계정
  }

  stages {
    stage('Upload file') {
      steps {
        sshagent(credentials: [env.SSH_CRED]) {
          bat '''
            @echo off
            echo 업로드 파일: %UPLOAD_FILE%
            echo 대상 서버: %SERVER_IP%
            scp -o StrictHostKeyChecking=no "%UPLOAD_FILE%" %REMOTE_USER%@%SERVER_IP%:%REMOTE_DIR%/
          '''
        }
      }
    }
  }

  post {
    success { echo "✅ 파일 업로드 성공: ${params.UPLOAD_FILE} → ${params.SERVER_IP}:${params.REMOTE_DIR}" }
    failure { echo "❌ 파일 업로드 실패" }
  }
}
