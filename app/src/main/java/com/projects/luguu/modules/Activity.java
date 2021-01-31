package com.projects.luguu.modules;

import java.util.Date;
import java.util.Observable;

public class Activity {
    protected String id;
    private String location;
    protected Account mentor;
    protected Account mentee;
    private String subject;
    private Date starttime;
    protected float mentorrating;
    protected float menteerating;
    private boolean isFullfilled;

    public Activity(Account mentor, Account mentee, String subject, String location, Date starttime, String postid){
        this.mentor = mentor;
        this.mentee = mentee;
        this.subject = subject;
        this.location = location;
        this.starttime = starttime;
        this.mentorrating = 0;
        this.menteerating = 0;
        this.isFullfilled = false;
        this.id = postid;
    }

    public void setComplete(){
        this.isFullfilled = true;
        //get mentor and mentee rating;
        Date endtime = new Date();
        this.mentor.addMentoringActivity(this);
        this.mentee.addMenteeingActivity(this);
        this.mentor.setCurrentActivity(null);
        this.mentee.setCurrentActivity(null);
    }

    public Account getBuddy(Account caller){
        if(caller.equals(mentee)){
            return this.mentor;
        }
        if(caller.equals(mentor)){
            return this.mentee;
        }
        return null;
    }

    public float getBuddyRating(Account caller){
        if(caller.equals(mentee)){
            return this.mentorrating;
        }
        if(caller.equals(mentor)){
            return this.menteerating;
        }
        return 0;
    }

    @Override
    public String toString() {
        return "Activity{" +
                "id=" + id +
                ", location='" + location + '\'' +
                ", mentor=" + mentor.getId() +
                ", mentee=" + mentee.getId() +
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
        return id;
    }

    public String getLocation() {
        return location;
    }
}
