package com.demate.jettrivia.screens

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.demate.jettrivia.data.DataOrException
import com.demate.jettrivia.model.QuestionItem
import com.demate.jettrivia.repository.QuestionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuestionViewModel @Inject constructor(private val repository: QuestionRepository) :
    ViewModel() {
    val data: MutableState<DataOrException<ArrayList<QuestionItem>, Boolean, Exception>> =
        mutableStateOf(
            DataOrException(null, true, Exception(""))
        )

    init {
        getQuestions()
    }

    private fun getQuestions() {
        viewModelScope.launch {
            data.value.isLoading = true
            data.value = repository.getQuestions()
            if (data.value.data.toString().isNotEmpty()) data.value.isLoading = false
        }
    }
}
