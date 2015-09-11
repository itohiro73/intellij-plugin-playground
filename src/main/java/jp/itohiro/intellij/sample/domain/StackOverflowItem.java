package jp.itohiro.intellij.sample.domain;

import com.google.api.client.util.Key;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class StackOverflowItem implements JsonItem{
    private static final String SITE = "Stack Overflow";
    @Key
    private long last_activity_date;
    @Key
    private long creation_date;
    @Key
    private String link;
    @Key
    private String title;
    @Key
    private Long last_edit_date;

    public StackOverflowItem() {
    }

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
        return Instant.ofEpochSecond(this.creation_date)
                .atZone(ZoneId.systemDefault())
                .format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
    }
}