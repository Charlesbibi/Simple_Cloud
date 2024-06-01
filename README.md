# Simple_Cloud
Set up a simple, practical, and re-developable microservices platform, with the entire logic based on the brand-new version of Spring Boot 3, and including mainstream technology frameworks such as Sentinel, Gateway, Seata, Nacos, Micrometer+Zipkin, Spring Security, Open Feign, Load Balance, MyBatis, and Redis.

# Project Architecture Framework Diagram
![图片](https://github.com/Charlesbibi/Simple_Cloud/assets/93184338/e4e57ea0-cee6-4d76-b24c-fbe22d066986)
</br>For each request send by web client, it is first intercepted by the **Gateway server** to determine if there are permissions for passage (token, path matching, etc.). </br>
Once past the Gateway server, the request is captured by the **Authentication server**, which checks whether the current user has the corresponding access rights. </br>
Ultimately
1. Users who can access normally are forwarded to the business logic server cluster to handle the response
2. While others will be rejected, resulting in an **404access denied**.</br>
