package com.seo.rxdemo.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao {
    @Query("select * from user")
    fun findAll():List<User>

    @Query("select * from user where uid in(:userIds)")
    fun findAllByUserIds(userIds:IntArray):List<User>

    @Query("select * from user where uid = :uid")
    fun findByuId(uid:Int):User

    @Query("select * from user where first_name like :first and lastName like :last limit 1")
    fun findByName(first:String, last:String):User

    @Insert
    fun insertAll(vararg  user: User)

    @Insert
    fun insertArray(users:ArrayList<User>)

    @Insert
    fun insert(user:User)

    @Delete
    fun delete(user: User)
}