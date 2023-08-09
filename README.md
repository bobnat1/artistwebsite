# DJ Website

A fully functional full stack website for Artists/DJs so they can interact with their fans, share new music, or post updates. 

Live website: <https://hvssr.net>

## Table of Contents

- How to demo
- Features
- Usage
- Technologies Used

# How to demo

To demo the website copy the repository to your local computer. Once copied to your computer load the project in an IDE (Preferably IntelliJJ).

Navigate to application.properties file in the resources folder. From there change lines 5-7 with your login info for your MySQL server.

```xml
spring.datasource.url=jdbc:mysql://IP-ADDRESS:PORT/capstone?createDatabaseIfNotExist=true
```
Replace **IP-ADDRESS:PORT** with your Server IP for remote or localhost for local servers and the port your service is running on.
```xml
spring.datasource.username=USERNAME
```
Replace **USERNAME** with your MySQL username
```xml
spring.datasource.password=PASSWORD
```
Finally, replace **PASSWORD** with your MySQL password

Once your applied the changes to connect to your database correctly, run the **ArtistWebsiteApplication** to automatically create a database and populate it on your server.

After the database is created, stop the program and navigate to the test folder.

In the test folder run the test class **InitialAppRunTest** so the method **setRolesInRepositoryTest()** will input ROLE_ADMIN and ROLE_USER into Database.

Then run **ArtistWebsiteApplication** and register with email and password. **Note:** The registration page can be accessed in the Login section of the website.

**Now you can log in as a basic user.**

To change account to admin to demo admin features, comment out **setRolesInRepositoryTest()** and uncomment **changeUserRoleTest()** then run **InitialAppRunTest** again.

**Now you can log in as an admin user.**

It's recommended to register another dummy account to demo the functionality of the basic user and admin user simultaneously.

# Features
- Send messages to admins/artist
- Download mixes or mp3 files posted by admins/artist
- Manage users as admin
- Manage mixes as admin
- Manage messages as admin
- Stay up to date on artist's the latest releases
- Stream artist's mixes provided by MixCloud

# Usage

Once you have a basic account, you can navigate to the Contact section to send a message to the artist or admin directly. Your email will be attached to the message so the admin/artist will follow up through email.

You can navigate to the Home section to logout or download music that the admin provides for at no cost. **Note:** the admin has to upload a mix for it to be viewable in the user home page.
Otherwise nothing will show in the homepage to download.

Navigating to Releases will redirect you to my BandCamp profile where tracks can be bought and downloaded.

Navigating to the Off The Top Mix will you can stream the latest mixes provided by MixCloud.

With an admin account, you can navigate to the Contact section, but you cannot send a message to yourself.

Instead of a Home section, when an authorized admin is logged in you will instead see an Admin section. 

Logging is written to a file called website.log in the current directory.

When navigating to the admin section you can select:
- Edit Mixes
- User Management
- Edit Messages

Within Edit Mixes, the admin can upload a mp3 file to the site, which will move the file to a specific directory and create a string with the path to the file. The string will save to a new mix object. If the directory is not present on the filesystem, it will create it and proceed.

Admins can also delete mix objects as well from the Edit Mixes page. 

**Note:** to demo these features use the mix provided in 
`/src/main/resources/static/audio/`<br> With this file you can upload it as an admin, then see it posted and/or download it in the basic user's home page.

Within User Management admins can delete users completely from the database as well as change their roles.

In the Edit Messages section, admins can view and delete the inquiry messages received from basic users.

On the home page, the Releases and Off The Top Mix sections function the same between admins and basic users.

# Technologies Used

**Front End:**
- HTML
- CSS
- JavaScript
- Thymeleaf

**Back End:**
- Java
- Spring Boot
- Spring Security
- MySQL
- JBDC

