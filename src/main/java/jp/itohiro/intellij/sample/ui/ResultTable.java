package jp.itohiro.intellij.sample.ui;

import com.gs.collections.api.list.ImmutableList;
import jp.itohiro.intellij.sample.domain.JsonItem;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ResultTable {
    private String columns[] = {
            "Site", "Title", "Date"
    };
    JTable table;

    public ResultTable(JTable table) {
        this.table = table;
        DefaultTableModel tableModel = new DefaultTableModel(new String[][]{}, columns);
        this.table.setModel(tableModel);
    }

    public synchronized void refreshResult(ImmutableList<JsonItem> response){
        final String[][] tableData = new String[100][20];
        response.forEachWithIndex(0, response.size()-1, (jsonItem,i) -> tableData[i] = (new String[]{jsonItem.getSite(), jsonItem.getTitle(), jsonItem.getCreateTime()}));
        DefaultTableModel tableModel = (DefaultTableModel)this.table.getModel();
        tableModel.addRow(tableData);
        this.table.setModel(tableModel);
    }
}
