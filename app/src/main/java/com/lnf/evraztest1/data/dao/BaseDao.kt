package com.lnf.evraztest1.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.lnf.evraztest1.data.models.NoteModel
import kotlinx.coroutines.flow.Flow

@Dao
interface BaseDao<T> {
    @Insert
    fun insert(vararg obj: T)
    @Delete
    fun delete(vararg obj: T)
    @Update
    fun update(vararg obj: T)
    @Query(value = "")
    fun getAll(): Flow<List<T>>
    @Query(value = "")
    fun getById(id: Int):T
}