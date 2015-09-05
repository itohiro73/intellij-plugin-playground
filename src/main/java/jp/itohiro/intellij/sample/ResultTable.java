package jp.itohiro.intellij.sample;

import com.gs.collections.api.list.ImmutableList;

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

    public void refreshResult(ImmutableList<Qiita.QiitaJson> response){
        final String[][] tableData = new String[100][20];
        response.forEachWithIndex(0, response.size()-1, (qiitaJson,i) -> tableData[i] = (new String[]{"Qiita", qiitaJson.title, qiitaJson.created_at}));
        DefaultTableModel tableModel = new DefaultTableModel(tableData, columns);
        this.table.setModel(tableModel);
    }
}
