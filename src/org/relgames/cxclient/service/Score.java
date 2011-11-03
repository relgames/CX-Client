package org.relgames.cxclient.service;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Path;
import org.simpleframework.xml.Root;

/**
 * @author Oleg Poleshuk
 */
@Root(name = "score")
public class Score {
    @Element
    public long bonusTime;

    @Element(required = false)
    public int level;

    @Element
    public long penaltyTime;

    @Element(name = "id") @Path("team" )
    public String teamId;
    @Element(name = "name") @Path("team" )
    public String teamName;

    @Element(required = false, name = "id") @Path("user")
    public String userId;
    @Element(required = false, name = "name") @Path("user")
    public String userName;

    @Element(required = false)
    public long timePassedLevel;

    @Element(required = false)
    public long timeSpentOnLevel;

    @Element(required = false)
    public long timeSpentOnGame;
}
