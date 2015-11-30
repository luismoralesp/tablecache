package tablecache;

import java.awt.Component;
import java.awt.Image;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Migue
 */
public class ImageRenderer extends DefaultTableCellRenderer {

    static Map<Object, ImageIcon> cache = new HashMap<>();
    static ArrayList<Object> keys = new ArrayList<>();
    int old_row = 0;
    int cache_size = 50;

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        JLabel label = new JLabel();
        ImageIcon icon = cache.getOrDefault(value, null);
        System.out.println("Pide " + row);
        if (icon != null) {
            label.setIcon(icon);
            System.out.println("Usa cache " + icon.getIconWidth());
        } else {
            if (cache.containsKey(value)) {
                System.out.println("Contains key");
                int index = keys.indexOf(value);
                int ux = old_row != row?(old_row - row) / Math.abs(old_row - row):1;
                for (int i = 0; i < 10; i++) {
                    Object key = (index + i * ux >= 0 && keys.size() > index + i * ux)?keys.get(index + i * ux):null;
                    if (key != null && cache.getOrDefault(key, null) == null) {
                        cache.put(key, resize(new ImageIcon((String) value), 100, 100));
                        System.out.println("recargando a cache:" + key + " size: " + cache.get(key).getIconWidth());
                    }

                    Object ukey = (index + i * ux - cache_size >= 0 && keys.size() > index + i * ux - cache_size)?keys.get(index + i * ux - cache_size):null;
                    if (ukey != null) {
                        cache.put(ukey, null);
                        System.out.println("descargando a cache:" + ukey);
                    }else{
                        System.out.println("index " + (index + i * ux - cache_size) + " no exite");
                    }
                }
            } else {
                Object ukey = (row - 10 >= 0 && keys.size() > row - 10)?keys.get(row - 10):null;
                if (ukey != null) {
                    cache.put(ukey, null);
                }
                cache.put(value, resize(new ImageIcon((String) value), 100, 100));
                keys.add(value);
                System.out.println("cargando a cache:" + value + " size: " + cache.get(value).getIconWidth());
            }
            label.setIcon(resize(new ImageIcon((String) value), 100, 100));
        }

        return label;
    }

    static ImageIcon resize(ImageIcon myIcon, int imageW, int imageH) {
        Image img = myIcon.getImage();
        Image newimg = img.getScaledInstance(imageW, imageH, java.awt.Image.SCALE_SMOOTH);
        return new ImageIcon(newimg);
    }
}
