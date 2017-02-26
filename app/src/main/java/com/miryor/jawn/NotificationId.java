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

package com.miryor.jawn;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by royrim on 12/8/16.
 */

public class NotificationId {

    public static int getId(Context context) {
        SharedPreferences p = context.getSharedPreferences( context.getString(R.string.preference_jawnnotifier), Context.MODE_PRIVATE );
        int lastNotificationId = p.getInt( context.getString(R.string.preference_lastnotificationid), 0 );
        lastNotificationId++;
        SharedPreferences.Editor e = p.edit();
        e.putInt( context.getString(R.string.preference_jawnnotifier), lastNotificationId );
        e.commit();
        return lastNotificationId;
    }
}
