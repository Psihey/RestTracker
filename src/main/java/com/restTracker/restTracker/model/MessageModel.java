package com.restTracker.restTracker.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.util.Map;

@Document (collection = "restTracker")
public class MessageModel implements Serializable {
   @Id
   private String id;
   private String eventType;
   private long ts;
   private Map<String,String> params;

    public MessageModel(String eventType, long ts) {
        this.eventType = eventType;
        this.ts = ts;
    }

    public MessageModel() {
    }

    public Map<String, String> getParams() {
        return params;
    }

    public void setParams(Map<String, String> params) {
        this.params = params;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public long getTs() {
        return ts;
    }

    public void setTs(long ts) {
        this.ts = ts;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "MessageModel{" +
                "id='" + id + '\'' +
                ", eventType='" + eventType + '\'' +
                ", ts=" + ts +
                ", params=" + params +
                '}';
    }
}
