package jp.itohiro.intellij.sample;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class SearchWindow implements ToolWindowFactory{
    private JTextField textField1;
    private JLabel searchLabel;
    private JPanel panel;
    private JTable table1;
    private DefaultTableModel tableModel;
    private String columns[] = {
            "Site", "Title", "Contents", "Date"
    };
    public SearchWindow() {
        textField1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    search(textField1.getText());
                }
            }
        });

        tableModel = new DefaultTableModel(columns, 0);
        table1.setModel(tableModel);
    }

    @Override
    public void createToolWindowContent(Project project, ToolWindow toolWindow) {
        JComponent parent = toolWindow.getComponent();
        parent.add(this.panel);
    }

    private void search(String text) {
        tableModel.addRow(new String[]{"Qiita", "How to implement " + text, "Content XXXX", "2015-08-31 23:36"});
        tableModel.addRow(new String[]{"Stack Overflow", "How to develop " + text, "Content YYYY", "2015-08-31 23:38"});
        tableModel.fireTableDataChanged();
    }
}
