package com.example.myapp.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapp.data.local.ProblemaDatabase
import com.example.myapp.data.model.Problema
import com.example.myapp.data.repository.ProblemaRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProblemaViewModel(application: Application) : AndroidViewModel(application) {
    private val problemaDao = ProblemaDatabase.getDatabase(application).problemaDao()
    private val problemaRepository = ProblemaRepository(problemaDao)

    private val _ultimoProblemaReportado = MutableStateFlow<Problema?>(null)
    val ultimoProblemaReportado: StateFlow<Problema?> = _ultimoProblemaReportado

    fun addProblema(latitude: Double, longitude: Double, descricao: String, gravidade: String) {
        viewModelScope.launch {
            //Log.d("ProblemaViewModel", "Tentando adicionar um novo problema...")
            val novoProblema = Problema(
                latitude = latitude,
                longitude = longitude,
                descricao = descricao,
                gravidade = gravidade
            )
            problemaRepository.insertProblema(novoProblema)
            //Log.d("ProblemaViewModel", "Problema inserido com sucesso.")
            val ultimoProblema = problemaRepository.getUltimoProblema()
            _ultimoProblemaReportado.value = ultimoProblema
            //Log.d("ProblemaViewModel", "Último problema reportado (após inserção): $ultimoProblema")
        }
    }
}