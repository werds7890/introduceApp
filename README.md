# introduceApp

https://github.com/werds7890/introduceApp/assets/81506621/e5d80884-2617-4ba5-ab4b-ec0878476d89

## 파일목록

### HomeActivity.kt
자기소개 페이지

```kotlin
package com.example.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import kotlin.random.Random

class HomeActivity : AppCompatActivity() {
    private val imageArr=arrayOf(
        R.drawable.bear2,
        R.drawable.bear3,
        R.drawable.bear4,
        R.drawable.bear5,
        R.drawable.bear6
    )
    private val randomIndex=Random.nextInt(imageArr.size)
    private val randomImg=imageArr[randomIndex]

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        val idData=intent.getStringExtra("dataFromId")
        val homeData=findViewById<TextView>(R.id.homeId)
        val homeImage=findViewById<ImageView>(R.id.homeImage)
        homeImage.setImageResource(randomImg)

        homeData.setText("아이디 : ${idData}")

        val closeHome=findViewById<Button>(R.id.close_home)
        closeHome.setOnClickListener {
            finish()
        }
    }
}
```

### SignInActivity.kt
로그인 페이지

```kotlin
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
```

### SignUpActivity
회원가입 페이지

```kotlin
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
```

### activity_home.xml
```kotlin
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:orientation="vertical"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:background="@color/pBlueBold"
    >
    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:orientation="vertical"
        android:background="@drawable/back2">
    
    <LinearLayout
        android:layout_width="match_parent"
        android:layout_weight="2"
        android:layout_height="0dp"
        android:layout_margin="20dp">

        <ImageView
            android:id="@+id/homeImage"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_weight="1"
            app:srcCompat="@drawable/bear2" />
    </LinearLayout>
    <androidx.constraintlayout.widget.ConstraintLayout
        android:layout_width="match_parent"
        android:layout_weight="2"
        android:layout_height="0dp"
        android:layout_margin="20dp">

        <TextView
            style="@style/hometextview"
            android:id="@+id/homeId"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_marginEnd="16dp"
            android:text="아이디 : "
            app:layout_constraintEnd_toEndOf="parent"
            app:layout_constraintStart_toStartOf="parent"
            app:layout_constraintTop_toTopOf="parent" />

        <TextView
            style="@style/hometextview"
            android:id="@+id/textView4"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_marginTop="16dp"
            android:layout_marginEnd="16dp"
            android:text="이름 : 김현걸"
            app:layout_constraintEnd_toEndOf="parent"
            app:layout_constraintStart_toStartOf="parent"
            app:layout_constraintTop_toBottomOf="@+id/homeId" />

        <TextView
            style="@style/hometextview"
            android:id="@+id/textView5"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_marginTop="16dp"
            android:layout_marginEnd="16dp"
            android:text="나이 : 29"
            app:layout_constraintEnd_toEndOf="parent"
            app:layout_constraintStart_toStartOf="parent"
            app:layout_constraintTop_toBottomOf="@+id/textView4" />

        <TextView
            style="@style/hometextview"
            android:id="@+id/textView6"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_marginTop="16dp"
            android:layout_marginEnd="16dp"
            android:text="MBTI : ISTP"
            app:layout_constraintEnd_toEndOf="parent"
            app:layout_constraintStart_toStartOf="parent"
            app:layout_constraintTop_toBottomOf="@+id/textView5" />

    </androidx.constraintlayout.widget.ConstraintLayout>
    <LinearLayout
        android:layout_width="match_parent"
        android:layout_weight="1"
        android:layout_height="0dp"
        android:layout_margin="20dp">

        <android.widget.Button
            android:id="@+id/close_home"
            style="@style/btn"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_weight="1"
            android:background="@drawable/background_button"
            android:text="닫기"
            android:drawableLeft="@drawable/background_icon"
            android:paddingRight="130dp"
            android:gravity="center"
            />
        </LinearLayout>
    </LinearLayout>
</LinearLayout>


```

### activity_sign_in.xml
```kotlin
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:orientation="vertical"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:background="@color/pBlueBold"
    >

    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:orientation="vertical"
        android:background="@drawable/back2"
        >


    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:orientation="vertical"
        android:layout_margin="20dp"
        android:layout_weight="1">

        <ImageView
            android:id="@+id/imageView"
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:src="@drawable/bear4" />
    </LinearLayout>

    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:orientation="vertical"
        android:layout_margin="20dp"
        android:layout_weight="1">

        <TextView
            style="@style/textview"
            android:id="@+id/textViewId"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:text="아이디" />

        <EditText
            style="@style/basic"
            android:id="@+id/idText"
            android:layout_width="match_parent"
            android:layout_height="50dp"
            android:ems="10"
            android:inputType="text"
            android:hint="아이디를 입력해주세요." />

        <TextView
            style="@style/textview"
            android:id="@+id/textViewPw"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:text="비밀번호" />

        <EditText
            style="@style/basic"
            android:id="@+id/pwText"
            android:layout_width="match_parent"
            android:layout_height="50dp"
            android:ems="10"
            android:hint="비밀번호를 입력해주세요."
            android:inputType="textPassword"
            />
    </LinearLayout>

    <androidx.constraintlayout.widget.ConstraintLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:layout_margin="20dp"
        android:layout_weight="1"
        android:orientation="vertical">

        <Button
            style="@style/btn"
            android:id="@+id/loginBtn"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:text="로그인"
            android:onClick="validation"
            app:layout_constraintEnd_toEndOf="parent"
            app:layout_constraintStart_toStartOf="parent"
            app:layout_constraintTop_toTopOf="parent" />

        <Button
            style="@style/btn"
            android:id="@+id/signupBtn"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:layout_marginTop="16dp"
            android:text="회원가입"
            app:layout_constraintEnd_toEndOf="parent"
            app:layout_constraintStart_toStartOf="parent"
            app:layout_constraintTop_toBottomOf="@+id/loginBtn" />
        </androidx.constraintlayout.widget.ConstraintLayout>
    </LinearLayout>
</LinearLayout>
```

### activity_sign_up.xml
```kotlin
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:orientation="vertical"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:background="@color/pBlueBold">

    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:orientation="vertical"
        android:background="@drawable/back2">

    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:orientation="vertical"
        android:layout_margin="20dp"
        android:layout_weight="1">

        <ImageView
            android:id="@+id/imageView2"
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:src="@drawable/bear5" />
    </LinearLayout>

    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:orientation="vertical"
        android:layout_marginLeft="20dp"
        android:layout_marginRight="20dp"
        android:layout_weight="0.9">

        <TextView
            style="@style/textview"
            android:id="@+id/textViewName"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:text="이름" />

        <EditText
            style="@style/basic"
            android:id="@+id/nameText"
            android:layout_width="match_parent"
            android:layout_height="50dp"
            android:ems="10"
            android:inputType="text"
            android:hint="이름을 입력해주세요.." />

        <TextView
            style="@style/textview"
            android:id="@+id/textViewId"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:text="아이디" />

        <EditText
            style="@style/basic"
            android:id="@+id/idText"
            android:layout_width="match_parent"
            android:layout_height="50dp"
            android:ems="10"
            android:inputType="text"
            android:hint="아이디를 입력해주세요." />

        <TextView
            style="@style/textview"
            android:id="@+id/textViewPw"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:text="비밀번호" />

        <EditText
            style="@style/basic"
            android:id="@+id/pwText"
            android:layout_width="match_parent"
            android:layout_height="50dp"
            android:ems="10"
            android:hint="비밀번호를 입력해주세요."
            android:inputType="textPassword" />
    </LinearLayout>

    <androidx.constraintlayout.widget.ConstraintLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:layout_margin="20dp"
        android:layout_weight="1"
        android:orientation="vertical">

        <Button
            android:id="@+id/signupBtn"
            style="@style/btn"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:text="회원가입"
            app:layout_constraintEnd_toEndOf="parent"
            app:layout_constraintStart_toStartOf="parent"
            app:layout_constraintTop_toTopOf="parent" />
        </androidx.constraintlayout.widget.ConstraintLayout>
    </LinearLayout>
</LinearLayout>
```

### drawable/background_button.xml
selector 이용, 클릭 시 색깔

```kotlin
<?xml version="1.0" encoding="utf-8"?>
<selector xmlns:android="http://schemas.android.com/apk/res/android">
    <item android:state_pressed="true"
          android:drawable="@drawable/clicked"/>

    <item android:state_pressed="false"
        android:drawable="@drawable/unclicked" >
    </item>
</selector>
```

### drawable/background_icon.xml
selector 이용, 클릭 시 아이콘

```kotlin
<?xml version="1.0" encoding="utf-8"?>
<selector xmlns:android="http://schemas.android.com/apk/res/android">
    <item android:state_pressed="true"
        android:drawable="@drawable/close_icon"/>

    <item android:state_pressed="false"
        android:drawable="@drawable/btn_icon" >
    </item>
</selector>
```
