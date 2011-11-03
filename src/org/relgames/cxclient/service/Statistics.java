package org.relgames.cxclient.service;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Path;
import org.simpleframework.xml.Root;

import java.util.List;

/**
 * @author Oleg Poleshuk
 */
@Root(name = "statistics")
public class Statistics {
    @ElementList
    public List<LevelColumn> levels;

    @ElementList(name = "scores") @Path("game")
    public List<Score> gameScores;

    @Element(name = "id") @Path("game")
    public String gameId;

    @Element(name = "name") @Path("game")
    public String gameName;
}
