package su.rico040.easywhitelist;

import com.mojang.authlib.GameProfile;
import net.minecraft.core.UUIDUtil;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.RegisterCommandsEvent;
import org.apache.logging.log4j.LogManager;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;
import su.rico040.easywhitelist.commands.*;

import java.util.Collection;
import java.util.Collections;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(EasyWhitelist.MODID)
public class EasyWhitelist {
    public static final String MODID = "easywhitelist";

    public static Collection<GameProfile> getProfileFromNickname(String name) {
        return Collections.singletonList(new GameProfile(UUIDUtil.createOfflinePlayerUUID(name), name));
    }

    public EasyWhitelist(IEventBus modEventBus, ModContainer modContainer) {
        LogManager.getLogger().info("[EasyWhitelist] Whitelist is now name-based.");
        NeoForge.EVENT_BUS.register(this.getClass());
    }

    @SubscribeEvent
    public static void registerCommands(RegisterCommandsEvent event){
        EasyWhitelistCommand.registerCommand(event.getDispatcher());
        EasyBanCommand.registerCommand(event.getDispatcher());
        EasyPardonCommand.registerCommand(event.getDispatcher());
        EasyOpCommand.registerCommand(event.getDispatcher());
        EasyDeOpCommand.registerCommand(event.getDispatcher());
    }
}
