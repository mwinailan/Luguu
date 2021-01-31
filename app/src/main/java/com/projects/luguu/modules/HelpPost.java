package com.projects.luguu.modules;

import java.util.Date;
import java.util.UUID;

public class HelpPost {
    private Account poster;
    private String subject;
    private Date starttime;
    private String description;
    private String location;
    private UUID postid;
    private boolean isAccepted;

    public HelpPost(Account poster, String subject, String description, Date starttime, String location) {
        this.poster = poster;
        this.subject = subject;
        this.description = description;
        this.location = location;
        this.starttime = starttime;

        this.postid = UUID.randomUUID();
    }

    public String getLocation() {
        return location;
    }

    public Account getPoster() {
        return poster;
    }

    public Date getStarttime() {
        return starttime;
    }

    public String getSubject() {
        return subject;
    }

    public String getDescription() {
        return description;
    }

    public UUID getPostid() {
        return postid;
    }
}
