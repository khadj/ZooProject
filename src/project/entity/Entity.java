package project.entity;

import lombok.*;
import project.helper.JsonEntity;
import project.helper.EntityBehavior;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@ToString
public abstract class Entity implements EntityBehavior, Cloneable{
    private String name;
    private String icon;
    private double weight;
    private int maxCountOnCell;
    private int speed;
    private double necessaryFoodForFullSatiety;
    private Map<String, Integer> eatingMap = new HashMap<>();
    private final Lock lock = new ReentrantLock(true);


    public Entity(JsonEntity jsonEntity) {
        this.name = jsonEntity.getName();
        this.icon = jsonEntity.getIcon();
        this.weight = jsonEntity.getWeight();
        this.maxCountOnCell = jsonEntity.getMaxCountOnCell();
        this.speed = jsonEntity.getSpeed();
        this.necessaryFoodForFullSatiety = jsonEntity.getNecessaryFoodForFullSatiety();
        this.eatingMap = jsonEntity.getEatingMap();
    }

    @Override
    public Entity clone() {
        try {
            // TODO: copy mutable state here, so the clone can't change the internals of the original
            return (Entity) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError("Cloning failed ", e);
        }
    }

    public void move(){
    }

    public void eat(){
    }

    public void reproduce(){
    }

}