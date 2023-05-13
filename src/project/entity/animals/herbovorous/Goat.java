package project.entity.animals.herbovorous;

import lombok.NoArgsConstructor;
import project.helper.JsonEntity;

@NoArgsConstructor
public class Goat extends Herbovorous {
    public Goat (JsonEntity jsonEntity){
        super(jsonEntity);
    }
//    @Override
//    public String toString() {
//        return "Goat{} " + super.toString();
//    }
}
