package com.suvorov.suit;

import com.suvorov.tests.web.gMailTests;
import com.suvorov.tests.api.gMapsDistanceMatrixJsonTests;
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
        gMapsDistanceMatrixJsonTests.class
})
public class TestSuite {}