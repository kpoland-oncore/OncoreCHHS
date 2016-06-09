# OncoreCHHS DevOps Environment Specifications
[README](README.md) > DevOps Environment Specifications

This document describes the technical setup of various aspects of the OncoreCHHS DevOps and environment approach:

1. GitHub  
2. Jenkins Builds  
3. Docker Containers  
4. Test Environment  
5. Production Environment  
6. Monitoring  

## GitHub
All source code, test scripts, design documentation, and other documentation is maintained in this GitHub repository.

The repository address is [https://github.com/OncoreLLC/OncoreCHHS](https://github.com/OncoreLLC/OncoreCHHS)  
**NOTE: This repo has been set to read-only as of June 9th, 2016.**

##### GitHub Organization
- several markdown files in the top level directory address
  - [README.md](README.md) - official proposal project writeup
  - [DEVELOPER.md](DEVELOPER.md) - developer documentation
  - [ENVIRONMENTS.md](ENVIRONMENTS.md) - this document containing DevOps and environment documentation
- a [documentation](documenation) folder containing
  - [the user interface guide](documentation/OncoreCHHSUserInterfaceGuide.pdf)
  - [JavaDocs](documentation/JavaDocs.zip) (zip file) for the built application
  - a [folder](documentation/DesignSpecsFromUserSessions) of design specifications in Word format
  - a [folder](documentation/PivotalTrackerArtifacts) of Pivotal Tracker agile project tool information and an extract from the tool
- java source and test projects are located in
  - OncoreCHHS - web tier containing an war and an ejb subproject
  - OncoreCHHSServices - services tier containing a war and an ejb project
  - OncoreCHHSServices-persistence - persistence layer used by OncoreCHHSServices
  - OncoreCHHSServices-client - REST client layer used by OncoreCHHSServices
  - rchCommon - common architectural layer used by both OncoreCHHS and OncoreCHHSServices
  - OncoreCHHS-selenium - selenium regression tests
- Docker materials
  - the top level Dockerfile that builds our Docker app image
  - a docker folder containing materials needed for the Docker build

## Jenkins Builds
We use the Jenkins open-source Continuous Integration server for builds and deployments. Jenkins is installed on a Azure Linux VM which also serves as our Test server.

The Jenkins console is available at: http://oncorechhsjenkins.westus.cloudapp.azure.com/
##### Jenkins Pipeline Job
A single continuous deployment pipeline job takes the code from GitHub push through build, unit tests, deployment to a test environment, regression tests, manual approval to deploy to Prod, and the Production deployment.

![alt text](https://github.com/OncoreLLC/OncoreCHHS/blob/master/documentation/jenkins_screenhsot.png "Jenkins screenshot")

Pushes to GitHub automatically trigger the pipeline job. **NOTE: as of June 9 this automatic build has been disabled.**

This pipeline goes through the following stages where each stage is dependent on the successful completion of previous stages:

1. __Checkout__ - get the latest code from GitHub  
2. __Clean__ - run ant targets to clean the workspaces   
3. __Build Web Tier__ - run ant target to build the web tier into the OncoreCHHS.ear  
4. __Build Services Tier__ - run ant target to build the REST services tier into the OncoreCHHSServices.ear  
5. __Unit Test__ - run ant target to build and run the JUnit tests  
6. __Dockerize__ - build a new Docker image based on the Dockerfile consisting of the built EARs plus supporting files on top of a Glassfish on Ubuntu Linux base image  
7. __Deploy To Test__ - deploy the newly built Docker image into a container on the Jenkins server, this is the Test environment  
8. __Regression Test__ - run selenium regression tests against the Test environment  
9. __Push to DockerHub__ - tag and push the image to DockerHub  
10. __Pause for Approval__ - a manual approval in Jenkins is necessary to proceed with the Production deployment. This allows time for additional manual regression testing in the test environment, testing from multiple devices, and consensus that the build is suitable for Production.  
11. __Deploy to Prod__ - pull the latest Docker image to the Production server, deploy it.

## Docker Containers
Our solution uses Docker for container-based deployment. Our Docker image, defined by the [Dockerfile](Dockerfile) in the root of the GitHub repository, consists of:
- a base glassfish-4.1.1 image (running on ubuntu linux)
- several additional packages added to make troubleshooting within the container easier
- project specific jars and configurations copied into the glassfish directories
- the two built EARs copied into the image
- a container startup script that starts glassfish and deploys the two EARs

##### Docker in the Build Pipeline
The docker image is built in step 6 of the pipeline, after the build and unit tests have succeeded. The docker image we build is tagged with the Jenkins build id.

The docker image is deployed into a container on the Jenkins server in step 7 of the pipeline. This is now a running instance of the application - our Test environment.

After selenium regression tests run the docker image is tagged as latest and pushed to DockerHub in step 8 of the pipeline.

The Production deployment in step 10 pulls the latest image from DockerHub and runs it on our Production server.

##### DockerHub
Our repository on DockerHub is: https://hub.docker.com/r/kpoland/oncorechhsapp/
The image can be pulled with: docker pull kpoland/oncorechhsapp:latest

## Test Environment
The application link for the Test environment is: http://oncorechhsjenkins.westus.cloudapp.azure.com:8080/OncoreCHHS-war/

The Test environment is co-resident with Jenkins on an Azure Linux VM. Docker Engine and MySQL with the CHHSDB database is also on the VM. We chose to not run MySQL in a Docker container for this demonstration project due to the additional administrative and persistent storage complexity. Use of an Azure database-as-a-service was also considered, but not chosen in this demonstration to ensure a more open-source stack. Both choices would be re-evaluated in a full implementation.

## Production Environment
The application link for the Production environment is: http://oncorechhsapp01.westus.cloudapp.azure.com:8080/OncoreCHHS-war/

The Production environment is a different Azure Linux VM which runs Docker Engine and MySQL with the CHHSDB database. For this technology demonstration a single VM and single container are in use; both would be load balanced in a full implementation. Deployment directly to a Docker platform-as-a-service such as Azure would also be a consideration.

## Monitoring
We are using the free tier of the StatusCake monitoring service for site monitoring. StatusCake is monitoring the health of:
- the Production Home page
- the Production REST services tier, including the database persistence layer
- the Test environment Home page
- Jenkins

![alt text](https://github.com/OncoreLLC/OncoreCHHS/blob/master/documentation/statuscake_screenshot.png "StatusCake screenshot")

Our monitoring dashboard is available at: https://www.statuscake.com/App/Login/
- use username: kyle.poland@oncorellc.com
- password: oncorechhs

We considered setting up our own independent monitoring stack, but went with StatusCake for two primary reasons.  First it was very quick and simple to implement, within a few minutes of our first successful deployment to the Test environment we were able to stand up monitoring of the site. Second, using a hosted monitoring solution ensured that it is independent of the health of our Azure stack and VMs.
