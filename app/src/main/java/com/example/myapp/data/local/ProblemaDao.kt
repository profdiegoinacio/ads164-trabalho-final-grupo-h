package com.example.myapp.data.local

import android.util.Log
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.myapp.data.model.Problema

@Dao
interface ProblemaDao {
    @Insert
    suspend fun insertProblema(problema: Problema)

    @Query("SELECT * FROM Problema ORDER BY id DESC LIMIT 1")
    suspend fun getUltimoProblema(): Problema? {
        //Log.d("ProblemaDao", "Executando query para obter o último problema.")
        return getUltimoProblemaInternal()
    }

    @Query("SELECT * FROM Problema ORDER BY id DESC LIMIT 1")
    suspend fun getUltimoProblemaInternal(): Problema? // Renomeei para evitar recursão
}