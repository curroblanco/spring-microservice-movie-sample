## Movie Sample Project

# Sample project on Spring-Boot using microservice architecture.

This is a sample projects using microservice structure. Also providing an Eureka Registry server with an API Gateway (Zuul). 

All the Microservices and Database have been Dockerized.

## Prerequisites

- Having Docker and Docker Toolbox installed

## Instructions

- First of all, start the DB: docker-compose up -d db
- Now start adminer in order to connect to the db: docker-compose up -d adminer
- You will have to create all the Required DBs for the project. Right now only one is needed: CREATE DATABASE movies;
- Start Eureka Registry Service: docker-compose up -d eureka-server
- Start Zuul API-Gateway: docker-compose up -d zuul-gateway
- Start Movie Microservice: docker-compose up -d movies-microservice
