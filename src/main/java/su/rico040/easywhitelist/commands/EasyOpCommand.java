package su.rico040.easywhitelist.commands;

import com.mojang.brigadier.CommandDispatcher;
import me.lucko.fabric.api.permissions.v0.Permissions;
import net.minecraft.commands.CommandSourceStack;

import static com.mojang.brigadier.arguments.StringArgumentType.getString;
import static com.mojang.brigadier.arguments.StringArgumentType.word;
import static net.minecraft.commands.Commands.argument;
import static net.minecraft.commands.Commands.literal;
import static net.minecraft.server.commands.OpCommand.opPlayers;
import static su.rico040.easywhitelist.EasyWhitelist.getProfileFromNickname;

public class EasyOpCommand {

    public static void registerCommand(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(literal("easyop")
                .requires(Permissions.require("easywhitelist.commands.easydeop", 4))
                .then(argument("targets", word())
                        .executes(ctx ->
                                opPlayers(ctx.getSource(), getProfileFromNickname(getString(ctx, "targets"))))));
    }
}
