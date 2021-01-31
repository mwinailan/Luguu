package com.projects.luguu.modules;

import com.google.rpc.Help;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

public class HelpPost {
    private UUID posterID;
    private String subject;
    private Date starttime;
    private String description;
    private String location;
    private UUID postid;
    private boolean isAccepted;

    public HelpPost(){
        this.postid = null;
        this.subject = null;
        this.starttime = null;
        this.description = null;
        this.location = null;

    }

    public HelpPost(Account poster, String subject, String description, Date starttime, String location) {
        this.posterID = poster.getUUID();
        this.subject = subject;
        this.description = description;
        this.location = location;
        this.starttime = starttime;

        this.postid = UUID.randomUUID();
    }

    public static HelpPost parseFromMapData(Map<String, Object> mapData){
        HelpPost output = new HelpPost();
        output.setSubject(mapData.get("subject").toString());
        output.setDescription(mapData.get("description").toString());
        output.setLocation(mapData.get("location").toString());
        output.setPosterID(UUID.fromString(mapData.get("poster").toString()));
        output.setPostid(UUID.fromString(mapData.get("postid").toString()));
        output.setStarttime(new Date(mapData.get("starttime").toString()));

        return output;
    }

    public boolean isAccepted() {
        return isAccepted;
    }

    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setAccepted(boolean accepted) {
        isAccepted = accepted;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPosterID(UUID posterID) {
        this.posterID = posterID;
    }

    public void setPostid(UUID postid) {
        this.postid = postid;
    }

    public String getLocation() {
        return location;
    }

    public String getPoster() {
        return posterID.toString();
    }

    public String getStarttime() {
        return starttime.toString();
    }

    public String getSubject() {
        return subject;
    }

    public String getDescription() {
        return description;
    }

    public String getPostid() {
        return postid.toString();
    }

    @Override
    public String toString() {
        return "HelpPost{" +
                "posterID=" + posterID.toString() +
                ", subject='" + subject + '\'' +
                ", starttime=" + starttime +
                ", description='" + description + '\'' +
                ", location='" + location + '\'' +
                ", postid=" + postid.toString() +
                ", isAccepted=" + isAccepted +
                '}';
    }
}
