# tablecache
Memory manager using cache for JTable ImageRenderer

##Implementation##

Just define an model for your JTable

*For example:*
```
import javax.swing.table.DefaultTableModel;
import tablecache.ImageRenderer;

class MyForm extends javax.swing.JFrame {
  private javax.swing.JTable jTable1;
  DefaultTableModel table = new DefaultTableModel(new String[]{"#", "Image"}, 0);
  vois MyForm(){
    jTable1.setModel(table);
```
then asign the ImageRender and set your row height 
```
    jTable1.getColumnModel().getColumn(1).setCellRenderer(new ImageRenderer());
    jTable1.setRowHeight(100);
    setLocationRelativeTo(this);
  }
```

For asign an image for you table just need set the path image, **do not asign an ImageIcon**.

```
table.addRow(new Object[]{
    (0),
    "Path\\TO\\YOUR\\IMAGE\\"
});
```
