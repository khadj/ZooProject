package project.entity.animals.predatory;

import lombok.NoArgsConstructor;
import project.helper.JsonEntity;

@NoArgsConstructor
public class Bear extends Predatory{

    public Bear(JsonEntity jsonEntity) {
        super(jsonEntity);
    }

//    @Override
//    public String toString() {
//        return "Bear{} " + super.toString();
//    }

}
