package party.iobserver.bjutloginapp.database

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import party.iobserver.bjutloginapp.model.User

/**
 * Created by ZeroGo on 2017/11/2.
 */
@Dao
interface UserDao {
    @Query("select * from user order by id")
    fun all(): LiveData<MutableList<User>>

    @Query("select * from user where id = :id")
    fun find(id: Int): MutableList<User>

    @Insert
    fun insert(user: User)

    @Update
    fun update(user: User)

    @Delete
    fun delete(user: User)
}