package com.example.myintentapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.RadioGroup

class ActivityMoveForResult : AppCompatActivity(), View.OnClickListener {
    private lateinit var btnGetResult: Button
    private lateinit var rgSelect:RadioGroup

    companion object{
        const val EXTRA_SELECTED_VALUE = ""
        const val RESULT_CODE = 110
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_move_for_result)
        btnGetResult = findViewById(R.id.btn_get_result)
        rgSelect = findViewById(R.id.rg_select)

        btnGetResult.setOnClickListener(this)
    }

    override fun onClick(p0: View) {
        if(p0.id === R.id.btn_get_result){
            if(rgSelect.checkedRadioButtonId>0){
                var value = 0
                when (rgSelect.checkedRadioButtonId) {
                    R.id.rb_100 -> value = 100
                    R.id.rb_200 -> value = 200
                    R.id.rb_300 -> value = 300
                    R.id.rb_400 -> value = 400
                }
                val resultIntent = Intent()
                resultIntent.putExtra(EXTRA_SELECTED_VALUE, value)
                setResult(RESULT_CODE, resultIntent)
                finish()
            }
        }
    }
}