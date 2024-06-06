## Elastic APM Walkthrough (WIP)



### Navigate to ElasticApmChart

Run the following

```sh
curl -sL https://github.com/operator-framework/operator-lifecycle-manager/releases/download/v0.28.0/install.sh | bash -s v0.28.0
```



Then run 

```sh
kubectl create -f https://download.elastic.co/downloads/eck/2.13.0/crds.yaml
kubectl apply -f https://download.elastic.co/downloads/eck/2.13.0/operator.yaml
```


then run the following command


```sh

helm upgrade elastic-apm . --install --namespace elastic-apm --create-namespace --wait

```


Install k9s 

```sh

sudo apt update
sudo apt install snapd

sudo snap install k9s

sudo ln -s /snap/k9s/current/bin/k9s /snap/bin/
```




