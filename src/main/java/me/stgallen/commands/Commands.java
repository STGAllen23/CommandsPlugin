package me.stgallen.commands;

import com.google.inject.Inject;
import me.stgallen.commands.Config.Config;
import me.stgallen.commands.Executors.FalseExecutor;
import me.stgallen.commands.Executors.TrainerFalse;
import me.stgallen.commands.Executors.TrainerTrue;
import me.stgallen.commands.Executors.TrueExecutor;
import org.spongepowered.api.Game;
import org.spongepowered.api.command.spec.CommandSpec;
import org.spongepowered.api.config.DefaultConfig;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GameInitializationEvent;
import org.spongepowered.api.event.game.state.GameStartedServerEvent;
import org.spongepowered.api.plugin.Plugin;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.serializer.TextSerializers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Logger;
@Plugin(
        id = "customcommands",
        name = "Custom Commands!",
        version = "1.0",
        authors = {"STG_Allen"}
)
public class Commands  {

    @Inject
    Game game;

    @Inject
    public Logger logger;

    @Inject
    @DefaultConfig(sharedRoot = false)
    public Path defaultConf;

    @Inject
    @DefaultConfig(sharedRoot = false)
    public File defaultConfFile;


    @Listener
    public void onServerStart(GameStartedServerEvent event) throws IOException{
        if(Config.pcEnabled) {
            logger.info("[ExtraCommands] Started.");
        }
    }


    @Listener
    public void onInit(GameInitializationEvent e) {
        CommandSpec pctrue = CommandSpec.builder()
                .description(Text.of("Allow players to access PC's in your claim"))
                .permission("command.pc.allow")
                .executor(new TrueExecutor())
                .build();
        game.getCommandManager().register(this, pctrue, "pcallow");

        CommandSpec pcfalse = CommandSpec.builder()
                .description(Text.of("Deny players from using PC's in your claim"))
                .permission("command.pc.deny")
                .executor(new FalseExecutor())
                .build();
        game.getCommandManager().register(this, pcfalse, "pcdeny");

        CommandSpec trainerTrue = CommandSpec.builder()
                .description(Text.of("Allow players to interact with trainers in your claim."))
                .permission("command.trainer.allow")
                .executor(new TrainerTrue())
                .build();
        game.getCommandManager().register(this, trainerTrue, "trainerallow");

        CommandSpec trainerFalse = CommandSpec.builder()
                .description(Text.of("Deny players from interacting with trainers in your claim."))
                .permission("commands.trainer.deny")
                .executor(new TrainerFalse())
                .build();
        game.getCommandManager().register(this, trainerFalse, "trainerdeny");

    }

    public Text fromLegacy(String legacy) {
        return TextSerializers.FORMATTING_CODE.deserializeUnchecked(legacy);
    }

}
