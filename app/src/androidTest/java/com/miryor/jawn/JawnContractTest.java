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
        Notifier n = new Notifier( 0, "11233", 1, 10, 0, true );
        long id = JawnContract.insertNotifier(InstrumentationRegistry.getTargetContext(), n);
        Log.d("JAWN", "Inserted id: " + id);
        List<Notifier> list = JawnContract.listNotifiers(InstrumentationRegistry.getTargetContext());
        assertEquals( 1, list.size() );
        JawnContract.deleteNotifier(InstrumentationRegistry.getTargetContext(), id);
        list = JawnContract.listNotifiers(InstrumentationRegistry.getTargetContext());
        assertEquals( 0, list.size() );
    }
}
