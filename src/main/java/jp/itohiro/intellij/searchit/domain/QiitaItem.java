package jp.itohiro.intellij.searchit.domain;

import com.google.api.client.util.Key;

public class QiitaItem implements JsonItem{
    private static final String SITE = "Qiita";

    @Key
    private String title;

    @Key
    private String created_at;

    @Key
    private String url;

    @Key
    private String raw_body;

    @Key
    private String body;

    @Override
    public String getSite() {
        return SITE;
    }

    @Override
    public String getTitle() {
        return this.title;
    }

    @Override
    public String getCreateTime() {
        return this.created_at;
    }
}
