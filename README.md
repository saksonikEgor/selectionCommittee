# SelectionCommittee is application built with Spring Boot
 
**Prerequisites: Maven, MySQL, Thymeleaf, Hibernate, Spring Data JPA**

## Getting Started

**1. Clone the application**

```bash
git clone https://github.com/saksonikEgor/selectionCommittee.git
```

**2. Change MySQL username and password**

+ Open `src/main/resources/application.properties`.
+ Change `spring.datasource.username` and `spring.datasource.password` as per your MySQL installation.

Or set your MySQL root password to "root" according `application.properties`.

**3. Сreate a database**

```bash
create database SelectionCommitteeDataBase;
```

**4. Populate the database with records**

+ Execute insert queries at src/main/resources/db/SelectionCommittee.sql

**5. Run the server using Maven**

To run the server, cd into the `selectionCommittee` folder and run:
 
```bash
mvn spring-boot:run
```

**6. Open the start page**

Go to `localhost:8080`.
