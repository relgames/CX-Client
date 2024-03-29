package org.relgames.cxclient.service;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

/**
 * @author Oleg Poleshuk
 */
@Root(name = "games")
class GameInfoList {
    @ElementList(inline = true)
    public List<GameInfo> gameInfos;
}
