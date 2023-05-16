# RestaurantBuddy

A Self-Contained Containerized Approach to Restaurant Menu and Ordering Software

| About this Document      |                |
|--------------------------|----------------|
| Author:                  | Samuel Mace    |
| Project License:         | MIT            |
| Originally Created Date: | March 24, 2023 |
| Last Modified Date:      | May 16, 2023   |

## Purpose and Project Introduction

The purpose of this application will be twofold -- to serve as an introduction to full stack web application and
deployment as well as to solve a business problem out in industry.

## Licensing and Contributions

Usage of this project is subject to the terms of the MIT license found in the project. Pull requests and contributions
to the project are accepted, but they will also be subject to the terms of the MIT license.

## Technologies

This application will make use of popular technologies used out in the industry in its implementation of frontend,
backend, database, and deployment-related technologies. Listed below are some of the technologies I will be using in
this project:

### Frontend

- HTML5
- CSS3
- JavaScript/ECMAScript6

### Backend (API)

- Java
- Gradle Build System
- Spring Boot

### Database

- MariaDB

### Testing Mechanisms

- Node.JS

### Deployment Mechanisms

- Docker Compose
- Apache HTTPd Web Server
- phpMyAdmin

## Requirements (Goals for the Project)

### Business Requirements

The primary purpose of the RestaurantBuddy full-stack web application is to provide a basic restaurant management
application for a local small business. The entire application should be fairly easy for any restaurant owner to
install, operate, and manage. Although it is expected that an IT person would be able to assist with any problems that
arise, these problems should be kept to a minimum and the application should require little maintenance.

With that said, the restaurant application will not be as fully-featured as other solutions that exist. However, after
the base of the project is completed, it should provide a well-documented foundation upon which additional features can
be added as the need arises.

### User Requirements

#### Owner

- Add additional employees (logins)
- Add additional menu items

#### Employee

- View orders that customers place in real-time
- Complete orders after they are done being made

#### Customer

- Signup for a new user account
- View inventory offered by the restaurant
- Make custom selections about which items are to be ordered
- Add items to the cart
- Complete order and provide payment details (with the possibility of adding Stripe integration later down the line)

#### All Parties

- Login to the application
- Have access to only the areas of the application which they are permitted to have access
- Have a basic, but visually-appealing frontend to the application

### Functional Requirements

#### Authentication and Authorization

The application will necessitate the need of authentication and authorization implementation in the application. The
authentication process will be responsible for verifying the credentials of any given user in question (and will
generate a JWT token to be used later). The authorization process, on the other hand, will be responsible for
determining which users are allowed to access specific services as well as which JWT tokens are issued to which users.

##### Authentication

During the authentication process, any user in question will be responsible for supplying the username and password to
the application. The application will then be responsible for generating a valid JWT token and supplying that to the
client to be passed in the header of all subsequent API requests.

##### Authorization

The authorization portion of the application will be responsible for managing _who_ will be able to access _what_ within
the API. This will be extracted from the API token every time the user makes a request to the API. These tokens will be
stored in the memory of the API (and will thus be deleted when the API application quits).

### Nonfunctional Requirements

The primary nonfunctional requirement of the application is the de-coupled nature of the full-stack web application. The
application will be based upon three layers: the database, the API (backend), and the frontend. This design will allow
additional apps and platforms to directly interact with the API without having to go through the website.
