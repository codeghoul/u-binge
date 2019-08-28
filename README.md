# U-Binge
A Food Delivery Service api created using Spring Rest, JPA/Hibernate and MySQL as part of final assessment at GlobalLogic.

## Installation
- Set up MySQL.
- Create database {databasename}.
- Update in spring.properties file.
  - spring.datasource.url=jdbc:mysql://localhost:{db port}/{your db name}?useSSL=false
  - spring.datasource.username={username}
  - spring.datasource.password={password}
- Use Postman Collection to run.
  - in Authorization tab select Basic from drop down and give admin as username & password to get admin rights.
- Add Roles in localhost:8080/admin/roles : 
  - OWNER
  - RESTRO
  - CUSTOMER
  - DELIVERY

## Accepted Requests
[Link to Postman Collection](https://www.getpostman.com/collections/1ec82c41ab9908ea3bef)\
[![Run in Postman](https://run.pstmn.io/button.svg)](https://app.getpostman.com/run-collection/1ec82c41ab9908ea3bef)

## Entity Relationship Diagram
[Link to Lucid Chart ERD](https://www.lucidchart.com/invitations/accept/717b2a94-a3b8-44b6-b556-0c4b482b931c)\
[Link to PDF](https://github.com/codeghoul/u-binge/blob/master/U-Binge%20ERD.pdf)

## Use Case
[Link to Lucid Chart Use Case Diagram](https://www.lucidchart.com/invitations/accept/a120c107-5a76-48b7-ae19-04cf8efb6c91)\
[Link to PDF](https://github.com/codeghoul/u-binge/blob/master/U-Binge%20Use%20Case.pdf)

### This system should handle all the scenarios stated below
1. List the restraunts and the food items they offer.
2. Allow the user to place an order.
3. The order should be assigned to a delivery guy.
4. The user has the option to pay using card, cash on delivery etc.
5. The user can cancel the order.
6. The user should not be allowed to add/edit/update/delete restraunts or food items.
7. The system should allow a restraunt owner to add his/her restraunt in the system.
