package com.mohdsohaib.tipcalculate

import android.annotation.SuppressLint
import android.nfc.Tag
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import android.widget.SeekBar
import android.widget.TextView
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*

private const val TAG = "MainActivity"
private const val INITIAL_TIP_PERCENT = 15
class MainActivity : AppCompatActivity() {

   //private lateinit var edtBillAmount : EditText
  // private lateinit var txtPercentage : TextView
   // private lateinit var seekBarTip : SeekBar

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

            seekBarTip.progress = INITIAL_TIP_PERCENT
            txtPercentage.text = "$INITIAL_TIP_PERCENT%"
            seekBarTip.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
                override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                    Log.i(TAG,"onProgressChanged $progress")
                    txtPercentage.text = "$progress%"
                    cumputeTipAmount()
                }
                override fun onStartTrackingTouch(seekBar: SeekBar?) {}
                override fun onStopTrackingTouch(seekBar: SeekBar?) {}

            })

        edtBase.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                Log.i(TAG,"afterTextChanged $s")
                cumputeTipAmount()
            }
        })
    }

    private fun cumputeTipAmount() {

        if(edtBase.text.isEmpty()){
            txtTip.text = ""
            txtTotal.text = ""
            return
        }

         val baseAmount = edtBase.text.toString().toDouble()
         val tipAmount = seekBarTip.progress

        val tipTotal = baseAmount * tipAmount / 100
        val totalAmount = baseAmount + tipTotal

        txtTip.text = "%.2f".format(tipTotal)
        txtTotal.text = "%.2f".format(totalAmount)
    }
}

