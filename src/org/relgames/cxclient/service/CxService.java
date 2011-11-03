package org.relgames.cxclient.service;

import java.util.List;

/**
 * @author Oleg Poleshuk
 */
public interface CxService {
    User getUserInfo() throws CxServiceException;
    List<Game> getGameList() throws CxServiceException;
}
