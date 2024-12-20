package com.example.announcement_procedures_automation_projectoop.Announcements;

public class Advertisements extends Announcement implements Multivariable{

    private String company;

    public Advertisements(String title, String message,String company) {
        super(title, message);
        this.company=company;
    }

    @Override
    public String announcementType() {
        return "Advertisements";
    }

    public String getCompany() {
        return company;
    }

}
