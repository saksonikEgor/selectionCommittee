# SelectionCommittee is application built with Spring Boot
 
**Prerequisites: Maven, MySQL, Thymeleaf, Hibernate, Spring Framework, Flyway**

## Getting Started

**1. Clone the application**

```bash
git clone https://github.com/saksonikEgor/selectionCommittee.git
```

**2. Start MySQL server**

**3. Change MySQL username and password**

+ Open `src/main/resources/application.properties`.
+ Change `spring.datasource.username` and `spring.datasource.password` as per your MySQL installation.

Or set your MySQL root password to "root" according `application.properties`.

**4. Сreate a database**

```bash
CREATE DATABASE SelectionCommitteeDataBase;
```

**5. Run the server using Maven**

To run the server, cd into the `selectionCommittee` folder and run:
 
```bash
mvn spring-boot:run
```

**6. Open the start page**

Go to `localhost:8080`.
