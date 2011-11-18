package org.relgames.cxclient.service;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Path;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.convert.Convert;
import org.simpleframework.xml.convert.Converter;
import org.simpleframework.xml.stream.InputNode;
import org.simpleframework.xml.stream.OutputNode;

import java.util.List;

/**
 * @author Oleg Poleshuk
 */
@Root(name = "ingame")
public class InGame {
    @Element(name = "name", required = false) @Path("level")
    public String levelName;

    @Element(name = "pos", required = false) @Path("level")
    public String levelPos;

    @Element(name = "html") @Path("level")
    public String levelHtml;

    @Element(required = false)
    public Boolean keyValid;

    @Element(required = false)
    public String keyValue;

    @ElementList(required = false)
    public List<Bonus> bonuses;

    @Root(name = "bonus")
    public static class Bonus {
        @Element
        public String id;

        @Element
        public String levels;

        @Element(required = false)
        public String question;

        @Element(required = false)
        public String answer;

        @Element
        public int teamCount;

        @Element
        public long time;

        @Element
        @Convert(BonusTypeConverter.class)
        public BonusType type;

        public static enum BonusType { BONUS, PENALTY }

        public static class BonusTypeConverter implements Converter<BonusType> {
            @Override
            public BonusType read(InputNode node) throws Exception {
                String value = node.getValue();
                if ("0".equals(value)) {
                    return BonusType.BONUS;
                } else if ("1".equals(value)) {
                    return BonusType.PENALTY;
                } else {
                    throw new IllegalArgumentException("Unknown BonusType " + value);
                }
            }

            @Override
            public void write(OutputNode node, BonusType value) throws Exception {
                throw new AssertionError("Not implemented");
            }
        }

    }
}
