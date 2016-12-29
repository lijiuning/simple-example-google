package com.suvorov.suit;

import com.suvorov.gmailTests;
import com.suvorov.googleSearchTests;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created by vsuvorov on 12/9/16.
 */

@RunWith(Suite.class)
@Suite.SuiteClasses({
        googleSearchTests.class,
        gmailTests.class
})
public class TestSuite {}