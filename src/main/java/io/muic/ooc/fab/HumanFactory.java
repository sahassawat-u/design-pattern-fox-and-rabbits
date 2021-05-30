package io.muic.ooc.fab;

import java.util.HashMap;
import java.util.Map;

public class HumanFactory {
    private static Map<HumanType, Class> humanClassMap = new HashMap<HumanType, Class>() {{
        HumanType[] humanTypes = HumanType.values();
        for (int i=0;i<humanTypes.length;i++) {
            put(humanTypes[i], humanTypes[i].getHumanClass());
        }
    }};
    public static Human createHuman(HumanType humanType,Field field,Location location) {
        Class humanClass = humanClassMap.get(humanType);
        return createHuman(humanClass, field, location);
    }
    public static Human createHuman(Class humanClass,Field field,Location location) {
        if (humanClass != null) {
            try {
                Human human = (Human) humanClass.newInstance();
                human.initialize(field, location);
                return human;
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        throw new IllegalArgumentException("Unknown humanType");
    }
}
