package dev.spaceseries.spacestatistics.command;

import dev.spaceseries.spaceapi.command.Command;
import dev.spaceseries.spaceapi.command.Permissible;
import dev.spaceseries.spaceapi.command.SpaceCommandSender;
import dev.spaceseries.spacestatistics.Messages;
import dev.spaceseries.spacestatistics.SpaceStatistics;

import java.util.concurrent.CompletableFuture;

@Permissible("space.statistics.reload")
public class ReloadCommand extends Command {

    public ReloadCommand() {
        super(SpaceStatistics.getInstance().getSpacePlugin().getPlugin(), "reload");
    }

    @Override
    public void onCommand(SpaceCommandSender sender, String label, String... args) {
        // run async task
        CompletableFuture.runAsync(() -> {
            try {
                // reload configurations
                SpaceStatistics.getInstance().loadConfigs();
                // load messages
                Messages.renew();
                // load statistics
                SpaceStatistics.getInstance().loadStatistics();
            } catch (Exception e) {
                Messages.getInstance().reloadFailure.msg(sender);
                e.printStackTrace();
                return;
            }
            Messages.getInstance().reloadSuccess.msg(sender);
        });
    }
}
