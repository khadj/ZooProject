package project.island;

import lombok.Getter;
import lombok.Setter;
import project.entity.Entity;
import project.entity.animals.Animal;
import project.helper.Randomizer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;
@Getter
@Setter
public class Location {
    private volatile int xPosition;
    private volatile int yPosition;
    private volatile double plant;
    private volatile List<Location> nearbyLocations = new ArrayList<>(); //!
    private volatile List<Entity> animals = new ArrayList<>(); //!
    private final Lock lock = new ReentrantLock(true); //!


    public Location(int x, int y) {
        this.xPosition = x;
        this.yPosition = y;
    }


    public boolean isEnoughSpace(Entity organism) {
        Map<Entity, Long> objectCounts = animals.stream()
                .collect(Collectors.groupingBy(animal -> animal, Collectors.counting()));

        long amountOfAnimal = objectCounts.entrySet().stream()
                .filter(entry -> organism.getClass().equals(entry.getKey().getClass()))
                .mapToLong(Map.Entry::getValue)
                .sum();

        return organism.getMaxCountOnCell() > amountOfAnimal;
    }

    public void addAnimal(Entity organism) {
        if (isEnoughSpace(organism)) animals.add(organism);
    }

    public void removeAnimal(Entity organism) {
        animals.remove(organism);
    }

    public Entity getAnimal(Animal organism) {
        Optional<Entity> foundOrganism = animals.stream()
                .filter(animal -> animal.equals(organism))
                .findFirst();

        return foundOrganism.orElse(null);
    }

    public Entity getPair(Animal organism) {
        Optional<Entity> foundOrganism = animals.stream()
                .filter(animal -> !animal.equals(organism) && animal.getClass().equals(organism.getClass()))
                .findFirst();

        return foundOrganism.orElse(null);
    }

}
