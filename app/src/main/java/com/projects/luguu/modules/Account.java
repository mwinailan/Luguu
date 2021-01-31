package com.projects.luguu.modules;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.UUID;

public class Account{
    private String name;
    private long phoneNumber;
    private ArrayList<Activity> mentoringhistory;
    private ArrayList<Activity> menteeinghistory;
    private ArrayList<HelpPost> activePosts;
    private float rating;
    private Activity currentActivity;
    private UUID id;
    private String email;
    private String password;

    private static PostsContainer postsContainer;
    private static AccountsContainer accountsContainer;

    public Account(){
        this.name = null;
        this.phoneNumber = 0;
        this.menteeinghistory = new ArrayList<>();
        this.mentoringhistory = new ArrayList<>();
        this.activePosts = new ArrayList<>();
        this.rating = 0;
        this.currentActivity = null;
        this.id = UUID.randomUUID();
        this.email = null;

        postsContainer = PostsContainer.getInstance();
        accountsContainer = AccountsContainer.getInstance();
    }

    public Account(String name, String email, String password){
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.mentoringhistory = new ArrayList<>();
        this.menteeinghistory = new ArrayList<>();
        this.activePosts = new ArrayList<>();
        this.rating = 0;
        this.currentActivity = null;
        postsContainer = PostsContainer.getInstance();
        accountsContainer = AccountsContainer.getInstance();

        this.email = email;
        this.password = password;

        this.id = UUID.randomUUID();
        System.out.println("Created Account with id: " + id);
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
        Activity newActivity = new Activity(this,
                accountsContainer.getAccount(hpost.getPoster().toString()),
                hpost.getSubject(), hpost.getLocation(), new Date(hpost.getStarttime()),
                UUID.fromString(hpost.getPostid()));
        this.currentActivity = newActivity;
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

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public float getRating() {
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

    public ArrayList<HelpPost> getActivePosts() {
        return activePosts;
    }

    public void removeActivePost(HelpPost hpost){
        this.activePosts.remove(hpost);
    }

    public String getId() {
        return id.toString();
    }

    public UUID getUUID(){return id;}

    public void setId(UUID id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public void setActivePosts(ArrayList<HelpPost> activePosts) {
        this.activePosts = activePosts;
    }

    public void setMenteeinghistory(ArrayList<Activity> menteeinghistory) {
        this.menteeinghistory = menteeinghistory;
    }

    public void setMentoringhistory(ArrayList<Activity> mentoringhistory) {
        this.mentoringhistory = mentoringhistory;
    }

    public static Account parseFromMapData(Map<String, Object> mapData){
        Account output = new Account();
        output.setName(mapData.get("name").toString());
        output.setId(UUID.fromString(mapData.get("id").toString()));
        output.setPhoneNumber(Long.parseLong(mapData.get("phoneNumber").toString()));
        output.setRating(Float.parseFloat(mapData.get("rating").toString()));
        output.setEmail(mapData.get("email").toString());
        output.setPassword(mapData.get("password").toString());
        output.setCurrentActivity(Activity.parseFromMapData(
                (Map<String, Object>) mapData.get("currentActivity")
        ));
        ArrayList<Activity> menteeinghistory = new ArrayList<>();
        for(Object obj : (List<Object>)mapData.get("menteeinghistory")){
            menteeinghistory.add(Activity.parseFromMapData((Map<String, Object>) obj));
        }
        output.setMenteeinghistory(menteeinghistory);

        ArrayList<Activity> mentoringhistory = new ArrayList<>();
        for(Object obj : (List<Object>)mapData.get("mentoringhistory")){
            mentoringhistory.add(Activity.parseFromMapData((Map<String, Object>) obj));
        }
        output.setMentoringhistory(mentoringhistory);

        return output;
    }

    @Override
    public String toString() {
        return "Account{" +
                "name='" + name + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", mentoringhistory=" + mentoringhistory +
                ", menteeinghistory=" + menteeinghistory +
                ", activePosts=" + activePosts +
                ", rating=" + rating +
                ", currentActivity=" + currentActivity +
                ", id=" + id +
                '}';
    }
}
