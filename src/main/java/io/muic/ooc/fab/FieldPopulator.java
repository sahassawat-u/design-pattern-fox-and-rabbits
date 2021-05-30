package io.muic.ooc.fab;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class FieldPopulator {
    private static final Random RANDOM = new Random();
    private Map<AnimalType, Double> probabilityMap = new HashMap<AnimalType, Double>() {{
        AnimalType[] animalTypes = AnimalType.values();
        for (int i=0;i<animalTypes.length;i++) {
            put(animalTypes[i], animalTypes[i].getBreedingProbability());
        }
    }};
    private Map<HumanType, Double> probabilityHumanMap = new HashMap<HumanType, Double>() {{
        HumanType[] humanTypes = HumanType.values();
        for (int i=0;i<humanTypes.length;i++) {
            put(humanTypes[i], humanTypes[i].getBreedingProbability());
        }
    }};

    /**
     * Randomly populate the field with foxes and rabbits.
     */
    public void populate(Field field, List<Animal> animals, List<Human> human) {

        field.clear();
        for (int row = 0; row < field.getDepth(); row++) {
            for (int col = 0; col < field.getWidth(); col++) {
                for(Map.Entry<AnimalType, Double> entry : probabilityMap.entrySet()){
                    if (RANDOM.nextDouble() <= entry.getValue()){
                        Location location = new Location(row, col);
                        Animal animal = AnimalFactory.createAnimal(entry.getKey(), field, location);
                        animals.add(animal);
                        break;
                    }
                }
                for(Map.Entry<HumanType, Double> entry : probabilityHumanMap.entrySet()){
                    if (RANDOM.nextDouble() <= entry.getValue()){
                        Location location = new Location(row, col);
                        Human human_ = HumanFactory.createHuman(entry.getKey(),field, location);
                        human.add(human_);
                        break;
                    }
                }
            }
        }
    }
}
