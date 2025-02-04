//
//  ========================================================================
//  Copyright (c) 1995-2019 Mort Bay Consulting Pty. Ltd.
//  ------------------------------------------------------------------------
//  All rights reserved. This program and the accompanying materials
//  are made available under the terms of the Eclipse Public License v1.0
//  and Apache License v2.0 which accompanies this distribution.
//
//      The Eclipse Public License is available at
//      http://www.eclipse.org/legal/epl-v10.html
//
//      The Apache License v2.0 is available at
//      http://www.opensource.org/licenses/apache2.0.php
//
//  You may elect to redistribute this code under either of these licenses.
//  ========================================================================
//

package org.eclipse.jetty.server.session;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import javax.servlet.SessionTrackingMode;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class SessionHandlerTest
{
    @Test
    public void testSessionTrackingMode()
    {
        SessionHandler sessionHandler = new SessionHandler();
        sessionHandler.setSessionTrackingModes(new HashSet<>(Arrays.asList(SessionTrackingMode.COOKIE, SessionTrackingMode.URL)));
        sessionHandler.setSessionTrackingModes(Collections.singleton(SessionTrackingMode.SSL));
        assertThrows(IllegalArgumentException.class,() ->
            sessionHandler.setSessionTrackingModes(new HashSet<>(Arrays.asList(SessionTrackingMode.SSL, SessionTrackingMode.URL))));
    }

    @Test
    public void testInvalidSessiongetLastAccessedTime()
    {
        Session session = new Session(new SessionHandler(),
                new SessionData("sd" ,"", "", 0, 0, 0, 0));
        session.getLastAccessedTime();
        session.invalidate();
        assertThrows(IllegalStateException.class, () -> session.getLastAccessedTime());
    }

}
