package project.entity.animals;
import lombok.*;
import project.entity.Entity;
import project.helper.JsonEntity;
import project.island.Island;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

@Getter
@Setter
@NoArgsConstructor
public abstract class Animal extends Entity {
    private int x;
    private int y;
    private Island island;
    private final ThreadLocalRandom random = ThreadLocalRandom.current();
    protected Map<String, Integer> animalsEaten = new HashMap<>();
    private double fullness = 1;
    private int timeToReproduce = 3;

    public Animal(JsonEntity jsonEntity) {
        super(jsonEntity);
    }


    @Override
    public void move() {
        if (getSpeed() == 0) return;
        int turn = random.nextInt(0, 4);

        if (turn == 0) {
            y += getSpeed();
        } else if (turn == 1) {
            y -= getSpeed();
        } else if (turn == 2) {
            x += getSpeed();
        } else  x -= getSpeed();
        boundsCheck();
        if (getCountAnimalsOnCell() > getMaxCountOnCell()) {
            move();
        }
    }

    @Override
    public void eat() {
        if (getFullness() >= 100) return;
        List<Animal> listAnimals = animalsOnCellWhichCanEat(this);
        if (listAnimals.isEmpty()) return;

        int animalIndex = getRandom().nextInt(0, listAnimals.size());
        int chance = getRandom().nextInt(1, 101);
        Animal animal = listAnimals.get(animalIndex);
        if (chance < getAnimalsEaten().get(animal.getClass().getSimpleName())) {
            setFullness(getFullness() + (getNecessaryFoodForFullSatiety() / animal.getWeight()));
            if (getFullness() > 100) setFullness(100);
            animal.die();
        }
    }

    @Override
    public void reproduce() {
        if (!(getTimeToReproduce() == 0)) return;
        if (!(getCountAnimalsOnCell() == 0)) {
            getIsland().getLocker().writeLock().lock();
            Animal child = (Animal) this.clone();
            getIsland().getAnimals().add(child);
            getIsland().getLocker().writeLock().unlock();
            setTimeToReproduce(6);
        }
    }

    private void boundsCheck(){
        if (getX() > getIsland().getHeight())
            setX(getX() - getIsland().getHeight());
        else if (getX() < 0)
            setX(getX() + getIsland().getHeight());
        if (getY() > getIsland().getWidth())
            setY(getY() - getIsland().getWidth());
        else if (getY() < 0)
            setY(getY() + getIsland().getWidth());
    }

    public long getCountAnimalsOnCell() {
        return getIsland().getAnimals().stream()
                .filter(a -> a.getX() == getX() && a.getY() == getY())
                .filter(p -> p.getClass().getSimpleName().equals(this.getClass().getSimpleName()))
                .count();
    }

    public List<Animal> animalsOnCellWhichCanEat(Animal animal) {
        getIsland().getLocker().readLock().lock();
        List<Animal> animalsOnCellWhichCanEat = getIsland().getAnimals().stream()
                .filter(a -> a.getX() == animal.getX() && a.getY() == animal.getY())
                .filter(a -> getAnimalsEaten().containsKey(a.getClass().getSimpleName()))
                .toList();
        getIsland().getLocker().readLock().unlock();
        return animalsOnCellWhichCanEat;
    }
    public void die() {
        getIsland().getLocker().writeLock().lock();
        getIsland().getAnimals().remove(this);
        getIsland().getLocker().writeLock().unlock();
    }
}
