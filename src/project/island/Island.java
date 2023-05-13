package project.island;

import lombok.Getter;
import project.entity.animals.Animal;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@Getter
public class Island {
    private final int height;
    private final int width;
    private final CopyOnWriteArrayList<Animal> animals = new CopyOnWriteArrayList<>();
    private final ReadWriteLock locker = new ReentrantReadWriteLock();


    public Island(int height, int width) {
        this.height = height;
        this.width = width;
    }

    public CopyOnWriteArrayList<Animal> getAnimals() {
        return animals;
    }
}