package me.stgallen.ccommands.Config;

import me.stgallen.ccommands.Commands;
import ninja.leaping.configurate.commented.CommentedConfigurationNode;
import ninja.leaping.configurate.hocon.HoconConfigurationLoader;
import ninja.leaping.configurate.loader.ConfigurationLoader;
import ninja.leaping.configurate.objectmapping.ObjectMappingException;

import java.io.IOException;
import java.util.Arrays;

public class Config {

    private final Commands plugin;

    private static ConfigurationLoader<CommentedConfigurationNode> loader;
    public static CommentedConfigurationNode config;

    public Config(Commands commands) throws IOException, ObjectMappingException {
        plugin = commands;
        loader = HoconConfigurationLoader.builder().setPath(plugin.defaultConf).build();
        config = loader.load();
        configCheck();
    }
    public static boolean pcEnabled;
    public static boolean pcAllowDisabled;

    public void configCheck() throws IOException, ObjectMappingException {
        if(!plugin.defaultConfFile.exists()) {
            plugin.defaultConfFile.createNewFile();
        }
        pcEnabled = check(config.getNode("pcallow", "enabled"), true, "Enable or Disable PC access inside claims.").getBoolean();
        pcAllowDisabled = check(config.getNode("togglecommand", "enabled"), true, "Toggle the 'pc allow' command.").getBoolean();

    }

    private CommentedConfigurationNode check(CommentedConfigurationNode node, Object defaultvalue, String comment) {
        if(node.isVirtual()) {
            node.setValue(Arrays.asList(defaultvalue)).setComment(comment);
        }
        return node;
    }
}

