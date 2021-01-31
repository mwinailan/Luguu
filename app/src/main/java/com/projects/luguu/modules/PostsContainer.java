package com.projects.luguu.modules;

import java.util.ArrayList;

// PostsContainer is a singleton
public final class PostsContainer {
    private static final PostsContainer INSTANCE = new PostsContainer();

    //data represents current active unaccepted posts
    private ArrayList<HelpPost> data;

    private PostsContainer(){
        this.data = new ArrayList<>();
    }

    public static PostsContainer getInstance(){
        return INSTANCE;
    }

    public int getSize(){
        return this.data.size();
    }

    public HelpPost getHelpPost(String postid){
        for(HelpPost curr: data) {
            if (curr.getPostid().equals(postid)) {
                return curr;
            }
        }
        return null;
    }

    public void addPost(HelpPost helpPost){
        this.data.add(helpPost);
    }

    public void removePost(HelpPost toDelete){
        this.data.remove(toDelete);
    }
}
