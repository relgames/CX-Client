package org.relgames.cxclient.service;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * @author Oleg Poleshuk
 */
@Root(name="user")
public class User {
    @Element(required = false)
    public boolean admin;

    @Element(required = false)
    public boolean captain;

    @Element
    public String email;

    @Element
    public String id;

    @Element
    public String name;

    @Element(required = false)
    public int numberGamesToOpen;

    @Element(required = false)
    public Team team;
}
