package su.rico040.easywhitelist.commands;

import com.mojang.brigadier.CommandDispatcher;
import me.lucko.fabric.api.permissions.v0.Permissions;
import net.minecraft.commands.CommandSourceStack;

import static com.mojang.brigadier.arguments.StringArgumentType.getString;
import static com.mojang.brigadier.arguments.StringArgumentType.word;
import static net.minecraft.commands.Commands.argument;
import static net.minecraft.commands.Commands.literal;
import static net.minecraft.server.commands.PardonCommand.pardonPlayers;
import static su.rico040.easywhitelist.EasyWhitelist.getProfileFromNickname;

public class EasyPardonCommand {

    public static void registerCommand(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(literal("easypardon")
                .requires(Permissions.require("easywhitelist.commands.easypardon", 3))
                .then(argument("targets", word())
                        .executes(ctx ->
                                pardonPlayers(ctx.getSource(), getProfileFromNickname(getString(ctx, "targets"))))));
    }
}

