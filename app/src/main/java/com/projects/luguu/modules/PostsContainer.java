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

    public void print(){
        System.out.println("        Contents in postsContainer: \n");
        for(HelpPost post: data){
            System.out.println(post.toString() + "\n");
        }
    }

    public void addPost(HelpPost helpPost){
        for(HelpPost hpost: data){
            if(hpost.getPostid().equals(helpPost.getPostid())){
                return;
            }
        }
        this.data.add(helpPost);
        print();
    }

    public void removePost(HelpPost toDelete){
        this.data.remove(toDelete);
    }
}
