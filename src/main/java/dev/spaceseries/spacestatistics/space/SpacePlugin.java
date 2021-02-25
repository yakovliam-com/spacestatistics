package dev.spaceseries.spacestatistics.space;

import dev.spaceseries.spaceapi.abstraction.plugin.BukkitPlugin;
import org.bukkit.plugin.java.JavaPlugin;

public class SpacePlugin {

    /**
     * SpaceAPI plugin
     */
    private final BukkitPlugin plugin;

    /**
     * Construct space plugin
     *
     * @param plugin plugin
     */
    public SpacePlugin(JavaPlugin plugin) {
        this.plugin = new BukkitPlugin(plugin);
    }

    /**
     * Returns spaceAPI plugin
     *
     * @return plugin
     */
    public BukkitPlugin getPlugin() {
        return plugin;
    }
}
