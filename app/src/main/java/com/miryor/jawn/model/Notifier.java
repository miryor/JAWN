/*
 * Copyright (c) 2017.
 *
 * JAWN is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

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
    private int hour;
    private int minute;
    private String provider;
    private String forecast;

    public static String EXTRA_NAME = "com.miryor.jawn.Notifier";

    public Notifier(long id, String postalCode, int daysOfWeek, int hour, int minute, String provider, String forecast) {
        this.id = id;
        this.postalCode = postalCode;
        this.daysOfWeek = daysOfWeek;
        this.hour = hour;
        this.minute = minute;
        this.provider = provider;
        this.forecast = forecast;
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

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getForecast() {
        return forecast;
    }

    public void setForecast(String forecast) {
        this.forecast = forecast;
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
        dest.writeInt(hour);
        dest.writeInt(minute);
        dest.writeString(provider);
        dest.writeString( forecast );
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
        hour = in.readInt();
        minute = in.readInt();
        provider = in.readString();
        forecast = in.readString();
    }
}
