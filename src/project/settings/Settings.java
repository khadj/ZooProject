package project.settings;

import lombok.Getter;
import project.entity.Entity;
import project.helper.ClassFinder;
import project.helper.JsonEntity;
import project.helper.JsonReader;
import project.helper.Randomizer;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
@Getter
public class Settings {
    public static final int X_POS = 2;
    public static final int Y_POS = 2;


    private static final String ENTITY_SETTINGS_FILE = "entitySettings.json";

    public static List<Entity> generateEntities(){
        List<Entity> createdEntities = new ArrayList<>();
        List<JsonEntity> jsonEntities = JsonReader.readJsonFile(ENTITY_SETTINGS_FILE);
        List<String> classNames = new ArrayList<>();
        for (JsonEntity jsonEntity : jsonEntities) {
            classNames.add(jsonEntity.getName());
        }
        Map<String, Class<?>> classesByName = ClassFinder.findClassByName(classNames);

        for (JsonEntity jsonEntity : jsonEntities) {
            int toBeOrNotToBe = Randomizer.getNumber(0, 2);
            if (toBeOrNotToBe == 0) {
                continue;
            }

            try {
                Class<?> classByName = classesByName.get(jsonEntity.getName());
                if (classByName == null) {
                    continue;
                }

                Constructor<?> constructor = classByName.getConstructor(JsonEntity.class);
                int counter = Randomizer.getNumber(0, jsonEntity.getMaxCountOnCell());
                for (int i = 0; i < counter; i++) {
                    Entity instance = (Entity) constructor.newInstance(jsonEntity);
                    createdEntities.add(instance);
                }
            } catch (InvocationTargetException e) {
                throw new RuntimeException(e);
            } catch (NoSuchMethodException e) {
                throw new RuntimeException(e);
            } catch (InstantiationException e) {
                throw new RuntimeException(e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
        return createdEntities;
    }
}
