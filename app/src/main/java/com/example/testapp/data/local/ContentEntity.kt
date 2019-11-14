package com.example.testapp.data.local

import androidx.room.*
import com.example.testapp.data.Genre

@Entity(tableName = "contents")
data class ContentEntity(
    @PrimaryKey
    val contentId : String,
    val artistName: String?,
    val artistId: String?,
    val releaseData: String?,
    val name: String?,
    val artistUrl: String?,
    val artworkUrl100: String?,
    val url: String?,
    var kind: String?,
    var isFavorite : Boolean = false
)

@Entity(foreignKeys = [
    ForeignKey(entity = ContentEntity::class, parentColumns = ["id"], childColumns = ["genreId"], onUpdate = ForeignKey.CASCADE)
])
data class GenreEntity(
        @PrimaryKey
        var genreId : String,
        var name : String?,
        var url: String?
)

data class ContentWithGenre(
        @Embedded
        var content: ContentEntity,
        @Relation(parentColumn = "id", entityColumn = "genreId", entity = GenreEntity::class)
        var genre : List<Genre>
)