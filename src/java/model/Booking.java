/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */package model;

import java.sql.Timestamp;

public class Booking {
    private int id;
    private String customer_email;
    private int driver_id;
    private String pickup_location;
    private String dropoff_location;
    private Timestamp booking_time;
    private String status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCustomer_email() {
        return customer_email;
    }

    public void setCustomer_email(String customer_email) {
        this.customer_email = customer_email;
    }

    public int getDriver_id() {
        return driver_id;
    }

    public void setDriver_id(int driver_id) {
        this.driver_id = driver_id;
    }

    public String getPickup_location() {
        return pickup_location;
    }

    public void setPickup_location(String pickup_location) {
        this.pickup_location = pickup_location;
    }

    public String getDropoff_location() {
        return dropoff_location;
    }

    public void setDropoff_location(String dropoff_location) {
        this.dropoff_location = dropoff_location;
    }

    public Timestamp getBooking_time() {
        return booking_time;
    }

    public void setBooking_time(Timestamp booking_time) {
        this.booking_time = booking_time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}

