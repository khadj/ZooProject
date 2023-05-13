package project.helper;

import project.entity.Entity;
import project.entity.animals.Animal;
import project.entity.animals.predatory.Predatory;
import project.island.Location;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface EntityBehavior {
    default Location getDestinationLocation(Location location) {
        Entity entity = (Entity) this;
        int steps = entity.getSpeed();
        for (int i = steps; i >= 0; i--) {
            List<Location> nearbyLocations = location.getNearbyLocations();
            location = nearbyLocations.get(Randomizer.getNumber(0, nearbyLocations.size()));
        }
        return location;
    }

    default Location moveSafe(Location location) {
        location.getLock().lock();

        Location destinationLocation = getDestinationLocation(location);
        Entity entity = (Entity) this;
        try {
            if (destinationLocation.isEnoughSpace(entity)) {
                return destinationLocation;
            }
        } finally {
            location.getLock().unlock();
        }
        return destinationLocation;
    }

    default Entity eatSafe(Location location) {
        location.getLock().lock();
        Animal animal = (Animal) this;
        try {
            if (animal.getNecessaryFoodForFullSatiety() < animal.getFullness()) {
                Map<String, Integer> mealList = animal.getEatingMap();
                Optional<Entity> firstVictim = location.getAnimals().stream()
                        .filter(animalCurr -> mealList.containsKey(animalCurr.getName()))
                        .findFirst();
                if (firstVictim.isPresent()) {
                    Entity victim = firstVictim.get();
                    Integer energyPercentage = mealList.get(victim.getName());
                    if (energyPercentage > 0) animal.setFullness(animal.getFullness() * 100 / energyPercentage);
                    return victim;
                } else {
//                    System.out.println("No victim found");
                }
            }
        } finally {
            location.getLock().unlock();
        }
        return null;
    }

    default Entity reproductionSafe(Location location) {
        location.getLock().lock();
        Entity entity = (Entity) this;
        try {
            Entity animalPair = location.getPair((Animal) entity);
            if (animalPair == null && location.isEnoughSpace(entity)) {
                return entity.clone();
            }
        } finally {
            location.getLock().unlock();
        }
        return entity;
    }

    default boolean dieSafe(Location location) {
//        location.getLock().lock();
        Entity entity = (Entity) this;
        try {
            if (entity.getWeight() == 0) {
                return true;
            }
        } finally {
//            location.getLock().unlock();
        }
        return false;
    }
}
