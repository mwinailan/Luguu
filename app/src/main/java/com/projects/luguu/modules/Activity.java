package com.projects.luguu.modules;

import java.util.Date;
import java.util.Map;
import java.util.Observable;
import java.util.UUID;

public class Activity {
    protected UUID id;
    private String location;
    protected String mentorID;
    protected String menteeID;
    private String subject;
    private Date starttime;
    protected float mentorrating;
    protected float menteerating;
    private boolean isFullfilled;
    private AccountsContainer accountsContainer;

    public Activity(){
        this.id = null;
        this.location = "";
        this.mentorID = "";
        this.menteeID = "";
        this.subject = null;
        this.starttime = null;
        this.mentorrating = 0;
        this.menteerating = 0;
        this.isFullfilled = false;
        this.accountsContainer = AccountsContainer.getInstance();
    }

    public Activity(Account mentor, Account mentee, String subject, String location, Date starttime, UUID postid){
        this.mentorID = mentor.getId();
        this.menteeID = mentee.getId();
        this.subject = subject;
        this.location = location;
        this.starttime = starttime;
        this.mentorrating = 0;
        this.menteerating = 0;
        this.isFullfilled = false;
        this.id = postid;
        this.accountsContainer = AccountsContainer.getInstance();
    }

    public void setComplete(){
        this.isFullfilled = true;
        //get mentor and mentee rating;
        Date endtime = new Date();
    }

    public Account getBuddy(Account caller){
        if(caller.getId().equals(menteeID)){
            return this.accountsContainer.getAccount(this.mentorID);
        }
        if(caller.getId().equals(mentorID)){
            return this.accountsContainer.getAccount(this.menteeID);
        }
        return null;
    }

    public float getBuddyRating(Account caller){
        if(caller.getId().equals(menteeID)){
            return this.accountsContainer.getAccount(this.mentorID).getRating();
        }
        if(caller.getId().equals(mentorID)){
            return this.accountsContainer.getAccount(this.menteeID).getRating();
        }
        return 0;
    }

    @Override
    public String toString() {
        return "Activity{" +
                "id=" + id +
                ", location='" + location + '\'' +
                ", mentor=" + mentorID +
                ", mentee=" + menteeID +
                ", subject='" + subject + '\'' +
                ", starttime=" + starttime +
                ", mentorrating=" + mentorrating +
                ", menteerating=" + menteerating +
                ", isFullfilled=" + isFullfilled +
                '}';
    }

    public String getSubject() {
        return subject;
    }

    public String getId() {
        return id.toString();
    }

    public String getLocation() {
        return location;
    }

    public String getMentorID() {
        return mentorID;
    }

    public String getMenteeID() {
        return menteeID;
    }

    public float getMenteerating() {
        return menteerating;
    }

    public float getMentorrating() {
        return mentorrating;
    }

    public String getStarttime() {
        return starttime.toString();
    }

    public boolean isFullfilled() {
        return isFullfilled;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setFullfilled(boolean fullfilled) {
        isFullfilled = fullfilled;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setMenteeID(String menteeID) {
        this.menteeID = menteeID;
    }

    public void setMentorID(String mentorID) {
        this.mentorID = mentorID;
    }

    public void setMenteerating(float menteerating) {
        this.menteerating = menteerating;
    }

    public void setMentorrating(float mentorrating) {
        this.mentorrating = mentorrating;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }

    public static Activity parseFromMapData(Map<String, Object> mapData){
        Activity temp = new Activity();
        if(mapData == null){
            return null;
        }
        temp.setSubject(mapData.get("subject").toString());
        temp.setId(UUID.fromString(mapData.get("id").toString()));
        temp.setMenteerating(Float.parseFloat(mapData.get("menteerating").toString()));
        temp.setMentorrating(Float.parseFloat(mapData.get("mentorrating").toString()));
        temp.setLocation(mapData.get("location").toString());
        temp.setStarttime(new Date(mapData.get("starttime").toString()));
        temp.setFullfilled(Boolean.getBoolean(mapData.get("fullfilled").toString()));
        temp.setMenteeID(mapData.get("menteeID").toString());
        temp.setMentorID(mapData.get("mentorID").toString());
        return temp;
    }
}
