package com.techelevator.controller;


import com.techelevator.dao.GymSessionDao;
import com.techelevator.model.Equipment;
import com.techelevator.model.GymSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@CrossOrigin
@RequestMapping("/session")
@RestController
public class GymSessionController {


    @Autowired
    private GymSessionDao gymSessionDao;


    @RequestMapping(path = "/{sessionId}", method = RequestMethod.GET)
    public GymSession getGymSessionBySessionId(@PathVariable int sessionId) {
        return gymSessionDao.getGymSessionBySessionId(sessionId);
    }


    @RequestMapping(path = "/user/{userId}", method = RequestMethod.GET)
    public List<GymSession> getGymSessionByUserId(@PathVariable int userId) {
        return gymSessionDao.getGymSessionByUserId(userId);
    }


    @RequestMapping(path = "/list", method = RequestMethod.GET)
    public List<GymSession> getAllGymSessions() {
        return gymSessionDao.getAllGymSessions();
    }


    @RequestMapping(path = "add/{sessionId}", method = RequestMethod.POST)
    public GymSession addNewGymSession(@RequestBody GymSession gymSession, @PathVariable int sessionId) {
        return gymSessionDao.addNewGymSession(gymSession);
    }
}

