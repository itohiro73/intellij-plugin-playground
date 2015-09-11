package jp.itohiro.intellij.sample.ui;

import com.gs.collections.api.list.ImmutableList;
import com.gs.collections.impl.factory.Lists;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogBuilder;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import jp.itohiro.intellij.sample.domain.Qiita;
import jp.itohiro.intellij.sample.domain.Searchable;
import jp.itohiro.intellij.sample.domain.StackOverflow;

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
    private JScrollPane jSrollPane;
    private ImmutableList<Searchable> searchables;
    private SearchConfig searchConfig = new SearchConfig();

    public SearchWindow() {
        ResultTable resultTable = new ResultTable(table1);
        searchables = Lists.immutable.of(new Qiita(resultTable), new StackOverflow(resultTable));
        textField1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    searchables.each(searchable -> searchable.search(textField1.getText()));
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
