package com.suvorov.suit;

import com.suvorov.tests.web.gMailTests;
import com.suvorov.tests.api.gMapsDistanceMatrixTests;
import com.suvorov.tests.web.gSearchTests;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created by vsuvorov on 12/9/16.
 */

@RunWith(Suite.class)
@Suite.SuiteClasses({
        gSearchTests.class,
        gMailTests.class,
        gMapsDistanceMatrixTests.class
})
public class TestSuite {}