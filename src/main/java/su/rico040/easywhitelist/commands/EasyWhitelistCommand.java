package su.rico040.easywhitelist.commands;

import com.mojang.brigadier.CommandDispatcher;
import me.lucko.fabric.api.permissions.v0.Permissions;
import net.minecraft.commands.CommandSourceStack;

import static com.mojang.brigadier.arguments.StringArgumentType.getString;
import static com.mojang.brigadier.arguments.StringArgumentType.word;
import static net.minecraft.commands.Commands.argument;
import static net.minecraft.commands.Commands.literal;
import static net.minecraft.server.commands.WhitelistCommand.addPlayers;
import static net.minecraft.server.commands.WhitelistCommand.removePlayers;
import static su.rico040.easywhitelist.EasyWhitelist.getProfileFromNickname;

public class EasyWhitelistCommand {

    public static void registerCommand(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(literal("easywhitelist")
                .requires(Permissions.require("easywhitelist.commands.easywhitelist.root", 3))
                .then(literal("add")
                        .requires(Permissions.require("easywhitelist.commands.easywhitelist.add", 3))
                        .then(argument("targets", word())
                                .executes(ctx ->
                                        addPlayers(ctx.getSource(), getProfileFromNickname(getString(ctx, "targets")))
                                )
                        )
                )
                .then(literal("remove")
                        .requires(Permissions.require("easywhitelist.commands.easywhitelist.remove", 3))
                        .then(argument("targets", word())
                                .executes(ctx ->
                                        removePlayers(ctx.getSource(), getProfileFromNickname(getString(ctx, "targets"))))
                        )
                )
        );
    }

}
