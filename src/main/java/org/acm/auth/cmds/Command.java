package org.acm.auth.cmds;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public abstract class Command {
    private String name;
    private String description;
    private boolean guildOnly;
    private Permission[] botPerms;
    private Permission[] userPerms;


    public Command(String name, String description, boolean guildOnly, Permission[] botPerms, Permission[] userPerms) {
        this.name = name;
        this.description = description;
        this.guildOnly = guildOnly;
        this.botPerms = botPerms;
        this.userPerms = userPerms;
    }

    public Command(String name, String description, boolean guildOnly) {
        this.name = name;
        this.description = description;
        this.guildOnly = guildOnly;
        this.botPerms = new Permission[]{};
        this.userPerms = new Permission[]{};
    }

    /**
     * Returns the name that will show up in help and usage messages.
     * @return the command's name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the extensive description to be shown in help messages.
     * @return the command's description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns whether this command should only be used in guilds and not in private.
     * @return  true    if the command should only be called in guild channels,
     *          false   if the command can be used both in guild and private channels
     */
    public boolean isGuildOnly() {
        return guildOnly;
    }

    /**
     * Returns a list of permissions needed by the bot in order to execute the command.
     * @return an array with the permissions required by the bot
     */
    public Permission[] getBotPerms() {
        return botPerms;
    }

    /**
     * Returns a list of permissions needed by the command issuer in order to execute the command.
     * @return an array with the permissions required by the command issuer
     */
    public Permission[] getUserPerms() {
        return userPerms;
    }

    /**
     * This method gets called when the {@link org.acm.auth.CmdRegistry CmdRegistry}
     * detects that the command has been called by a user.
     * @param event The {@link MessageReceivedEvent} event that was fired when this command was called
     * @param args The user's arguments that accompanied this command after its label invocation
     */
    public abstract void execute(MessageReceivedEvent event, String[] args);
}
