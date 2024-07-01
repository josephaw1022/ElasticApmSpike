## Elastic APM Walkthrough

### Setup Operator Framework

Run the following command to install the Operator Lifecycle Manager:

```sh
curl -sL https://github.com/operator-framework/operator-lifecycle-manager/releases/download/v0.28.0/install.sh | bash -s v0.28.0
```

### Setup Elastic CRDs and Operator

Run the following commands to create the necessary CRDs and apply the Elastic operator:

```sh
kubectl create -f https://download.elastic.co/downloads/eck/2.13.0/crds.yaml
kubectl apply -f https://download.elastic.co/downloads/eck/2.13.0/operator.yaml
```

### Install Elastic APM

Run the following command to install the Elastic APM chart using Helm:

```sh
helm upgrade elastic-apm . --install --namespace elastic-apm --create-namespace --wait
```

Follow the instructions provided in the Helm notes after installation.

### Post-Installation Steps

1. Port forward the APM server and the Kibana UI.
2. Retrieve the Elastic user password by running the appropriate `kubectl` commands.
3. Log in to Kibana and add the APM integration:
   - Ensure the token is set to 'secret-token'.
   - Add Java, .NET, and OTLP to the allowed agents.

### Run Java Application

Navigate to the Java application in the `src` directory and run the application using Quarkus:

```sh
./mvnw quarkus:dev
```

### Run .NET Application

Navigate to the .NET Aspire application and run the application in the app host project.

```sh
dotnet watch
```




### Not just for metric monitoring

- Dashboards: SREs can create custom dashboards to visualize the metrics that matter most for their SLOs, such as latency, error rates, and request counts.
- Centralized Logging: Aggregate logs from different sources, making it easier to search and correlate log entries from different parts of the system.
- Anomaly Detection: Kibana’s machine learning features can automatically detect anomalies in the system performance, which is crucial for maintaining SLOs.

