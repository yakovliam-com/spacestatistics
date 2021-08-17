package com.yakovliam.spacestatistics;

import com.yakovliam.spacestatistics.api.AbstractPlugin;
import com.yakovliam.spacestatistics.papi.SpaceStatisticsExpansion;

public final class SpaceStatisticsPlugin extends AbstractPlugin {

    @Override
    public void onEnable() {
        // initialize statistic manager
        loadStatistics();

        // register placeholder manager
        new SpaceStatisticsExpansion(this).register();
    }

    /**
     * Loads statistics
     */
    public void loadStatistics() {
        StatisticManager.getInstance().reload();
    }
}
