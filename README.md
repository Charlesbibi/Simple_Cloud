# Simple_Cloud
Set up a simple, practical, and re-developable microservices platform, with the entire logic based on the brand-new version of Spring Boot 3, and including mainstream technology frameworks such as Sentinel, Gateway, Seata, Nacos, Micrometer+Zipkin, Spring Security, Open Feign, Load Balance, MyBatis, and Redis.

# Project Architecture Framework Diagram
![图片](https://github.com/Charlesbibi/Simple_Cloud/assets/93184338/a4ac46b7-30f1-4f1e-b227-27f40878273f)


</br>For each request send by web client, it is first intercepted by the **Gateway server** to determine if there are permissions for passage (token, path matching, etc.). </br>
Once past the Gateway server, the request is captured by the **Authentication server**, which checks whether the current user has the corresponding access rights. </br>
Ultimately
1. Users who can access normally are forwarded to the business logic server cluster to handle the response
2. While others will be rejected, resulting in an **404access denied**.</br>

# Quick Start
Please note that before running, it is necessary to start the following five services(Whether it's on a local machine, a remote server, or in a virtual machine). You can download latest version of those package in official website.
</br> 
> 1. **nacos** server
> 2. **seata** server
> 3. **zipkin** server(jar package)
> 4. **sentinel** server(jar package)
> 5. **redis** server

After pulling the GitHub code in your compiler, you can run normally.

# Example
Under this architecture, we have built an example to simulate a business chain for `Placing orders -> Deducting account balance -> reducing inventory`.</br>

In this example
- **Seata** is used for distributed transaction commits and rollbacks.
- **Sentinel** implements traffic control.
- **Micrometer** is used for link tracing while Zipkin is used for displaying the trace links.
- Unified calls between various microservices are implemented through **Feign** interfaces.

For detail infomation you can find in the [createOrder method](https://github.com/Charlesbibi/Simple_Cloud/blob/main/simpleCloud_order_server/src/main/java/com/simple/cloud/service/impl/OrderServiceImpl.java)

---
We have also built front-end monitoring for this microservice server cluster. </br>
For those interested readers , click [here](https://github.com/Charlesbibi/Simple_Cloud_Monitor) for more detail.
