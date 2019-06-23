package com.restTracker.restTracker.persistence;

import com.restTracker.restTracker.model.MessageModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends MongoRepository<MessageModel, String>{
    List<MessageModel> findByEventType(String eventType);

    List<MessageModel> findByEventTypeAndTsBetween(String eventType, long dateStart, long dateEnd);

    List<MessageModel> findByTsBetween(long dateStart, long dateEnd);
}

