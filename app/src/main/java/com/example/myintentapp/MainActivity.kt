package com.example.myintentapp

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts


class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var edtName: EditText
    private lateinit var edtAge:EditText
    private lateinit var tvResult : TextView

    private val resultLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == ActivityMoveForResult.RESULT_CODE && result.data != null) {
            val selectedValue =
                result.data?.getIntExtra(ActivityMoveForResult.EXTRA_SELECTED_VALUE, 0)
            tvResult.text = "Hasil : $selectedValue"
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        edtName= findViewById(R.id.edt_name)
        edtAge = findViewById(R.id.edt_age)

        val btnMoveActivity: Button = findViewById(R.id.btn_move_activity)
        val btnMoveActivityWithData: Button = findViewById(R.id.btn_move_activity_with_data)
        val btnMoveActivityWithObject: Button = findViewById(R.id.btn_move_activity_with_object)
        val btnDialNumber: Button  = findViewById(R.id.btn_dial_number)
        val btnMoveForResult:Button = findViewById(R.id.btn_move_for_result)
        tvResult = findViewById(R.id.tv_result)

        btnMoveForResult.setOnClickListener(this)

        btnMoveActivity.setOnClickListener(this)
        btnMoveActivityWithData.setOnClickListener(this)
        btnMoveActivityWithObject.setOnClickListener(this)
        btnDialNumber.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        val inputName = edtName.text.toString()
        val inputAge = edtAge.text.toString()
        when (v?.id){
            R.id.btn_move_activity->{
                val moveIntent = Intent(this@MainActivity, MoveActivity::class.java)
                startActivity(moveIntent)
            }
            R.id.btn_move_activity_with_data->{
                val moveIntentWithData = Intent(this@MainActivity, ActivityWithData::class.java)
                moveIntentWithData.putExtra(ActivityWithData.EXTRA_NAME, inputName)
                moveIntentWithData.putExtra(ActivityWithData.EXTRA_AGE, inputAge)
                startActivity(moveIntentWithData)
            }
            R.id.btn_move_activity_with_object->{
                val moveIntentWithObject = Intent(this@MainActivity, ActivityWIthObject::class.java)
                val person = Person(
                    "Muhamad Afif Fadillah",
                    21,
                    "Avwaveaf@gmail.com",
                    "Jakarta"
                )
                moveIntentWithObject.putExtra(ActivityWIthObject.EXTRA_PERSON, person)
                startActivity(moveIntentWithObject)
            }
            R.id.btn_dial_number->{
                val phoneNumber = "081388500059"
                val dialPhoneIntent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phoneNumber"))
                startActivity(dialPhoneIntent)
            }
            R.id.btn_move_for_result->{
                val moveForResultIntent =
                    Intent(this@MainActivity, ActivityMoveForResult::class.java)
                resultLauncher.launch(moveForResultIntent)
            }

        }
    }
}