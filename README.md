# User Management Service

This project is a simple User Management Service implemented in Java. It provides functionality to retrieve user information by ID. We can create, update and delete users.

## Table of Contents

- [Getting Started](#getting-started)
  - [Prerequisites](#prerequisites)
  - [Building the Project](#building-the-project)
- [Usage](#usage)
  - [Running Locally](#running-locally)
  - [Docker](#docker)
- [Endpoints](#endpoints)
- [Tests](#tests)
- [Contributing](#contributing)
- [License](#license)

## Getting Started

### Prerequisites

- Java Development Kit (JDK) 17
- Apache Maven
- Docker (optional, for containerization)

### Building the Project

Clone the repository and navigate to the project directory:

```bash
git clone https://github.com/your-username/user-management-service.git
cd user-management-service
```
### Running Locally 

To run the project use maven or IDE like intellij or VSCode.

### Docker 
- Build the Docker Image, in the directory containing your Dockerfile.
```bash
docker build -t user_service .
```
- Run the Docker Container
```bash
docker run -p 8080:8080 user_service:latest
```

### Kubernetes Deployment

We can deploy this service to Kubernetes using deployment.yaml with this command :
```bash
minikube start
kubectl apply -f .\deploy\deployment.yaml
kubectl apply -f .\deploy\usermanagement-service.yaml
```