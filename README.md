# Stock Management REST API

Welcome to the Stock Management REST API tutorial! In this guide, we will build a robust REST API using the latest technologies in the Java ecosystem.

## Table of Contents
- [Introduction](#introduction)
- [Technologies Used](#technologies-used)
- [Getting Started](#getting-started)
- [Features](#features)
- [Benchmarking](#benchmarking)
- [Conclusion](#conclusion)

## Introduction

Join us in this tutorial as we delve into crafting a robust Stock Management REST API using the cutting-edge combination of Spring Boot 3.3, Java 21, and Virtual Threads. Leveraging the power of Java records and Spring Data JDBC, we'll seamlessly access data stored in a MySQL database. Exploring the latest features, we'll harness the new RestClient API within Spring to effortlessly fetch stock prices from an external source.

Towards the conclusion, we'll unravel the concept of Virtual Threads, delving into how they revolutionize Java applications, bringing significant benefits. To quantify the enhancements, we'll conduct a thorough benchmarking analysis using the Apache Benchmark tool, showcasing the tangible performance improvements achieved through the integration of Virtual Threads. Join us on this journey of innovation and optimization in API development.

## Technologies Used

- **Java 21**
- **Spring Boot 3.3**
- **Spring Data JDBC**
- **MySQL**
- **RestClient API**
- **Virtual Threads**

## Getting Started

To get started with this project, ensure you have the following prerequisites:

1. **Java 21** installed on your machine.
2. **Maven** for managing dependencies.
3. **MySQL** database setup.

Clone the repository:

```sh
git clone https://github.com/yourusername/stock-management-api.git
cd stock-management-api
```

Build and run the application:

```sh
mvn clean install
mvn spring-boot:run
```
## Features

- **Java Records: Leveraging Java's new record feature for data modeling.**
- **Spring Data JDBC: Seamless data access and manipulation.**
- **External API Integration: Fetching stock prices using the new RestClient API in Spring.**
- **Virtual Threads: Enhancing performance and scalability with Java's Virtual Threads.**

## Benchmarking

To showcase the performance improvements brought by Virtual Threads, we will conduct benchmarking using the Apache Benchmark tool. Follow these steps to run the benchmark tests:

- Ensure the application is running.
- Open a terminal and execute the benchmark:

```sh
ab -n 1000 -c 10 http://localhost:8080/api/stocks
```

Analyze the results to observe the performance benefits of Virtual Threads.

## Conclusion

Through this tutorial, we've explored the latest advancements in Java and Spring Boot, culminating in a highly optimized and efficient Stock Management REST API. 
The integration of Virtual Threads demonstrates significant performance improvements, paving the way for more scalable Java applications.
