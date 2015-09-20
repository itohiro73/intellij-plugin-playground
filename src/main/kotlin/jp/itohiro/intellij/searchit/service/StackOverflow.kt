package jp.itohiro.intellij.searchit.service

import com.google.api.client.http.HttpTransport
import com.google.api.client.http.javanet.NetHttpTransport
import com.google.api.client.json.JsonFactory
import com.google.api.client.json.JsonObjectParser
import com.google.api.client.json.jackson.JacksonFactory
import com.gs.collections.impl.list.fixed.ArrayAdapter
import jp.itohiro.intellij.searchit.domain.JsonItem
import jp.itohiro.intellij.searchit.domain.RestUrl
import jp.itohiro.intellij.searchit.domain.StackOverflowItems
import jp.itohiro.intellij.searchit.ui.ResultTable

public class StackOverflow(private val table: ResultTable) : Searchable {
    private val SEARCH_URL = "https://api.stackexchange.com/2.2/search/advanced?site=stackoverflow&order=desc&sort=activity&q="

    override fun search(text: String) {
        val requestFactory = HTTP_TRANSPORT.createRequestFactory { request -> request.setParser(JsonObjectParser(JSON_FACTORY)) }

        val url = RestUrl(SEARCH_URL + text, "items,title,creation_date,link")
        val request = requestFactory.buildGetRequest(url)
        var itemJson = request.execute().parseAs(StackOverflowItems::class.java).getItems()

        table.refreshResult(ArrayAdapter.adapt<JsonItem>(*itemJson).toImmutable())
    }

    companion object {
        val HTTP_TRANSPORT: HttpTransport = NetHttpTransport()
        val JSON_FACTORY: JsonFactory = JacksonFactory()
    }
}
