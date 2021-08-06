package com.yakovliam.spacestatistics.model;

import org.jetbrains.annotations.NotNull;

import java.util.Comparator;

public class Entry<K, V extends Comparable> implements Comparable<Entry<K, V>> {

    private static final Comparator<Entry> NORMAL_ENTRY_COMPARATOR = Comparator.<Entry, Comparable>comparing(o1 -> o1.value).reversed();

    /**
     * Key
     */
    private final K key;

    /**
     * Value
     */
    private final V value;

    /**
     * Sort mode
     */
    private final SortMode sortMode;

    /**
     * Construct entry
     *
     * @param key      key
     * @param value    value
     * @param sortMode sort mode
     */
    public Entry(K key, V value, SortMode sortMode) {
        this.key = key;
        this.value = value;
        this.sortMode = sortMode;
    }

    /**
     * Returns key
     *
     * @return key
     */
    public K getKey() {
        return key;
    }

    /**
     * Returns value
     *
     * @return value
     */
    public V getValue() {
        return value;
    }

    /**
     * Returns sort mode
     *
     * @return sort mode
     */
    public SortMode getSortMode() {
        return sortMode;
    }

    /**
     * Is an object equal to this entry?
     *
     * @param o object
     * @return does equal
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entry<?, ?> entry = (Entry<?, ?>) o;
        return key.equals(entry.getKey()) &&
                value.equals(entry.getValue());
    }

    /**
     * Returns hash code
     *
     * @return hash code
     */
    @Override
    public int hashCode() {
        return key.hashCode();
    }

    /**
     * Compares this entry to another object, {@code o} param
     *
     * @param o entry
     * @return compared
     */
    @Override
    public int compareTo(@NotNull Entry<K, V> o) {
        return NORMAL_ENTRY_COMPARATOR.compare(this, o);
    }
}
