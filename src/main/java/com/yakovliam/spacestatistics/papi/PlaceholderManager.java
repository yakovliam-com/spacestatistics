package com.yakovliam.spacestatistics.papi;

import com.yakovliam.spacestatistics.SpaceStatisticsPlugin;
import com.yakovliam.spacestatistics.StatisticManager;
import com.yakovliam.spacestatistics.model.Manager;
import com.yakovliam.spacestatistics.model.ReturnType;
import com.yakovliam.spacestatistics.api.Statistic;
import com.yakovliam.spacestatistics.model.Entry;

import java.util.stream.Stream;

public class PlaceholderManager implements Manager {

    /**
     * Static error parsing message
     */
    private static final String ERROR = "&c\u274C Error parsing! \u274C";

    /**
     * Space statistics plugin
     */
    private final SpaceStatisticsPlugin plugin;

    /**
     * Placeholder manager
     *
     * @param plugin plugin
     */
    public PlaceholderManager(SpaceStatisticsPlugin plugin) {
        this.plugin = plugin;
    }


    /**
     * Parse arguments into a result via placeholders
     *
     * @param statisticName statistic name
     * @param returnType    return type
     * @param placeNumber   place number
     * @return result
     */
    public String parse(String statisticName, String returnType, String placeNumber) {
        // is the return type valid?
        ReturnType type;

        try {
            type = ReturnType.valueOf(returnType.toUpperCase());
        } catch (Exception ignored) {
            plugin.getLogger().warning("Invalid return type for '" + returnType + "'");
            return ERROR;
        }

        // parse place number into integer
        int place;
        try {
            place = Integer.parseInt(placeNumber);
        } catch (Exception ignored) {
            plugin.getLogger().warning("Invalid place number for '" + placeNumber + "'");
            return ERROR;
        }

        // find the statistic with the provided name
        Statistic<?, ?> found = StatisticManager.getInstance().find(statisticName).orElse(null);
        if (found == null) {
            plugin.getLogger().warning("Invalid statistic name (handle) for '" + statisticName + "'");
            return ERROR;
        }

        // return data
        Stream<Entry<String, String>> sorted = found.getSorted();

        // output value
        String output;

        // switch case for return type
        switch (type) {
            case KEY:
                // key
                output = sorted.skip(place - 1).map(Entry::getKey).findFirst().orElse(ERROR);
                break;
            case VALUE:
                // value
                output = sorted.skip(place - 1).map(Entry::getValue).findFirst().orElse(ERROR);
                break;
            default:
                plugin.getLogger().warning("Invalid return type for '" + type + "'");
                return ERROR;
        }

        return output;
    }
}
