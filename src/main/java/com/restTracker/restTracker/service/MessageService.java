package com.restTracker.restTracker.service;

import com.restTracker.restTracker.model.MessageModel;
import com.restTracker.restTracker.persistence.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class MessageService {
    @Autowired
    private MessageRepository messageRepository;

    public List<MessageModel> findAll() {
        return messageRepository.findAll();
    }

    public boolean saveActivity(MessageModel messageModel) {
        messageModel.setTs(Instant.now().getEpochSecond());
        MessageModel savedModel = messageRepository.save(messageModel);
        return savedModel != null;
    }

    public List<MessageModel> findByActivityType(String eventType) {
        return messageRepository.findByEventType(eventType);
    }

    public List<MessageModel> findByActivityAndDate(String eventType, String start, String end) {
        long timeFrom = getDateFrom(start);
       return  messageRepository.findByEventTypeAndTsBetween(eventType, timeFrom, getDateTo(timeFrom, end));
    }

    public List<MessageModel> findByDate(String start, String end){
        long timeFrom = getDateFrom(start);
       return messageRepository.findByTsBetween(getDateFrom(start), getDateTo(timeFrom, end));
    }

    public List<MessageModel> findActivity(String eventType, String startPeriod, String endPeriod) {
        if (eventType.isEmpty()){
            if (startPeriod.isEmpty()){
                return findAll();
            }else return findByDate(startPeriod,endPeriod);

        }else if (startPeriod.isEmpty()){
            return findByActivityType(eventType);
        }else{
            return findByActivityAndDate(eventType,startPeriod,endPeriod);
        }
    }

    private long getDateFrom(String start){
        return start.startsWith("0") ? Instant.now().getEpochSecond() : (Instant.now().getEpochSecond() - convertEnd(start));
    }

    private long getDateTo(long timeFrom, String end){
        return  (end.startsWith("0")|| end.isEmpty()) ? Instant.now().getEpochSecond() : (timeFrom + convertEnd(end));
    }

    private long convertEnd(String date) {
        String dateEnd =date.substring(0, date.length()-1);
        if (date.endsWith("m")) {
           return Long.valueOf(dateEnd) * 60;
        } else if (date.endsWith("h")) {
           return   Long.valueOf(dateEnd) * 3600;
        } else if (date.endsWith("d")) {
           return   Long.valueOf(dateEnd) * 86400;
        }
        return 0;
    }

}
