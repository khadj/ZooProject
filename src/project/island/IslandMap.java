package project.island;

import project.utils.ConsoleView;

public class IslandMap {
    private final Location[][] locations;

    public IslandMap(int i, int j) {
        this.locations = new Location[i][j];
    }

    public Location[][] getLocations() {
        return locations;
    }

    public void showStatisticsOfIsland(int simulationStepNumber) {
        ConsoleView.showStatisticsOfIsland(this, simulationStepNumber);

    }

    public Location getLocation(int i, int j) {
        return locations[i][j];
    }
}
