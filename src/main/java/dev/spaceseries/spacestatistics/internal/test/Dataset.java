package dev.spaceseries.spacestatistics.internal.test;

import java.util.Map;

public class Dataset<K, V> {

    /**
     * Set of data
     */
    private Map<K, V> set;

    /**
     * Construct dataset
     */
    public Dataset() {
    }

    /**
     * Construct dataset
     */
    public Dataset(Map<K, V> set) {
        this.set = set;
    }

    /**
     * Returns set
     *
     * @return set
     */
    public Map<K, V> getSet() {
        return set;
    }

    /**
     * Sets set
     *
     * @param set set
     */
    public void setSet(Map<K, V> set) {
        this.set = set;
    }
}
