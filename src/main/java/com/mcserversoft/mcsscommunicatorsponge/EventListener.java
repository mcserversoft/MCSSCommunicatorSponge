package com.mcserversoft.mcsscommunicatorsponge;

import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.filter.Getter;
import org.spongepowered.api.event.network.ClientConnectionEvent;
import org.slf4j.Logger;

public class EventListener {

    private final EventProcessor eventProcessor;

    public EventListener(Logger logger, Config config) {
        this.eventProcessor = new EventProcessor(logger, config);
    }

    @Listener
    public void onPlayerLogin(ClientConnectionEvent.Join event, @Getter("getTargetEntity") Player player) {
        eventProcessor.playerLogin(player);
    }

    @Listener
    public void onPlayerQuit(ClientConnectionEvent.Disconnect event, @Getter("getTargetEntity") Player player) {
        eventProcessor.playerQuit(player);
    }
}
