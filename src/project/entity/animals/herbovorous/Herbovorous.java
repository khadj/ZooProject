package project.entity.animals.herbovorous;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import project.entity.animals.Animal;
import project.helper.JsonEntity;

import java.util.Map;
@NoArgsConstructor
public abstract class Herbovorous extends Animal {
    public Herbovorous(JsonEntity jsonEntity) {
        super(jsonEntity);
    }

}
