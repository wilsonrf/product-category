# Runing the Product and Category application

## Authentication

Use the HTTP Basic authentication:

**User**: user
**Password**: user
<br/>
**User**: super
**Password**: super

## Running localhost, using a local mariadb and a wiremock of openexchangerate.org API

### RUN mariadb
```
docker run --name mariadb -e MYSQL_ROOT_PASSWORD=B4kJqnjVc4FLavFT -e MYSQL_DATABASE=product_category -e MYSQL_USER=product_category -e MYSQL_PASSWORD=QddSHQC5hzZ2x8e9 -d mariadb:10
```
### RUN local wiremocks on port 9090
```
gradle startWiremocks
```
### RUN the application

```
gradle bootRun -Dspring.profiles.active=default
```

## Running on production, using a local mariadb

### RUN mariadb
```
docker run --name mariadb -e MYSQL_ROOT_PASSWORD=B4kJqnjVc4FLavFT -e MYSQL_DATABASE=product_category -e MYSQL_USER=product_category -e MYSQL_PASSWORD=QddSHQC5hzZ2x8e9 -d mariadb:10
```
### RUN the application
#### Choose the client
You ***must*** choose the external currency converter client:
<br/>
##### Run with Fixer
```
gradle bootRun -Dspring.profiles.active=production -Dclient=fixer
```
##### Run with Open Exchange Rate
```
gradle bootRun -Dspring.profiles.active=production -Dclient=oxr
```