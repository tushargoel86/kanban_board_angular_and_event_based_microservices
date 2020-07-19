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


### Screenshots

 ![](https://github.com/tushargoel86/kanban_board_angular_and_event_based_microservices/blob/master/LoginAndKanbanBoard.gif)
 
I have completed Login and Board Creation part. I will complete Board listing and Task part next week.
