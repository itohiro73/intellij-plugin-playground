package jp.itohiro.intellij.sample.domain;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.util.Key;

public class RestUrl extends GenericUrl {
    @Key
    private String fields;

    public RestUrl(String encodedUrl, String fields) {
        super(encodedUrl);
        this.fields = fields;
    }

    public String getFields() {
        return fields;
    }
}
