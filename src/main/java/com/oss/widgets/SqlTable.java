/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oss.widgets;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import javax.swing.JComponent;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import org.jdesktop.swingx.JXTable;
import org.jdesktop.swingx.table.ColumnControlButton;
import org.jdesktop.swingx.table.ColumnControlPopup;

/**
 *
 * @author g46737
 */
public class SqlTable extends JXTable {

    public SqlTable() {
        setAutoCreateRowSorter(true);
        setColumnControlVisible(true);

        //setAutoscrolls(true);
        setDefaultRenderer(Object.class, new SqlRenderer());
        setColumnControl(new MyColumnControlButton(this));
    }
    
    public void setFilterString(int column)
    {        
        String filter = filterMap.get(column).toString();
        filter = filter + "*";
                
        RowFilter rowFilter = RowFilter.regexFilter(filter, column);
        setRowFilter(rowFilter);      
    }

    HashMap filterMap = new HashMap();
    public void setHeaderRenderer() {
        getTableHeader().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                    final int index = convertColumnIndexToModel(columnAtPoint(mouseEvent.getPoint()));                if (index >= 0) {
                    System.out.println("Clicked on column " + index);
                    final JPopupMenu menu = new JPopupMenu();
                    final JTextField item = new JTextField();
                    Object get = filterMap.get(index);
                    if (get!=null)
                        item.setText(get.toString());
                    item.setPreferredSize(new Dimension(100, 26));
                    item.addFocusListener(new FocusListener() {                     
                        @Override
                        public void focusGained(FocusEvent e) {
                        }

                        @Override
                        public void focusLost(FocusEvent e) {
                            filterMap.put(index, item.getText());
                            setFilterString(index);
                        }                                                       
                    });
                    menu.add(item);
                    menu.show(getTableHeader(), (int)mouseEvent.getPoint().getX(), (int)mouseEvent.getPoint().getY()-30);
                }
            }
        ;
    }

    );
      
              
    }
    
    @Override
    public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
        Component returnComp = super.prepareRenderer(renderer, row, column);
        Color alternateColor = new Color(252, 242, 206);
        Color whiteColor = Color.WHITE;
        if (!returnComp.getBackground().equals(getSelectionBackground())) {
            Color bg = (row % 2 == 0 ? alternateColor : whiteColor);
            returnComp.setBackground(bg);
            bg = null;
        }
        return returnComp;
    }

    ;
    
  static class SqlRenderer extends DefaultTableCellRenderer {

        public SqlRenderer() {
            super();
        }

        @Override
        public JComponent getTableCellRendererComponent(JTable table, Object value,
                boolean isSelected, boolean hasFocus, int row, int column) {
            JComponent tableCellRendererComponent = (JComponent) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            Object valueAt = table.getModel().getValueAt(row, column);
            if (valueAt != null) {
                tableCellRendererComponent.setToolTipText(valueAt.toString());
            }
            return tableCellRendererComponent;
        }
    }

    public class MyColumnControlButton extends ColumnControlButton {

        public MyColumnControlButton(JXTable table) {
            super(table);
        }
        /*
         * This modifies the popup menu that hides/shows table
         * columns such that the popup is kept open even after a column is clicked.
         * The popup will close if the ColumnControlButton is pressed again.
         */

        @Override
        protected ColumnControlPopup createColumnControlPopup() {
            return new DefaultColumnControlPopup() {
            };
        }

    }

}
