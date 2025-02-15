package com.example.m_7_fetchdata

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import androidx.lifecycle.MutableLiveData

class MainViewModel : ViewModel() {
    val postsLiveData = MutableLiveData<List<Post>>()
    val loading = MutableLiveData<Boolean>()

    fun fetchPosts() {
        viewModelScope.launch {
            loading.value = true
            try {
                val posts = RetrofitInstance.api.getPosts()
                postsLiveData.value = posts
            } catch (e: Exception) {
                e.printStackTrace()
            }
            loading.value = false
        }
    }
}
