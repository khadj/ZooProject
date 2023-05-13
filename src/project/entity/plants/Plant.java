package project.entity.plants;

import project.entity.Entity;
import project.helper.JsonEntity;

public abstract class Plant extends Entity {
    public Plant(JsonEntity jsonEntity) {
        super(jsonEntity);
    }
}
