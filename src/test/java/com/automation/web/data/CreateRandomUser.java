package com.automation.web.data;

/**
 * Random User creator class, for ESPN FinalExam
 * @author alejandro.carpio
 */
public class CreateRandomUser {

    private String firstName;
    private String lastName;
    private String email;
    private String password;

    public CreateRandomUser(){
        int randomNumber = (int)(5821 * Math.random() + 2808);

        firstName = "David"+randomNumber;
        lastName = "Noroeste"+randomNumber;
        email = "linkNorthWest"+randomNumber+"@gmail.com";
        password = "pqrs$$AbC";
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
