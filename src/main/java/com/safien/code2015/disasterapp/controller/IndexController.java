package com.safien.code2015.disasterapp.controller;

import com.safien.code2015.disasterapp.entity.CounterEntity;
import com.safien.code2015.disasterapp.entity.DataEntity;
import com.safien.code2015.disasterapp.entity.UserEntity;
import com.safien.code2015.disasterapp.service.CounterRepository;
import com.safien.code2015.disasterapp.service.DataEntityRepository;
import com.safien.code2015.disasterapp.service.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by salman on 2014-11-15.
 */
@RestController
public class IndexController {

    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private DataEntityRepository dataRepository;
    @Autowired
    private CounterRepository counterRepository;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "Hello World!";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST, headers = "Accept=application/json")
    public void register(@RequestBody UserEntity user) {

        userRepository.save(user);
        logger.info("User {} updated", user.getUsername());
    }

    @RequestMapping(value = "/login/{username}:{password}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public UserEntity login (@PathVariable("username")String username, @PathVariable("password")String password) {

        logger.info("Find user by user={}, password={}", username, password);
        UserEntity user = userRepository.findByUsernameAndPassword(username, password);

        return user;
    }

    @RequestMapping(value = "/{username}/getdata", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public List<DataEntity> getUserData(@PathVariable("username") String username) {

        logger.info("Find user data by user={}", username);
        List<DataEntity> data = dataRepository.findByUsername(username);

        return data;
    }

    @RequestMapping(value = "/savedata", method = RequestMethod.POST, headers = "Accept=application/json")
    public void register(@RequestBody DataEntity data) {

        dataRepository.save(data);
        logger.info("Data saved for user {}", data.getUsername());
    }

    @RequestMapping(value = "/savecounters", method = RequestMethod.POST, headers = "Accept=application/json")
    public void saveCounter(@RequestBody CounterEntity counterEntity) {

        logger.info(counterEntity.toString());
        counterRepository.save(counterEntity);
        logger.info("Counter data saved for user {}", counterEntity.getUsername());
    }

    @RequestMapping(value = "/getcounters/{username}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public List<CounterEntity> getCounters(@PathVariable("username") String username) {

        logger.info("Find counters by user={}", username);
        List<CounterEntity> data = counterRepository.findByUsername(username);

        return data;
    }

}
