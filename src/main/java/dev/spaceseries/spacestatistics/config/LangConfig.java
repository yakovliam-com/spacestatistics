package dev.spaceseries.spacestatistics.config;

import dev.spaceseries.spacestatistics.SpaceStatistics;

public class LangConfig extends dev.spaceseries.spaceapi.config.obj.Config {

    public LangConfig() {
        super(SpaceStatistics.getInstance().getSpacePlugin().getPlugin(), "lang.yml");
    }
}