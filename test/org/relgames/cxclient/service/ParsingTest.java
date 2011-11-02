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


    @Test
    public void testParseGameList() throws Exception {
        Persister serializer = new Persister();
        GameList games = serializer.read(GameList.class, new StringReader("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><games><game><dateStart>1320433200000</dateStart><dateStop>1320462000000</dateStop><id>2886012</id><name>Кубраечки</name><number>51</number><price>20000</price><type>Точки</type><zone>Гомель </zone></game><game><dateStart>1321030800000</dateStart><dateStop>1321056000000</dateStop><id>2898001</id><name>Так-то!</name><number>T52</number><price>15.000</price><type>Точки</type><zone>г. Гомель</zone></game><game><dateStart>1322244000000</dateStart><dateStop>1322301600000</dateStop><id>2900608</id><name>Феерия смысла в куплетах и нотах</name><number>T53</number><price>20000 BYR</price><type>Точки</type><zone>г. Гомель</zone></game></games>"));

        assertEquals(3, games.games.size());
    }

    @Test
    public void testParseGame() throws Exception {
        Persister serializer = new Persister();
        Game game = serializer.read(Game.class, new StringReader(
                "<game>\n" +
                "    <dateStart>1321030800000</dateStart>\n" +
                "    <dateStop>1321056000000</dateStop>\n" +
                "    <id>2898001</id>\n" +
                "    <name>Так-то!</name>\n" +
                "    <number>T52</number>\n" +
                "    <price>15.000</price>\n" +
                "    <type>Точки</type>\n" +
                "    <zone>г. Гомель</zone>\n" +
                "</game>"));

        assertEquals(1321030800000L, game.dateStart);
        assertEquals(1321056000000L, game.dateStop);
        assertEquals("2898001", game.id);
        assertEquals("Так-то!", game.name);
        assertEquals("T52", game.number);
        assertEquals("15.000", game.price);
        assertEquals("Точки", game.type);
        assertEquals("г. Гомель", game.zone);
    }
}
