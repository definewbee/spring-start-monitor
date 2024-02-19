// #!/usr/bin/env groovy
// pipeline {
//     agent any
//
//     tools {
//         maven 'maven'
//         dockerTool 'docker'
//     }
//     stages {
//         stage('Checkout') {
//             steps {
//                 script {
//                     checkout([$class: 'GitSCM', branches: [[name: 'dev']], userRemoteConfigs: [[url: 'https://github.com/definewbee/spring-start-monitoring.git']]])
//                 }
//             }
//         }
//         stage('Build and Test') {
//             steps {
//                 script {
//                     sh 'mvn clean package -DskipTests'
//                 }
//             }
//         }
//         stage('Build Docker Image') {
//             steps {
//                 script {
//                     sh 'docker build -t spring-start-monitor:latest .'
//                 }
//             }
//         }
//         stage('Push to ECR') {
//             steps {
//                 script {
//                     withAWS(credentials: 'aws-credentials', region: 'ap-southeast-1') {
//                         def ecrLoginCommand = ecrLogin(registryIds: ['746744850355'])
//                         sh "${ecrLoginCommand}"
//                         sh "docker tag spring-start-monitor:latest 746744850355.dkr.ecr.ap-southeast-1.amazonaws.com/spring-start-monitor:latest"
//                         sh "docker push 746744850355.dkr.ecr.ap-southeast-1.amazonaws.com/spring-start-monitor:latest"
//                     }
//                 }
//             }
//         }
//         stage('Deploy to EKS') {
//             steps {
//                 script {
//                     // get kubernetes service url command:
//                     // kubectl config view --minify --output jsonpath="{.clusters[*].cluster.server}"
//                     // kubectl exec --namespace ops -it svc/jenkins -c jenkins -- /bin/cat /run/secrets/additional/chart-admin-password && echo
//                     // helm upgrade jenkins jenkins/jenkins --set kubectl.enabled=true --set kubectl.version=1.29.1 --set kubectl.namespace=default
//
//                     withKubeConfig([credentialsId: 'kubeconfig-credentials', serverUrl: 'https://02C9CAE1530CB6894D2023CE9F6E3969.gr7.ap-southeast-1.eks.amazonaws.com']) {
//                         sh 'kubectl apply -f kubernetes/deployment.yaml'
//                     }
//                 }
//             }
//         }
//     }
// }

@Library('jenkins-shard-libaray@main') _
jenkinsPipeline()