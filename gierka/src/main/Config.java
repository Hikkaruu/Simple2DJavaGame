package main;
import java.io.*;

public class Config {

    GamePanel gp;

    public Config(GamePanel gp) {
        this.gp = gp;
    }

    public void saveConfig() {
        String userHome = System.getProperty("user.home");
        File configDir = new File(userHome, ".twoja_gierka");
        if (!configDir.exists()) {
            configDir.mkdirs();
        }

        File configFile = new File(configDir, "config.txt");

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(configFile))) {
            bw.write(gp.keyH.showGameInfo ? "On" : "Off");
            bw.newLine();

            bw.write(String.valueOf(gp.music.volumeScale));
            bw.newLine();

            bw.write(String.valueOf(gp.se.volumeScale));
            bw.newLine();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadConfig() {
        String userHome = System.getProperty("user.home");
        File userConfig = new File(userHome, ".twoja_gierka/config.txt");

        if (userConfig.exists()) {
            try (BufferedReader br = new BufferedReader(new FileReader(userConfig))) {
                loadFromReader(br);
                return;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        try (InputStream is = getClass().getResourceAsStream("/config/default_config.txt");
             BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
            loadFromReader(br);
        } catch (Exception e) {
            e.printStackTrace();
            setDefaultConfig();
        }
    }

    private void loadFromReader(BufferedReader br) throws IOException {
        String s = br.readLine();
        if (s != null) {
            gp.keyH.showGameInfo = s.equals("On");
        }

        s = br.readLine();
        if (s != null) {
            gp.music.volumeScale = Integer.parseInt(s);
        }

        s = br.readLine();
        if (s != null) {
            gp.se.volumeScale = Integer.parseInt(s);
        }
    }

    private void setDefaultConfig() {
        gp.keyH.showGameInfo = true;
        gp.music.volumeScale = 2;
        gp.se.volumeScale = 2;
    }

}
