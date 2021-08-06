package com.yakovliam.spacestatistics.api.config.generic.key;

import com.yakovliam.spacestatistics.api.config.generic.adapter.ConfigurationAdapter;

import java.util.function.Function;

/**
 * Basic {@link ConfigKey} implementation.
 *
 * @param <T> the value type
 */
public class SimpleConfigKey<T> implements ConfigKey<T> {
    private final Function<? super ConfigurationAdapter, ? extends T> function;

    private int ordinal = -1;
    private boolean reloadable = true;

    SimpleConfigKey(Function<? super ConfigurationAdapter, ? extends T> function) {
        this.function = function;
    }

    @Override
    public T get(ConfigurationAdapter adapter) {
        return this.function.apply(adapter);
    }

    @Override
    public int ordinal() {
        return this.ordinal;
    }

    @Override
    public boolean reloadable() {
        return this.reloadable;
    }

    public void setOrdinal(int ordinal) {
        this.ordinal = ordinal;
    }

    public void setReloadable(boolean reloadable) {
        this.reloadable = reloadable;
    }
}
