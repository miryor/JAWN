package com.miryor.jawn.model;

/**
 * Created by royrim on 11/25/16.
 */

public class Notifier {
    private long id;
    private String postalCode;
    private int daysOfWeek;
    private int time;
    private boolean enabled;

    public Notifier(long id, String postalCode, int daysOfWeek, int time, boolean enabled) {
        this.id = id;
        this.postalCode = postalCode;
        this.daysOfWeek = daysOfWeek;
        this.time = time;
        this.enabled = enabled;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public int getDaysOfWeek() {
        return daysOfWeek;
    }

    public void setDaysOfWeek(int daysOfWeek) {
        this.daysOfWeek = daysOfWeek;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
