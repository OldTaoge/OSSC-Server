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
              sh 'mvn package -Dmaven.test.skip=true'
          }
       }

	  stage('Archive') {
         steps {
             archiveArtifacts artifacts: '**/target/*.jar', fingerprint: true
         }
      }

      stage('dockerMake') {
          steps {
              sh 'docker build -t ${ImageName}:snaphost .'
              sh 'docker tag ${ImageName}:snaphost docker.oldtaoge.space:5000/${ImageName}:snaphost'
              sh 'docker push docker.oldtaoge.space:5000/${ImageName}:snaphost'
              sh 'docker image rm docker.oldtaoge.space:5000/${ImageName}:snaphost'
              sh 'docker image rm ${ImageName}:snaphost'
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