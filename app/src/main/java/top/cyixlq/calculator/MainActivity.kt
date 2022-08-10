package top.cyixlq.calculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import top.cyixlq.calculator.ui.screen.Calculator
import top.cyixlq.calculator.ui.theme.CalculatorTheme

class MainActivity : ComponentActivity() {

    private var mLast = 0 // 按下操作符按钮前的数字
    private var mCurrent = 0 // 按下操作符后的数字
    private var mOp = "" // 运算符
    private var mInputFirstNum = true // 是否需要覆盖当前所有数字，否的话就往后面追加数字
    private val mText = mutableStateOf("0")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CalculatorTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    Calculator(mText.value) {
                        operation(it)
                    }
                }
            }
        }
    }

    private fun operation(op: String) {
        when (op) {
            "+", "-", "*", "/" -> {
                if (mOp.isNotEmpty()) {
                    mCurrent = mText.value.toInt()
                    calculation()
                } else {
                    mLast = mText.value.toInt()
                }
                mOp = op
                mInputFirstNum = true
            }
            "=" -> {
                if (mOp.isNotEmpty()) {
                    mCurrent = mText.value.toInt()
                    calculation()
                }
                mOp = ""
                mInputFirstNum = true
            }
            "ac" -> {
                mOp = ""
                mLast = 0
                mCurrent = 0
                mText.value = "0"
                mInputFirstNum = true
            }
            else -> {
                if (mInputFirstNum) {
                    mText.value = op
                    mInputFirstNum = false
                } else {
                    mText.value += op
                }
            }
        }
    }

    private fun calculation() {
        mLast = when (mOp) {
            "+" -> mLast + mCurrent
            "/" -> mLast / mCurrent
            "*" -> mLast * mCurrent
            else -> mLast - mCurrent
        }
        mText.value = mLast.toString()
    }
}

