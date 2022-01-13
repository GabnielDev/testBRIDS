package com.example.testbri.repository

import com.example.testbri.di.AppModule
import javax.inject.Inject

class Repository @Inject constructor() {

    suspend fun postKandidat(kandidat: HashMap<String, Any>) = AppModule.provideClient().postKandidat(kandidat)

}