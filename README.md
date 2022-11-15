[![Open in Visual Studio Code](https://classroom.github.com/assets/open-in-vscode-c66648af7eb3fe8bc4f294546bfd86ef473780cde1dea487d3c4ff354943c9ae.svg)](https://classroom.github.com/online_ide?assignment_repo_id=7808800&assignment_repo_type=AssignmentRepo)
# spring boot exercise - Internet Programming Course

Book store website written in Java using Spring library, final project of an Internet Programming course.
To run, start a SQL server in XAMPP in port 3306. Open project in IntelliJ and run. 
## Authors
Student name - student email.

Shaked Stossel - shakedst@edu.hac.ac.il

Barak Saadon - baraksa@edu.hac.ac.il

## General Information
this app is a website of a book store called 'book shelf store'
the app can be accessed by all, except for the "admin section" which can only be accessed by admin.
for purchases it is required to log in as either: admin or user1/user2/user3.

the site's main page presents 5 books with the top 5 highest discounts available.

a customer may add to his cart his desire books. to find specific book one may use the search bar.

navigate to your shopping cart to view it, change quantity of books to purchase
and pay (requires login as user or admin) for everything in the cart.
note that there is no money-back-guarantee and you will not recieve any book because this is a homework exercise.


admin may navigate to admin section at localhost8080/admin and authenticate as admin
to view the payments log or edit the products and stock of the store.

implemented custom error pages.

## login info:
username: admin password: password role: admin
username: user1 password: user role: user
username: user2 password: user role: user
username: user3 password: user role: user

## run configuration
![image](https://user-images.githubusercontent.com/77246098/174443649-b10957d5-9767-4b64-be75-8a79fdeb06ba.png)

## functionallity
# admin:
- view the pay log
- view and edit the items book stock

# anonymous
- view book on discount
- perform a search by book's name
- add books to cart
- edit books in cart
- pay for the items in the cart.
- log in / logout as user1/user2/user3/admin

# customer:
- pay for the items in the cart.


### libraries used for this project:
spring boot jpa
spring boot security
thymeleaf
bootstrap

## known bugs


