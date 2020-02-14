package org.acm.auth;

import net.dv8tion.jda.api.JDABuilder;
import org.acm.auth.cfg.Config;

import javax.security.auth.login.LoginException;
import java.io.IOException;



public class BotMain {

    public static void main(String[] args) throws LoginException, IOException{

        // We initialize the Config object, which holds information such as the bot's token
        // This way, sensitive information is not visible within the source code!
        Config cfg = new Config();
            JDABuilder jdaBuilder = new JDABuilder();
            jdaBuilder.setToken(cfg.getToken());
            jdaBuilder.addEventListeners(new MessageReceived());
            jdaBuilder.build();

            System.out.println("Jda finished building!");
    }

}
