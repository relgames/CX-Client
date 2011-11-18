package org.relgames.cxclient.service;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import java.io.Serializable;

/**
 * @author Oleg Poleshuk
 */
@Root(name = "game")
public class GameInfo implements Serializable {
    @Element
    public long dateStart;

    @Element
    public long dateStop;

    @Element
    public String id;

    @Element
    public String name;

    @Element(required = false)
    public String number;

    @Element(required = false)
    public String price;

    @Element
    public String type;

    @Element(required = false)
    public String zone;

    @Override
    public String toString() {
        return "GameInfo{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
