package com.rohit.learning.limitsservice.restcontroller;

import com.rohit.learning.limitsservice.configuration.LimitsConfiguration;
import com.rohit.learning.limitsservice.model.Limit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SuppressWarnings("unused")
public class LimitsRestController {

    @Autowired
    private LimitsConfiguration limitsConfiguration;

    @GetMapping(path = "/limits")
    public Limit getLimits(){
        return new Limit(limitsConfiguration.getMin(),limitsConfiguration.getMax());
    }

}
