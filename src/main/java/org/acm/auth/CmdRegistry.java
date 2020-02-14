package org.acm.auth;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.acm.auth.cmds.Command;
import org.acm.auth.cmds.DiceCommand;
import org.acm.auth.cmds.HelloCommand;
import org.acm.auth.cmds.KickCommand;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


public class CmdRegistry extends ListenerAdapter{
    private String prefix; //The bot's global prefix
    private Map<String, Command> commands;

    public CmdRegistry(String prefix) {
        this.prefix = prefix;
        registerCommands();
    }

    private void registerCommands() {
        commands = new HashMap<>();
        commands.put("diceCommand", new DiceCommand());
    }

    @Override
    public void onMessagedReceived(MessageReceivedEvent event) {
        if(event.getAuthor().isBot()) {
            //Prevents reaction to bot commands
            return;
        }

        String


    }
}
