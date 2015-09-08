package jp.itohiro.intellij.sample.domain;

import com.google.api.client.util.Key;

import java.time.Instant;
import java.time.LocalDateTime;

public class StackOverflowItem implements JsonItem{
    private static final String Site = "Stack Overflow";

    @Key
    private String title;

    @Key
    private long creation_date;

    @Key
    private String link;

    public String getTitle() {
        return this.title;
    }

    public long getCreation_date() {
        return this.creation_date;
    }

    public String getLink() {
        return this.link;
    }

    @Override
    public String getSite() {
        return Site;
    }

    @Override
    public String getCreateTime() {
        return LocalDateTime.from(Instant.ofEpochMilli(this.creation_date)).toString();
    }
}
