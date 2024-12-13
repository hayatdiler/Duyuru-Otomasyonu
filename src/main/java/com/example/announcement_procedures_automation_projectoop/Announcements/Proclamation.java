package com.example.announcement_procedures_automation_projectoop.Announcements;

public class Proclamation extends Announcement{

    public Proclamation(String title, String message) {
        super(title, message);
    }

    @Override
    public String announcementType() {
        return "Proclamation";
    }
}
