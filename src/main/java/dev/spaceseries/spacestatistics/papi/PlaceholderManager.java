package dev.spaceseries.spacestatistics.papi;

import dev.spaceseries.spacestatistics.SpaceStatistics;
import dev.spaceseries.spacestatistics.api.Statistic;
import dev.spaceseries.spacestatistics.model.Entry;
import dev.spaceseries.spacestatistics.model.Manager;
import dev.spaceseries.spacestatistics.model.ReturnType;

import java.util.stream.Stream;

public class PlaceholderManager implements Manager {

    /**
     * Static error parsing message
     */
    private static final String ERROR = "&c\u274C Error parsing! \u274C";


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
        ReturnType type = ReturnType.lookup(returnType);
        // if type null, return (not valid)
        if (type == null) return ERROR;

        // parse place number into integer
        int place;
        try {
            place = Integer.parseInt(placeNumber);
        } catch (Exception ignored) {
            SpaceStatistics.getInstance().getLogger().warning("Invalid place number for '" + placeNumber + "'");
            return null;
        }

        // find the statistic with the provided name
        Statistic<?, ?> found = SpaceStatistics.getInstance().getStatisticManager().find(statisticName).orElse(null);
        if (found == null) {
            SpaceStatistics.getInstance().getLogger().warning("Invalid statistic name (handle) for '" + statisticName + "'");
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
                output = sorted.skip(Integer.parseInt(placeNumber) - 1).map(Entry::getKey).findFirst().orElse(ERROR);
                break;
            case VALUE:
                // value
                output = sorted.skip(Integer.parseInt(placeNumber) - 1).map(Entry::getValue).findFirst().orElse(ERROR);
                break;
            default:
                SpaceStatistics.getInstance().getLogger().warning("Invalid return type for '" + type + "'");
                return ERROR;
        }

        return output;
    }
}
