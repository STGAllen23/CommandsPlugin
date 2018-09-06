package me.stgallen.ccommands.Executors;

import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;


public class TrueExecutor implements CommandExecutor {

    public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {
        Player player = (Player) src;
            Sponge.getCommandManager().process(player, "cf interact-entity-secondary pixelmon:pc true");
            src.sendMessage(Text.of(TextColors.GREEN, "PC Interaction set to True."));
        return CommandResult.empty();
        }
    }





