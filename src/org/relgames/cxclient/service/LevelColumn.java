package org.relgames.cxclient.service;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

/**
 * @author Oleg Poleshuk
 */
@Root(name = "level")
public class LevelColumn {
    @Element
    public String name;

    @Element
    public int pos;

    @ElementList
    public List<Score> scores;
}
