# Blog Application

## Table of Content

- Functionalities
- Tools and Technologies
- Features
- Requirements
- Connections
- Necessary Instructions

  `  Functionalities `
  
   - Admin
     1. Create the post
     2. Delete the post
     3. Update the post
     4. Get all Post By Id
     5. Get Post By Id
     6. Create the comment
     7. Delete the comment
     8. Get all the comments on a post
     9. Create a Category
     10. Update a Category
     11. Delete a Category
     12. Get all Categories
         
  - User
     1. Get all Post By Id
     2. Get Post By Id
     3. Create a Comment
     4. Delete a Comment
     5. Get all Post By Category
   
    `Tools and Technologies`

       - Intellij
       - Postman

    `Features`

    1. Admin is only authorized to create the post and update and delete the post depending on the category. Admin will also be authorized to create a new Category and update and delte the category. Admin can also manipulate the comment and create and delete the comment.
    2. User is authorized to create and update and delete the comment . User can only get all the post by categories and comment on them. Use can check other users comment as well.
    3. When a new person is registered through the application he will only be registered as a user and hence there will be only one admin.
    4. Session has been generated and hence when admin or user enter the id password he will be remain present inside the session.
   
    `Requirement`

     1. IntelliJ: Make Sure you have latest version of Intellij installed on your machine. [TnelliJ IDEA](https://www.jetbrains.com/idea/)
     2. Postman: Make sure you have a latest version of postman installed on your machine. [Postman](https://www.postman.com/downloads/)
     3. MySQL: You will need a database connection to store and retrieve the data.And hence make sure you have MySQL workbench installed on your machine. [MySql](https://dev.mysql.com/downloads/workbench/)
        #### Make sure you use versions compatible with your machine.

    `Connections`
    
    #### Go to application.properties file inside the resources folder and make the necessary changes for establishing connection with your database
         spring.datasource.url = jdbc:mysql://localhost:3306/YOUR_DATABASE_NAME
         spring.datasource.username = YOUR_DATABASE_USERNAME
         spring.datasource.password = YOUR_DATABASE_PASSWORD

    #### Make sure the connection established Succesfully. you can check the output on postman
    #### If the project run Sucessfully You can check all the Endpoints and Schema of project on [Swagger](http://localhost:8080/swagger-ui/index.html#/)

    `Necessary Instructions`
    
    #### 1. Make sure your connection is running on port 8080 and if not make the necessary changes as per your port no.
    #### 2. Admin data you have to insert manually inside your database as every individual is registered a admin. Username:admin || emailId:admin@gmail.com || password:$2a$10$NCiGYtuZCAJFrfCPLQBdce6vV97nNFPHDGPt0wnPwp7SrvrswkpbW
    #### 3. The password is an encypted password and hence if you want to change and hence use encrypted password to change it.
    #### 4. Database Samples
    <img width="559" alt="Post" src="https://github.com/anshu0007singh/Blog/assets/67259788/36b4994c-f651-4be2-91db-e78d1ca6c85f">
    <img width="622" alt="Comments" src="https://github.com/anshu0007singh/Blog/assets/67259788/f3078894-d9c5-4c89-8ab6-c440488cc7e4">
    <img width="565" alt="User" src="https://github.com/anshu0007singh/Blog/assets/67259788/52d1f034-72bb-4f9d-a1d3-f913433abcbd">
    <img width="563" alt="Role" src="https://github.com/anshu0007singh/Blog/assets/67259788/5608ab72-6bb8-491f-9b8e-dd86529394fa">
    <img width="563" alt="User-Role" src="https://github.com/anshu0007singh/Blog/assets/67259788/b78a9266-8cce-4c2e-ad9a-5c082a17e289">

