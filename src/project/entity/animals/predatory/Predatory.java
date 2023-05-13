package project.entity.animals.predatory;

import lombok.NoArgsConstructor;
import project.entity.animals.Animal;
import project.helper.JsonEntity;

import java.util.Map;
@NoArgsConstructor
public abstract class Predatory extends Animal {

    public Predatory(JsonEntity jsonEntity) {
        super(jsonEntity);
    }
}
