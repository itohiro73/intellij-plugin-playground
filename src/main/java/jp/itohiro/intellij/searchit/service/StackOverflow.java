package jp.itohiro.intellij.searchit.service;

import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.JsonObjectParser;
import com.google.api.client.json.jackson.JacksonFactory;
import com.gs.collections.impl.list.fixed.ArrayAdapter;
import jp.itohiro.intellij.searchit.domain.JsonItem;
import jp.itohiro.intellij.searchit.domain.RestUrl;
import jp.itohiro.intellij.searchit.domain.StackOverflowItems;
import jp.itohiro.intellij.searchit.ui.ResultTable;

import java.io.IOException;

public class StackOverflow implements Searchable {
    static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
    static final JsonFactory JSON_FACTORY = new JacksonFactory();
    private final String SEARCH_URL = "https://api.stackexchange.com/2.2/search/advanced?site=stackoverflow&order=desc&sort=activity&q=";
    private final ResultTable table;

    public StackOverflow(ResultTable table) {

        this.table = table;
    }

    @Override
    public void search(String searchText) {
        HttpRequestFactory requestFactory =
                HTTP_TRANSPORT.createRequestFactory(request
                        -> request.setParser(new JsonObjectParser(JSON_FACTORY)));

        RestUrl url = new RestUrl(SEARCH_URL + searchText, "items,title,creation_date,link");
        HttpRequest request = null;
        JsonItem[] itemJson = null;
        try {
            request = requestFactory.buildGetRequest(url);
            itemJson = request.execute().parseAs(StackOverflowItems.class).getItems();
        } catch (IOException e) {
            throw new RuntimeException("Failed create search request", e);
        }

        table.refreshResult(ArrayAdapter.adapt(itemJson).toImmutable());
    }
}
