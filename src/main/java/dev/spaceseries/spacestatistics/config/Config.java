package dev.spaceseries.spacestatistics.config;

import dev.spaceseries.spacestatistics.SpaceStatistics;

public class Config extends dev.spaceseries.spaceapi.config.obj.Config {

    public Config() {
        super(SpaceStatistics.getInstance().getSpacePlugin().getPlugin(), "config.yml");
    }
}
