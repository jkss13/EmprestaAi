package com.emprestaai

import android.app.Application
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.content.Intent.FLAG_ACTIVITY_SINGLE_TOP
import com.google.firebase.Firebase
import com.google.firebase.auth.auth

class EmprestaAi : Application() {
    override fun onCreate() {
        super.onCreate()
        Firebase.auth.addAuthStateListener { firebaseAuth ->
            if (firebaseAuth.currentUser != null) {
                goToMain()
            } else {
                goToLogin()
            }
        }
    }

    private fun goToMain() {
        this.startActivity(
            Intent(this, HomePageActivity::class.java).setFlags(
                FLAG_ACTIVITY_SINGLE_TOP or FLAG_ACTIVITY_NEW_TASK
            )
        )
    }

    private fun goToLogin() {
        this.startActivity(
            Intent(this, LoginActivity::class.java).setFlags(
                FLAG_ACTIVITY_SINGLE_TOP or FLAG_ACTIVITY_NEW_TASK
            )
        )
    }
}