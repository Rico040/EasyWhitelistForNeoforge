package su.rico040.easywhitelist.commands;

import com.mojang.brigadier.CommandDispatcher;
import me.lucko.fabric.api.permissions.v0.Permissions;
import net.minecraft.commands.CommandSourceStack;

import static com.mojang.brigadier.arguments.StringArgumentType.getString;
import static com.mojang.brigadier.arguments.StringArgumentType.word;
import static net.minecraft.commands.Commands.argument;
import static net.minecraft.commands.Commands.literal;
import static net.minecraft.server.commands.DeOpCommands.deopPlayers;
import static su.rico040.easywhitelist.EasyWhitelist.getProfileFromNickname;

public class EasyDeOpCommand {

    public static void registerCommand(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(literal("easydeop")
                .requires(Permissions.require("easywhitelist.commands.easydeop", 4))
                .then(argument("targets", word())
                        .executes(ctx ->
                                deopPlayers(ctx.getSource(), getProfileFromNickname(getString(ctx, "targets"))))));
    }
}
