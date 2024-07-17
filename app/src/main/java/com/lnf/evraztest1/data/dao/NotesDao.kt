package com.lnf.evraztest1.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.lnf.evraztest1.data.models.NoteModel
import kotlinx.coroutines.flow.Flow

@Dao
interface NotesDao: BaseDao<NoteModel> {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    override fun insert(vararg obj: NoteModel)

    @Delete
    override fun delete(vararg obj: NoteModel)

    @Update
    override fun update(vararg obj: NoteModel)

    @Query ("SELECT * FROM Notes")
    override fun getAll(): Flow<List<NoteModel>>

    @Query("SELECT * FROM Notes WHERE id = :id")
    override fun getById(id: Int): NoteModel
}