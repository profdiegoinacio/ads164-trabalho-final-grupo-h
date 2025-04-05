package com.example.myapp.data.repository

import android.util.Log
import com.example.myapp.data.local.ProblemaDao
import com.example.myapp.data.model.Problema

class ProblemaRepository(private val problemaDao: ProblemaDao) {

    suspend fun insertProblema(problema: Problema) {
        //Log.d("ProblemaRepository", "Inserindo problema no banco de dados: $problema")
        problemaDao.insertProblema(problema)
        // Log.d("ProblemaRepository", "Problema inserido no banco de dados.")
    }

    suspend fun getUltimoProblema(): Problema? {
        // Log.d("ProblemaRepository", "Obtendo o último problema do banco de dados...")
        val ultimo = problemaDao.getUltimoProblema()
        //Log.d("ProblemaRepository", "Último problema obtido: $ultimo")
        return ultimo
    }
}