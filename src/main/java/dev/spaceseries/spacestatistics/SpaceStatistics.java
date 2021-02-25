package dev.spaceseries.spacestatistics;

import dev.spaceseries.spacestatistics.papi.SpaceStatisticsExpansion;
import dev.spaceseries.spacestatistics.space.SpacePlugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class SpaceStatistics extends JavaPlugin {

    /**
     * Instance of {@link SpaceStatistics} class (singleton)
     */
    private static SpaceStatistics instance;

    /**
     * Space plugin api instance
     */
    private SpacePlugin spacePlugin;

    /**
     * Statistic manager
     */
    private StatisticManager statisticManager;

    @Override
    public void onLoad() {
        // assign instance
        instance = this;
    }

    @Override
    public void onEnable() {
        // assign space plugin
        spacePlugin = new SpacePlugin(this);

        // initialize statistic manager
        statisticManager = new StatisticManager();

        // register placeholder manager
        new SpaceStatisticsExpansion().register();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    /**
     * Returns instance
     *
     * @return instance
     */
    public static SpaceStatistics getInstance() {
        return instance;
    }

    /**
     * Returns space plugin
     *
     * @return plugin
     */
    public SpacePlugin getSpacePlugin() {
        return spacePlugin;
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
