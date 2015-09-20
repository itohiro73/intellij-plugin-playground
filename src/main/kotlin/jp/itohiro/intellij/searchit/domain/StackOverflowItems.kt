package jp.itohiro.intellij.searchit.domain

import com.google.api.client.util.Key

public class StackOverflowItems {
    @Key
    private var items: Array<StackOverflowItem>? = null
    @Key
    private var has_more: Boolean = false
    @Key
    private var quota_max: Long = 0
    @Key
    private var quota_remaining: Long = 0

    public fun getItems(): Array<out JsonItem>? {
        return items
    }
}
