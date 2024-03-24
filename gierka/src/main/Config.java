package main;

import javax.imageio.ImageIO;
import java.io.*;
import java.util.Objects;

public class Config {

    GamePanel gp;

    public Config(GamePanel gp) {
        this.gp = gp;
    }

    public void saveConfig() {

        try {
            File file = new File("../projekt/gierka/res/config.txt");
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));

            // Game info
            if (gp.keyH.showGameInfo == true) {
                bw.write("On");
            }
            if (gp.keyH.showGameInfo == false) {
                bw.write("Off");
            }
            bw.newLine();

            // Głośność muzyki
            bw.write(String.valueOf(gp.music.volumeScale));
            bw.newLine();

            // Głośność dźwięków
            bw.write(String.valueOf(gp.se.volumeScale));
            bw.newLine();

            bw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadConfig() {

        try {
            File file = new File("../projekt/gierka/res/config.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String s = br.readLine();

            // Game info
            if (s.equals("On")) {
                gp.keyH.showGameInfo = true;
            }
            if (s.equals("Off")) {
                gp.keyH.showGameInfo = false;
            }

            // Głośność muzyki
            s = br.readLine();
            gp.music.volumeScale = Integer.parseInt(s);

            // Głośność dźwięków
            s = br.readLine();
            gp.se.volumeScale = Integer.parseInt(s);

            br.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
