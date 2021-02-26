package dev.spaceseries.spacestatistics.command;

import dev.spaceseries.spaceapi.command.Command;
import dev.spaceseries.spaceapi.command.Permissible;
import dev.spaceseries.spaceapi.command.SpaceCommandSender;
import dev.spaceseries.spacestatistics.Messages;
import dev.spaceseries.spacestatistics.SpaceStatistics;

@Permissible("space.statistics")
public class SpaceStatisticsCommand extends Command {

    public SpaceStatisticsCommand() {
        super(SpaceStatistics.getInstance().getSpacePlugin().getPlugin(), "spacestatistics");

        // add sub commands
        addSubCommands(
                new ReloadCommand()
        );
    }

    @Override
    public void onCommand(SpaceCommandSender sender, String s, String... args) {
        // send help message
        Messages.getInstance().generalHelp.msg(sender);
    }
}