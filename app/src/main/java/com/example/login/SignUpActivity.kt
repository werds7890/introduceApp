package com.example.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class SignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        val signUpBtn=findViewById<Button>(R.id.signupBtn)

        signUpBtn.setOnClickListener {
            val name=findViewById<EditText>(R.id.nameText).text.toString()
            val id=findViewById<EditText>(R.id.idText).text.toString()
            val pw=findViewById<EditText>(R.id.pwText).text.toString()

            if(name.isEmpty() || id.isEmpty() || pw.isEmpty()){
                Toast.makeText(this,"입력되지 않은 정보가 있습니다.",Toast.LENGTH_SHORT).show()
            }else {
                intent.putExtra("name",name)
                intent.putExtra("id",id)
                intent.putExtra("pw",pw)
                setResult(RESULT_OK,intent)
                finish()
            }
        }
    }
}