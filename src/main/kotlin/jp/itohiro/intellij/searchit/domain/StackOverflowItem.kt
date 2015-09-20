package jp.itohiro.intellij.searchit.domain

import com.google.api.client.util.Key

import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

public class StackOverflowItem : JsonItem {
    @Key
    private var last_activity_date: Long = 0
    @Key
    private var creation_date: Long = 0
    @Key
    private var link: String? = null
    @Key
    private var title: String? = null
    @Key
    private var last_edit_date: Long? = null

    override fun getSite(): String? {
        return SITE
    }

    override fun getTitle(): String? {
        return this.title
    }

    override fun getCreateTime(): String? {
        return Instant.ofEpochSecond(this.creation_date).atZone(ZoneId.systemDefault()).format(DateTimeFormatter.ISO_OFFSET_DATE_TIME)
    }

    companion object {
        private val SITE = "Stack Overflow"
    }
}