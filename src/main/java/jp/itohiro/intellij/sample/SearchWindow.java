package jp.itohiro.intellij.sample;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogBuilder;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SearchWindow implements ToolWindowFactory{
    private Project project;
    private JTextField textField1;
    private JLabel searchLabel;
    private JPanel panel;
    private JTable table1;
    private JButton button1;
    private Searchable searchable;
    private SearchConfig searchConfig = new SearchConfig();

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

        button1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                showConfigDialg();
            }
        });
    }

    @Override
    public void createToolWindowContent(Project project, ToolWindow toolWindow) {
        JComponent parent = toolWindow.getComponent();
        parent.add(this.panel);
    }

    private void showConfigDialg() {
        DialogBuilder dialogBuilder = new DialogBuilder(this.project);
        dialogBuilder.setCenterPanel(searchConfig.getSearchConfigPanel());
        dialogBuilder.setTitle("Search It Config");
        dialogBuilder.show();
    }

}
