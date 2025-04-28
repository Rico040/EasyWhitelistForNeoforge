package su.rico040.easywhitelist.commands;

import com.mojang.brigadier.CommandDispatcher;
import me.lucko.fabric.api.permissions.v0.Permissions;
import net.minecraft.commands.CommandSourceStack;

import static com.mojang.brigadier.arguments.StringArgumentType.getString;
import static com.mojang.brigadier.arguments.StringArgumentType.word;
import static net.minecraft.commands.Commands.argument;
import static net.minecraft.commands.Commands.literal;
import static net.minecraft.commands.arguments.MessageArgument.getMessage;
import static net.minecraft.commands.arguments.MessageArgument.message;
import static net.minecraft.server.commands.BanPlayerCommands.banPlayers;
import static su.rico040.easywhitelist.EasyWhitelist.getProfileFromNickname;

public class EasyBanCommand {

    public static void registerCommand(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(literal("easyban")
                .requires(Permissions.require("easywhitelist.commands.easyban", 3))
                .then((argument("targets", word())
                        .executes(ctx ->
                                banPlayers(ctx.getSource(), getProfileFromNickname(getString(ctx, "targets")), null)))
                        .then(argument("reason", message())
                                .executes(ctx ->
                                        banPlayers(ctx.getSource(), getProfileFromNickname(getString(ctx, "targets")), getMessage(ctx, "reason"))))));
    }
}
