package dev.spaceseries.spacestatistics;

import dev.spaceseries.spaceapi.config.impl.Configuration;
import dev.spaceseries.spaceapi.text.Message;

public class Messages {

    /**
     * Instance of this class
     */
    private static Messages instance;

    /**
     * Gets instance of messages class
     * <p>(Singleton)</p>
     *
     * @return messages
     */
    public static Messages getInstance() {
        if (instance == null)
            instance = new Messages();
        return instance;
    }


    /**
     * Renews the messages
     */
    public static void renew() {
        instance = null;
    }

    /* General */

    // help
    public Message generalHelp = Message.fromConfigurationSection(getLangConfiguration().getSection("general.help"), "general.help")
            .build();

    /* Reload */

    // success
    public Message reloadSuccess = Message.fromConfigurationSection(getLangConfiguration().getSection("reload.success"), "reload.success")
            .build();

    // failure
    public Message reloadFailure = Message.fromConfigurationSection(getLangConfiguration().getSection("reload.failure"), "reload.failure")
            .build();

    /**
     * Gets the lang configuration from the main class
     *
     * @return The lang configuration
     */
    private Configuration getLangConfiguration() {
        return SpaceStatistics.getInstance().getLangConfig().getConfig();
    }
}

