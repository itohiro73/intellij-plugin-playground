package jp.itohiro.intellij.searchit.service

import com.google.api.client.http.HttpTransport
import com.google.api.client.http.javanet.NetHttpTransport
import com.google.api.client.json.JsonFactory
import com.google.api.client.json.JsonObjectParser
import com.google.api.client.json.jackson.JacksonFactory
import com.gs.collections.impl.list.fixed.ArrayAdapter
import jp.itohiro.intellij.searchit.domain.JsonItem
import jp.itohiro.intellij.searchit.domain.QiitaItem
import jp.itohiro.intellij.searchit.domain.RestUrl
import jp.itohiro.intellij.searchit.ui.ResultTable

public class Qiita(private val table: ResultTable) : Searchable {
    private val SEARCH_URL = "https://qiita.com/api/v1/search?q="

    override fun search(text: String) {
        val requestFactory = HTTP_TRANSPORT.createRequestFactory { request -> request.setParser(JsonObjectParser(JSON_FACTORY)) }

        val url = RestUrl(SEARCH_URL + text, "title,created_at,body")
        val request = requestFactory.buildGetRequest(url)
        val qiitaItem = request.execute().parseAs(Array<QiitaItem>::class.java)

        table.refreshResult(ArrayAdapter.adapt<JsonItem>(*qiitaItem).toImmutable())
    }

    companion object {
        val HTTP_TRANSPORT: HttpTransport = NetHttpTransport()
        val JSON_FACTORY: JsonFactory = JacksonFactory()
    }
}
