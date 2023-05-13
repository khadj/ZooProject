package project.island;

import lombok.Getter;
import project.entity.Entity;
import project.helper.JsonEntity;
import project.helper.JsonReader;
import project.settings.Settings;

import java.util.List;
import java.util.Map;
@Getter
public class IslandGenerator {
    private final IslandMap islandMap;

    public IslandGenerator(int x, int y) {
        this.islandMap = new IslandMap(x, y);
        generateLocation();
    }




    public void generateLocation() {
        Location[][] locations = islandMap.getLocations();
        for (int i = 0; i < locations.length; i++) {
            for (int j = 0; j < locations[i].length; j++) {
                Location location = new Location(i, j);
                locations[i][j] = location;
                generateOrganisms(location);
                initNearbyLocations(location);
            }
        }
    }
    private void initNearbyLocations(Location location) {
        int yLeft = Math.max(location.getYPosition() - 1, 0);
        int xDown = Math.min(location.getXPosition() + 1, Settings.X_POS - 1);
        int yRight = Math.min(location.getYPosition() + 1, Settings.Y_POS - 1);
        int xUp = Math.max(location.getXPosition() - 1, 0);

        for (int i = xUp; i < xDown ; i++) {
            for (int j = yLeft; j < yRight; j++) {
                location.getNearbyLocations().add(islandMap.getLocation(i,j));
            }
        }
    }

    private void generateOrganisms(Location location) {
        List<Entity> entities = Settings.generateEntities();
        generateEatingMenu(entities);
        location.setAnimals(entities);
    }

    private void generateEatingMenu(List<Entity> entities) {
        String filePath = "entitySettings.json";
        List<JsonEntity> jsonEntities = JsonReader.readJsonFile(filePath);
        for (Entity entity : entities) {
            for (JsonEntity jsonEntity : jsonEntities) {
                entity.setEatingMap(jsonEntity.getEatingMap());
            }
        }
    }
}
