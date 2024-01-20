package com.techelevator.controller;

import com.techelevator.dao.UserProfileDao;
import com.techelevator.model.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RequestMapping("/profile")
@RestController
public class UserProfileController {

    @Autowired
    private UserProfileDao userProfileDao;
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path="/create/{userId}", method= RequestMethod.POST)
    public UserProfile createUserProfile(@RequestBody UserProfile userProfile, @PathVariable int userId) {
        return userProfileDao.createUserProfile(userProfile, userId);
    }

    @RequestMapping(path="/{profileId}", method= RequestMethod.GET)
    public UserProfile getProfileByProfileId(@PathVariable int profileId) {
        return userProfileDao.getProfileByProfileId(profileId);
    }

    @RequestMapping(path="/member/{userId}", method= RequestMethod.GET)
    public UserProfile getProfileByUserId(@PathVariable int userId) {
        return userProfileDao.getProfileByUserId(userId);
    }

    @RequestMapping(path="/{profileId}", method= RequestMethod.PUT)
    public UserProfile editUserProfile(@RequestBody UserProfile modifiedProfile, @PathVariable int profileId) {
        return userProfileDao.editUserProfile(modifiedProfile, profileId);
    }

    @RequestMapping(path="/profileIdOfUser{userId}", method=RequestMethod.GET)
    public int getProfileIdByUserId(@PathVariable int userId) {
        return userProfileDao.getProfileIdByUserId(userId);
    }

}
