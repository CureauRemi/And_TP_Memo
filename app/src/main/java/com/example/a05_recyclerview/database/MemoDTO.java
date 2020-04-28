package com.example.a05_recyclerview.database;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "Memo")
public class MemoDTO {

    @PrimaryKey(autoGenerate = true)
    public long memoId = 0;

    public String intitule;

    @Ignore
    public boolean isSelected;

    public MemoDTO() {
    }

    public MemoDTO(String intitule) {
        this.intitule = intitule;
    }
}
