package com.example.testapp.data.local

import androidx.room.*
import com.example.testapp.data.Kind

@Dao
interface ContentDao {

    @Query("SELECT * from contents WHERE kind = :kind")
    suspend fun getContents(@Kind kind: String): List<ContentEntity>

    @Query("SELECT * from contents WHERE isFavorite = 1 and kind = :kind")
    suspend fun getFavoritesByKind(@Kind kind: String): List<ContentEntity>

    @Query("SELECT * FROM contents WHERE isFavorite = 1")
    suspend fun getAllFavorite(): List<ContentEntity>

    @Query("UPDATE contents SET isFavorite = :isFavorite WHERE contentId = :id")
    suspend fun updateFavorite(id: String, isFavorite: Boolean)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addContents(contents: List<ContentEntity>): List<Long>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertIfNotExist(content: ContentEntity)

    @Query("DELETE FROM contents WHERE kind = :kind")
    suspend fun deleteAll(@Kind kind: String)


    @Transaction
    suspend fun insertAll(contents: List<ContentEntity>): List<ContentEntity> {
        return try {
            val kind = contents.first().kind ?: return emptyList()
            contents.forEach {
                insertIfNotExist(it)
            }
            getContents(kind)
        } catch (e: NoSuchElementException) {
            emptyList()
        }
    }
}