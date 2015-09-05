package jp.itohiro.intellij.sample;

import com.google.api.client.http.*;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.JsonObjectParser;
import com.google.api.client.json.jackson.JacksonFactory;
import com.google.api.client.util.Key;
import com.gs.collections.impl.factory.Lists;
import com.gs.collections.impl.list.fixed.ArrayAdapter;

import java.io.IOException;
import java.util.List;

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
        HttpRequestFactory requestFactory =
            HTTP_TRANSPORT.createRequestFactory(request
                    -> request.setParser(new JsonObjectParser(JSON_FACTORY)));

        QiitaUrl url = new QiitaUrl(SEARCH_URL+searchText);
        url.fields = "title,created_at,body";
        HttpRequest request = null;
        QiitaJson[] qiitaJson = null;
        try {
            request = requestFactory.buildGetRequest(url);
            qiitaJson = request.execute().parseAs(QiitaJson[].class);
        } catch (IOException e) {
            throw new RuntimeException("Failed create search request", e);
        }

        table.refreshResult(ArrayAdapter.adapt(qiitaJson).toImmutable());
    }

    public static class QiitaUrl extends GenericUrl {

        public QiitaUrl(String encodedUrl) {
            super(encodedUrl);
        }

        @Key
        public String fields;
    }

    public static class QiitaJson {
        @Key
        public String title;

        @Key
        public String created_at;

        @Key
        public String url;

        @Key
        public String raw_body;

        @Key
        public String body;
    }
}
