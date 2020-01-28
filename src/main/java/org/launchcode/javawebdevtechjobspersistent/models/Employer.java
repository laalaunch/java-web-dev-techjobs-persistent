package org.launchcode.javawebdevtechjobspersistent.models;

import com.sun.istack.NotNull;

import javax.persistence.Entity;
import javax.validation.constraints.Size;

@Entity
public class Employer extends AbstractEntity {

    @NotNull
    @Size(max = 75)
    private String location;

    public Employer(@Size(max = 155, message = "Name must be at least 3 characters long") String location) {
        this.location = location;
    }

    public Employer() {}

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
