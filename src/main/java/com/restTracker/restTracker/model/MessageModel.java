package com.restTracker.restTracker.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.restTracker.restTracker.model.jwt.JwtRequest;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Map;

@Document (collection = "restTracker")
@TypeAlias("message")
public class MessageModel implements Serializable {
   @Id
   @JsonView(JsonViewer.FullMessageMode.class)
   private String id;
    @JsonView(JsonViewer.WithoutID.class)
   private String eventType;
    @JsonView(JsonViewer.WithoutID.class)
   private long ts;
    @JsonView(JsonViewer.WithoutID.class)
   private Map<String,String> params;
    @DBRef(db="usr")
    private JwtRequest user;

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

    public JwtRequest getUser() {
        return user;
    }

    public void setUser(JwtRequest user) {
        this.user = user;
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
