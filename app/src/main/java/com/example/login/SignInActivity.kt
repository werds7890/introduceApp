package com.example.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts

class SignInActivity : AppCompatActivity() {
    private lateinit var resultLauncher:ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        val signupBtn=findViewById<Button>(R.id.signupBtn)  // 회원가입 버튼
        val validation=findViewById<Button>(R.id.loginBtn)  // 로그인 버튼

        val idCheck=findViewById<EditText>(R.id.idText)     // 아이디 텍스트
        val pwCheck=findViewById<EditText>(R.id.pwText)     // 비밀번호 텍스트

        setResultNext()

        signupBtn.setOnClickListener {
            val intent= Intent(this,SignUpActivity::class.java)
            resultLauncher.launch(intent)
        }

        validation.setOnClickListener {
            val id=idCheck.text.toString()
            val pw=pwCheck.text.toString()

            if(id.isEmpty() || pw.isEmpty()){
                Toast.makeText(this,"아이디, 비밀번호를 입력해주세요.",Toast.LENGTH_SHORT).show()
            }else {
                Toast.makeText(this,"로그인 성공",Toast.LENGTH_SHORT).show()
                val intent=Intent(this, HomeActivity::class.java)
                intent.putExtra("dataFromId",id)
                startActivity(intent)
            }
        }

    }

    private fun setResultNext() {
        val idCheck=findViewById<EditText>(R.id.idText)     // 아이디 텍스트
        val pwCheck=findViewById<EditText>(R.id.pwText)     // 비밀번호 텍스트
        resultLauncher=registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) {
            result -> if(result.resultCode== RESULT_OK) {
                val id=result.data?.getStringExtra("id") ?:""
                val pw=result.data?.getStringExtra("pw") ?:""

                idCheck.setText(id)
                pwCheck.setText(pw)
        }
        }
    }



}