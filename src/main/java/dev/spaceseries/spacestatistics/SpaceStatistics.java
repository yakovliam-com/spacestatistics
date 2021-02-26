package dev.spaceseries.spacestatistics;

import dev.spaceseries.spacestatistics.command.SpaceStatisticsCommand;
import dev.spaceseries.spacestatistics.config.Config;
import dev.spaceseries.spacestatistics.config.LangConfig;
import dev.spaceseries.spacestatistics.internal.test.statistic.tracker.MoveStatisticTracker;
import dev.spaceseries.spacestatistics.papi.SpaceStatisticsExpansion;
import dev.spaceseries.spacestatistics.space.SpacePlugin;
import dev.spaceseries.spacestatistics.internal.test.statistic.TestReversedStatistic;
import dev.spaceseries.spacestatistics.internal.test.statistic.TestStatistic;
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

    /**
     * SpaceStatistics Config
     */
    private Config spaceStatisticsConfig;

    /**
     * Lang config
     */
    private LangConfig langConfig;

    @Override
    public void onLoad() {
        // assign instance
        instance = this;
    }

    @Override
    public void onEnable() {
        // assign space plugin
        spacePlugin = new SpacePlugin(this);

        // load configs
        loadConfigs();

        // register commands
        new SpaceStatisticsCommand();

        // initialize statistic manager
        loadStatistics();

        // register placeholder manager
        new SpaceStatisticsExpansion().register();

        // register test statistic
        new TestStatistic().register();
        new TestReversedStatistic().register();
        // // initialize test move statistic tracker
        // new MoveStatisticTracker();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    /**
     * Loads configs
     */
    public void loadConfigs() {
        // load configs
        spaceStatisticsConfig = new Config();
        langConfig = new LangConfig();
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

    /**
     * Returns space statistics config
     *
     * @return config
     */
    public Config getSpaceStatisticsConfig() {
        return spaceStatisticsConfig;
    }

    /**
     * Returns lang config
     *
     * @return lang config
     */
    public LangConfig getLangConfig() {
        return langConfig;
    }
}
