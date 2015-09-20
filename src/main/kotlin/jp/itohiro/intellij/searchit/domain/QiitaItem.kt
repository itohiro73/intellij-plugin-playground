package jp.itohiro.intellij.searchit.domain

import com.google.api.client.util.Key

public class QiitaItem : JsonItem {

    @Key
    private var title: String? = null

    @Key
    private var created_at: String? = null

    @Key
    private var url: String? = null

    @Key
    private var raw_body: String? = null

    @Key
    private var body: String? = null

    override fun getSite(): String {
        return SITE
    }

    override fun getTitle(): String? {
        return this.title
    }

    override fun getCreateTime(): String? {
        return this.created_at
    }

    companion object {
        private val SITE = "Qiita"
    }
}
