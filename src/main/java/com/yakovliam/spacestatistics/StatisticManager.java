package com.yakovliam.spacestatistics;

import com.yakovliam.spacestatistics.api.Statistic;
import com.yakovliam.spacestatistics.model.MapManager;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;

public class StatisticManager extends MapManager<String, Statistic<?, ?>> {

    /**
     * Instance (singleton)
     */
    private static StatisticManager instance;

    /**
     * Returns the statistic manager
     * <p>
     * Singleton
     *
     * @return statistics manager
     */
    public static StatisticManager getInstance() {
        if (instance == null) {
            instance = new StatisticManager();
        } else {
            return instance;
        }

        return instance;
    }

    /**
     * Exposed register method
     *
     * @param statistic statistic to register
     */
    public void register(Statistic<?, ?> statistic) {
        this.add(statistic.getHandle(), statistic);
    }

    /**
     * Find a statistic with the provided handle
     *
     * @param handle handle
     * @return statistic
     */
    public Optional<? extends Statistic<?, ?>> find(String handle) {
        return this.getMap().entrySet().stream()
                .filter(e -> e.getKey().equals(handle))
                .map(Map.Entry::getValue)
                .findFirst();
    }

    /**
     * Reload all statistics (clear sorted, and re-sort)
     */
    public void reload() {
        this.getMap().forEach((handle, statistic) -> statistic.update(Collections.emptyList(), false, true));
    }
}
