package com.example.announcement_procedures_automation_projectoop.Announcements;


public abstract class Announcement {
    private String title;
    private String message;

    public Announcement(String title, String message) {
        this.title = title;
        this.message = message;
    }

    public abstract String announcementType();

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}