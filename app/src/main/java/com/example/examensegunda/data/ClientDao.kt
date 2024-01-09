package com.example.examensegunda.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface ClientDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(client: Client)

    @Update
    suspend fun update(client: Client)

    @Delete
    suspend fun delete(client: Client)

    @Query("SELECT * from client WHERE id = :id")
    fun getClient(id: Int): Flow<Client>

    @Query("SELECT * FROM client ORDER BY name ASC")
    fun getClients(): Flow<List<Client>>

}