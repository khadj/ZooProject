package project.helper;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import project.exceptions.IslandException;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class JsonReader {
    public static List<JsonEntity> readJsonFile(String filePath){
        ObjectMapper objectMapper= new ObjectMapper(new JsonFactory());
        List<JsonEntity> jsonEntities;
        InputStream inputStream = JsonReader.class.getClassLoader().getResourceAsStream(filePath);
        try {
            jsonEntities = objectMapper.readValue(inputStream,
                    objectMapper.getTypeFactory().constructCollectionType(List.class, JsonEntity.class));
        } catch (IOException e){
            throw new IslandException(e);
        }
        return jsonEntities;
    }

    /*
    public static void main(String[] args) {
        String filePath = "entitySettings.json";
        List<JsonEntity> yamlOrganisms = JsonReader.readJsonFile(filePath);
        for (JsonEntity organism : yamlOrganisms) {
            System.out.println("Name: " + organism.getName());
            System.out.println("Icon: " + organism.getIcon());
            System.out.println("Weight: " + organism.getWeight());
            System.out.println("Max: " + organism.getMaxCountOnCell());
            System.out.println("Speed: " + organism.getSpeed());
            System.out.println("Necessary: " + organism.getNecessaryFoodForFullSatiety());
            System.out.println("EatingMap: " + organism.getEatingMap());
        }
    }
     */
}
