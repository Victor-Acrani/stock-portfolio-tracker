# Stock Management REST API

Welcome to the Stock Management REST API tutorial! In this guide, we will build a simple REST API using the latest technologies in the Java ecosystem.

## Table of Contents
- [Introduction](#introduction)
- [Technologies Used](#technologies-used)
- [Features](#features)
- [Getting Started](#getting-started)
- [Endpoints](#endpoints)
- [Benchmarking](#benchmarking)
- [References](#references)

## Introduction

Join us in this tutorial as we delve into crafting a simple Stock Management REST API using the cutting-edge combination of Spring Boot 3.3, Java 21, and Virtual Threads. Leveraging the power of Java records and Spring Data JDBC, we'll seamlessly access data stored in a MySQL database. Exploring the latest features, we'll harness the new RestClient API within Spring to effortlessly fetch stock prices from an external source.

Towards the conclusion, we'll unravel the concept of Virtual Threads, delving into how they revolutionize Java applications, bringing significant benefits. To quantify the enhancements, we'll conduct a thorough benchmarking analysis using the Apache Benchmark tool, showcasing the tangible performance improvements achieved through the integration of Virtual Threads. Join us on this journey of innovation and optimization in API development.

## Technologies Used

- **Java 21**
- **Spring Boot 3.3**
- **Spring Data JDBC**
- **MySQL**
- **RestClient API**
- **Virtual Threads**

## Features

- **Java Records:** Leveraging Java's new record feature for data modeling.
- **Spring Data JDBC:** Seamless data access and manipulation.
- **External API Integration:** Fetching stock prices using the new RestClient API in Spring.
- **Virtual Threads:** Enhancing performance and scalability with Java's Virtual Threads.

## Getting Started

To get started with this project, ensure you have the following prerequisites:

1. **Java 21** installed on your machine.
2. **Gradle** for managing dependencies.
3. **MySQL** database setup.

### Create Spring project
Access [spring initializr](https://start.spring.io/) and create a project with the following parameters:
- **Project:** Gradle-Groovy;
- **Language:** Java;
- **Spring Boot:** 3.3.1;
- **Packaging:** JAR;
- **Java:** 21;
- **Dependencies:** Spring Web, Lombok, Spring Boot DevTools, Spring Data JDBC, MySQL Driver.

### Set Database Container
To avoid installing MySQL database on a local machine. Let`s use a docker container to set up the database.

Download MySQL image:
```sh
docker pull mysql/mysql-server
```

Run MySQL container:
```sh
docker run --name mysql-container -e MYSQL_ROOT_PASSWORD=password -d -p 3306:3306 mysql/mysql-server
```

Access container:
```sh
docker exec -it mysql-container mysql -u root -p
```

Create user:
```mysql
CREATE USER 'user'@'%' IDENTIFIED BY 'password';
```

Check users:
```mysql
SELECT User FROM mysql.user;
```

Grant permissions:
```mysql
GRANT ALL PRIVILEGES ON *.* TO 'user'@'%'; 
```

Flush permissions:
```mysql
FLUSH PRIVILEGES;
```

### Set Database
Use a database tool like [dbeaver](https://dbeaver.io/download/) to access your database instance in your docker container
and do the following.

Create database:
```mysql
CREATE DATABASE stock_portfolio;
```

Select database:
```mysql
USE stock_portfolio;
```

Create table:
```mysql
CREATE TABLE orders
(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT,
    stock_symbol varchar(255),
    quantity DOUBLE,
    price DOUBLE,
    order_date DATETIME,
    order_type ENUM('BUY','SELL')
);
```

### Get FMP ApiKey
Access [financialmodelingprep](https://site.financialmodelingprep.com/) and create an account to get you apikey for
using the FMP API. For this example we are using the FMP Bulk Request end-point. 
```http request
https://financialmodelingprep.com/api/v3/quote/AAPL,MSFT?apikey=WtWfRwGN8sAadKJexYaDTsLuB5g8c6oE
```

Read [FMP API documentation](https://site.financialmodelingprep.com/developer/docs) for better undestanting of how to use it.

## Endpoints
### Get All Orders (GET /orders)
- JSON structure of the response:
``` json
[
    {
        "id": 1,
        "user_id": 1,
        "stockSymbol": "AAPL",
        "quantity": 200.0,
        "price": 160.0,
        "orderType": "BUY",
        "orderDate": "2024-07-07T16:32:07"
    },
    {
        "id": 2,
        "user_id": 1,
        "stockSymbol": "AAPL",
        "quantity": 100.0,
        "price": 161.0,
        "orderType": "BUY",
        "orderDate": "2024-07-07T16:35:16"
    }
]
```

### Create Order (POST /orders)
- Receives a JSON payload.
``` json
{
    "user_id": 1,
    "stockSymbol": "AAPL",
    "quantity": 200.0,
    "price": 160.0,
    "orderType": "BUY",  
}
```

- JSON structure of the response:
``` json
{ 
    "id": 1,
    "user_id": 1,
    "stockSymbol": "AAPL",
    "quantity": 200.0,
    "price": 160.0,
    "orderType": "BUY",
    "orderDate": "2024-07-07T16:32:07"
}
```

### Get Stock Holding Details (GET /stock-holdings)
- JSON structure of the response:
``` json
[
    {
        "stockSymbol": "MSFT",
        "quantity": 150.0,
        "currentMarketValue": 69936.0,
        "currentMarketPrice": 466.24
    },
    {
        "stockSymbol": "AAPL",
        "quantity": 450.0,
        "currentMarketValue": 102519.0,
        "currentMarketPrice": 227.82
    }
]
```

## Benchmarking

To showcase the performance improvements brought by Virtual Threads, we will conduct benchmarking using the Apache Benchmark tool. Follow these steps to run the benchmark tests:

- Ensure the application is running.
- Open a terminal and execute the benchmark:

```sh
ab -n 1000 -c 50 http://localhost:8080/stock-holdings
```

Analyze the results to observe the performance benefits of Virtual Threads.

## References

- [REST API with Spring Boot 3.3, Java 21 and Virtual Threads | Benchmarking performance](https://www.youtube.com/watch?v=iRiJqI3oAqA&list=LL&index=1&t=195s)

