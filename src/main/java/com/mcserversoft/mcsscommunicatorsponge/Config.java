package com.mcserversoft.mcsscommunicatorsponge;

import java.nio.file.Path;

import com.google.inject.Inject;
import ninja.leaping.configurate.hocon.HoconConfigurationLoader;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.config.ConfigDir;
import org.spongepowered.api.config.DefaultConfig;
import ninja.leaping.configurate.ConfigurationNode;
import ninja.leaping.configurate.commented.CommentedConfigurationNode;
import ninja.leaping.configurate.loader.ConfigurationLoader;

import org.slf4j.Logger;

public class Config {

    private String url;
    private String serverGUID;
    private boolean debug;

    @Inject
    @DefaultConfig(sharedRoot = true)
    private Path defaultConfig;

    @Inject
    @DefaultConfig(sharedRoot = true)
    private ConfigurationLoader<CommentedConfigurationNode> configurationLoader;

    @Inject
    @ConfigDir(sharedRoot = false)
    private Path configPath;

    Config(Logger logger, MCSSCommunicatorSponge plugin) {
        try {
            Sponge.getAssetManager().getAsset(plugin, "MCSSCommunicatorSponge.conf").get().copyToFile(configPath, false, true);
            this.configurationLoader = HoconConfigurationLoader.builder().setPath(configPath).build();
            ConfigurationNode rootNode = configurationLoader.load();

            url = rootNode.getNode("general", "url", "{0}").getString();
            serverGUID = rootNode.getNode("general", "serverGUID", "{1}").getString();
            debug = rootNode.getNode("general", "debug", "false").getBoolean();

         /*
            Property urlProperty = configuration.get(Configuration.CATEGORY_GENERAL,
                    "url",
                    "\"{0}\"",
                    "URL of the mcss internal webserver");

            Property serverGUIDProperty = configuration.get(Configuration.CATEGORY_GENERAL,
                    "serverGUID",
                    "\"{1}\"",
                    "GUID of the mcss server");

            Property debugProperty = configuration.get(Configuration.CATEGORY_GENERAL,
                    "debug",
                    false,
                    "Enable or disable debugging of mcss requests");

            // trim quotes from strings
            this.url = urlProperty.getString().replace("\"", "");
            this.serverGUID = serverGUIDProperty.getString().replace("\"", "");
            this.debug = debugProperty.getBoolean();
            */

        } catch (Exception ex) {
            logger.warn(String.format("Could not read config file: (%s)", ex.getMessage()));
        }
    }

    public String getUrl() {
        return this.url;
    }

    public String getServerGUID() {
        return this.serverGUID;
    }

    public boolean getIsDebugEnabled() {
        return this.debug;
    }
}
