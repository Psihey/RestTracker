package com.restTracker.restTracker.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.restTracker.restTracker.model.JsonViewer;
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
    public ResponseEntity saveActivity(@RequestBody MessageModel messageModel) {
        if (messageService.saveActivity(messageModel)) {
            return ResponseEntity.ok(HttpStatus.OK);
        } else {
            return ResponseEntity.badRequest().body(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/message", method = RequestMethod.GET)
    @JsonView(JsonViewer.WithoutID.class)
    public List<MessageModel> getActivitiesByTypeAndDate(@RequestParam(defaultValue = "") String eventType,
                                                         @RequestParam(defaultValue = "") String start,
                                                         @RequestParam(defaultValue = "") String end) {
        return messageService.findActivity(eventType, start, end);
    }


    @Cacheable(value = "messageModel", key = "#eventType")
    @RequestMapping(value = "/message/{eventType}", method = RequestMethod.GET)
    @JsonView(JsonViewer.WithoutID.class)
    public List<MessageModel> getActivityByType(@PathVariable String eventType) {
        return messageService.findByActivityType(eventType);
    }

    @RequestMapping(value = "/mymessage", method = RequestMethod.GET)
    @JsonView(JsonViewer.WithoutID.class)
    public List<MessageModel> getActivityByUser() {
        return messageService.findByUser();
    }
}
