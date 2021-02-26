package dev.spaceseries.spacestatistics.internal.test.statistic.tracker;

import dev.spaceseries.spaceapi.command.BukkitSpaceCommandSender;
import dev.spaceseries.spaceapi.text.Message;
import dev.spaceseries.spacestatistics.Messages;
import dev.spaceseries.spacestatistics.internal.test.statistic.TestMoveStatistic;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class MoveStatisticTracker extends StatisticTracker<TestMoveStatistic> {

    /**
     * Data map
     */
    private final Map<UUID, Integer> dataMap;

    /**
     * Construct statistic tracker
     **/
    public MoveStatisticTracker() {
        // register tracked
        this.registerTracked(new TestMoveStatistic(this));

        // initialize data map
        this.dataMap = new HashMap<>();

        // register
        this.getTracked().register();
    }

    /**
     * Returns data map
     *
     * @return data map
     */
    public Map<UUID, Integer> getDataMap() {
        return dataMap;
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Location from = event.getFrom();
        Location to = event.getTo();

        if (to == null) return;

        // if they moved a whole block, update with +1
        if ((from.getBlockX() != to.getBlockX() || from.getBlockZ() != to.getBlockZ() || from.getBlockY() != to.getBlockY())) {
            Message.builder("test-move")
                    .addLine("&7You moved &e1 &7block. You now have a total of &b" + this.dataMap.get(event.getPlayer().getUniqueId()) + " &7blocks.")
                    .build().msg(new BukkitSpaceCommandSender(event.getPlayer()));
            // update, add +1
            this.dataMap.put(event.getPlayer().getUniqueId(), this.dataMap.get(event.getPlayer().getUniqueId()) + 1);
            // re-sort //TODO this is not a great way to do this- a better way would to have a scheduled task that sorts
            this.getTracked().update(Collections.singletonList(event.getPlayer().getUniqueId()), false, true);
        }
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        // add to cache
        this.dataMap.put(event.getPlayer().getUniqueId(), 0);
        // add to track cache
        this.getTracked().update(Collections.singletonList(event.getPlayer().getUniqueId()), true, true);
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        // remove from cache
        this.getTracked().remove(Collections.singletonList(event.getPlayer().getUniqueId()), true);
        // update
        this.getTracked().update(Collections.emptyList(), false, true);
    }
}
