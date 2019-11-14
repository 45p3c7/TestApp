package com.example.testapp.data

import androidx.annotation.IntDef
import androidx.annotation.StringDef
import com.google.gson.annotations.SerializedName


data class Feed(
    val feed: RSSResponse
)

data class RSSResponse(
    val title: String?,
    val id: String?,
    val author: Author?,
    val links: ArrayList<Link>?,
    val copyright: String?,
    val country: String?,
    val icon: String?,
    val updated: String?,
    val results: List<Content>?
)

data class Author(
    val name: String?,
    val uri: String?
)

data class Link(
    val self: String?
)

data class Content(
    val id: String,
    val artistName: String?,
    val artistId: String?,
    val releaseData: String?,
    val name: String?,
    val artistUrl: String?,
    val artworkUrl100: String?,
    val url: String?,
    var kind: String?,
    val genres: List<Genre>?
)


data class Genre(
    var genreId: String,
    var name: String?,
    var url: String?
)


//enum class Kind(val kind : String) {
//    @SerializedName("book")
//    BOOK("book"),
//    @SerializedName("movie")
//    MOVIE("movie"),
//    @SerializedName("podcast")
//    PODCAST("podcast")
//}

const val BOOK = "book"
const val MOVIE = "movie"
const val PODCAST = "podcast"

@StringDef(value = [BOOK, MOVIE, PODCAST])
@Retention(AnnotationRetention.SOURCE)
annotation class Kind

