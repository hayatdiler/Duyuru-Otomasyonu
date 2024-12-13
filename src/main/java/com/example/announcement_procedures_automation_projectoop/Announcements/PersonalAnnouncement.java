package com.example.announcement_procedures_automation_projectoop.Announcements;

public class PersonalAnnouncement extends Announcement{

    private String person;

    public PersonalAnnouncement(String title, String message,String person) {
        super(title, message);
        this.person=person;
    }

    @Override
    public String announcementType() {
        return "PersonalAnnouncement";
    }

    public String getPerson() {
        return person;
    }

}
