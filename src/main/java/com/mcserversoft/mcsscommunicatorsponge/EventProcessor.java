package com.mcserversoft.mcsscommunicatorsponge;

import com.google.gson.Gson;
import com.mcserversoft.mcsscommunicatorsponge.dto.PlayerDTO;
import org.spongepowered.api.entity.living.player.Player;
import org.slf4j.Logger;

public class EventProcessor {

    private final HTTPClient client;
    private final String serverGUID;

    public EventProcessor(Logger logger, Config config) {
        this.serverGUID = config.getServerGUID();
        this.client = new HTTPClient(logger, config);
    }

    public void playerLogin(Player player) {
        PlayerDTO playerDTO = createPlayerDTO(player);
        client.post("player/login", new Gson().toJson(playerDTO));
    }

    public void playerQuit(Player player) {
        PlayerDTO playerDTO = createPlayerDTO(player);
        client.post("player/quit", new Gson().toJson(playerDTO));
    }

    private PlayerDTO createPlayerDTO(Player player) {
        String username = player.getName();
        String uuid = player.getUniqueId().toString();
        String serverGuid = serverGUID;

        PlayerDTO playerDTO = new PlayerDTO();
        playerDTO.setUsername(username);
        playerDTO.setUUID(uuid);
        playerDTO.setServerUUID(serverGuid);

        return playerDTO;
    }
}
