package jp.itohiro.intellij.searchit.domain

import com.google.api.client.http.GenericUrl
import com.google.api.client.util.Key

public class RestUrl(
        encodedUrl: String,
        @Key public val fields: String
) : GenericUrl(encodedUrl)
