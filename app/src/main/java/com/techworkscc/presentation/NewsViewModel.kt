package com.techworkscc.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.techworkscc.domain.ListNewsVO
import com.techworkscc.domain.NewsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NewsViewModel(private val newsUseCase: NewsUseCase) : ViewModel() {
   val newsLivewData: MutableLiveData<ListNewsVO> = MutableLiveData()

   fun loadNews(){
      viewModelScope.launch(Dispatchers.Main) {
         val response = newsUseCase.loadNews()
         newsLivewData.value = response
      }
   }
}