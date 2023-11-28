package ru.zidkov.secondtask

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import retrofit2.http.DELETE


@Dao
interface Dao {
    @Insert
    fun insertItem(item: Item)
    @Query("SELECT * FROM items")
    fun getAllItems(): List<Item>
    @Query("DELETE FROM items")
    fun deleteAllItems()
    @Query("DELETE FROM sqlite_sequence WHERE name = 'items'")
    fun resetPrimaryKey()
}