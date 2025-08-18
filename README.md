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

#Database
spring.datasource.hikari.maximum-pool-size=3
spring.datasource.hikari.schema=public
spring.datasource.url=jdbc:postgresql://localhost:<port>/<name-database>
spring.datasource.username=<username>
spring.datasource.password=<password>
spring.jpa.open-in-view=false
springdoc.swagger-ui.operationsSorter=alpha
springdoc.writer-with-order-by-keys=true

spring.servlet.multipart.enabled=true
spring.servlet.multipart.max-file-size=5MB
spring.servlet.multipart.max-request-size=5MB

#Claim
spring.security.oauth2.resourceserver.jwt.authority-prefix=
spring.security.oauth2.resourceserver.jwt.authorities-claim-name=roles

#Logs
logging.logback.rollingpolicy.max-file-size=10KB
logging.logback.rollingpolicy.total-size-cap=100KB
#logging.level.org.hibernate=INFO
#spring.mvc.log-request-details=true

#Cors and jwt
jamix.cors=http://localhost:<port>
jamix.jwt.issuer=http://localhost:<port>/
jamix.jwt.exp=<seconds>
jamix.jwt.secret=<secret-256>
jamix.bcrypt.rounds=<round-number>
jamix.token.expiration-minutes=<minutes>

jamix.api.geo=https://geo.api.gouv.fr/communes

#File localpath replacement for Scaleway
jamix.uploads.destination=C:/<your-local-path>/jamix-business/uploads/dest
spring.servlet.multipart.location=C:/<your-local-path>/jamix-business/uploads/tmp
jamix.uploads.url=C:/<your-local-path>/jamix-business/uploads/dest


#Scaleway
scw.accessKey=<secret>
scw.secretKey=<secret>
jamix.url.images=<url-path>
jamix.url.musics=<url-path>
jamix.uploads.url=<url-path>
scw.region=<bucket-region>
scw.bucket=<bucket-name>


# SMTP/Mail properties:
jamix.email.from=<adress>
spring.mail.host=<host>
spring.mail.port=<port>
spring.mail.username=<username>
spring.mail.password=<password>
#complete with your other mail properties

# Adress Swagger 
#localhost:<port>/swagger-ui/index.html#/ or localhost:<port>/v3/api-docs
 ```
> For full secrets, contact me.
 

4- To save files .jpg in local, create `uploads` in `src/main` and create 2 packages `dest` and `tmp`.

 5- Run as `Spring Boot App`

### Front-end
1 - Open `jamix-presentation` with Visual Studio Code (or other).
2 - Install properties in a file `.env.local` in th root of `jamix-presentation` and fill :
```
VITE_API_URL=http://localhost:<port-back>
VITE_IMAGE_URL=VITE_IMAGE_URL=http://localhost:<port-back>/images
```

3 - Run 
```bash
npm install
```
And launch the server :
```bash
npm run dev
```

The project is installed. :tada:
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

#Database
spring.datasource.hikari.maximum-pool-size=3
spring.datasource.hikari.schema=public
spring.datasource.url=jdbc:postgresql://localhost:<port>/<name-database>
spring.datasource.username=<username>
spring.datasource.password=<password>
spring.jpa.open-in-view=false
springdoc.swagger-ui.operationsSorter=alpha
springdoc.writer-with-order-by-keys=true

spring.servlet.multipart.enabled=true
spring.servlet.multipart.max-file-size=5MB
spring.servlet.multipart.max-request-size=5MB

#Claim
spring.security.oauth2.resourceserver.jwt.authority-prefix=
spring.security.oauth2.resourceserver.jwt.authorities-claim-name=roles

#Logs
logging.logback.rollingpolicy.max-file-size=10KB
logging.logback.rollingpolicy.total-size-cap=100KB
#logging.level.org.hibernate=INFO
#spring.mvc.log-request-details=true

#Cors and jwt
jamix.cors=http://localhost:<port>
jamix.jwt.issuer=http://localhost:<port>/
jamix.jwt.exp=<seconds>
jamix.jwt.secret=<secret-256>
jamix.bcrypt.rounds=<round-number>
jamix.token.expiration-minutes=<minutes>

jamix.api.geo=https://geo.api.gouv.fr/communes

#File localpath replacement for Scaleway
jamix.uploads.destination=C:/<your-local-path>/jamix-business/uploads/dest
spring.servlet.multipart.location=C:/<your-local-path>/jamix-business/uploads/tmp
jamix.uploads.url=C:/<your-local-path>/jamix-business/uploads/dest


#Scaleway
scw.accessKey=<secret>
scw.secretKey=<secret>
jamix.url.images=<url-path>
jamix.url.musics=<url-path>
jamix.uploads.url=<url-path>
scw.region=<bucket-region>
scw.bucket=<bucket-name>


# SMTP/Mail properties:
jamix.email.from=<adress-server>
spring.mail.host=<host>
spring.mail.port=<port>
spring.mail.username=<username>
spring.mail.password=<password>
spring.mail.protocol=smtp
spring.mail.properties.mail.smtp.ssl.enable=true
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true

jamix.email.confirmation-url-base=http://localhost:<port-frontend>/accounts/verify
jamix.email.verification-url-back=http://localhost:<port-backend>/accounts/verify

# Adress Swagger 
#localhost:8080/swagger-ui/index.html#/ or localhost:8080/v3/api-docs
 ```
> For full secrets, contact me.
 

4- To save files .jpg in local, create `uploads` in `src/main` and create 2 packages `dest` and `tmp`.

 5- Run as `Spring Boot App`

### Front-end
1 - Open `jamix-presentation` with Visual Studio Code (or other).
2 - Install properties in a file `.env.local` in th root of `jamix-presentation` and fill :
```
VITE_API_URL=http://localhost:<port-back>
VITE_IMAGE_URL=VITE_IMAGE_URL=http://localhost:<port-back>/images
```

3 - Run 
```bash
npm install
```
And launch the server :
```bash
npm run dev
```

The project is installed. :tada:
