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

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.miryor.jawn.model.HourlyForecast;

import java.util.List;

/**
 * Created by royrim on 12/5/16.
 */

public class HourlyForecastArrayAdapter extends ArrayAdapter<HourlyForecast> {

    private int layoutResourceId;
    private Context context;

    public HourlyForecastArrayAdapter(Context context, int layoutResourceId, List<HourlyForecast> list) {
        super(context, layoutResourceId, list);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
    }

    static class ViewHolder {
        TextView emoji;
        TextView time;
        TextView temp;
        TextView feelsLike;
        TextView condition;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ViewHolder holder = null;
        final HourlyForecast hf = getItem(position);

        if (row == null) {
            Log.d("JAWN", hf.getHour() + " " + hf.getCondition() );
            LayoutInflater inflator = ((Activity) context).getLayoutInflater();
            row = inflator.inflate(layoutResourceId, parent, false);

            TextView emoji = (TextView) row.findViewById(R.id.forecast_emoji);
            TextView time = (TextView) row.findViewById(R.id.forecast_time);
            TextView temp = (TextView) row.findViewById(R.id.forecast_temp);
            TextView feelsLike = (TextView) row.findViewById(R.id.forecast_feelslike);
            TextView condition = (TextView) row.findViewById(R.id.forecast_condition);

            holder = new ViewHolder();
            holder.emoji = emoji;
            holder.time = time;
            holder.temp = temp;
            holder.feelsLike = feelsLike;
            holder.condition = condition;

            row.setTag(holder);

        }
        else {
            holder = (ViewHolder)row.getTag();
        }

        holder.emoji.setText( JawnContract.getEmoji( hf.getCondition().toLowerCase(), hf.getHour() ) );
        holder.time.setText( hf.getHourPadded() + ":" + hf.getMinPadded() );
        holder.temp.setText( hf.getTempF() + "°" );
        holder.feelsLike.setText( hf.getFeelsLikeF() + "°" );
        holder.condition.setText( hf.getCondition() );

        return row;
    }


}
