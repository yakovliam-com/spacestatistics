package com.yakovliam.spacestatistics.papi;

import com.yakovliam.spacestatistics.SpaceStatisticsPlugin;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;

/**
 * This class will automatically register as a placeholder expansion
 * when a jar including this class is added to the directory
 * {@code /plugins/PlaceholderAPI/expansions} on your server.
 * <br>
 * <br>If you create such a class inside your own plugin, you have to
 * register it manually in your plugins {@code onEnable()} by using
 * {@code new SpaceStatisticsExpansion().register();}
 */
public class SpaceStatisticsExpansion extends PlaceholderExpansion {

    /**
     * Placeholder manage
     */
    private final PlaceholderManager placeholderManager;

    /**
     * Space statistics plugin
     */
    private final SpaceStatisticsPlugin plugin;

    /**
     * Construct expansion
     *
     * @param plugin plugin
     */
    public SpaceStatisticsExpansion(SpaceStatisticsPlugin plugin) {
        this.plugin = plugin;
        this.placeholderManager = new PlaceholderManager(plugin);
    }

    /**
     * This method should always return true unless we
     * have a dependency we need to make sure is on the server
     * for our placeholders to work!
     *
     * @return always true since we do not have any dependencies.
     */
    @Override
    public boolean canRegister() {
        return true;
    }

    /**
     * The name of the person who created this expansion should go here.
     *
     * @return The name of the author as a String.
     */
    @Override
    public @NotNull String getAuthor() {
        return String.join(", ", plugin.getDescription().getAuthors());
    }

    /**
     * The placeholder identifier should go here.
     * <br>This is what tells PlaceholderAPI to call our onRequest
     * method to obtain a value if a placeholder starts with our
     * identifier.
     * <br>The identifier has to be lowercase and can't contain _ or %
     *
     * @return The identifier in {@code %<identifier>_<value>%} as String.
     */
    @Override
    public @NotNull String getIdentifier() {
        return "spacestatistics";
    }

    /**
     * This is the version of this expansion.
     * <br>You don't have to use numbers, since it is set as a String.
     *
     * @return The version as a String.
     */
    @Override
    public @NotNull String getVersion() {
        return plugin.getDescription().getVersion();
    }

    /**
     * This is the method called when a placeholder with our identifier
     * is found and needs a value.
     * <br>We specify the value identifier in this method.
     * <br>Since version 2.9.1 can you use OfflinePlayers in your requests.
     *
     * @param player     A {@link org.bukkit.OfflinePlayer OfflinePlayer}.
     * @param identifier A String containing the identifier/value.
     * @return Possibly-null String of the requested identifier.
     */
    @Override
    public String onRequest(OfflinePlayer player, String identifier) {
        // <stat-name>_key_<place #>
        // if the identifier doesn't contain a _, it's the wrong format
        if (!identifier.contains("_")) {
            return null;
        }

        // split by _
        String[] parts = identifier.split("_");

        // if the part size is not 3, then it's the wrong format
        if (parts.length < 3) {
            return null;
        }

        // assign to vars
        String statisticName = parts[0];
        String returnType = parts[1];
        String placeNumber = parts[2];

        // return result
        return this.placeholderManager.parse(statisticName, returnType, placeNumber);
    }
}
