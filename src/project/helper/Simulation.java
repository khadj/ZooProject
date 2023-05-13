package project.helper;

import project.entity.Entity;
import project.entity.animals.Animal;
import project.entity.plants.Grass;
import project.exceptions.IslandException;
import project.island.IslandMap;
import project.island.Location;
import project.settings.Settings;

import java.util.ArrayList;
import java.util.List;

public class Simulation{
    public static void startSimulation(IslandMap island) {
        Location[][] locations = island.getLocations();
        for (int i = 0; i < Settings.X_POS; i++) {
            for (int j = 0; j < Settings.Y_POS; j++) {
                Location location = locations[i][j];
                List<Entity> animals = location.getAnimals();
                List<Entity> newAnimals = new ArrayList<>(); // Store new organisms
                List<Entity> deadAnimals = new ArrayList<>(); // Store dead organisms
                try {
                    for (Entity organism : animals) {
                        if (organism instanceof Grass) {
                            newAnimals.add(organism.reproductionSafe(location));
                            if (organism.dieSafe(location)) {
                                deadAnimals.add(organism);
                            }
                        } else {
                            Entity victim = organism.eatSafe(location);
                            if (victim != null) {
                                deadAnimals.add(victim);
                            }
                            organism.moveSafe(location);
                            newAnimals.add(organism.reproductionSafe(location));
                            if (organism.dieSafe(location)) {
                                deadAnimals.add(organism);
                            }
                        }
                    }
                    animals.removeAll(deadAnimals);
                    animals.addAll(newAnimals);
                    location.setAnimals(animals);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
/*
    private final Entity organism;

    public Simulation(Entity organism) {
        this.organism = organism;
    }
    @Override
    public void run() {
        organism.getLock().lock();

        try {
            if (organism instanceof Animal pitcher) {
                organism.eat();
                organism.reproduce();
                ((Animal) organism).die();
            } else if (organism instanceof Animal plant) {
                organism.reproduce();
                ((Animal) organism).die();
            } else {
                organism.eat();
                organism.move();
                organism.reproduce();
                ((Animal) organism).die();
            }
        } catch (Exception e) {
            throw new IslandException(e);
        } finally {
            organism.getLock().unlock();
        }
    }
 */
}
