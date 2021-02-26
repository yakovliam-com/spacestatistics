package dev.spaceseries.spacestatistics;

import dev.spaceseries.spacestatistics.api.Statistic;
import dev.spaceseries.spacestatistics.model.MapManager;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;

public class StatisticManager extends MapManager<String, Statistic<?, ?>> {

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
        this.getMap().forEach((handle, statistic) -> {
            statistic.update(Collections.emptyList(), false, true);
        });
    }
}
