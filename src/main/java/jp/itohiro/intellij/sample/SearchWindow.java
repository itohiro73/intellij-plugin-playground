package jp.itohiro.intellij.sample;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class SearchWindow implements ToolWindowFactory{
    private JTextField textField1;
    private JLabel searchLabel;
    private JPanel panel;
    private JTable table1;
    private Searchable searchable;

    public SearchWindow() {
        searchable = new Qiita(new ResultTable(table1));
        textField1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    searchable.search(textField1.getText());
                }
            }
        });
    }

    @Override
    public void createToolWindowContent(Project project, ToolWindow toolWindow) {
        JComponent parent = toolWindow.getComponent();
        parent.add(this.panel);
    }
}
