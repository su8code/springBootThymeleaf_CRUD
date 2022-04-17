package com.ethioclicks.userProfileCrud.controller;

import com.ethioclicks.userProfileCrud.model.UserProfile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@Controller
public class UserProfileController {
    public static List<UserProfile> getProfiles() {
        return profiles;
    }

    private static List<UserProfile> profiles = new ArrayList<>();
    private static boolean isInitialized = false;
    public static int numberOfProfiles = 0;
    private static String profilePic = "http://localhost:8080/profile/user.png/";
    public static String baseUrl = "http://localhost:8080/";

    private static List<UserProfile> init() {

        profiles.add(new UserProfile( 1,"Amanuel Tadesse", baseUrl+"profile/Amanuel Tadesse/aman.jpg" , 6 ,"male"));
        profiles.add(new UserProfile( 2,"Fasika Zewdie", baseUrl+"profile/Fasika Zewdie/fasika.jpeg" , 6 , "male"));
       return profiles;
    }
    private static List<UserProfile> addNewProfile(UserProfile newProfile) {
        if(newProfile != null)
        profiles.add(newProfile);
        return profiles;
    }
    private static List<UserProfile> updateProfile( int userId , UserProfile updateUser){
        int index =0;
        for( int i = 0; i < profiles.size() ;  i++)
        {
            if(profiles.get(i).getUserId() == userId){
                index = i;
            }
        }
        profiles.set(index , updateUser);
        return profiles;
    }

    @GetMapping("/")
    public String getAllProfiles(Model model){
        if(profiles.isEmpty() && !isInitialized){
            init();
            isInitialized = true;
            numberOfProfiles += 3;
        }
        model.addAttribute("allProfiles" , profiles );
        return "index";
    }

    @GetMapping("/userProfile/viewDetail/{id}")
    public String viewDetailPage(@PathVariable int id , Model model){

        int index = 0;
        for( int i = 0; i < profiles.size() ;  i++)
        {
            if(profiles.get(i).getUserId() == id){
               index = i;
            }
        }

        model.addAttribute("user",profiles.get(index));
        return "viewDetail";
    }

    @GetMapping("addNew")
    public String addNew(Model model){
        model.addAttribute("userId" , numberOfProfiles);
        model.addAttribute("profilePic", profilePic);

        return "addNew";
    }

    @PostMapping( value= "addNewProfile" )
    public String addProfile(@ModelAttribute UserProfile newProfile , Model model){

        numberOfProfiles += 1;
        // reset the default image back for a new user in the future
        profilePic = "http://localhost:8080/profile/user.png";
        addNewProfile(newProfile);
        return "redirect:/";
    }
   @GetMapping("/userProfile/deleteProfile/{id}")
    public String deleteProfile(@PathVariable int id , Model model){
        deleteProfile(id);
        return "redirect:/";

   }

   private void deleteProfile(int id) {
        int i = 0;
        for( i = 0; i < profiles.size() ;  i++)
        {
            if(profiles.get(i).getUserId() == id)
            {
                System.out.println("Removing: "+profiles.get(i).getUserName()+" age: "+profiles.get(i).getAge());
                profiles.remove(i);
                System.out.println("Removed Successfully");
            }
        }
    }


    // edit and update user profile
    @GetMapping("/userProfile/edit/{id}")
    public String editProfile(Model model , @PathVariable int id){
        int index =0;
        for( int i = 0; i < profiles.size() ;  i++)
        {
            if(profiles.get(i).getUserId() == id){
                index = i;
            }
        }
        model.addAttribute("title" , "Edit "+profiles.get(index).getUserName()+"'s Profile info");
        model.addAttribute("user", profiles.get(index));
        Boolean isMale = false;
        if(profiles.get(index).getGender().equals("male")){
            isMale= true;
        }
        model.addAttribute("isMale" , isMale);
        model.addAttribute("isFemale" , !isMale);

        return "editProfile";
    }

    @PostMapping("/userProfile/update/{id}")
    public String updateProfile(@ModelAttribute UserProfile updatedUser  , @PathVariable int id){

       System.out.println("Gender: "+updatedUser.getGender());
       updateProfile(id , updatedUser);
       return "redirect:/";
    }
}