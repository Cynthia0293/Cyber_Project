# Cyber_Project
The project of Cyber Security of Software Systems: 

We completed the basic requirements:
We developed a server application that selects whether to turn on the server side of the service and connects to the client
In addition to this we created a login screen allowing the client to choose whether to log in or register.
If the user chooses to register, the user needs to register with a unique username and a password that matches the password policies. In the case of successful registration, the message "Registration successful" should appear should appear. Otherwise, a corresponding error message prompt will be displayed.
If the user chooses to log in, the user may be able to verify their identity on the server and join the forum. In case of a successful login, the message "You have been authenticated, welcome <username> to the forum" should appear. However, if an incorrect username/password appears, the message 'Please enter the correct username password' prompt message.
  
We have also fulfilled two advanced requirements:
One is that we implemented GUIs for various pages, such as login page, registration page, server page, etc.
Second, we specify the password policy. We specify that: Password must be a combination of 8 or more digits containing uppercase letters, lowercase letters, numbers, special symbols ( characters other than letters, numbers, underscores, Chinese characters). And the password entered twice during registration must be the same. If there is a problem, an error message will pop up and the user will be asked to re-enter the registration information.

We store the user's username and password in the pgSQL database, and operations such as inserting and querying new users use SQL statements
