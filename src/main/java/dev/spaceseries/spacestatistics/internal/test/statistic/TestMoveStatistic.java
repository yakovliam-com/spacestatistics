package dev.spaceseries.spacestatistics.internal.test.statistic;

import dev.spaceseries.spacestatistics.api.Statistic;
import dev.spaceseries.spacestatistics.internal.test.statistic.tracker.MoveStatisticTracker;
import dev.spaceseries.spacestatistics.model.SortMode;
import org.bukkit.Bukkit;

import java.util.Collections;
import java.util.UUID;

public class TestMoveStatistic extends Statistic<UUID, Integer> {

    /**
     * Tracker
     */
    private MoveStatisticTracker tracker;

    /**
     * Construct statistic
     */
    public TestMoveStatistic(MoveStatisticTracker tracker) {
        super("test-move", SortMode.NORMAL);

        this.tracker = tracker;

        // initialize keys
        update(Collections.emptyList(), false, true);
    }

    @Override
    protected Integer get(UUID key) {
        return tracker.getDataMap().get(key);
    }

    @Override
    protected String keyAsString(UUID s) {
        return Bukkit.getOfflinePlayer(s).getName();
    }

    @Override
    protected String valueAsString(Integer integer) {
        return integer.toString();
    }
}