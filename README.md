# Kodluyoruz-Ticketary

This project is a ticket buying app to plane and bus travels.

## Table of Contents
* [General Info](#general-info)
* [Used Technologies](#used-technologies)
* [Password Protection](#password-protection)
* [Asynchronous Operations](#asynchronous-operations)

## General Info
Graduation project for Kodluyoruz Java Bootcamp.<br />
This is a app for ticket buying application build with microservices architecture.<br />
In this project I used postgreSql and rabbitMq with docker.<br />
You can read more information about the project in project [document](https://github.com/erenduzova/Kodluyoruz-Ticketary/blob/master/kodluyoruz-project-details.pdf).

## Used Technologies
Project is created with:
* Java 17
* RESTful
* Spring Boot
* PostgreSQL
* RabbbitMQ
* Docker

## Password Protection
PBKDF2WithHmacSHA1 Algorithm used for securing password.<br />
Password saved database after hashing.<br />
This algorithm makes password cracking much more difficult with added computational work.<br />
[For more information and examples](https://howtodoinjava.com/java/java-security/how-to-generate-secure-password-hash-md5-sha-pbkdf2-bcrypt-examples/#5-hashes-using-bcrypt-and-scrypt)

## Asynchronous Operations
RabbitMQ used for asynchronous operations.<br />
In this project notification service works asynchronously.<br />
After registration we send an mail to the new user and after buying tickets we send tickets details to the passengers via sms.<br />
