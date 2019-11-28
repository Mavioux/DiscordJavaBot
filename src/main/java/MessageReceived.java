import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

//Regex Imports
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.util.List;

public class MessageReceived extends ListenerAdapter {
    private static final String INPUT = "?1d6";

    String prefix = "?";
//    String diceRegEx = "(\\?)(\\d*)(D)(\\d*)";
    String diceRegEx = ".s";

    @Override
    public void onReady(ReadyEvent event) {
        if (event instanceof ReadyEvent)
            System.out.println("API is ready!");

    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        User author = event.getAuthor();
        MessageChannel channel = event.getChannel();

        boolean bot = author.isBot();

        // Create a DicePattern object
        Pattern dicePattern = Pattern.compile(diceRegEx);
        Matcher diceMatcher = dicePattern.matcher("g");

        if(diceMatcher.matches()) {
            System.out.println("yes!");
        }

        if(!bot) {

            //Who am I Command
            if(event.isFromType(ChannelType.TEXT)) {
                Guild guild = event.getGuild();
                TextChannel textChannel = event.getTextChannel();
                Member member = event.getMember();
                Message message = event.getMessage();

                //Regex Matcher
                System.out.println(message.getContentDisplay().toLowerCase());
//                Matcher diceMatcher = dicePattern.matcher(message.getContentDisplay().toLowerCase());

                if(message.getContentDisplay().toLowerCase().equals("who am i?")) {
                    textChannel.sendMessage("You are " + member.getEffectiveName()).queue();
                }

                //Kick Command
                else if(message.getContentDisplay().toLowerCase().startsWith("?kick")) {
                    if(message.getMentionedUsers().isEmpty()) {
                        textChannel.sendMessage("Thin Air cannot be kicked").queue();
                        return;
                    }

                    Member selfMember = guild.getSelfMember();

                    if(!selfMember.hasPermission(Permission.KICK_MEMBERS)) {
                        textChannel.sendMessage("I don't have permission to kick!").queue();
                        return;
                    }

                    List<User> kickedUsersList = message.getMentionedUsers();

                    for (User user: kickedUsersList) {
                        Member kickedMember = guild.getMember(user);

                        if(!member.canInteract(kickedMember)) {
                            textChannel.sendMessage(member.getEffectiveName() + " is not higher in hierarchy than " + kickedMember.getEffectiveName() + " !").queue();
                            continue;
                        }

                        if(!selfMember.canInteract(kickedMember)) {
                            textChannel.sendMessage(kickedMember.getEffectiveName() + " is higher in hierarchy than me!").queue();
                            continue;
                        }

                        textChannel.sendMessage(kickedMember.getEffectiveName() + " got kicked by " + member.getEffectiveName() + ".").queue();
//                        guild.getController().kick(kickedMember).queue();
                    }
                }

                //Ban Command
                else if(message.getContentDisplay().toLowerCase().startsWith("?ban")) {
                    if(message.getMentionedUsers().isEmpty()) {
                        textChannel.sendMessage("Thin Air cannot be banned").queue();
                        return;
                    }

                    Member selfMember = guild.getSelfMember();

                    if(!selfMember.hasPermission(Permission.BAN_MEMBERS)) {
                        textChannel.sendMessage("I don't have permission to ban!").queue();
                        return;
                    }

                    List<User> bannedUsersList = message.getMentionedUsers();

                    for (User user: bannedUsersList) {
                        Member bannedMember = guild.getMember(user);

                        if(!member.canInteract(bannedMember)) {
                            textChannel.sendMessage(member.getEffectiveName() + " is not higher in hierarchy than " + bannedMember.getEffectiveName() + " !").queue();
                            continue;
                        }

                        if(!selfMember.canInteract(bannedMember)) {
                            textChannel.sendMessage(bannedMember.getEffectiveName() + " is higher in hierarchy than me!").queue();
                            continue;
                        }

                        textChannel.sendMessage(bannedMember.getEffectiveName() + " got kicked by " + member.getEffectiveName() + ".").queue();
//                        guild.getController().kick(bannedMember).queue();
                    }
                }

                //Roll a dice
                else if(message.getContentDisplay().toLowerCase().equals("?roll")) {
                    int roll = 1 + (int)(Math.random() * 6);
                    textChannel.sendMessage("You rolled " + roll + "!").queue();
                }

                //Roll a custom dice
//                else if(diceMatcher.matches()) {
//                    System.out.println("mphka");
//                    int numberOfDices = Integer.parseInt(diceMatch.group(1));
//                    int diceValue = Integer.parseInt(diceMatch.group(3));
//
//                    int sum = 0;
//
//                    for (int i = 0; i < numberOfDices; i++) {
//                        sum += (int)((Math.random()*((diceValue-1)+1))+1);
//                    }
//
//                    textChannel.sendMessage("You rolled " + sum + "!").queue();
//                }

                //Say a message
                else if(message.getContentDisplay().toLowerCase().startsWith("?say")) {
                    String temp = message.getId();
                    textChannel.sendMessage(message.getContentDisplay().substring(4)).queue();
                    textChannel.deleteMessageById(temp).queue();
                }

                //Champions League Standings
                else if(message.getContentDisplay().toLowerCase().equals("?clstanding")) {
                    GuruGame clGame = new GuruGame();
                    clGame.standing(textChannel);
                }

                //Add Role Command
                else if(message.getContentDisplay().toLowerCase().startsWith("?addrole")) {
                    List<Member> mentionedMembers = message.getMentionedMembers();
                    List<Role> mentionedRoles = message.getMentionedRoles();

                    if(mentionedMembers.isEmpty()) {
                        textChannel.sendMessage("No mentioned Users!").queue();
                        return;
                    }

                    for(int i  = 0; i < mentionedMembers.size(); i ++) {
                        Member tempMember = mentionedMembers.get(i);

                        if(!guild.getSelfMember().canInteract(tempMember)) {
                            textChannel.sendMessage("I am not higher in hierarchy than " + tempMember.getEffectiveName() + ".").queue();
                            return;
                        }

                        if(member.canInteract(tempMember)) {
//                            guild.getController().addRolesToMember(tempMember, mentionedRoles).queue();
                            textChannel.sendMessage("Role(s) attributed!").queue();
                        }
                        else {
                            textChannel.sendMessage("You are not higher in hierarchy than " + tempMember.getEffectiveName() + ".").queue();
                            return;
                        }
                    }
                }

                //Remove Role Command
                else if(message.getContentDisplay().toLowerCase().startsWith("?removerole")) {
                    List<Member> mentionedMembers = message.getMentionedMembers();
                    List<Role> mentionedRoles = message.getMentionedRoles();

                    if(mentionedMembers.isEmpty()) {
                        textChannel.sendMessage("No mentioned Users!").queue();
                        return;
                    }

                    if(mentionedRoles.isEmpty()) {
                        textChannel.sendMessage("No mentioned Roles!").queue();
                        return;
                    }

                    for(int i  = 0; i < mentionedMembers.size(); i ++) {
                        Member tempMember = mentionedMembers.get(i);

                        if(!guild.getSelfMember().canInteract(tempMember)) {
                            textChannel.sendMessage("I am not higher in hierarchy than " + tempMember.getEffectiveName() + ".").queue();
                            return;
                        }

                        if(member.canInteract(tempMember)) {
//                            guild.getController().removeRolesFromMember(tempMember, mentionedRoles).queue();
                            textChannel.sendMessage("Role(s) removed!").queue();
                        }
                        else {
                            textChannel.sendMessage("You are not higher in hierarchy than " + tempMember.getEffectiveName() + ".").queue();
                            return;
                        }
                    }
                }

                //

            }
        }


    }

}
