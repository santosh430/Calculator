package com.example.calculator

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.calculator.databinding.ActivityMainBinding
import com.example.calculator.enums.DataUpdateType
import com.example.calculator.enums.ViewType


class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding
    private var arrayListCalcHistory = ArrayList<String>()
    private var mListAdapter = ArrayList<String>()
    private lateinit var rvCalcHistory:RecyclerView
    interface IOCalcHistoryListener{
        fun listenDataChange(data:String, updateType: DataUpdateType)
    }

    private val iOCalcHistoryListener = object :IOCalcHistoryListener{
        override fun listenDataChange(data: String, updateType: DataUpdateType) {
            if (updateType == DataUpdateType.Insert){
                arrayListCalcHistory.add(data)
                mListAdapter.removeAll(mListAdapter.toSet())
                for (i in arrayListCalcHistory.indices.reversed()) {
                    mListAdapter.add(arrayListCalcHistory[i])
                }
                rvCalcHistory.adapter = AdapterCalcHistory(mListAdapter)
            }else{
                mListAdapter.removeAll(mListAdapter.toSet())
                rvCalcHistory.adapter = AdapterCalcHistory(mListAdapter)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        val btnCancel = binding.btnCancelText
        val btnEquals = binding.btnEquals
        val btnCancelSingleText = binding.btnCancelSingleText
        val btnClearData = binding.btnClearData
        val btnMod = binding.btnModulus
        val btnDivision = binding.btnDivision
        val btnMultiplication = binding.btnMultiplication
        val btnSubtraction = binding.btnSubtraction
        val btnAddition = binding.btnAddition
        val btnNum1 = binding.btnNumber1
        val btnNum2 = binding.btnNumber2
        val btnNum3 = binding.btnNumber3
        val btnNum4 = binding.btnNumber4
        val btnNum5 = binding.btnNumber5
        val btnNum6 = binding.btnNumber6
        val btnNum7 = binding.btnNumber7
        val btnNum8 = binding.btnNumber8
        val btnNum9 = binding.btnNumber9
        val btnNum0 = binding.btnNumber0
        val btnBrackets = binding.btnBrackets
        val tvDisplayNumbers = binding.tvDisplayNumbers
        val adapterCalcHistory: AdapterCalcHistory
        rvCalcHistory = binding.rvCalcHistory

        rvCalcHistory.apply {
            layoutManager = LinearLayoutManager(this@MainActivity,LinearLayoutManager.VERTICAL,true)
            adapterCalcHistory = AdapterCalcHistory(mListAdapter)
            adapter = adapterCalcHistory
        }

        val mapOfButtons = HashMap<ViewType,View>()
        mapOfButtons.apply {
            this[ViewType.Cancel] = btnCancel
            this[ViewType.CancelSingleText] = btnCancelSingleText
            this[ViewType.ClearData] = btnClearData
            this[ViewType.Equals] = btnEquals
            this[ViewType.Addition] = btnAddition
            this[ViewType.Subtraction] = btnSubtraction
            this[ViewType.Multiplication] = btnMultiplication
            this[ViewType.Division] = btnDivision
            this[ViewType.Mod] = btnMod
            this[ViewType.Brackets] = btnBrackets
            this[ViewType.TVDisplayNumbers] = tvDisplayNumbers
            this[ViewType.Brackets] = btnBrackets
            this[ViewType.Btn0] = btnNum0
            this[ViewType.Btn1] = btnNum1
            this[ViewType.Btn2] = btnNum2
            this[ViewType.Btn3] = btnNum3
            this[ViewType.Btn4] = btnNum4
            this[ViewType.Btn5] = btnNum5
            this[ViewType.Btn6] = btnNum6
            this[ViewType.Btn7] = btnNum7
            this[ViewType.Btn8] = btnNum8
            this[ViewType.Btn9] = btnNum9
        }
        CustomClickListener(mapOfButtons,iOCalcHistoryListener)
    }
}