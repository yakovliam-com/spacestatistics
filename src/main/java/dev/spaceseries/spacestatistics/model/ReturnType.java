package dev.spaceseries.spacestatistics.model;

import dev.spaceseries.spacestatistics.SpaceStatistics;

public enum ReturnType {
    KEY,
    VALUE;

    /**
     * Simple lookup method
     *
     * @param id  id
     * @return type
     */
    public static ReturnType lookup(String id) {
        ReturnType result;

        try {
            result = ReturnType.valueOf(id);
        } catch (IllegalArgumentException e) {
            SpaceStatistics.getInstance().getLogger().warning("Invalid return type for '" + id + "'");
            return null;
        }

        return result;
    }
}
