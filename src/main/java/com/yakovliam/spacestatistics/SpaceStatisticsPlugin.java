package com.yakovliam.spacestatistics;

import com.yakovliam.spacestatistics.api.AbstractPlugin;
import com.yakovliam.spacestatistics.papi.SpaceStatisticsExpansion;

public final class SpaceStatisticsPlugin extends AbstractPlugin {

    /**
     * Statistic manager
     */
    private StatisticManager statisticManager;

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
        // if null, re-initialize
        if (statisticManager == null)
            statisticManager = new StatisticManager();

        // run reload method
        statisticManager.reload();
    }

    /**
     * Returns statistic manager
     *
     * @return statistic manager
     */
    public StatisticManager getStatisticManager() {
        return statisticManager;
    }
}
