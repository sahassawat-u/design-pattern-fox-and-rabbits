package io.muic.ooc.fab;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class FieldStats {

    // Counters for each type of entity (fox, rabbit, etc.) in the simulation.
    private HashMap<Class, Counter> counters;
    // Whether the counters are currently up to date.
    private boolean countsValid;
    private Subject subject;
    private RabbitObserver rabbitObserver;
    private FoxObserver foxObserver;
    private TigerObserver tigerObserver;
    private HunterObserver hunterObserver;
    /**
     * Construct a FieldStats object.
     */
    public FieldStats() {
        // Set up a collection for counters for each type of animal that
        // we might find
        subject = new Subject();
        rabbitObserver = new RabbitObserver(subject);
        foxObserver = new FoxObserver(subject);
        tigerObserver = new TigerObserver(subject);
        hunterObserver = new HunterObserver(subject);
        counters = new HashMap<>();
        countsValid = true;
    }

    /**
     * Get details of what is in the field.
     *
     * @param field
     * @return A string describing what is in the field.
     */
    public String getPopulationDetails(Field field) {
        StringBuffer buffer = new StringBuffer();
        if (!countsValid) {
            generateCounts(field);
        }
        for (Class key : counters.keySet()) {
            Counter info = counters.get(key);
            String which = info.getName().substring(info.getName().lastIndexOf('.')+1);
            buffer.append(which);
            buffer.append(": ");
            if(which.equals("Rabbit")) {
                rabbitObserver.update(info.getCount());
                buffer.append(rabbitObserver.getPopulation());
            } else if(which.equals("Hunter")){
                hunterObserver.update(info.getCount());
                buffer.append(hunterObserver.getPopulation());
            } else if(which.equals("Fox")){
                foxObserver.update(info.getCount());
                buffer.append(foxObserver.getPopulation());
            } else if(which.equals("Tiger")){
                tigerObserver.update(info.getCount());
                buffer.append(tigerObserver.getPopulation());
            }
            buffer.append(' ');
        }
//        System.out.println(buffer.toString());
        return buffer.toString();
    }

    /**
     * Invalidate the current set of statistics; reset all counts to zero.
     */
    public void reset() {
        countsValid = false;
        for (Class key : counters.keySet()) {
            Counter count = counters.get(key);
            count.reset();
        }
    }

    /**
     * Increment the count for one class of animal.
     *
     * @param animalClass The class of animal to increment.
     */
    public void incrementCount(Class animalClass) {
        Counter count = counters.get(animalClass);
//        System.out.println(count);
        if (count == null) {
            // We do not have a counter for this species yet.
            // Create one.
            count = new Counter(animalClass.getName());
            counters.put(animalClass, count);
        }
        count.increment();
    }

    /**
     * Indicate that an animal count has been completed.
     */
    public void countFinished() {
        countsValid = true;
    }

    /**
     * Determine whether the simulation is still viable. I.e., should it
     * continue to run.
     *
     * @return true If there is more than one species alive.
     */
    public boolean isViable(Field field) {
        // How many counts are non-zero.
        int nonZero = 0;
        if (!countsValid) {
            generateCounts(field);
        }
        for (Class key : counters.keySet()) {
            Counter info = counters.get(key);
            if (info.getCount() > 0) {
                nonZero++;
            }
        }
        return nonZero > 1;
    }

    /**
     * Generate counts of the number of foxes and rabbits. These are not kept up
     * to date as foxes and rabbits are placed in the field, but only when a
     * request is made for the information.
     *
     * @param field The field to generate the stats for.
     */
    private void generateCounts(Field field) {
        reset();
        for (int row = 0; row < field.getDepth(); row++) {
            for (int col = 0; col < field.getWidth(); col++) {
                Object animal = field.getObjectAt(row, col);
                if (animal != null) {
                    incrementCount(animal.getClass());
                }
            }
        }
        countsValid = true;
    }
}
