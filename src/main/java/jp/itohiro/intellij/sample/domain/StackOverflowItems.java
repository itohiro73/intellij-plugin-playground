package jp.itohiro.intellij.sample.domain;

import com.google.api.client.util.Key;

public class StackOverflowItems {
    @Key
    private StackOverflowItem[] items;
    @Key
    private boolean has_more;
    @Key
    private long quota_max;
    @Key
    private long quota_remaining;

    public JsonItem[] getItems() {
        return items;
    }
}
