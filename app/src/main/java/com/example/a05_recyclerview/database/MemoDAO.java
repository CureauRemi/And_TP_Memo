package com.example.a05_recyclerview.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public abstract class MemoDAO {

    @Query("SELECT * FROM memo")
    public abstract List<MemoDTO> getListMemo();

    @Query("SELECT COUNT(*) FROM memo WHERE intitule = :intitule")
    public abstract long countMemoParIntitule(String intitule);

    @Insert
    public abstract void insert(MemoDTO... memos);

    @Update
    public abstract void update(MemoDTO... memos);

    @Delete
    public abstract void delete(MemoDTO... memos);

    //    Clear la database
    @Query("DELETE FROM memo")
    public abstract void deleteAll();
}
