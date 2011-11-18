package org.relgames.cxclient.service;

import org.junit.Test;
import org.simpleframework.xml.convert.AnnotationStrategy;
import org.simpleframework.xml.core.Persister;

import java.io.StringReader;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

/**
 * @author Oleg Poleshuk
 */
public class ParsingTest {
    private Persister p = new Persister(new AnnotationStrategy());

    @Test
    public void testParseTeam() throws Exception {
        Team team = p.read(Team.class, new StringReader("<team><id>123</id><name>чисто</name></team>"));
        assertEquals("123", team.id);
        assertEquals("чисто", team.name);
    }

    @Test
    public void testParseUser() throws Exception {
        User user = p.read(User.class, new StringReader(
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
        GameInfoList games = p.read(GameInfoList.class, new StringReader("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><gameInfos><game><dateStart>1320433200000</dateStart><dateStop>1320462000000</dateStop><id>2886012</id><name>Кубраечки</name><number>51</number><price>20000</price><type>Точки</type><zone>Гомель </zone></game><game><dateStart>1321030800000</dateStart><dateStop>1321056000000</dateStop><id>2898001</id><name>Так-то!</name><number>T52</number><price>15.000</price><type>Точки</type><zone>г. Гомель</zone></game><game><dateStart>1322244000000</dateStart><dateStop>1322301600000</dateStop><id>2900608</id><name>Феерия смысла в куплетах и нотах</name><number>T53</number><price>20000 BYR</price><type>Точки</type><zone>г. Гомель</zone></game></gameInfos>"));

        assertEquals(3, games.gameInfos.size());
    }

    @Test
    public void testParseGame() throws Exception {
        GameInfo gameInfo = p.read(GameInfo.class, new StringReader(
                "<gameInfo>\n" +
                "    <dateStart>1321030800000</dateStart>\n" +
                "    <dateStop>1321056000000</dateStop>\n" +
                "    <id>2898001</id>\n" +
                "    <name>Так-то!</name>\n" +
                "    <number>T52</number>\n" +
                "    <price>15.000</price>\n" +
                "    <type>Точки</type>\n" +
                "    <zone>г. Гомель</zone>\n" +
                "</gameInfo>"));

        assertEquals(1321030800000L, gameInfo.dateStart);
        assertEquals(1321056000000L, gameInfo.dateStop);
        assertEquals("2898001", gameInfo.id);
        assertEquals("Так-то!", gameInfo.name);
        assertEquals("T52", gameInfo.number);
        assertEquals("15.000", gameInfo.price);
        assertEquals("Точки", gameInfo.type);
        assertEquals("г. Гомель", gameInfo.zone);
    }

    @Test
    public void testStatistics() throws Exception {
        Statistics statistics = p.read(Statistics.class, "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
                "<statistics>\n" +
                "    <levels>\n" +
                "        <level>\n" +
                "            <scores>\n" +
                "                <score>\n" +
                "                    <bonusTime>0</bonusTime>\n" +
                "                    <penaltyTime>0</penaltyTime>\n" +
                "                    <team>\n" +
                "                        <id>72</id>\n" +
                "                        <name>b</name>\n" +
                "                    </team>\n" +
                "                    <timePassedLevel>1319812206451</timePassedLevel>\n" +
                "                    <timeSpentOnLevel>6451</timeSpentOnLevel>\n" +
                "                    <user>\n" +
                "                        <id>70</id>\n" +
                "                        <name>b</name>\n" +
                "                    </user>\n" +
                "                </score>\n" +
                "                <score>\n" +
                "                    <bonusTime>0</bonusTime>\n" +
                "                    <penaltyTime>0</penaltyTime>\n" +
                "                    <team>\n" +
                "                        <id>3</id>\n" +
                "                        <name>a</name>\n" +
                "                    </team>\n" +
                "                    <timePassedLevel>1319812219543</timePassedLevel>\n" +
                "                    <timeSpentOnLevel>19543</timeSpentOnLevel>\n" +
                "                    <user>\n" +
                "                        <id>1</id>\n" +
                "                        <name>a</name>\n" +
                "                    </user>\n" +
                "                </score>\n" +
                "            </scores>\n" +
                "            <name>1</name>\n" +
                "            <pos>1</pos>\n" +
                "        </level>\n" +
                "        <level>\n" +
                "            <scores>\n" +
                "                <score>\n" +
                "                    <bonusTime>0</bonusTime>\n" +
                "                    <penaltyTime>0</penaltyTime>\n" +
                "                    <team>\n" +
                "                        <id>72</id>\n" +
                "                        <name>b</name>\n" +
                "                    </team>\n" +
                "                    <timePassedLevel>1319812211158</timePassedLevel>\n" +
                "                    <timeSpentOnLevel>4707</timeSpentOnLevel>\n" +
                "                    <user>\n" +
                "                        <id>70</id>\n" +
                "                        <name>b</name>\n" +
                "                    </user>\n" +
                "                </score>\n" +
                "                <score>\n" +
                "                    <bonusTime>0</bonusTime>\n" +
                "                    <penaltyTime>0</penaltyTime>\n" +
                "                    <team>\n" +
                "                        <id>3</id>\n" +
                "                        <name>a</name>\n" +
                "                    </team>\n" +
                "                    <timePassedLevel>1319812226417</timePassedLevel>\n" +
                "                    <timeSpentOnLevel>6874</timeSpentOnLevel>\n" +
                "                    <user>\n" +
                "                        <id>1</id>\n" +
                "                        <name>a</name>\n" +
                "                    </user>\n" +
                "                </score>\n" +
                "            </scores>\n" +
                "            <name>2</name>\n" +
                "            <pos>2</pos>\n" +
                "        </level>\n" +
                "    </levels>\n" +
                "    <game>\n" +
                "        <scores>\n" +
                "            <score>\n" +
                "                <bonusTime>180000</bonusTime>\n" +
                "                <level>8</level>\n" +
                "                <penaltyTime>0</penaltyTime>\n" +
                "                <team>\n" +
                "                    <id>72</id>\n" +
                "                    <name>b</name>\n" +
                "                </team>\n" +
                "                <timeSpentOnGame>1319812245104</timeSpentOnGame>\n" +
                "            </score>\n" +
                "            <score>\n" +
                "                <bonusTime>180000</bonusTime>\n" +
                "                <level>8</level>\n" +
                "                <penaltyTime>420000</penaltyTime>\n" +
                "                <team>\n" +
                "                    <id>3</id>\n" +
                "                    <name>a</name>\n" +
                "                </team>\n" +
                "                <timeSpentOnGame>1319812247863</timeSpentOnGame>\n" +
                "            </score>\n" +
                "        </scores>\n" +
                "        <id>12</id>\n" +
                "        <name>test4</name>\n" +
                "    </game>\n" +
                "</statistics>");

        assertNotNull(statistics);
    }

    @Test
    public void testStatisticsMinimum() throws Exception {
        Statistics statistics = p.read(Statistics.class, "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
                "<statistics>\n" +
                "    <levels/>\n" +
                "    <game>\n" +
                "        <scores/>\n" +
                "        <id>45</id>\n" +
                "        <name>TEST API</name>\n" +
                "    </game>\n" +
                "</statistics>");

        assertNotNull(statistics);
    }

    @Test
    public void testInGameMinimum() throws Exception {
        InGame inGame = p.read(InGame.class, "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
                "<ingame>\n" +
                "\t<level>\n" +
                "\t\t<html>\n" +
                "\t\t\tjust a message...\n" +
                "\t\t</html>\n" +
                "\t</level>\n" +
                "</ingame>");

        assertNotNull(inGame);
    }

    @Test
    public void testInGame() throws Exception {
        InGame inGame = p.read(InGame.class, "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
                "<ingame>\n" +
                "\t<bonuses>\n" +
                "\t\t<bonus>\n" +
                "\t\t\t<levels>4 5 6 7 8 9</levels>\n" +
                "\t\t\t<answer>This is an answer on bonus!</answer>\n" +
                "\t\t\t<id>258</id>\n" +
                "\t\t\t<teamCount>0</teamCount>\n" +
                "\t\t\t<time>420000</time>\n" +
                "\t\t\t<type>0</type>\n" +
                "\t\t</bonus>\n" +
                "\t\t<bonus>\n" +
                "\t\t\t<levels>4 5 6 7 8 9</levels>\n" +
                "\t\t\t<question>Who has killed Kenny?</question>\n" +
                "\t\t\t<id>259</id>\n" +
                "\t\t\t<teamCount>0</teamCount>\n" +
                "\t\t\t<time>480000</time>\n" +
                "\t\t\t<type>1</type>\n" +
                "\t\t</bonus>\n" +
                "\t</bonuses>\n" +
                "\t<keyValid>false</keyValid>\n" +
                "\t<keyValue>7</keyValue>\n" +
                "\t<level>\n" +
                "\t\t<html>\n" +
                "\t\t\thtml text skipped...\n" +
                "\t\t</html>\n" +
                "\t\t<name>8</name>\n" +
                "\t\t<pos>8</pos>\n" +
                "\t</level>\n" +
                "</ingame>");

        assertNotNull(inGame);
        assertEquals(2, inGame.bonuses.size());
        assertEquals(InGame.Bonus.BonusType.BONUS, inGame.bonuses.get(0).type);
        assertEquals(InGame.Bonus.BonusType.PENALTY, inGame.bonuses.get(1).type);
    }
}
