package com.seo.rxdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.activity_coroutine_ui.*
import kotlinx.coroutines.*

import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.channels.awaitClose

import kotlinx.coroutines.flow.*
import kotlin.coroutines.CoroutineContext


/**
 * 코루틴 플로우 UI 처리
 * https://www.youtube.com/watch?v=7m5T10OYGUA
 */
class CoroutineUIActivity : AppCompatActivity() {

    companion object{
        private const val TAG = "CoroutineUIActivity";
    }

    private var job:Job = Job()
    private val jobContext: CoroutineContext
        get() = Dispatchers.IO + job

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coroutine_ui)
        GlobalScope.launch(jobContext){
            edit_input.textChagesToFlow()
                    .debounce(2000)
                    .filter { it!!.isNotEmpty() }
                    .onEach {
                        Log.d(TAG, "== onEach ==  text = $it")
                    }
                    .launchIn(this)
//                    .collect {
//                        Log.d(TAG, " == collect ==  text = $it")
//                    }
        }
    }

    override fun onDestroy() {
        jobContext.cancel()
        super.onDestroy()
    }

    @ExperimentalCoroutinesApi
    fun EditText.textChagesToFlow():Flow<CharSequence?>{
        return callbackFlow<CharSequence?> {
            val textWatcher = object : TextWatcher{

                override fun beforeTextChanged(text: CharSequence?, start: Int, count: Int, after: Int) {
                    Log.d(TAG, "beforeTextChanged: text = $text")
                    Unit
                }

                override fun afterTextChanged(text: Editable?) {
                    Log.d(TAG, "afterTextChanged: text = $text")
                    Unit
                }

                override fun onTextChanged(text: CharSequence?, start: Int, before: Int, count: Int) {
                    Log.d(TAG, "onTextChanged: text = $text")
                    offer(text)
                }

            }
            addTextChangedListener(textWatcher)

            awaitClose {
                // 콜백이 사라질때 실행되는 메소드
                Log.d(TAG, "textChagesToFlow: <====== awaitClose")
                removeTextChangedListener(textWatcher)
            }
        }.onStart{
            Log.d(TAG, "textChagesToFlow: onStart ====>")
            emit(text)
        }

    }

}