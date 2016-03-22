# tablecache
Memory manager using cache for JTable ImageRenderer

##Implementation##

Just define a model for your JTable

*For example:*
```java
import javax.swing.table.DefaultTableModel;
import tablecache.ImageRenderer;

class MyForm extends javax.swing.JFrame {
  private javax.swing.JTable jTable1;
  DefaultTableModel table = new DefaultTableModel(new String[]{"#", "Image"}, 0);
  vois MyForm(){
    jTable1.setModel(table);
```
then asign the ImageRender and set your row height 
```java
    jTable1.getColumnModel().getColumn(1).setCellRenderer(new ImageRenderer());
    jTable1.setRowHeight(100);
    setLocationRelativeTo(this);
  }
```

For asign an image for your table just need to set the image path, **do not asign an ImageIcon**.

```java
table.addRow(new Object[]{
    (0),
    "Path\\TO\\YOUR\\IMAGE\\"
});
```
##Notes##
 - Make sure that the images paths are distincts, because they are used as key in the cache management.
 - Make sure that the image file exist while table is used, else just will show a black box in the cell.
 
 Thanks and Fork!
