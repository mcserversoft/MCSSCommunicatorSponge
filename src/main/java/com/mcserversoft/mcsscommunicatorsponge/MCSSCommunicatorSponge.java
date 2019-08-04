package com.mcserversoft.mcsscommunicatorsponge;

import com.google.inject.Inject;
import org.slf4j.Logger;
import org.spongepowered.api.Platform;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GameStartedServerEvent;
import org.spongepowered.api.plugin.Plugin;


@Plugin(
        id = MCSSCommunicatorSponge.ID,
        name = MCSSCommunicatorSponge.NAME,
        description = MCSSCommunicatorSponge.DESCRIPTION,
        url = MCSSCommunicatorSponge.WEBSITE,
        authors = MCSSCommunicatorSponge.AUTHORS
)
public class MCSSCommunicatorSponge {

    public static final String ID = "mcsscommunicatormod";
    public static final String NAME = "MCSSCommunicatorMod";
    public static final String WEBSITE = "https://www.mcserversoft.com";
    public static final String VERSION = "10.1";
    public static final String DESCRIPTION = "Core-element of MC Server Soft. Provides real-time diagnostics and server telemetry.";
    public static final String AUTHORS = "Fireblade115 a.k.a. Fiahblade";

    @Inject
    private Logger logger;
    private Config config;

    @Listener
    public void onServerStart(GameStartedServerEvent event) {
        this.config = new Config(logger, this);

        // Register EventListener
        Sponge.getEventManager().registerListeners(this, new EventListener(logger, config));


        logger.info("Powering your Minecraft Server since Beta 1.5");
        logger.info("> Core-element of MC Server Soft.");
        logger.info("> Provides real-time diagnostics and server telemetry.");
        logger.info(String.format("For more info visit: %s", MCSSCommunicatorSponge.WEBSITE));
        logger.info(String.format("Server version: %s", Sponge.getPlatform().getContainer(Platform.Component.IMPLEMENTATION).getVersion()));
        logger.info(String.format("MCSSCommunicatorMod version: %s", MCSSCommunicatorSponge.VERSION));
    }
}
