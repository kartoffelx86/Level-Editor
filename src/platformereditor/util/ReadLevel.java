/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package platformereditor.util;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Map;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import org.yaml.snakeyaml.Yaml;
import platformereditor.Bloecke;
import platformereditor.Level;
import platformereditor.Objekt;
import platformereditor.Player;

/**
 *
 * @author Julian Blazek
 */
public class ReadLevel {

    private Yaml yaml = new Yaml();
    private Object level;
    private Level lvlobject;

    public static Level readLevel(File leveldatei) {
        ReadLevel tmp = new ReadLevel(leveldatei);
        return tmp.lvlobject;
    }

    public ReadLevel(File leveldatei) {
        lvlobject = new Level();
        Object load = null;
        try {
            load = yaml.load(new FileInputStream(leveldatei));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ReadLevel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        Map map = (Map) load;

        ProcessMeta((Map) map.get("meta"));
        ProcessPlayer((Map) map.get("player"));
        ProcessBlocks((Map) map.get("blocks"));

        ArrayList<Objekt> objekte = new ArrayList<>();
        ArrayList<Objekt> blockliste = lvlobject.bloecke.getBloecke();

        for (Objekt block : blockliste) {
            objekte.add(block);
        }

        lvlobject.setObjekte(objekte);
    }

    private void ProcessPlayer(Map player) {
        Map spawn = (Map) player.get("spawn");
        int X = (int) spawn.get("X");
        int Y = (int) spawn.get("Y");
        lvlobject.setPlayer(new Player(X, Y, 20, 20, null));

    }

    private void ProcessBlocks(Map blocks) {
        lvlobject.bloecke = lvlobject.new Blocks();
        for (Object blockname : blocks.keySet()) {
            Map blockdata = (Map) blocks.get(blockname);
            BufferedImage Sprite = null;
            Color color = null;
            if (blockdata.get("Sprite") != null) {
                try {
                    Sprite = ImageIO.read(getClass().getResource((String) blockdata.get("Sprite")));
                } catch (Exception ex) {
                    System.out.println("Konnte Resource nicht laden: " + blockdata.get("Sprite"));
                }
            }
            if (blockdata.containsKey("Color")) {
                color = new Color((int) blockdata.get("Color"));
            }
            ArrayList positionlist = (ArrayList) blockdata.get("positions");
            for (Object position : positionlist) {
                ArrayList pos = ((ArrayList) position);
                int X = (int) pos.get(0);
                int Y = (int) pos.get(1);
                int W = (int) pos.get(2);
                int H = (int) pos.get(3);
                if (Sprite == null) {
                    lvlobject.bloecke.addBlock(new Bloecke(X, Y, W, H, color));
                } else {
                    lvlobject.bloecke.addBlock(new Bloecke(X, Y, W, H, Sprite));
                }

            }

        }
    }

    private void ProcessMeta(Map meta) {
        lvlobject.setLvlname((String) meta.get("name"));
        int lvlwidth = (int) meta.get("width");
        int lvlheigth = (int) meta.get("height");
        lvlobject.setSize(new Dimension(lvlwidth, lvlheigth));
    }

}
