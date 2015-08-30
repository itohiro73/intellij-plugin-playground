package jp.itohiro.intellij.sample;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;

import javax.swing.*;

public class SearchWindow implements ToolWindowFactory{
    private JTextField textField1;
    private JLabel searchLabel;
    private JPanel panel;
    private JTable table1;

    @Override
    public void createToolWindowContent(Project project, ToolWindow toolWindow) {
        JComponent parent = toolWindow.getComponent();
        parent.add(this.panel);
    }
}
