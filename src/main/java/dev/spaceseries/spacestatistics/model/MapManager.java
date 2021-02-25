package dev.spaceseries.spacestatistics.model;

import java.util.HashMap;
import java.util.Map;

public abstract class MapManager<K, V> implements Manager {

    /**
     * Map
     */
    private final Map<K, V> map;

    /**
     * Map manager
     *
     * @param map map
     */
    public MapManager(Map<K, V> map) {
        this.map = map;
    }

    /**
     * Construct map manager
     */
    public MapManager() {
        this.map = new HashMap<>();
    }

    /**
     * Add
     *
     * @param k k
     * @param v v
     */
    public void add(K k, V v) {
        this.map.put(k, v);
    }

    /**
     * Remove
     *
     * @param k k
     */
    public void remove(K k) {
        this.map.remove(k);
    }

    /**
     * Get
     *
     * @param k k
     */
    public V get(K k) {
        return this.map.get(k);
    }

    /**
     * Get (default)
     *
     * @param k            k
     * @param defaultValue default
     */
    public V get(K k, V defaultValue) {
        return this.map.getOrDefault(k, defaultValue);
    }

    /**
     * Returns the map
     *
     * @return map
     */
    public Map<K, V> getMap() {
        return map;
    }
}
