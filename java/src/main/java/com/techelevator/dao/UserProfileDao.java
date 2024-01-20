package com.techelevator.dao;

import com.techelevator.model.UserProfile;

public interface UserProfileDao {

    UserProfile createUserProfile(UserProfile newProfile, int userId);

    UserProfile getProfileByProfileId(int id);

    UserProfile getProfileByUserId(int id);

    UserProfile editUserProfile(UserProfile modifiedProfile, int profileId);

    int getProfileIdByUserId(int userId);
}
