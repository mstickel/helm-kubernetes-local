# helm-kubernetes-local
Example application using Maven to build and deploy Helm charts locally.

### Prerequisites
In order to build and run this service, you'll need to install and configure the following software:
* Java (version 14.0 or greater; or modify pom to support an older version)
* Maven (version 3 or greater)
* Git
* A version of Kubernetes running locally: Docker for Desktop (Mac or Windows) with Kubernetes support; or MiniKube or K3s
* Kubernetes client (kubectl)
* Helm (version 3 or greater)

Optionally, we like to use
* k9s
* jenv

### Getting the Source Code

Clone from Github: `git clone https://github.com/mstickel/helm-kubernetes-local.git`

### Installing Supporting Services

You'll need the following supporting services.  For the purposes of this example, we run them locally, but you could configure the project to point at a remote registry and Helm chart repo.
* Docker `registry` - a repository for Docker images
* Helm `chartmuseum` - a repository for Helm charts

We prefer to run these locally in Kubernetes, although you could install and run them locally as services on your machine.  To do so, first make sure you're using the right context:
* `kubectl config get-contexts`.  You'll want to use one called  `docker-desktop` or `docker-for-desktop`
* To set it, run `kubectl config use-context docker-desktop`

Then, `cd` to the `kubernetes` directory and run the following command to create the `devsupport` namespace in which we'll run these two supporting services:
* `kubectl apply -f devsupport-namespace.yaml`

Now that you've created the `devsupport` namespace, let's bring up the two supporting services in it:
* `cd` to `helm/devsupport`
* The charts we're pulling both reside in a chart repo at `https://kubernetes-charts.storage.googleapis.com`.  You will need to add this chart repo if it's not a part of your Helm repositories already.  To do this, run: `helm repo add stable https://kubernetes-charts.storage.googleapis.com`
* run `helm repo up`
* run `helm dep up`
* run `helm install devsupport . --namespace devsupport`

You will only have to run these once.  The services should continue to run and will start and stop automatically with your local Kubernetes service from here on.  They run just like normal services do on your dev machine. 

Once those have started, run:
* `helm repo add devsupport http://localhost:32702`

### Building from Source

Run `mvn clean deploy`.  This will build the project jar, build the project's Helm chart, wrap the jar in a Docker image, and deploy both the Docker image and the Helm chart to their corresponding local repositories.

### Running

To run, we want to deploy our project in our locally running Kubernetes node.  To do this, run `helm repo up`, then `helm install example http://localhost:32702/charts/helm-kubernetes-example`.

You can view the status of your service using either `kubectl` commands or by running `k9s`.

### Accessing the Service

Once your service is up and running, you can point Postman at `GET http://localhost:8080/joke`.  This should return a random joke.  