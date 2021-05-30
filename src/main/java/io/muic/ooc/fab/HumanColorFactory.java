package io.muic.ooc.fab;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class HumanColorFactory {
    private static Map<HumanType, Color> humanColorMap = new HashMap<HumanType, Color>() {{
        HumanType[] humanTypes = HumanType.values();
        for (int i=0;i<humanTypes.length;i++) {
            put(humanTypes[i], humanTypes[i].getColor());
        }
    }};

    public static Color getColor(Class humanClass) {
        return humanColorMap.get(humanClass);
    }
}
