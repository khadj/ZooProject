package project.helper;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@ToString
public class JsonEntity {
    private String name;
    private String icon;
    private double weight;
    private int maxCountOnCell;
    private int speed;
    private double necessaryFoodForFullSatiety;
    private Map<String, Integer> eatingMap = new HashMap<>();
}