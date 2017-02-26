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

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.miryor.jawn.model.Notifier;

import java.util.Calendar;
import java.util.List;

/**
 * Created by royrim on 12/5/16.
 */

public class OnBootReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d( "JAWN", "Getting list of notificatiers and settings alarms" );
        // if (intent.getAction().equals("android.intent.action.BOOT_COMPLETED")) {
            List<Notifier> list = JawnContract.listNotifiers(context);
            Calendar calendar = Calendar.getInstance();
            for ( Notifier notifier : list ) {
                Log.d( "JAWN", "Setting alarm for " + notifier.getPostalCode() + " at " + notifier.getHour() + ":" + notifier.getMinute() );

                Utils.setNotificationAlarm(context, notifier);

            }
        // }
    }
}
