package dev.spaceseries.spacestatistics.api;

import dev.spaceseries.spacestatistics.SpaceStatistics;
import dev.spaceseries.spacestatistics.model.Entry;
import dev.spaceseries.spacestatistics.model.Resolver;

import java.util.*;
import java.util.stream.Stream;

public abstract class Statistic<K, V extends Comparable<V>> implements Resolver {

    /**
     * The statistic handle
     *
     * <p>For example, if a plugin is tracking 'player kills', the author may want to
     * consider setting the handle to 'player-kills', or something similar. It acts as a
     * general identifier.</p>
     */
    private final String handle;

    /**
     * The current cached list of sorted entries
     */
    private List<Entry<K, V>> sorted;

    /**
     * A set of all of the keys that can be accessed
     *
     * <p>This set contains a list of keys for this statistic. For example, if an author was tracking
     * 'player kills', they should consider making the key type a {@link java.util.UUID}. This contain the
     * uuid of every player who has joined the server. Upon request, the plugin would sort the data
     * by using the uuid (key) and calling the {@link #get} method.</p>
     */
    private final Set<K> keys;

    /**
     * Construct statistic
     *
     * @param handle handle
     */
    public Statistic(String handle) {
        // set handle
        this.handle = handle;
        // initialize sorted list (empty, of course)
        sorted = new ArrayList<>();
        // initialize keys (empty)
        this.keys = new HashSet<>();
    }

    /**
     * Construct statistic
     *
     * @param handle handle
     */
    public Statistic(String handle, Collection<K> keys) {
        // set handle
        this.handle = handle;
        // initialize sorted list (empty, of course)
        this.sorted = new ArrayList<>();
        // initialize keys (empty)
        this.keys = new HashSet<>();

        // since we are provided keys, try and sort them!
        update(keys, true);
    }

    /**
     * Updates the sorted, cached entry list
     *
     * <p>This method is essentially a 'sort' method, but it provides other features too. If you set
     * {@code updateKeySet} to true, we will also add those keys into the cache for sorting later. If set to false,
     * the method will only update those keys that were just referenced in the {@code keys} param.</p>
     *
     * @param updateKeySet update key set
     * @param keys         keys
     */
    public void update(Collection<K> keys, boolean updateKeySet) {
        // create a master entry list of the current sorted entries
        List<Entry<K, V>> entries = new ArrayList<>(sorted);

        // loop through the provided keys (param) and update if applicable
        keys.forEach(key -> {
            // update...
            // if key exists already in the sorted entry list, remove it
            entries.removeIf(entry -> entry.getKey().equals(key));

            // now that the key was removed (if it was even present in the first place), add it to the entry list
            entries.add(new Entry<>(key, this.get(key)));
        });

        // now that everything is updated (keys, not the actual sorted cache), we can sort
        // if we are updating the actual keys of the cache (referenced in updateKeySet param)
        if (updateKeySet) {
            this.keys.addAll(keys);
        }

        // sort entries
        Collections.sort(entries);

        // assign the new sorted entries to the current cached sorted entries
        this.sorted = Collections.synchronizedList(entries);
    }

    /**
     * Removes keys from being sorted in the future
     *
     * @param keys      keys to be removed
     * @param removeNow if we want to remove the keys from the current sorted entry list as well as the key cache
     */
    public void remove(Collection<K> keys, boolean removeNow) {
        this.keys.removeIf(keys::contains);

        // if we are removing the keys defined in the keys param from the sorted list too
        if (removeNow) {
            this.sorted.removeIf(e -> keys.contains(e.getKey()));
        }
    }

    /**
     * Uses a {@link K} key to get corresponding data, which is returned as a {@link V} value
     *
     * @return data of type {@link V}
     */
    protected abstract V get(K key);

    /**
     * Allows implementation of a method that converts a key of type {@link K} into a string
     * that is parsable by other methods
     *
     * @param k key
     * @return key as string
     */
    protected abstract String keyAsString(K k);

    /**
     * Allows implementation of a method that converts a value of type {@link V} into a string
     * that is parsable by other methods
     *
     * @param v value
     * @return value as string
     */
    protected abstract String valueAsString(V v);

    /**
     * Register the plugin in the api
     */
    public void register() {
        SpaceStatistics.getInstance().getStatisticManager().register(this);
    }

    /**
     * Returns the handle of this statistic
     *
     * @return handle
     */
    public String getHandle() {
        return handle;
    }

    /**
     * Returns sorted, converted string entry list
     *
     * @return sorted
     */
    public Stream<Entry<String, String>> getSorted() {
        return sorted.stream()
                .map(e -> new Entry<>(this.keyAsString(e.getKey()), this.valueAsString(e.getValue())));
    }
}
