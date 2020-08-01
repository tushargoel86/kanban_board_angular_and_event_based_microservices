# kanban_board_angular_and_event_based_microservices
Kanban board using angular, java, rabbitmq, axon, event based architecture and CQRS. Also added OAUTH support to login using social side like faceboot and gmail.
Gmail is not working due bug in angularx-social-login library.

This sample application, which is written in Java and uses Spring Boot, It used Axon server to build a real-time, multi-user collaborative application. 

Technology used: 
1. Java, Spring Boot
2. H2 database
3. RabbitMQ for event propogation
4. Axon server for event processing
5. Angular as front end
6. OAUTH protocol to enable social login (facebook and gmail)

Diagram is as below:

![](https://github.com/tushargoel86/kanban_board_angular_and_event_based_microservices/blob/master/kanban_server_design.PNG)

The application consists of the following:

AngularJS browser application
Kanban Server - a Java and Spring Boot-based server-side application.
H2 database - stores materialized views of boards and tasks
The Kanban Board server has a Spring MVC-based REST API for creating, updating and querying Kanban boards and tasks. 


The application is structured using the Command Query Responsibility Segregation (CQRS) pattern. It consists of the following modules:

* Command-side module - it handles requests to create and update (e.g. HTTP POST, PUT and DELETE requests) boards and tasks. The business logic consists of event sourcing based Board and Command aggregates.

* Query-side module - it handles query requests (ie. HTTP GET requests) by querying a H2 materialized view that it maintains. It consists of an event handler that subscribes to Board and Task events and updates H2. 

### Default credential
user: test@gmail.com
password: abcd1234


I have completed board and tasks part. Due to time crunch, I have dropped idea to add websocketgateway. Following are the functionality available now:
### For Board
1. Create Board
2. Delete Board (API is added but UI is not added)

### Tasks
1. Create Task
2. Move Task between states
3. Delete Task
4. Edit Task (API is added but UI is not added)


### UI server ####
as social login requires https connection, hence you need to start node server using SSL option

```
ng serve --ssl
```
### Backend API
We have 4 microservices:
1. For Board command
2. For Board Query
3. For Task Command
4. For Task Query

### AXON server
Download axon server from here https://axoniq.io/download
unzip it and start server using below command. It will start server at default port 8024/8124 which will be listen by above microservices.

```
java -jar axonserver.jar
```

### RabbitMQ server
I have used RabbiMQ docker image
```
docker pull rabbitmq
docker run -d --hostname my-rabbit --name some-rabbit -p 15672:15672  -p 5672:5672 rabbitmq:3-management
 ```


Complete demo is ![here](https://github.com/tushargoel86/kanban_board_angular_and_event_based_microservices/blob/master/images/bandicam%202020-08-01%2017-04-01-734.mp4)




