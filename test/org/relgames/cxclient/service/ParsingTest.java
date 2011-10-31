package org.relgames.cxclient.service;

import org.junit.Test;
import org.simpleframework.xml.core.Persister;

import java.io.StringReader;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

/**
 * @author Oleg Poleshuk
 */
public class ParsingTest {
    @Test
    public void testParseTeam() throws Exception {
        Persister serializer = new Persister();
        Team team = serializer.read(Team.class, new StringReader("<team><id>123</id><name>чисто</name></team>"));
        assertEquals("123", team.id);
        assertEquals("чисто", team.name);
    }

    @Test
    public void testParseUser() throws Exception {
        Persister serializer = new Persister();
        User user = serializer.read(User.class, new StringReader(
                "<user>\n" +
                    "<admin>true</admin>\n" +
                    "<captain>true</captain>\n" +
                    "<email>b@b.b</email>\n" +
                    "<id>37</id>\n" +
                    "<name>b</name>\n" +
                    "<numberGamesToOpen>1</numberGamesToOpen>\n" +
                    "<team>\n" +
                        "<id>39</id>\n" +
                        "<name>b2</name>\n" +
                    "</team>\n" +
                "</user>"));
        assertEquals(true, user.admin);
        assertEquals(true, user.captain);
        assertEquals("b@b.b", user.email);
        assertEquals("37", user.id);
        assertEquals("b", user.name);
        assertEquals(1, user.numberGamesToOpen);

        assertNotNull(user.team);
        assertEquals("39", user.team.id);
        assertEquals("b2", user.team.name);
    }
}
