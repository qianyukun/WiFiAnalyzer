/*
 *    Copyright (C) 2015 - 2016 VREM Software Development <VREMSoftwareDevelopment@gmail.com>
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package com.vrem.wifianalyzer.wifi;

import com.vrem.wifianalyzer.BuildConfig;
import com.vrem.wifianalyzer.Configuration;
import com.vrem.wifianalyzer.MainContextHelper;
import com.vrem.wifianalyzer.RobolectricUtil;
import com.vrem.wifianalyzer.settings.Settings;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.support.v4.SupportFragmentTestUtil;

import java.util.Locale;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class ChannelAvailableFragmentTest {

    private Configuration configuration;
    private Settings settings;
    private ChannelAvailableFragment fixture;

    @Before
    public void setUp() throws Exception {
        RobolectricUtil.INSTANCE.getMainActivity();

        configuration = MainContextHelper.INSTANCE.getConfiguration();
        settings = MainContextHelper.INSTANCE.getSettings();

        when(settings.getCountryCode()).thenReturn(Locale.US.getCountry());

        fixture = new ChannelAvailableFragment();
    }

    @After
    public void tearDown() throws Exception {
        verify(configuration, atLeastOnce()).isDevelopmentMode();
        verify(settings, atLeastOnce()).getCountryCode();
        MainContextHelper.INSTANCE.restore();
    }

    @Test
    public void testOnCreateView() throws Exception {
        // setup
        when(configuration.isDevelopmentMode()).thenReturn(false);
        // execute
        SupportFragmentTestUtil.startFragment(fixture);
        // validate
        assertNotNull(fixture);
    }

    @Test
    public void testOnCreateViewInDevelopmentMode() throws Exception {
        // setup
        when(configuration.isDevelopmentMode()).thenReturn(true);
        // execute
        SupportFragmentTestUtil.startFragment(fixture);
        // validate
        assertNotNull(fixture);
    }

}