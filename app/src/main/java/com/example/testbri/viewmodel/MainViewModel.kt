package com.example.testbri.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testbri.data.Response
import com.example.testbri.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor (private val repository: Repository): ViewModel() {



    fun postKandidat(kandidat: HashMap<String, Any>): LiveData<Response> {
        val kandidatData = MutableLiveData<Response>()
        viewModelScope.launch {
            repository.postKandidat(kandidat).let {
                try {
                    val data = it.body()
                    kandidatData.value = data
                } catch (e: Exception) {
                    e.localizedMessage.toString()
                }
            }
        }
        return kandidatData
    }



}