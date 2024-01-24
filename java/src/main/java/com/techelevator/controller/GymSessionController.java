package com.techelevator.controller;


import com.techelevator.dao.GymSessionDao;
import com.techelevator.model.Equipment;
import com.techelevator.model.GymSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@CrossOrigin(origins = "http://127.0.0.1:5173")
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


    @RequestMapping(path = "/add", method = RequestMethod.POST)
    public GymSession addNewGymSession(@RequestBody GymSession gymSession) {
        return gymSessionDao.addNewGymSession(gymSession);
    }

    @RequestMapping(path="/endSession/{gymSessionId}", method = RequestMethod.PUT)
    public GymSession addDuration(@PathVariable int gymSessionId, @RequestBody Integer duration) {
        return gymSessionDao.addDuration(gymSessionId, duration);
    }

}

