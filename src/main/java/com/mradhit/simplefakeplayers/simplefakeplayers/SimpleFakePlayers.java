package com.mradhit.simplefakeplayers.simplefakeplayers;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public final class SimpleFakePlayers extends JavaPlugin {

    @Override
    public void onEnable() {
        this.saveDefaultConfig();
        FileConfiguration config = this.getConfig();
        ProtocolLibrary.getProtocolManager().addPacketListener(new PacketAdapter(this, ListenerPriority.NORMAL, PacketType.Status.Server.SERVER_INFO) {
            @Override
            public void onPacketSending(PacketEvent event) {
                if(config.getBoolean("enable-fake-max")){
                    event.getPacket().getServerPings().read(0).setPlayersMaximum(config.getInt("fake-max"));
                }
                event.getPacket().getServerPings().read(0).setPlayersOnline(config.getInt("fake-players"));
            }
        });
    }

    @Override
    public void onDisable() {
    }
}
