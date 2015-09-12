package jp.itohiro.intellij.sample.domain;

import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.JsonObjectParser;
import com.google.api.client.json.jackson.JacksonFactory;
import com.gs.collections.impl.list.fixed.ArrayAdapter;
import jp.itohiro.intellij.sample.ui.ResultTable;

import javax.swing.*;
import java.io.IOException;

public class Qiita implements Searchable {
    static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
    static final JsonFactory JSON_FACTORY = new JacksonFactory();
    private final String SEARCH_URL = "https://qiita.com/api/v1/search?q=";
    private final ResultTable table;

    public Qiita(ResultTable table) {

        this.table = table;
    }

    @Override
    public void search(String searchText) {
        SwingUtilities.invokeLater(() -> {
            HttpRequestFactory requestFactory =
                    HTTP_TRANSPORT.createRequestFactory(request
                            -> request.setParser(new JsonObjectParser(JSON_FACTORY)));

            RestUrl url = new RestUrl(SEARCH_URL + searchText, "title,created_at,body");
            HttpRequest request = null;
            JsonItem[] qiitaItem = null;
            try {
                request = requestFactory.buildGetRequest(url);
                qiitaItem = request.execute().parseAs(QiitaItem[].class);
            } catch (IOException e) {
                throw new RuntimeException("Failed create search request", e);
            }

            table.refreshResult(ArrayAdapter.adapt(qiitaItem).toImmutable());
        });
    }
}
