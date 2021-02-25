package dev.spaceseries.spacestatistics.model;

import java.util.Comparator;

public class Entry<K, V extends Comparable> implements Comparable<Entry<K, V>> {

    private static final Comparator<Entry> ENTRY_COMPARATOR = Comparator.<Entry, Comparable>comparing(o1 -> o1.value).reversed();

    /**
     * Key
     */
    private final K key;

    /**
     * Value
     */
    private final V value;

    /**
     * Construct entry
     *
     * @param key   key
     * @param value value
     */
    public Entry(K key, V value) {
        this.key = key;
        this.value = value;
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
    public int compareTo(Entry<K, V> o) {
        return ENTRY_COMPARATOR.compare(this, o);
    }
}
