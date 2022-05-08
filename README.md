<p align="center">
  <a href="https://ethioclicks.com" rel="noopener" target="_blank"><img width="150" src="https://avatars.githubusercontent.com/u/84285742?v=4" alt="ethioclicks logo"></a>
</p>
<h1 align="center">ethio clicks</h1>
<h2 align="center"> Spring Boot Simple Users Profile Manager CRUD Web App </h2>

The main objective of the spring boot project in this repository is to create a fullstack web application for implementing and testing spring boot as a backend to implement a File Upload and Download functionality. 

In this project we are going to create a simple demo of user profile manager web application that enables to create , edit , delete and update user profile information, each and every user profile consists of profile picture as well as a video file,which is our main target for implementing and testing the file upload and download functionality.


### We Used

Development Machine:- Linux Operating System(debian based Linux OS). <br />
Spring Web <br />
Thymeleaf <br />

### 

To demonstrate file uploading, we'll be building a typical Spring MVC application which consists of a `Controller`, a `Service` for backend processing, and `Thymeleaf` for view rendering , [thymeleaf](https://www.thymeleaf.org/documentation.html) is a modern server-side Java template engine for both web and standalone environments.

The simplest way to start with a skeleton Spring Boot project, as always, is using Spring Initializer. Select your preferred version of Spring Boot and add the Web and Thymeleaf dependencies:

![spring starter](https://user-images.githubusercontent.com/88676535/167289063-22107abc-1997-4bab-9a8c-1e467076f6f3.png)

Spring provides a MultipartFile interface to handle HTTP multi-part requests for uploading files. Multipart-file requests break large files into smaller chunks which makes it efficient for file uploads. 

### Configure and Increase the Spring Boot File Upload Size Limit

![properties](https://user-images.githubusercontent.com/88676535/166115921-bbde902c-415c-40ed-ae3f-29b891c23676.png)

by default spring boot allows uploading only upto 1MB size of files via multipart form and the default for `spring.servlet.multipart.max-request-size` is 10MB. Increasing the limit for max-file-size is probably a good idea since the default is very low, but be careful not to set it too high, which could overload your server.


so inorder to enable our web application allow uploading larger files which are greater than 1MB we must modify some configurations and override the default multipart upload size limit inside the spring boot configuration file , every spring boot project may modify the configuration file differently because of so many options available out there to use external configuration for defining the spring boot project property but each of them will allow us to modify the default values.

here we will use the application file, the application file will help in this scenario since in the  `application.properties`  each line is a single configuration used to set and modify the default value, depending on the version of spring boot, add different configurations to the application file, the file which is found inside `src>main>resource`. <br /> nevigate to `src>main>resource` from your project directory and you will get the the application file.

According to Your spring boot version append the following line of codes to this file , as an example let's increase the upload limit upto 2GB.

you can set the limits in `KB`,`MB`,`GB`...etc
The default value for spring.servlet.multipart.max-file-size is 1MB and the default for spring.servlet.multipart.max-request-size is 10MB. Increasing the limit for max-file-size is probably a good idea since the default is very low, but be careful not to set it too high, which could overload your server.

#### Before Spring Boot 2.0:

```
spring.http.multipart.max-file-size=2048MB
spring.http.multipart.max-request-size=2048MB

```

#### For those using Spring Boot 2.0 and above (as of M1 release), the property names have changed to:

```

spring.servlet.multipart.max-file-size=2048MB
spring.servlet.multipart.max-request-size=2048MB

```

in this code carfully Note that the prefix have changed to `spring.servlet` instead of writing `spring.http` we have to use servlet after the dot.

#### There is also another option for those who prefer to use yaml configuration 

inside your application.yaml add the following line of code , this code will override the default limit for multipart file upload and enable it to accept uploading extra large size files. depending your goal you can set the value but here we will demonstrate by setting the upload limmit upto 2GB.


```

spring:
 servlet:
    multipart:
      max-file-size: 2048MB
      max-request-size: 2048MB
      
```      

### In this simple user profile manager project we have the following Spring Boot API endpoint's 


#### to Upload a new Users Profile Picture
http://localhost:8080/uploadImage

#### Endpoint to Download Profile Picture 
http://localhost:8080/userProfile/downloadPhoto/{id}

<br />
the above api endpoint is used to fetch and download user profile picture. it expects an id , the id is used to identify which users profile picture you want to download. by providing a correct and registered user id you can successfully download a picture of  the respective user. 


#### Upload a new Video in the Users Profile
http://localhost:8080/userProfile/uploadVideo

#### Download a Video the user have Uploaded
http://localhost:8080/userProfile/downloadVideo/{id}
<br />
the above api endpoint enables to fetch and download a video that a user has uploaded into their profile. it also expects an id as it is in the picture download api endpoint, the id is used to identify which users video you want to download. providing a correct and registered user id will allow you to download a video that coresponds with that user profile.

#### View Detail
http://localhost:8080/userProfile/viewDetail/{id}

#### Add a new User
http://localhost:8080/addNew

#### Edit and Update a user Profile
http://localhost:8080/userProfile/update/{id}

#### Delete User Profile
http://localhost:8080/userProfile/deleteProfile/{id}


### Screenshot
### Main Landing Page of the web app look

<hr>

![landingPage](https://user-images.githubusercontent.com/88676535/165318743-04a5a50f-36d4-4abd-becd-73446dd2a920.png)

<hr>


### Custom Exception



```java
  System.out.print("Hello world!")

```

### Add/Register a New Account

![add-new](https://user-images.githubusercontent.com/88676535/165318780-54981fac-eb54-4796-adee-1a9caac30341.png)

### Details Page after Clicking the Details icon (the eye icon) 
![details page](https://user-images.githubusercontent.com/88676535/165319032-52566eca-8912-4d7b-9502-4a50d60924d9.png)

### Edit User Pofile Information
![edit profile](https://user-images.githubusercontent.com/88676535/165319071-ecdf8117-755f-49d8-90a3-8958b1fb3db1.png)


### Simple File Manager to View files uploaded by each individual user
![file-manager](https://user-images.githubusercontent.com/88676535/165319116-cad1c83f-1a8d-4e0d-94c1-fa80e24b2d7b.png)

### Adding a New User 
![add new user](https://user-images.githubusercontent.com/88676535/165318865-ece777f4-fed7-46a3-aee8-1c719a7ec9a9.png)

### Upload Photo 
![uploaad profile picture](https://user-images.githubusercontent.com/88676535/165319172-d8a55af3-0650-4e4d-8f6e-101f7cbd9ab4.png)

### Landing Page After Adding a new user and uploading a Photo
![home-after-adding-user](https://user-images.githubusercontent.com/88676535/165318708-e4090f70-a457-489a-b53e-893410e09504.png)

### File Manager Tab After Adding a new User 
![filemgr-afteraddinguser](https://user-images.githubusercontent.com/88676535/165319143-7e9c7605-6e2a-48b3-9ab7-4dabd0526ea4.png)

### Go to Details page to upload a video
![details page](https://user-images.githubusercontent.com/88676535/165319032-52566eca-8912-4d7b-9502-4a50d60924d9.png)

### Uploading a Video
![upload and dowload video](https://user-images.githubusercontent.com/88676535/165319202-a2720c4d-be60-4347-b3f4-bed1d9db9c4f.png)
