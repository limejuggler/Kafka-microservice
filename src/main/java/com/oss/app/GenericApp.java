/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oss.app;

import com.oss.GenericAppDescriptor;
import com.oss.GenericAppUtil;
import com.oss.Page;
import com.oss.menu.CreateSqlPopup;
import com.oss.utils.AppData;
import com.oss.utils.GuiHelper;
import com.oss.utils.Helper;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.lang.reflect.Constructor;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import org.jdesktop.swingx.JXTreeTable;
import org.jdesktop.swingx.renderer.DefaultTreeRenderer;
import org.jdesktop.swingx.renderer.IconValue;
import org.jdesktop.swingx.treetable.DefaultMutableTreeTableNode;
import org.jdesktop.swingx.treetable.DefaultTreeTableModel;
import org.jdesktop.swingx.treetable.TreeTableNode;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author g46737
 */
public class GenericApp implements ActionListener, ItemListener {

    static String appFileName = "AdaptivUtil";
    public static GenericAppDescriptor appDescriptor;
    static boolean createNewTemplate = false;
    public static boolean test = false;
    public static BeanFactory factory;
    public static BeanFactory factoryWebService;

    public static HashMap<String, Object> objectStore = new HashMap();
    public static ArrayList<Connection> connectionStore = new ArrayList();

    public static GenericApp app = new GenericApp();
    
    public static boolean headless = false;

    public static void main(String... aArgs) {
        Logger.getAnonymousLogger().setLevel(Level.SEVERE);
        System.out.println("Starting ADAPTIV Util tool");
        String classpath = System.getProperty("java.class.path");
        System.out.println("ClassPath: " + classpath);

        System.out.println("Paramters: " + aArgs);

        for (String param : aArgs) {
            if (param.equals("test")) {
                test = true;
            }

            if (param.equals("page")) {
                createNewTemplate = true;
            }

        }

        if (createNewTemplate) {
            appDescriptor = GenericAppUtil.createNewApplication(appFileName);
            appDescriptor.toXMLFile(appFileName);
        } else {
            appDescriptor = GenericAppUtil.loadAppDescriptor(appFileName);
        }
        app.buildAndDisplayGui();
    }

    public static Object getSpringBean(String name) {
        return factory.getBean(name);
    }

    public static Object getWSSpringBeanFromClass(Class name) {
        return factoryWebService.getBean(name);
    }

    // PRIVATE //
    JFrame frame;

    private void buildAndDisplayGui() {
        GuiUtils.setLookAndFeel();
        frame = new JFrame(appDescriptor.appName + "" + AppData.getInst().getVersion());
        frame.setSize(appDescriptor.width, appDescriptor.height);
        JMenuBar menuBar = new JMenuBar();
        JMenu menuFile = new JMenu("File");
        JMenu menuCreate = new JMenu("Create");

        menuFile.setMnemonic(KeyEvent.VK_F);

        JMenuItem menuItem = new JMenuItem("Save");
        menuItem.addActionListener(this);
        menuFile.add(menuItem);

        JMenuItem menuCreateSql = new JMenuItem("Page");
        menuCreateSql.addActionListener(this);
        menuCreate.add(menuCreateSql);

        menuBar.add(menuFile);
        menuBar.add(menuCreate);
        frame.setJMenuBar(menuBar);

        buildContent(frame);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.pack();

        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(WindowEvent winEvt) {
                ArrayList<Connection> connectionStore = GenericApp.connectionStore;
                for (Connection connection : connectionStore) {
                    try {
                        if (connection != null) {
                            System.out.println("CLOSING Connection" + connection);
                            connection.close();
                            System.out.println("CLOSED");
                        }
                    } catch (Exception ex) {
                        System.err.println("Unable to close CONNECTION" + ex.getMessage());
                    }
                }

                int n = JOptionPane.showConfirmDialog(
                        frame,
                        "Save configuration ?\n",
                        "Closing application",
                        JOptionPane.YES_NO_OPTION);

                if (n == 0) {
                    saveConfiguration();
                }
            }
        });

        frame.setVisible(true);
    }
    TopMainPanel topPanel;
    JPanel mainPanel;
    JPanel contentPanel = new JPanel();
    static JTextPane textPane = new JTextPane();
    JSplitPane splitPane;
    static HashMap<String, JPanel> panels = new HashMap<String, JPanel>();

    public static JPanel getPanelByName(String name) {
        return panels.get(name);
    }

    static JXTreeTable menuTree;
    static MenuTreeModel menuTreeModel;

    private void buildContent(JFrame aFrame) {
        mainPanel = new JPanel(new BorderLayout());
        topPanel = new TopMainPanel("");//appDescriptor.appName + " " + AppData.getInst().getVersion());
        panels.put("TopMainPanel", topPanel);
        mainPanel.add(topPanel, BorderLayout.NORTH);
        contentPanel = new JPanel(new BorderLayout());
        contentPanel.setPreferredSize(new Dimension(500, 500));
        menuTree = createMenuTree();
        //menuTree.setPreferredSize(new Dimension(150, 400));
        menuTree.setMinimumSize(new Dimension(140, 400));
        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
                menuTree, contentPanel);
        splitPane.setOneTouchExpandable(true);
        splitPane.setDividerLocation(150);
        mainPanel.add(new JScrollPane(splitPane), BorderLayout.CENTER);
        textPane.setMaximumSize(new Dimension(100, 100));
        textPane.setPreferredSize(new Dimension(100, 100));
        JScrollPane logSrcoll = new JScrollPane(textPane) {
            @Override
            public Dimension getPreferredSize() {
                Dimension d = super.getPreferredSize();
                int limit = Math.min(100, d.height);
                return new Dimension(d.width, limit);
            }
        };
        mainPanel.add(logSrcoll, BorderLayout.SOUTH);
        try {
            preLoadPages();
        } catch (Exception ex) {
            addLogMessage("Unable to load pages... " + ex.getMessage());
            ex.printStackTrace();
        }
        showPage(appDescriptor.startPage.id);
        aFrame.getContentPane().add(mainPanel);
    }

    public static void updatemMenuTree() {
        menuTreeModel = new MenuTreeModel();
        menuTree.setTreeTableModel(menuTreeModel);
        menuTree.repaint();
        menuTree.updateUI();
        menuTree.expandAll();
    }

    private void setContent(JPanel jPanel) {
        try {
            splitPane.remove(splitPane.getRightComponent());
            splitPane.add(jPanel);
            splitPane.repaint();
        } catch (Exception ex) {
            addLogMessage("Unable to show panel");
            addLogMessage(ex.getMessage());
            ex.printStackTrace();
        }

    }

    public static void addLogMessage(String logMessage) {
        try {
            Document document = textPane.getDocument();
            document.insertString(document.getLength(), logMessage + "\n", null);
        } catch (BadLocationException exc) {
            exc.printStackTrace();
        }
    }

    public static String getLog() {
        try {
            Document document = textPane.getDocument();
            return document.getText(0, document.getLength());
        } catch (BadLocationException ex) {
            GuiHelper.addLogMessage("Unable to get log: " + ex.getMessage());
        }
        return "";
    }

    private JXTreeTable createMenuTree() {
        JXTreeTable menuTree = new JXTreeTable() {
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                Component returnComp = super.prepareRenderer(renderer, row, column);
                Color alternateColor = new Color(252, 242, 206);
                Color whiteColor = Color.WHITE;
                if (!returnComp.getBackground().equals(getSelectionBackground())) {
                    Color bg = (row % 2 == 0 ? whiteColor : whiteColor);
                    returnComp.setBackground(bg);
                    bg = null;
                }
                return returnComp;
            }
        };

        menuTree.setRootVisible(false);
        menuTree.expandAll();

        menuTreeModel = new MenuTreeModel();
        menuTree.setTreeTableModel(menuTreeModel);
        menuTree.setColumnSelectionAllowed(true);
        menuTree.setRowSelectionAllowed(true);
        menuTree.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        menuTree.setTableHeader(null);
        menuTree.getColumnModel().getColumn(0).setCellRenderer(new LabelRenderer());
        //menuTree.setTreeCellRenderer(new MyTreeCellRenderer(IconValue.NULL_ICON));
        menuTree.addTreeSelectionListener(new TreeSelectionListener() {
            public void valueChanged(TreeSelectionEvent e) {
                DefaultMutableTreeTableNode node = (DefaultMutableTreeTableNode) e.getPath().getLastPathComponent();
                if (node != null) {
                    Page page = (Page) node.getUserObject();
                    showPage(page.id);
                }

            }
        });
        menuTree.expandAll();
        return menuTree;
    }

    void preLoadPages() throws Exception {
        ArrayList<Page> pages = appDescriptor.getPages();
        for (Page page : pages) {
            String loadAtStart = page.loadAtStartUp;
            if ((loadAtStart != null) && (loadAtStart.equalsIgnoreCase("true"))) {
                putPage(page.id);
            }
        }
    }

    JPanel putPage(String pageName) throws Exception {
        Page pageFromName = appDescriptor.getPageFromId(pageName);
        String pageClassName = pageFromName.pageViewClass;
        Class cls = Class.forName(pageClassName);
        Constructor constructors = cls.getConstructors()[0];
        Object clsInstance = constructors.newInstance(pageFromName); // = (Object) cls.newInstance();
        JPanel newPanel = (JPanel) clsInstance;
        panels.put(pageName, newPanel);
        return newPanel;
    }

    public JPanel getJpanel(String pageId) {
        if (panels.containsKey(pageId)) {
            return (panels.get(pageId));
        }
        return null;
    }

    public void showPage(String pageId) {
        String pageClassName = "";
        try {
            // addLogMessage("Show " + pageName);
            if (panels.containsKey(pageId)) {
                setContent(panels.get(pageId));
            } else {
                JPanel newPanel = putPage(pageId);
                setContent(newPanel);
            }
        } catch (Exception ex) {
            addLogMessage("Unable to load pagename: " + pageId + "  - View: " + pageClassName);
            addLogMessage(ex.getMessage());
            ex.printStackTrace();
        }
    }

    public void saveConfiguration() {
        appDescriptor.toXMLFile(appFileName);
    }

    public void actionPerformed(ActionEvent e) {
        JMenuItem source = (JMenuItem) (e.getSource());
        String s = "Action event detected.   Event source: " + source.getText() + " (an instance of " + getClassName(source) + ")";
        Helper.out(s);
        if (source.getText().equals("Save")) {
            saveConfiguration();
        }

        if (source.getText().equals("Page")) {

            CreateSqlPopup dialog = new CreateSqlPopup(frame, true);
            dialog.setVisible(true);
        }
    }

    public void itemStateChanged(ItemEvent e) {
        JMenuItem source = (JMenuItem) (e.getSource());
        String s = "Item event detected.    Event source: " + source.getText() + " (an instance of " + getClassName(source) + ")   New state: " + ((e.getStateChange() == ItemEvent.SELECTED) ? "selected" : "unselected");
        Helper.out(s);
    }

    // Returns just the class name -- no package info.    
    protected String getClassName(Object o) {
        String classString = o.getClass().getName();
        int dotIndex = classString.lastIndexOf(".");
        return classString.substring(dotIndex + 1);
    }

    public void sleep(int seconds) {
        try {
            if (app.frame!=null) 
                app.frame.repaint();
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
        }
    }

    static class MenuTreeModel extends DefaultTreeTableModel {

        DefaultMutableTreeTableNode root = null;

        public MenuTreeModel() {
            root = new DefaultMutableTreeTableNode();
            this.setRoot(root);
            for (Page page : appDescriptor.pages) {
                if (!Helper.isEqualWithNull(page.doShow, "false")) {
                    DefaultMutableTreeTableNode newNode = new DefaultMutableTreeTableNode(page);
                    newNode.setAllowsChildren(true);
                    if (Helper.hasStringContent(page.parent)) {
                        nodeTofind = null;
                        traverseAndFindNode(root, page.parent);
                        if (nodeTofind != null) {
                            nodeTofind.insert(newNode, nodeTofind.getChildCount());
                        }
                    } else {
                        root.add(newNode);
                    }
                }
            }
        }

        DefaultMutableTreeTableNode nodeTofind = null;

        public synchronized void traverseAndFindNode(TreeTableNode parentNode, String nodeToFind) {
            // traverse all nodes that belong to the parent
            //if (parentNode.isLeaf()) //System.out.println(parentNode.getUserObject());
            //{
            if ((Page) parentNode.getUserObject() != null) {
                // System.out.println("node "  +((Page)parentNode.getUserObject()).id + " Looking for " + nodeToFind);
                if (((Page) parentNode.getUserObject()).id.equals(nodeToFind)) {
                    // System.out.println("FOUND "  +((Page)parentNode.getUserObject()).id);
                    nodeTofind = (DefaultMutableTreeTableNode) parentNode;
                }
            }
            //}

            for (int i = 0; i < parentNode.getChildCount(); i++) {
                if (nodeTofind != null) {
                    break;
                }
                traverseAndFindNode(parentNode.getChildAt(i), nodeToFind);
            }
        }

        public int getColumnCount() {
            return 1;
        }

        public boolean isCellEditable(Object node, int column) {
            return false;
        }

        public Object getValueAt(Object arg0, int arg1) {
            if (arg1 == 0) {
                DefaultMutableTreeTableNode node = (DefaultMutableTreeTableNode) arg0;
                if (node != null) {
                    Page page = ((Page) node.getUserObject());
                    if (page != null) {
                        return page.pageName;
                    }
                }
            }
            return "";
        }

//     @Override
//     public boolean isLeaf(Object node) {
//         return false;
//     }
        @Override
        public int getChildCount(Object parent) {
            return ((DefaultMutableTreeTableNode) parent).getChildCount();
        }

        @Override
        public Object getChild(Object parent, int index) {
            return ((DefaultMutableTreeTableNode) parent).getChildAt(index);
        }
    }

    class LabelRenderer extends DefaultTableCellRenderer {

        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            isSelected = false;
            super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            this.setText("Menu");
            return this;
        }
    }

    class MyTreeCellRenderer extends DefaultTreeRenderer {

        MyTreeCellRenderer(IconValue icon) {
            super(icon);
        }

        public java.awt.Component getTreeCellRendererComponent(javax.swing.JTree tree, Object value, boolean isSelected, boolean expanded, boolean leaf, int row, boolean hasFocus) {
            isSelected = false;
            return super.getTreeCellRendererComponent(tree, value, isSelected, expanded, leaf, row, hasFocus);
        }
    }
}
