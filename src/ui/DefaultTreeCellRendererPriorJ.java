
package ui;

/**
 *
 * @author Samuel
 */
import java.awt.Component;
import javax.swing.ImageIcon;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeCellRenderer;

public class DefaultTreeCellRendererPriorJ extends DefaultTreeCellRenderer implements TreeCellRenderer {

    @Override
    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus) {

        super.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);


        return this;

    }
    
    /** how to use this code...
    setOpenIcon(new ImageIcon(getClass().getResource("noAberto.png")));
    setClosedIcon(new ImageIcon(getClass().getResource("noFechado.png")));
    setLeafIcon(new ImageIcon(getClass().getResource("noFolha.png")));
     */
}
