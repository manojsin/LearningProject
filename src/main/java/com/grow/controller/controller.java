package com.grow.controller;

import com.grow.Client.EmailClient;
import com.grow.beans.request.AddTransactionRequest;
import com.grow.beans.response.BaseResponse;
import com.grow.beans.response.GetGroupWiseAmount;
import com.grow.beans.response.GetTransactionStatement;
import com.grow.dao.MongoEntity;
import com.grow.repository.mongorepository.MongoDbAddUserGroupRepository;
import com.grow.service.SplitWiseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class controller {
    private SplitWiseServiceImpl splitWiseService;
    private MongoDbAddUserGroupRepository mongoDbAddUserGroupRepository;
    private EmailClient emailClient;
   @Autowired
    public controller(SplitWiseServiceImpl splitWiseService,
                      MongoDbAddUserGroupRepository mongoDbAddUserGroupRepository,
                      EmailClient emailClient){
       this.splitWiseService=splitWiseService;
       this.mongoDbAddUserGroupRepository=mongoDbAddUserGroupRepository;
       this.emailClient=emailClient;

    }
    @RequestMapping(value="/getTransactionDetails" , method = RequestMethod.GET)
    public @ResponseBody
    GetTransactionStatement getTransactionDetails(@RequestParam String uerEmail){
        return  splitWiseService.getTransactionStatement(uerEmail);
    }

    @RequestMapping(value="/addAmount" , method = RequestMethod.POST)
    public @ResponseBody BaseResponse addTransaction(@RequestBody AddTransactionRequest addTransactionRequest){
        MongoEntity mongoEntity=new MongoEntity();
        mongoEntity.setId(15);
        mongoEntity.setName("miral");
        mongoEntity.setLastName("modi");
        ////insert in mongodb
/*
        String to, String cc, String subject, String bodyText , String replyTo
*/
        emailClient.sendMail("manoj.singh@yatra.com",null,"testing","hello",null);
        //System.out.println("saving data : "+ mongoDbAddUserGroupRepository.save(mongoEntity));



       // System.out.println("find by name : "+ mongoDbAddUserGroupRepository.findByName("AKASH"));

       // System.out.println("find all : "+ mongoDbAddUserGroupRepository.findAll());

        return null;//splitWiseService.addTransactionInGroup(addTransactionRequest);
    }

    @RequestMapping(value="/getGroupWiseAmount" , method = RequestMethod.GET)
    public @ResponseBody
    GetGroupWiseAmount addTransaction(@RequestParam String uerEmail, @RequestParam String groupName){
        return splitWiseService.getGroupWiseAmount(uerEmail,groupName);
    }
}
