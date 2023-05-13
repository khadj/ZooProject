import project.entity.Entity;
import project.helper.Simulation;
import project.island.IslandGenerator;
import project.island.IslandMap;
import project.island.Location;
import project.settings.Settings;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        IslandGenerator island = new IslandGenerator(Settings.X_POS, Settings.Y_POS);

        IslandMap islandMap = island.getIslandMap();
        for (int i = 1; i <= 3; i++) {
            Simulation.startSimulation(islandMap);
            islandMap.showStatisticsOfIsland(i);
        }
    }
}