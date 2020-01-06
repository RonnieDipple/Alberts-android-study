package com.stepasha.buildinglocator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.stepasha.buildinglocator.model.LoginResponse
import com.stepasha.buildinglocator.model.UserLogin
import com.stepasha.buildinglocator.retrofit.ServiceBuilder
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    companion object{
        var token:String = ""
        var userId:Int = 0
    }
    private var validatedUsername: Boolean = false
    private var validatedPassword: Boolean = false
    private var error: Boolean? = false

    //Making these variables global since they're probably gonna be needed when checking the database
    lateinit var username: String
    lateinit var password: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        //Move to the list activity if username and password are good
        btn_login.setOnClickListener {

            validateUsername()
            validatePassword()
            //confirmLogin()
            startActivity(Intent(this, MainActivity::class.java))
        }

        //Move to the register activity when the register button is clicked on
        btn_register.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }
    //Checks to see if the entered username is okay or not.
    private fun validateUsername(): Boolean {
        //Gets the text from the username text input layout
        username = text_input_username.editText?.text.toString().trim()

        if (username.isEmpty()) {
            text_input_username.error = "Field can't be empty"
            validatedUsername = false
            return false
        } else if (username.length < 4) {
            text_input_username.error = "Username should be at least four characters"
            return false
        } else if (username.length > 12) {
            text_input_username.error = "Username can't be more than 12 characters"
            return false
        } else {
            //Removes the error message if it already exists
            text_input_username.error = null
            text_input_username.isErrorEnabled = false
            validatedUsername = true
            return true
        }
    }
    //Checks to see if the entered password is okay or not.
    private fun validatePassword(): Boolean {
        //Gets the text from the password text input layout
        password = text_input_password.editText?.text.toString().trim()

        if (password.isEmpty()) {
            text_input_password.error = "Field can't be empty"
            validatedPassword = false
            return false
        } else if (password.length < 4) {
            text_input_password.error = "Password should be at least four characters"
            return false
        } else if (password.length > 12) {
            text_input_password.error = "Password can't be more than 12 characters"
            return false
        } else {
            //Removes the error message if it already exists
            text_input_password.error = null
            text_input_password.isErrorEnabled = false
            validatedPassword = true
            return true
        }
    }
    //Checks to see if both login and password are correct. If so, move to ListActivity.

    private fun confirmLogin() {
        //If either the username or password is incorrect, prevent logging in.
        //For some reason, doing if(!validateUsername() || !validatePassword()) messes up the error message)
        if (!validatedUsername || !validatedPassword)
            return

        val call: Call<LoginResponse> = ServiceBuilder.create().userLoginPost(UserLogin(username, password))

        call.enqueue(object: Callback<LoginResponse> {
            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                //Log.i("onFailure", t.message)
                return
            }

            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                error = response.body()?.error
                Log.i("onResponsee", response.body()?.error.toString())

                if(response.isSuccessful){
                    token = response.body()!!.token
                   userId = response.body()!!.userId
                    Log.i("onResponse", token)
                    Log.i("onResponse", username.toString())
                    text_input_username.error = null
                    text_input_password.error = null
                    startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                }
                else{
                    text_input_username.error = "Either Invalid Username or Password"
                    text_input_password.error = "Either Invalid Username or Password"
                }
            }
        })
    }

}

