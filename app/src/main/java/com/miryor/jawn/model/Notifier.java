package com.miryor.jawn.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by royrim on 11/25/16.
 */

public class Notifier implements Parcelable {
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(postalCode);
        dest.writeInt(daysOfWeek);
        dest.writeInt(time);
        dest.writeInt( (enabled) ? 1 : 0 );
    }

    public static final Parcelable.Creator<Notifier> CREATOR = new Parcelable.Creator<Notifier>() {
        public Notifier createFromParcel(Parcel in) {
            return new Notifier(in);
        }

        public Notifier[] newArray(int size) {
            return new Notifier[size];
        }
    };

    private Notifier(Parcel in) {
        id = in.readLong();
        postalCode = in.readString();
        daysOfWeek = in.readInt();
        time = in.readInt();
        enabled = ( (in.readInt() == 1) ? true : false );
    }
}
