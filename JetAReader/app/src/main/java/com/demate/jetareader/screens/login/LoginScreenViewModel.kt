package com.demate.jetareader.screens.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.demate.jetareader.model.MUser
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class LoginScreenViewModel : ViewModel() {
    val loadingState = MutableStateFlow(LoadingState.IDLE)
    private val auth: FirebaseAuth = Firebase.auth

    private val _loading = MutableLiveData(false)
    val loading: LiveData<Boolean> = _loading

    fun singInWithEmailAndPassword(email: String, password: String, home: () -> Unit) =
        viewModelScope.launch {
            try {
                auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Log.d(
                                "LoginScreenViewModel",
                                "singInWithEmailAndPassword: ${task.result.toString()}"
                            )
                            home()
                        } else {
                            Log.d(
                                "LoginScreenViewModel",
                                "singInWithEmailAndPassword: ${task.result.toString()}"
                            )
                            //loadingState.value = LoadingState.FAILED
                        }
                    }
            } catch (ex: Exception) {
                //loadingState.value = LoadingState.FAILED
            }
        }

    fun createUserWithEmailAndPassword(
        email: String,
        password: String,
        home: () -> Unit
    ) {
        if (_loading.value == false) {
            _loading.value = true
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val displayName = task.result.user?.email?.split("@")?.get(0)
                        createUser(displayName)
                        home()
                    } else {
                        Log.d(
                            "LoginScreenViewModel",
                            "createUserWithEmailAndPassword: ${task.result.toString()}"
                        )
                    }
                    _loading.value = false
                }
        }


    }

    private fun createUser(displayName: String?) {
        val userId = auth.currentUser?.uid

        val user = MUser(
            userId = userId.toString(),
            displayName = displayName.toString(),
            avatarUrl = "",
            quote = "",
            profession = "Android Developer",
            id = ""
        ).toMap()


        FirebaseFirestore.getInstance().collection("users")
            .document(userId.toString())
            .set(user)
            .addOnSuccessListener {
                Log.d("LoginScreenViewModel", "createUser: User created")
            }
            .addOnFailureListener {
                Log.d("LoginScreenViewModel", "createUser: User not created")
            }

    }
}