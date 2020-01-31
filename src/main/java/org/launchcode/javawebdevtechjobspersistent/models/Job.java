package org.launchcode.javawebdevtechjobspersistent.models;

import javax.persistence.*;

@Entity
public class Job extends AbstractEntity{

//    @ManyToOne
    private String Employer;
    private String skills;

    public Job() {
    }

    public Job(String anEmployer, String someSkills) {
        super();
        this.Employer = anEmployer;
        this.skills = someSkills;
    }

    // Getters and setters.

    public String getEmployer() {
        return Employer;
    }

    public void setEmployer(String employer) {
        this.Employer = employer;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }
}
