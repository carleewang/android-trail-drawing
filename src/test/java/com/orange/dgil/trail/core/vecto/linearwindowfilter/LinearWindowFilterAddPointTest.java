/**
 * Trail drawing library
 * Copyright (C) 2014 Orange
 * Authors: christophe.maldivi@orange.com, eric.petit@orange.com
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package com.orange.dgil.trail.core.vecto.linearwindowfilter;

import com.orange.dgil.trail.TestTools;
import com.orange.dgil.trail.core.common.TrailPoint;
import com.orange.dgil.trail.core.vecto.SlidingWindow;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class LinearWindowFilterAddPointTest {

  private LinearWindowFilter filter;

  @Before
  public void setUp() {
    filter = Mockito.mock(LinearWindowFilter.class);
  }

  @Test
  public void shouldAddPointWhenSameAsLast() throws IllegalAccessException {
    // given
    boolean sameAsLast = true;
    TrailPoint point = new TrailPoint();
    SlidingWindow slidingWindow = Mockito.mock(SlidingWindow.class);
    TestTools.setObj("slidingWindow", LinearWindowFilter.class, filter, slidingWindow);
    Mockito.when(slidingWindow.isSameAsLast(point)).thenReturn(sameAsLast);
    Mockito.doCallRealMethod().when(filter).addPoint(point);
    // do
    filter.addPoint(point);
    // then
    Mockito.verify(filter, Mockito.times(0)).doAddPoint(point);
  }

  @Test
  public void shouldAddPointWhenNotSameAsLast() throws IllegalAccessException {
    // given
    boolean sameAsLast = false;
    TrailPoint point = new TrailPoint();
    SlidingWindow slidingWindow = Mockito.mock(SlidingWindow.class);
    TestTools.setObj("slidingWindow", LinearWindowFilter.class, filter, slidingWindow);
    Mockito.when(slidingWindow.isSameAsLast(point)).thenReturn(sameAsLast);
    Mockito.doCallRealMethod().when(filter).addPoint(point);
    // do
    filter.addPoint(point);
    // then
    Mockito.verify(filter, Mockito.times(1)).doAddPoint(point);
  }
}
