/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package platformereditor;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import platformereditor.util.ReadLevel;

/**
 *
 * @author leonb
 */
public class Editor {

    private EditorGUI GUI;
    private PropsGUI Properties;
    private Level currentLevel = new Level();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        setLaF();
        new Editor();
    }

    void openClicked() {
        JFileChooser chooser = new javax.swing.JFileChooser();
        FileFilter filter = new FileNameExtensionFilter("Level Dateien (*.yml)", "yml");
        chooser.setFileFilter(filter);
        int option = chooser.showOpenDialog(null);
        if (option == JFileChooser.APPROVE_OPTION) {
            File leveldatei = chooser.getSelectedFile();
            currentLevel = readLevel(leveldatei);
        }
        GUI.setPanelSize(currentLevel.getSize());
        GUI.reRender();

    }

    public void Render(Graphics2D g2d) {
        System.out.println("render");
        ArrayList<Objekt> objects = currentLevel.getObjekte();
        for (Objekt objekt : objects) {
            g2d.setColor(objekt.getCOLOR());
            if (objekt.getSPRITE() == null) {
                g2d.fillRect(objekt.getX(), objekt.getY(), objekt.getWIDTH(), objekt.getHEIGHT());
            } else {
                g2d.drawImage(objekt.getSPRITE(), objekt.getX(), objekt.getY(), objekt.getWIDTH(), objekt.getHEIGHT(), null);
            }

        }
        Player player = currentLevel.getPlayer();
        g2d.setColor(player.getCOLOR());
        g2d.fillRect(player.getX(), player.getY(), player.getHEIGHT(), player.getWIDTH());
    }

    private Level readLevel(File leveldatei) {
        Level level = ReadLevel.readLevel(leveldatei);
        return level;
    }

    public Editor() {
        init();
    }

    private static void setLaF() {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(EditorGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EditorGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EditorGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EditorGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }

    private void init() {
        GUI = new EditorGUI(this);
        GUI.setVisible(true);
    }

    public void panelClicked(MouseEvent evt) {
        if (SwingUtilities.isLeftMouseButton(evt)) {
            Point clickedPoint = new Point(evt.getX(), evt.getY());
            ArrayList<Objekt> objects = currentLevel.getObjekte();
            for (Objekt objekt : objects) {
                if (objekt.getREKT().contains(clickedPoint)) {
                    System.out.println("" + GUI.getRightClickMenuStatus());
                    if (GUI.getRightClickMenuStatus() == 2) {
                        objects.remove(objekt);
                    }
                    break;
                }
            }
            if (GUI.getRightClickMenuStatus() == 1) {
                System.out.println("hä?");
                Bloecke block = addblock(evt);
                objects.add(block);
            }
            currentLevel.setObjekte(objects);
            GUI.reRender();
        }

        if (SwingUtilities.isRightMouseButton(evt)) {
            GUI.showRightClickMenu(evt);
        }
    }

    public void openProps() {
        Properties = new PropsGUI();
        Properties.setVisible(true);
    }

    private Bloecke addblock(MouseEvent evt) {
        if (Properties.error) {
            return null;
        }
        int x = evt.getX();
        int y = evt.getY();
        int width = Properties.getWidthProp();
        int height = Properties.getHeightProp();
        Color color = Properties.getColor();
        Bloecke block = new Bloecke(x, y, width, height, color);
        return block;
    }
}
