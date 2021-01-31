package com.projects.luguu.modules;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.Observable;
import java.util.Observer;

public class Account implements Observer {
    private String name;
    private long phoneNumber;
    private ArrayList<Activity> mentoringhistory;
    private ArrayList<Activity> menteeinghistory;
    private ArrayList<HelpPost> activePosts;
    private long rating;
    private Activity currentActivity;

    private static PostsContainer postsContainer;

    public Account(String name, long phoneNumber){
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.mentoringhistory = new ArrayList<>();
        this.menteeinghistory = new ArrayList<>();
        this.activePosts = new ArrayList<>();
        this.rating = 0;
        this.currentActivity = null;
        postsContainer = PostsContainer.getInstance();
    }

    //TODO

    public boolean requestHelp(String subject, Date date, String description, String location){
        HelpPost newPost = new HelpPost(this, subject, description, date, location);
        this.activePosts.add(newPost);
        //TODO: checks if post is already in postsContainer
        Account.postsContainer.addPost(newPost);
        return true;
    }

    public boolean acceptRequest(HelpPost hpost){
        Activity newActivity = new Activity(this, hpost.getPoster(), hpost.getSubject(), hpost.getLocation(), hpost.getStarttime(), hpost.getPostid());
        this.currentActivity = newActivity;
        hpost.getPoster().setCurrentActivity(newActivity);
        hpost.getPoster().removeActivePost(hpost);
        return false;
    }

    public void addMentoringActivity(Activity activity){
        this.mentoringhistory.add(activity);
    }

    public void addMenteeingActivity(Activity activity){
        this.menteeinghistory.add(activity);
    }

    public void setCurrentActivity(Activity activity){
        this.currentActivity = activity;
    }

    public String getName() {
        return name;
    }

    public long getRating() {
        return rating;
    }

    public ArrayList<Activity> getMenteeinghistory() {
        return menteeinghistory;
    }

    public ArrayList<Activity> getMentoringhistory() {
        return mentoringhistory;
    }

    public Activity getCurrentActivity() {
        return currentActivity;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public void removeActivePost(HelpPost hpost){
        this.activePosts.remove(hpost);
    }

    // Observe: set currentActivity to null if currentActivity has been completed and updated
    @Override
    public void update(Observable o, Object arg) {
        //TODO
    }
}
