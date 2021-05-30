package io.muic.ooc.fab;

import java.util.List;
import java.util.Random;

public abstract class Human {


    // The fox's position.
    private Location location;
    // The field occupied.
    protected Field field;

    public void initialize(Field field, Location location) {
        this.field = field;
        setLocation(location);
    }

    public Location getLocation() {
        return location;
    }

    protected void setLocation(Location newLocation) {
        if (location != null) {
            field.clear(location);
        }
        location = newLocation;
        field.place(this, newLocation);
    }

    protected abstract Location moveToNewLocation();

    public void act(){
        // Try to move into a free location.
        Location newLocation = moveToNewLocation();
        if (newLocation != null) {
            setLocation(newLocation);
        }
    }
}
