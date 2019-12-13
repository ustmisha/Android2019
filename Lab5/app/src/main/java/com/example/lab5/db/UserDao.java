package com.example.lab5.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface UserDao {

    @Query("SELECT * FROM user WHERE first_name LIKE :firstName AND " +
            "last_name LIKE :lastName LIMIT 1")
    User findByName(String firstName, String lastName);

    @Insert
    void insert(User user);
}
