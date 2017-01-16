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
