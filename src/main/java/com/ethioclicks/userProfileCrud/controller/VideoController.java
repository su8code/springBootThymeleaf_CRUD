package com.ethioclicks.userProfileCrud.controller;

import com.ethioclicks.userProfileCrud.services.ImageStorage;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.IOException;

import static com.ethioclicks.userProfileCrud.controller.UserProfileController.baseUrl;


@Controller
public class VideoController {


    @PostMapping("/userProfile/uploadVideo")
    public String uploadVideo(@RequestParam( name = "userId" , required = false) int userId  , @RequestParam(name="userVideo") MultipartFile multipartFile) throws IOException {
        int index =0;
        for( int i = 0; i < UserProfileController.getProfiles().size() ;  i++)
        {
            if(UserProfileController.getProfiles().get(i).getUserId() == userId){
                index = i;
            }
        }

        String name = UserProfileController.getProfiles().get(index).getUserName();
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        System.out.println("File Name: "+fileName);
        String uploadDir = "user-photos/"+name+"/video";
        ImageStorage.saveFile(uploadDir, fileName, multipartFile);


        String url = baseUrl+"profile/"+name+"/video/"+fileName;
        UserProfileController.getProfiles().get(index).setUserVid(url);
        System.out.println("Generated Video Link: ' "+url+" '");

        return "redirect:/userProfile/viewDetail/"+userId;
    }


    @RequestMapping("/userProfile/downloadVideo/{id}")
    @ResponseBody

    public void downloadVideo(@PathVariable int id , HttpServletResponse response) throws IOException {
        int index =0;
        String userDir;
        String fileName;

        for( int i = 0; i < UserProfileController.getProfiles().size() ;  i++)
        {
            if(UserProfileController.getProfiles().get(i).getUserId() == id)
            {
                index = i;
            }
        }

        if(UserProfileController.getProfiles().get(index).getUserVid().endsWith("defaultVideo.mp4")){
            userDir = "user-photos/";
            fileName = "defaultVideo.mp4";
        }else{
            String str = UserProfileController.getProfiles().get(index).getUserVid();
            str = str.replace("http://localhost:8080/profile/" , "");
            userDir = str.split("/")[0];

            userDir = "user-photos/"+userDir+"/video";
            System.out.println("Image Directory: "+userDir);

            fileName = str.split("/")[2];
            System.out.println("FileName: "+fileName);
        }

        if(index == 0 && id != 1){
            userDir = "user-photos/";
            fileName = "defaultVideo.mp4";
        }

        Resource res = ImageStorage.loadProfilePic(userDir , fileName);
        response.setHeader("Content-Disposition", "attachment; filename=" +fileName);
        response.setHeader("Content-Transfer-Encoding", "binary");
        try{

            BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());
            FileInputStream fis = new FileInputStream(res.getFile());
            int len;
            byte[] buf = new byte[1024];

            while((len = fis.read(buf)) > 0) {
                bos.write(buf,0,len);
            }

            bos.close();
            response.flushBuffer();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    @GetMapping("/fileManager")
    public String getFileManager(Model model){
        model.addAttribute("allProfiles" , UserProfileController.getProfiles());
        return "FileManager";
    }
}
