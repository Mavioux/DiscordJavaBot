package org.acm.auth.cmds;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class DiceCommand extends Command{

    public DiceCommand() {
        super("Dice", "rolls n dices with m values", false);
    }

    @Override
    public void execute(MessageReceivedEvent event, String[] args) {
        event.getChannel().sendMessage("hi there!").queue();
    }
    else if(diceMatcher.find()) {
        System.out.println("mphka");

        int numberOfDices = Integer.parseInt(diceMatcher.group(2));
        int diceValue = Integer.parseInt(diceMatcher.group(4));

        System.out.println(diceMatcher.group(2));
        System.out.println(diceMatcher.group(4));


        int sum = 0;

        for (int i = 0; i < numberOfDices; i++) {
            sum += (int)((Math.random()*((diceValue-1)+1))+1);
        }

        textChannel.sendMessage("You rolled " + sum + "!").queue();
    }
}
