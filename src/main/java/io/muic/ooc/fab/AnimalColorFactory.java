package io.muic.ooc.fab;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class AnimalColorFactory {
    private static Map<AnimalType, Color> animalColorMap = new HashMap<AnimalType, Color>() {{
        AnimalType[] animalTypes = AnimalType.values();
        for (int i=0;i<animalTypes.length;i++) {
            put(animalTypes[i], animalTypes[i].getColor());
        }
    }};

    public static Color getColor(Class animalClass) {
        return animalColorMap.get(animalClass);
    }
}
