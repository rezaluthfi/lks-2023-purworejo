package com.example.purworejoapik.data.dao

import android.icu.text.StringSearch
import androidx.room.*
import com.example.purworejoapik.data.entity.User

@Dao
interface UserDao {
    @Query("SELECT * FROM user ORDER BY full_name ASC")
    fun getAll(): List<User>

    @Query("SELECT * FROM user WHERE full_name LIKE '%' || :search || '%'")
    fun searchByName(search: String): List<User>

    @Query("SELECT * FROM user WHERE uid IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): List<User>

    @Insert
    fun insertAll(vararg users: User)

    @Delete
    fun delete(user: User)

    @Query("SELECT * FROM user WHERE uid = :uid")
    fun get(uid: Int) : User

    @Update
    fun update(user: User)
}
