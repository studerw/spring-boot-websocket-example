# Spring Boot Websocket Chat Example
This is a simple Spring Boot web app that demonstrates using Websockets and STOMP protocol to implement a simple Chat Server.
The Chat Html and Javascript.

It requires Java 8. 

![spring-boot-websocket-example](https://github.com/studerw/spring-boot-websocket-example/blob/master/chat_animated.png)


## Building the Project

* Windows: `mvnw.cmd clean install`
* Linux/Mac: 
  - `chmod u+x ./mvnw`
  - `./mvnw clean install`

## Run Locally
* Windows: `mvnw.cmd spring-boot:run`
* Linux/Mac: `./mvnw spring-boot:run`

The following test can be run from the command line:
`curl -vk http://localhost:8080/ping`
> Should get a `pong` response

## Running the Chat Application

In your browser, open up two tabs, both to [https://localhost:8080/chat.html](https://localhost:8080/chat.html).

From within each tab, send chat messages. You should see the messages appear in both tabs.

