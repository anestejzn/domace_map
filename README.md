
<h1 align="center">
  DomaćeMap Web Application
  <br>
</h1>

<p align="center">
  • <a href="#-project-setup-and-commands">FTN, Novi Sad, Septembar 2023</a>
  •
</p>


## 👨‍💻 Developer
    • Anastasija Samčović   SW44-2019 https://www.linkedin.com/in/anastasija-samcovic/

## 🚀 Project setup

#### <span style="vertical-align: middle">:warning:</span> *Pre requirements:*

- Installed Node.js
- Angular version 14+
- JDK version 17
- PostgreSQL

#### <span style="vertical-align: middle">:floppy_disk:</span> *How to run backend:*

- Open graduate backend app in IntelliJ IDE
- Click on run button to run server

#### <span style="vertical-align: middle">:floppy_disk:</span> *How to run frontend:*

- Open angular-app in wanted IDE (VSCode, WebStorm etc.)
- Run npm install in terminal to install all needed dependencies
- Run ng serve in terminal to start application
<br>

## 🤝 Useful to know:
- DomaćeMap application is using Google map and Material Angular, stable internet connection is needed
- There are three types of user roles to login:
    - Admin (domacemap+admin@gmail.com)
    - Regular user (domacemap+ana@gmail.com)
- Password for all users is: sifra1234A2@
- PostgreSQL configuration is set in application properties

## 🏗️ Project structure:
- BACKEND
    - main
        - java
            - config (project configuration classes)
            - controller (application endpoints are located here)
            - dto (data transfer objects)
            - exception (global exception handler and all exception types)
            - enums
            - model (all entity classes)
            - repository (classes with queries for reaching data from DB)
            - service (all bussines logic)
            - util (helping functions and constants)
        - resources
            - application.properties
            - data-postgres.sql (script for populating database)
            - staticly stored images
            - spring email configuration
- FRONTEND
    - enviroments (global constants that are needed across application)
    - assets (images)
    - modules
        - admin (components used only by admins)
        - auth (security based components)
        - material (imports for angular material)
        - root (starting point for frontend)
        - shared (components used by multiple user roles, interceptors, pipes)
* Note* All user based elements are consisting of pages, components and services



