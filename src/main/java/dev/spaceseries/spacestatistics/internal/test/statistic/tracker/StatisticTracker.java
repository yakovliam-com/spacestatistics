package dev.spaceseries.spacestatistics.internal.test.statistic.tracker;

import dev.spaceseries.spacestatistics.SpaceStatistics;
import dev.spaceseries.spacestatistics.api.Statistic;
import org.bukkit.event.Listener;

import java.util.UUID;

public abstract class StatisticTracker<T extends Statistic<UUID, Integer>> implements Listener {

    /**
     * Tracked statistic
     */
    private T tracked;

    /**
     * Construct statistic tracker
     */
    public StatisticTracker() {
        // register
        SpaceStatistics.getInstance().getServer().getPluginManager().registerEvents(this, SpaceStatistics.getInstance());
    }

    /**
     * Tracked
     *
     * @param tracked tracked
     */
    public void registerTracked(T tracked) {
        this.tracked = tracked;
    }

    /**
     * Returns tracked
     *
     * @return tracked
     */
    public T getTracked() {
        return tracked;
    }
}
