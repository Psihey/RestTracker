package com.restTracker.restTracker.controller;

import com.restTracker.restTracker.model.MessageModel;
import com.restTracker.restTracker.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Controller
public class MessageController {
   @Autowired
    private MessageService messageService;

   @RequestMapping(value = "/message", method = RequestMethod.POST)
    public ResponseEntity saveActivity(@RequestBody MessageModel messageModel){
        if (messageService.saveActivity(messageModel)){
             return ResponseEntity.ok(HttpStatus.OK);
        }else {
            return ResponseEntity.badRequest().body(HttpStatus.BAD_REQUEST);
        }
   }



   @RequestMapping(value = "/message", method = RequestMethod.GET)
    public List<MessageModel> getActivitiesByType(@RequestParam(defaultValue = "none") String eventType,
                                                  @RequestParam(defaultValue = "none") String start,
                                                  @RequestParam(defaultValue = "none") String end){
        if (eventType.equals("none")){
            if (start.equals("none") || end.equals("none")){
                return messageService.findAll();
            }else return messageService.findByDate(start,end);

        }else if (start.equals("none") || end.equals("none")){
            return messageService.findByActivityType(eventType);
        }else
            return messageService.findByActivityAndDate(eventType,start,end);
        }


    @Cacheable(value = "messageModel", key = "#eventType")
    @RequestMapping(value = "/message/{eventType}", method = RequestMethod.GET)
    public List<MessageModel> getAllActivityByType(@PathVariable String eventType){
        return messageService.findByActivityType(eventType);
    }
}
