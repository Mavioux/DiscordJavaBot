import net.dv8tion.jda.api.entities.TextChannel;


public class GuruGame {

    public GuruGame() {;}

    void standing(TextChannel textChannel)
    {
        GuruPlayer alexPak = new GuruPlayer("AlexPak\t\t\t\t\t\t     ", 4544);
        GuruPlayer strik = new GuruPlayer("strik\t\t\t\t\t\t\t       ", 3104);
        GuruPlayer mavioux = new GuruPlayer("Mavioux\t\t\t\t\t\t   ", (3681 - 321));
        GuruPlayer gab = new GuruPlayer("Theofanis Gavriilidis\t ", 2794);
        GuruPlayer dinos = new GuruPlayer("Dinos Papakostas\t      ", 2678);
        GuruPlayer joanEst = new GuruPlayer("JoanEst\t\t\t\t\t\t     ", 3369);
        GuruPlayer makispaiktis = new GuruPlayer("makispaiktis\t\t\t\t    ", 2973);
        GuruPlayer georgeyiann = new GuruPlayer("george yiannakos\t       ", 1937);
        GuruPlayer grister = new GuruPlayer("grister\t\t\t\t\t\t\t    ", 842);

        GuruPlayer[] array = new GuruPlayer[] {makispaiktis, grister, gab, dinos, joanEst, alexPak, strik, georgeyiann, mavioux};

        for(int i = 0; i < 9; i++) {
            for(int j = i; j < 9; j++){
                if(array[i].points < array[j].points){
                    GuruPlayer temp;
                    temp = array[i];
                    array[i] = array[j];
                    array[j] = temp;
                }
            }
        }

        textChannel.sendMessage("1) " + array[0].name + " " + array[0].points + "\n" +
                "2) " + array[1].name + " " + array[1].points + "\n" +
                "3) " + array[2].name + " " + array[2].points + "\n" +
                "4) " + array[3].name + " " + array[3].points + "\n" +
                "5) " + array[4].name + " " + array[4].points + "\n" +
                "6) " + array[5].name + " " + array[5].points + "\n" +
                "7) " + array[6].name + " " + array[6].points + "\n" +
                "8) " + array[7].name + " " + array[7].points + "\n" +
                "9) " + array[8].name + " " + array[8].points).queue();;


    }
}
