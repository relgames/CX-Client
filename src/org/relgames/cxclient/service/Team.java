package org.relgames.cxclient.service;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * @author Oleg Poleshuk
 */
@Root(name = "team")
public class Team {
    @Element
    public String id;

    @Element
    public String name;
}
