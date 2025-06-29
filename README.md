# Welcome to the _jamix-app_ project 

This project uses : Java Spring Boot, Maven, PostgreSql and Vue.js.

## Installation
### DataBase
Create your DB and connect it to a new project `jamix-database`.
```sql
CREATE DATABASE jamix_db;
```
When your project and your database are connected, run these scripts :

1 - `script.ddl.sql`

2 - `data.dml.sql`

### Backend
1 - With Eclipse (or your favorite IDE) open the project in directory `jamix-business`.
> Note: import and choose `Existing Maven Project`

 2- Create a directory `resources` in `src/main` and create a file `application.properties`.
 
 Structure : 
 
![image](https://github.com/user-attachments/assets/42433ffe-d7cb-4e11-aa42-7418b3df540e)


 
 3- In `application.properties`, fill :
 ```
spring.application.name=jamix-business
spring.datasource.url=jdbc:postgresql://localhost:<port>/<name-database>
spring.datasource.username=<your-user-name>
spring.datasource.password=<your-password>

spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
 ```
> For full secrets, contact me.
 
 4- Run as `Spring Boot App`

### Front-end
Open `jamix-presentation` with Visual Studio Code (or other).

Run 
```bash
npm install
```
And launch the server :
```bash
npm run dev
```

The project is installed. :tada:
