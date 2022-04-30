<p align="center">
  <a href="https://ethioclicks.com" rel="noopener" target="_blank"><img width="150" src="https://avatars.githubusercontent.com/u/84285742?v=4" alt="ethioclicks logo"></a>
</p>
<h1 align="center">ethio clicks</h1>
<h2 align="center"> Spring Boot Users Profile Manager Simple CRUD Web App </h2>

The main objective of the spring boot project in this repository is to create a fullstack web application for implementing and testing spring boot as a backend to implement a File Upload and Download functionality by using the file system inside our backend server. 

in this project we are going to create a user profile manager web application that enables us to create , edit , delete and update user profile information and, each and every user profile consists of profile picture as well as a video file for each and every individual user profile we have in our profiles list.



### Spring Boot multipartFile upload file size limit

![properties](https://user-images.githubusercontent.com/88676535/166115921-bbde902c-415c-40ed-ae3f-29b891c23676.png)

on the bove screenshot the first two line of code show how we can configure and change the default value of file upload size limmit in multipartFile upload form we have when we upload image and video files 

```

spring.servlet.multipart.max-file-size=6144MB
spring.servlet.multipart.max-request-size=6144MB


```

![upload size limit](https://user-images.githubusercontent.com/88676535/166115837-fc89059d-1c63-4720-8f05-210fce6b4470.png)


### All of our Spring Boot API Endpoints are listed bellow


#### Upload a new Users Profile Picture
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
the above api endpoint enables to fetch and download a video that a user has uploaded into their profile. it also expects an id as it is in the picture download api endpoint, the id is used to identify which users video you want to download. providing a correct and registered user id will allow you to download a video that coresponds with that user profile account.

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
