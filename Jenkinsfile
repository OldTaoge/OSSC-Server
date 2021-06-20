pipeline {
   agent any

   options {
         gitLabConnection('gitlab')
   }

    triggers {
        gitlab(triggerOnPush: true, triggerOnMergeRequest: true, branchFilterType: 'All')
    }

    environment {
        ImageName='OSSC-Server'
    }

   stages {
      stage('gitlab') {
         steps {
             echo 'Notify GitLab'
             updateGitlabCommitStatus name: 'build', state: 'pending'
         }
      }

      stage('build') {
          steps {
              sh 'mvn clean package -Dmaven.test.skip=true'
          }
       }

	stage('Archive') {
        steps {
            script {
                artifacts = [
                    'business-cms-config-service',
                    'business-ums-client-service',
                    'business-oauth2',
                    'provider-cms-config-service',
                    'provider-ums-client-service',
                    'provider-ums-user',
                    'gateway']
                path = [
                        'business/business-cms-config/business-cms-config-service/target/',
                        'business/business-ums-client/business-ums-client-service/target/',
                        'business/business-oauth2/target/',
                        'provider/provider-cms-config/provider-cms-config-service/target/',
                        'provider/provider-ums-client/provider-ums-client-service/target/',
                        'provider/provider-ums-user/provider-ums-user-service/target/',
                        'gateway/target/']
                for (int i = 0; i < artifacts.size(); ++i) {
                    archiveArtifacts artifacts: "${path[i]}/artifacts-*.jar", fingerprint: true
                }
                for (int i = 0; i < artifacts.size(); ++i) {
                    sh 'docker build -t ossc-server-${artifacts[i]}:latest ${path[i]}'
                    sh 'docker tag ossc-server-${artifacts[i]}:latest docker.oldtaoge.space:5000/ossc-server-${artifacts[i]}:latest'
                    sh 'docker push docker.oldtaoge.space:5000/ossc-server-${artifacts[i]}:latest'
                    sh 'docker image rm docker.oldtaoge.space:5000/ossc-server-${artifacts[i]}:latest'
                    sh 'docker image rm ossc-server-${artifacts[i]}:latest'
                }
            }
        }
    }
    }


    post {
        failure {
            updateGitlabCommitStatus name: 'Complete', state: 'failed'
        }
        success {
            updateGitlabCommitStatus name: 'Complete', state: 'success'
        }
    }

}
