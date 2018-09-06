package me.stgallen.ccommands.Executors;

import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.command.TabCompleteEvent;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;

public class TrainerFalse implements CommandExecutor {
    @Override
    public CommandResult execute(CommandSource src, CommandContext args) {
        Player player = (Player) src;
        src.sendMessage(Text.of(TextColors.GREEN, "Trainer interaction Denied."));
        Sponge.getCommandManager().process(player, "cf interact-entity-secondary pixelmon:trainer false");
        return CommandResult.success();
    }
}
