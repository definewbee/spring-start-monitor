<<<<<<< HEAD
# spring-start-monitor
=======
<<<<<<< HEAD
# spring-start-monitor
=======
# spring-start-monitoring
# 删除jenkins

```shell
kubectl delete statefulset jenkins -n ops
kubectl delete service jenkins -n ops 
kubectl delete service jenkins-agent -n ops
kubectl delete persistentvolumeclaim -l release=jenkins (有问题)
kubectl delete configmap jenkins -n ops
kubectl delete configmap jenkins-jenkins-jcasc-config -n ops
kubectl delete secret jenkins -n ops


//docker相关
docker build --platform=linux/amd64 -t jenkins/inbound-agent-with-kubectl:v0.3 .
docker tag jenkins/inbound-agent-with-kubectl:v0.3 definewbie/jenkins:v0.3 
docker push definewbie/jenkins:v0.3

```
>>>>>>> b42d7f6 (init commit)
>>>>>>> c55218f (init)
