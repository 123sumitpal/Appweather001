package com.example.mwether

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mwether.api.Constant
import com.example.mwether.api.NetworkResponse
import com.example.mwether.api.Retrofitinstance
import com.example.mwether.api.WeatherModel
import kotlinx.coroutines.launch

class WeatherViewModel:ViewModel() {

    private val wetherApi = Retrofitinstance.wetherApi
    private val _weatherResult = MutableLiveData<NetworkResponse<WeatherModel>>()
    val weatherResult : LiveData<NetworkResponse<WeatherModel>> = _weatherResult

    fun getData(city:String){

        _weatherResult.value =NetworkResponse.Loading

        viewModelScope.launch {

            try {
                val response= wetherApi.getWether(Constant.apikey,city)
                if(response.isSuccessful){
                    response.body()?.let {
                        _weatherResult.value = NetworkResponse.Success(it)
                    }
                }else{
                    _weatherResult.value = NetworkResponse.Error("faild to load data")
                }
            }
            catch (e : Exception){
                _weatherResult.value = NetworkResponse.Error("faild to load data")

            }
        }
    }
}