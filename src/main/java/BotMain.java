import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

import javax.security.auth.login.LoginException;

public class BotMain {

    public static void main(String[] args) {
        try {
            JDABuilder jdaBuilder = new JDABuilder();
            jdaBuilder.setToken(Token.getToken());
            jdaBuilder.addEventListeners(new MessageReceived());
            jdaBuilder.build();

            System.out.println("Jda finished building!");
        }
        catch(LoginException e) {
            e.printStackTrace();
        }

    }

}
