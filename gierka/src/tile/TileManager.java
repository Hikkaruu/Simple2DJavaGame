package tile;

import main.GamePanel;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.util.Objects;

public class TileManager {
    GamePanel gp;
    public Tile[] tile;
    public int mapTileNum[][][];
    boolean drawPath = true;

    public TileManager(GamePanel gp) {
        this.gp = gp;

        tile = new Tile[300];
        mapTileNum = new int[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];
        getTileImage();
        loadMap("maps/world001.txt", 0);
        loadMap("maps/interior01.txt", 1);
        loadMap("maps/dungeon01.txt", 2);
        loadMap("maps/mapGoblinBoss.txt", 3);
        loadMap("maps/dungeon02.txt", 4);
    }
    public void getTileImage(){

            // nic
            setup(0, "grass00", false, false);
            setup(1, "grass00", false, false);
            setup(2, "grass00", false, false);
            setup(3, "grass00", false, false);
            setup(4, "grass00", false, false);
            setup(5, "grass00", false, false);
            setup(6, "grass00", false, false);
            setup(7, "grass00", false, false);
            setup(8, "grass00", false, false);
            setup(9, "grass00", false, false);
            // nic

            setup(10, "grass00", false, false);
            setup(11, "grass01", false, false);
            setup(12, "water00", true, false);
            setup(13, "water01", true, false);
            setup(14, "water02", true, false);
            setup(15, "water03", true, false);
            setup(16, "water04", true, false);
            setup(17, "water05", true, false);
            setup(18, "water06", true, false);
            setup(19, "water07", true, false);
            setup(20, "water08", true, false);
            setup(21, "water09", true, false);
            setup(22, "water10", true, false);
            setup(23, "water11", true, false);
            setup(24, "water12", true, false);
            setup(25, "water13", true, false);
            setup(26, "road00", false, false);
            setup(27, "road01", false, false);
            setup(28, "road02", false, false);
            setup(29, "road03", false, false);
            setup(30, "road04", false, false);
            setup(31, "road05", false, false);
            setup(32, "road06", false, false);
            setup(33, "road07", false, false);
            setup(34, "road08", false, false);
            setup(35, "road09", false, false);
            setup(36, "road10", false, false);
            setup(37, "road11", false, false);
            setup(38, "road12", false, false);
            setup(39, "earth", false, false);
            setup(40, "wall", true, true);
            setup(41, "tree", true, true);
            setup(42, "water_border", true, true);
            setup(43, "hut", false, true);
            setup(44, "floor01", false, false);
            setup(45, "table01", true, true);
            setup(46, "wall2", true, true);
            setup(47, "piasek1", true, true);
            setup(48, "null", true, true);
            setup(49, "crystal_down", true, true);
            setup(50, "crystal_up", true, true);
            setup(51, "051", true, true);
            setup(52, "052", true, true);
            setup(53, "053", true, true);
            setup(54, "054", true, true);
            setup(55, "055", true, true);
            setup(56, "056", true, true);
            setup(57, "057", true, true);
            setup(58, "058", true, true);
            setup(59, "059", true, true);
            setup(60, "060", true, true);
            setup(61, "061", true, true);
            setup(62, "062", true, true);
            setup(63, "063", false, false);
            setup(64, "064", false, false);
            setup(65, "065", false, false);
            setup(66, "066", false, false);
            setup(67, "067", false, false);
            setup(68, "068", false, false);
            setup(69, "069", false, false);
            setup(70, "070", false, false);
            setup(71, "071", false, false);
            setup(72, "072", false, false);
            setup(73, "073", false, false);
            setup(74, "074", false, false);
            setup(75, "075", false, false);
            setup(76, "076", false, false);
            setup(77, "077", false, false);
            setup(78, "078", false, false);
            setup(79, "079", false, false);
            setup(80, "080", false, false);
            setup(81, "081", false, false);
            setup(82, "082", false, false);
            setup(83, "083", false, false);
            setup(84, "084", false, false);
            setup(85, "085", false, false);
            setup(86, "086", false, false);
            setup(87, "087", false, false);
            setup(88, "088", false, false);
            setup(89, "089", false, false);
            setup(90, "090", false, false);
            setup(91, "091", false, false);
            setup(92, "092", false, false);
            setup(93, "093", false, false);
            setup(94, "094", false, false);
            setup(95, "095", false, false);
            setup(96, "096", false, false);
            setup(97, "097", false, false);
            setup(98, "098", false, false);
            setup(99, "099", false, false);
            setup(100, "100", false, false);
            setup(101, "101", false, false);
            setup(102, "102", false, false);
            setup(103, "103", false, false);
            setup(104, "104", false, false);
            setup(105, "105", false, false);
            setup(106, "106", false, false);
            setup(107, "107", false, false);
            setup(108, "108", false, false);
            setup(109, "109", false, false);
            setup(110, "110", false, false);
            setup(111, "111", false, false);
            setup(112, "112", false, false);
            setup(113, "113", false, false);
            setup(114, "114", false, false);
            setup(115, "115", false, false);
            setup(116, "116", false, false);
            setup(117, "117", false, false);
            setup(118, "118", false, false);
            setup(119, "119", false, false);
            setup(120, "120", true, true);
            setup(121, "121", true, true);
            setup(122, "122", true, true);
            setup(123, "123", true, true);
            setup(124, "124", true, true);
            setup(125, "125", true, true);
            setup(126, "126", true, true);
            setup(127, "127", true, true);
            setup(128, "128", true, true);
            setup(129, "129", true, true);
            setup(130, "130", true, true);
            setup(131, "131", true, true);
            setup(132, "132", true, true);
            setup(133, "133", true, true);
            setup(134, "134", true, true);
            setup(135, "135", true, true);
            setup(136, "136", true, true);
            setup(137, "137", true, true);
            setup(138, "138", true, true);
            setup(139, "139", true, true);
            setup(140, "140", true, true);
            setup(141, "141", true, true);
            setup(142, "142", true, true);
            setup(143, "143", true, true);
            setup(144, "144", true, true);
            setup(145, "145", true, true);
            setup(146, "146", true, true);
            setup(147, "147", true, true);
            setup(148, "148", true, true);
            setup(149, "149", true, true);
            setup(150, "150", false, false);
            setup(151, "151", false, false);
            setup(152, "152", false, false);
            setup(153, "153", false, false);
            setup(154, "154", false, false);
            setup(155, "155", false, false);
            setup(156, "156", false, false);
            setup(157, "157", false, false);
            setup(158, "158", false, false);
            setup(159, "159", true, true);
            setup(160, "160", true, true);
            setup(161, "161", true, true);
            setup(162, "162", true, true);
            setup(163, "163", true, true);
            setup(164, "164", true, true);
            setup(165, "165", true, true);
            setup(166, "166", true, true);
            setup(167, "167", true, true);
            setup(168, "168", false, false);
            setup(169, "169", false, false);
            setup(170, "170", false, false);
            setup(171, "171", false, false);
            setup(172, "172", false, false);




    }

    public void setup(int index, String imageName, boolean collision, boolean collisionProjectile){

        UtilityTool uTool = new UtilityTool();

        try {
            tile[index] = new Tile();
            tile[index].image = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("tiles/" + imageName + ".png")));
            tile[index].image = uTool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
            tile[index].collision = collision;
            tile[index].collisionProjectile = collisionProjectile;

        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public void loadMap(String filePath, int map) {
        try {
            InputStream is = getClass().getClassLoader().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(Objects.requireNonNull(is)));

            int col = 0;
            int row = 0;

            while (col < gp.maxWorldCol && row < gp.maxWorldRow){
                String line = br.readLine();

                while (col < gp.maxWorldCol){

                    String[] numbers = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[map][col][row] = num;
                    col++;
                }
                if (col == gp.maxWorldCol){
                    col = 0;
                    row++;
                }
            }
            br.close();
        }
        catch (IOException e){
        }
    }
    public void draw(Graphics2D g2) {

        int worldCol = 0;
        int worldRow = 0;

        while (worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow){

            int tileNum = mapTileNum[gp.currentMap][worldCol][worldRow];

            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;

            // renderowanie mapy tylko w zasiegu postaci + 1 "kratka" w każdą stronę, by nie marnować zasobów.
            if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                    worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                    worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                    worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {

                g2.drawImage(tile[tileNum].image, screenX, screenY,null);
            }

            worldCol++;

            if(worldCol == gp.maxWorldCol){
                worldCol = 0;
                worldRow++;
            }
        }

    }

}
