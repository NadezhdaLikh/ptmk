# PTMK Console Application

This is a console-based Java application for working with employee data using PostgreSQL.

## Running the Application

1. **Set up PostgreSQL**  
   - Create a database named `ptmk`.
   - Make sure PostgreSQL is running on `localhost:5432`.
   - Update `src/main/resources/application.yml` if your settings differ (url, username, password properties in spring.datasource).

2. **Build or Use Provided `.jar`**

   You can build the project yourself or use the provided jar file located at: target/ptmk-1.0.0.jar.

4. **Run the App with Command-Line Parameters**

   The app requires parameters to be passed when running, for example:

   ```bash
   java -jar target/ptmk-1.0.0.jar 1  # create Employee table
   java -jar target/ptmk-1.0.0.jar 2  # add a new employee
   java -jar target/ptmk-1.0.0.jar 3  # print all employees with a unique combination of last name and date of birth (also ordered by last name)
   java -jar target/ptmk-1.0.0.jar 4  # automatically add 1,000,100 employees (both male and female)
   java -jar target/ptmk-1.0.0.jar 5  # select all male employees with last name starting with F
