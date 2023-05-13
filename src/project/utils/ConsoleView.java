package project.utils;

import project.entity.Entity;
import project.island.IslandMap;
import project.island.Location;
import project.settings.Settings;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ConsoleView {
    public static void showStatisticsOfIsland(IslandMap islandMap, int simulationStepNumber) {

        System.out.printf("\uD83D\uDDD3️ day: %d%n", simulationStepNumber);
        Location[][] locations = islandMap.getLocations();
        for (int i = 0; i < Settings.X_POS; i++) {
            for (int j = 0; j < Settings.Y_POS; j++) {
                System.out.print("\n" + String.format("cell[%d][%d] = ", i, j));
                Location location = locations[i][j];
                List<Entity> animals = location.getAnimals();
                Map<Entity, Long> objectCounts = animals.stream()
                        .collect(Collectors.groupingBy(animal -> animal, Collectors.counting()));
                objectCounts.forEach((s, aLong) -> System.out.printf("{%s = %d}", s.getIcon(), aLong));
            }
        }
        System.out.println();
        System.out.println("===========================================================================");
        System.out.println();

    }
}

