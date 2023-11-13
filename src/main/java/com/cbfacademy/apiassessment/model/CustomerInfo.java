package com.cbfacademy.apiassessment.model;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.PastOrPresent;

@Entity
public class CustomerInfo {
    //TODO: CHANGE ID FROM LONG TO AUTOGENERATED UUID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message="You must enter your first name")
    private String firstName;

    @NotEmpty
    private String lastName;

    @NotEmpty(message = "Email must not be empty")
    @Email(message = "Email must be valid")
    //TODO: more specific email validation using regular expressions
    private String email;
    // @UniqueElements - to make sure all elemts in the investment preferences list is unique

    @Embedded
    InvestmentPreferences investmentPreferences;
    //TODO: GENERATE AUTOMATIC CREATED AND UPDATED DATES

    @DateTimeFormat
    @PastOrPresent
    LocalDateTime createdDate;

    @DateTimeFormat
    LocalDateTime updatedDate;


    CustomerInfo(){}
    
    public CustomerInfo(@NotEmpty String firstName, @NotEmpty String lastName, @NotEmpty @Email String email,
            InvestmentPreferences investmentPreferences, @PastOrPresent LocalDateTime createdDate,
            LocalDateTime updatedDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.investmentPreferences = investmentPreferences;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }



    @Override
    public String toString(){
        return String.format(
            //TODO: FIND OUT WHY %s doesnt work but %S does
            //%s returns an error: [Request processing failed: java.util.MissingFormatArgumentException: Format specifier '%s'] with root cause
            //%S works fine
            "   {Customer id=%d, firstName=%S, lastName=%S }" , id, firstName, lastName);
    } 
 
    public Long id() {
        return id;
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



    public InvestmentPreferences getInvestmentPreferences() {
        return investmentPreferences;
    }



    public void setInvestmentPreferences(InvestmentPreferences investmentPreferences) {
        this.investmentPreferences = investmentPreferences;
    }



    public LocalDateTime getCreatedDate() {
        return createdDate;
    }



    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }



    public LocalDateTime getUpdatedDate() {
        return updatedDate;
    }



    public void setUpdatedDate(LocalDateTime updatedDate) {
        this.updatedDate = updatedDate;
    }

    
}
