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

import android.support.test.InstrumentationRegistry;
import android.util.Log;

import com.miryor.jawn.model.Notifier;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by royrim on 11/25/16.
 */

public class JawnContractTest {

    //private JawnContract.JawnNotifierDbHelper notifierDbHelper;

    @Before
    public void setUp() {
        //notifierDbHelper = new JawnContract.JawnNotifierDbHelper();
        JawnContract.reset(InstrumentationRegistry.getTargetContext());
    }

    @Test
    public void testInsert() {
        Notifier n = new Notifier( 0, "11233", 1, 10, 0, JawnContract.WEATHER_API_PROVIDER_JAWNREST, "" );
        long id = JawnContract.saveNotifier(InstrumentationRegistry.getTargetContext(), n);
        Log.d("JAWN", "Inserted id: " + id);
        List<Notifier> list = JawnContract.listNotifiers(InstrumentationRegistry.getTargetContext());
        assertEquals( 1, list.size() );
        n = list.get(0);
        n.setPostalCode("12345");
        JawnContract.saveNotifier(InstrumentationRegistry.getTargetContext(),n);
        n = JawnContract.getNotifier(InstrumentationRegistry.getTargetContext(), n.getId() );
        assertEquals( "12345", n.getPostalCode() );
        JawnContract.deleteNotifier(InstrumentationRegistry.getTargetContext(), id);
        list = JawnContract.listNotifiers(InstrumentationRegistry.getTargetContext());
        assertEquals( 0, list.size() );
    }
}
