package org.relgames.cxclient.service;

import java.util.List;

/**
 * @author Oleg Poleshuk
 */
public interface CxService {
    User getUserInfo() throws CxServiceException;
    List<GameInfo> getGameList() throws CxServiceException;
    Statistics getStatistics(String gameId) throws CxServiceException;
    InGame inGame(String gameId, String bonusId, String key) throws CxServiceException;
}
