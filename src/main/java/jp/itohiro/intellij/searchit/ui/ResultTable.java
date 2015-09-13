package jp.itohiro.intellij.searchit.ui;

import com.gs.collections.api.list.ImmutableList;
import com.gs.collections.impl.utility.ArrayIterate;
import jp.itohiro.intellij.searchit.domain.JsonItem;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ResultTable {
    JTable table;

    public ResultTable(JTable table) {
        this.table = table;
        DefaultTableModel tableModel = new DefaultTableModel(new String[][]{}, new String[]{"Site", "Title", "Date"});
        this.table.setModel(tableModel);
    }

    public synchronized void refreshResult(ImmutableList<JsonItem> response){
        final String[][] tableData = new String[response.size()][3];
        DefaultTableModel tableModel = (DefaultTableModel) this.table.getModel();
        if(!response.isEmpty()) {
            response.forEachWithIndex(0, response.size() - 1, (jsonItem, i) -> tableData[i] = (new String[]{jsonItem.getSite(), jsonItem.getTitle(), jsonItem.getCreateTime()}));
            ArrayIterate.forEach(tableData, tableModel::addRow);
        }
        this.table.setModel(tableModel);
    }
}
