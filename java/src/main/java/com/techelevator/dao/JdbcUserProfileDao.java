package com.techelevator.dao;


import com.techelevator.exception.DaoException;
import com.techelevator.model.User;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import com.techelevator.model.UserProfile;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class JdbcUserProfileDao implements UserProfileDao {


    private final JdbcTemplate jdbcTemplate;

    public JdbcUserProfileDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public UserProfile createUserProfile(UserProfile profile, int userId) {
        UserProfile newProfile = null;
        String insertProfileSql = "INSERT INTO user_profile (user_id, name, email, photo_url, age, height, weight, experience_level) values (?, ?, ?, ?, ?, ?, ?, ?) RETURNING profile_id";
        try {
            int newProfileId = jdbcTemplate.queryForObject(insertProfileSql, int.class, userId, profile.getName(), profile.getEmail(), profile.getPhoto_url(), profile.getAge(), profile.getHeight(), profile.getWeight(), profile.getExperienceLevel());
            newProfile = getProfileByProfileId(newProfileId);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return newProfile;
    }

    @Override
    public UserProfile getProfileByProfileId(int id) {
        UserProfile profile = null;
        String sql = "SELECT * FROM user_profile WHERE profile_id = ?";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
            if (results.next()) {
                profile = mapRowToProfile(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return profile;
    }

    @Override
    public UserProfile getProfileByUserId(int id) {
        UserProfile profile = null;
        String sql = "SELECT * FROM user_profile WHERE user_id = ?";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
            if (results.next()) {
                profile = mapRowToProfile(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return profile;
    }

    @Override
    public UserProfile editUserProfile(UserProfile modifiedProfile, int profileId) {
        UserProfile updatedProfile = null;
        String updateProfileSql = "UPDATE user_profile SET name=?, email=?, photo_url=?, age=?, height=?, weight=?, experience_level=? WHERE profile_id=?";
        try {
            jdbcTemplate.update(updateProfileSql, modifiedProfile.getName(), modifiedProfile.getEmail(), modifiedProfile.getPhoto_url(), modifiedProfile.getAge(), modifiedProfile.getHeight(), modifiedProfile.getWeight(), modifiedProfile.getExperienceLevel(), profileId);
            updatedProfile = getProfileByProfileId(profileId);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return updatedProfile;
    }

    private UserProfile mapRowToProfile(SqlRowSet rs) {
        UserProfile userProfile = new UserProfile();
        userProfile.setProfileId(rs.getInt("profile_id"));
        userProfile.setName(rs.getString("name"));
        userProfile.setEmail(rs.getString("email"));
        userProfile.setPhoto_url(rs.getString("photo_url"));
        userProfile.setAge(rs.getInt("age"));
        userProfile.setHeight(rs.getDouble("height"));
        userProfile.setWeight(rs.getDouble("weight"));
        userProfile.setExperienceLevel(rs.getString("experience_level"));
        return userProfile;
    }

}
