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
import static com.ethioclicks.userProfileCrud.controller.UserProfileController.numberOfProfiles;


@Controller
public class ImageController {

    @PostMapping("/uploadImage")
    public String uploadImage(@RequestParam( name = "name" , required = false) String name , Model model , @RequestParam("image") MultipartFile multipartFile) throws IOException {

        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        System.out.println("File Name: "+fileName);
        String uploadDir = "user-photos/"+name;
        ImageStorage.saveFile(uploadDir, fileName, multipartFile);

        model.addAttribute("userId" , numberOfProfiles);
        model.addAttribute("profilePic", baseUrl+"profile/"+name+"/"+fileName);
        model.addAttribute("name" , name);

        return "addNew";
    }

    @RequestMapping("/userProfile/downloadPhoto/{id}")
    @ResponseBody

    public void downloadPic(@PathVariable int id , HttpServletResponse response) throws IOException {
        int index =0;

        for( int i = 0; i < UserProfileController.getProfiles().size() ;  i++)
        {
            if(UserProfileController.getProfiles().get(i).getUserId() == id)
            {
                index = i;
            }
        }

        String str = UserProfileController.getProfiles().get(index).getProfilePic();
        str = str.replace("http://localhost:8080/profile/" , "");

        String imageDir = str.split("/")[0];

        imageDir = "user-photos/"+imageDir;
        System.out.println("Image Directory: "+imageDir);

        String fileName = str.split("/")[1];
        System.out.println("FileName: "+fileName);

        if(index == 0 && id != 1){
            imageDir = "user-photos/";
            fileName = "user.png";
        }
        Resource res = ImageStorage.loadProfilePic(imageDir , fileName);


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
}