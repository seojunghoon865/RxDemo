package com.seo.rxdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ProgressBar
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_coroutine.*
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.channels.ticker
import kotlinx.coroutines.flow.*
import java.time.LocalDateTime
import java.util.concurrent.CancellationException


class CoroutineActivity : AppCompatActivity() {

    companion object{
        private const val TAG = "CoroutineActivity";
    }

    private val PROGRESS_MAX = 100
    private val PROGRESS_START = 0
    private val JOB_TIME = 4000 //ms
    private lateinit var job: CompletableJob

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coroutine)

        job_button.setOnClickListener {
            /// lateinit 확인
            if(!::job.isInitialized){
                initJob()
            }
            job_progress_bar.startJobCancel(job)
        }


        runFlow()


        /// 코루틴 타이머
        //runTimer()


    }

    private fun runTimer(){

        /**
         * https://www.python2.net/questions-961679.htm
         *
         * 아직 실험적이라고 생각하지만 TickerChannel을 사용하여 모든 X 밀리미터마다 값을 생성 할 수 있습니다.

        val tickerChannel = ticker(delayMillis = 60_000, initialDelayMillis = 0)
        repeat(10) {
        tickerChannel.receive()
        val currentTime = LocalDateTime.now()
        println(currentTime)
        }

        "구독"이 각 "틱"에 대해 무언가를하는 동안 작업을 계속해야한다면 launch  이 채널에서 읽고 원하는 것을 수행하는 백그라운드 코 루틴 :

        val tickerChannel = ticker(delayMillis = 60_000, initialDelayMillis = 0)
        launch {
        for (event in tickerChannel) { // event is of type Unit, so we don't really care about it
        val currentTime = LocalDateTime.now()
        println(currentTime)
        }
        }
        // when you're done with the ticker and don't want more events
        tickerChannel.cancel()
         */


        val tickerChannel = ticker(delayMillis = 1000, initialDelayMillis = 0)

        CoroutineScope(IO).launch {
//            val tickerChannel = ticker(delayMillis = 1000, initialDelayMillis = 0)
//            repeat(10) {
//                tickerChannel.receive()
//                val currentTime = LocalDateTime.now()
//                Log.d(TAG, "currentTime : $currentTime ")
//            }


            for (event in tickerChannel) { // event is of type Unit, so we don't really care about it
                val currentTime = LocalDateTime.now()
                Log.d(TAG, "currentTime : $currentTime ")
            }

        }

        //tickerChannel.cancel()
    }

    private fun runFlow() {
        CoroutineScope(IO).launch {

            Log.d(TAG, "Receiving numbers")
    //            sendNumbers()
    //
    //                    .collect {
    //                Log.d(TAG, "Received number : $it")
    //            }
    /*
                (1..10).asFlow()
                        .map {
                            delay(300)
                           it
                        }
                        .filter { it % 2 == 0 }
                        .transform {
                            emit("Emitting string $it")
                        }
                        .collect {
                            Log.d(TAG, "Received number : $it")
                        }

     */

            (1..10).asFlow()
                    .map {
                        delay(300)
                        it
                    }
                    //  .onEach { check(it != 7) }
                    .catch { e -> Log.d(TAG, "Caught exception $e") }
                    .onCompletion { Log.d(TAG, "Flow completion") }
                    .collect {
                        Log.d(TAG, "Received number : $it")
                    }


            Log.d(TAG, "Finished receiving numbers")
        }
    }

    //fun sendNumbers() = flowOf("one","two","tree")

    /*
    fun sendNumbers() = listOf(1,2,3,4,5).asFlow()
     */

    /*
    fun sendNumbers(): Flow<Int> = flow{
        val primesList = listOf(2,3,5,7,11,13,17,19,23,29)
        primesList.forEach {
            delay(it * 100L)
            emit(it)
        }
    }
     */

    private fun initJob() {
        job_button.text = "Start Job #1"
        updateJobCompleteTextView("")
        job = Job()
        job.invokeOnCompletion {
            it?.message.let {
                var msg = it
                if(msg.isNullOrBlank()){
                    msg = "Unknown cancellation error"
                }
                Log.e(TAG, "initJob: $job was cancelled. Reason: $msg" )
                showToast(msg)
            }
        }
        job_progress_bar.max = PROGRESS_MAX
        job_progress_bar.progress = PROGRESS_START
    }

    private fun showToast(msg: String) {
        GlobalScope.launch(Main) {
            Toast.makeText(this@CoroutineActivity,msg,Toast.LENGTH_SHORT).show()
        }
    }

    private fun updateJobCompleteTextView(text: String) {
        GlobalScope.launch(Main) {
            job_complete_text.text = text
        }
    }


    /**
     * This is extension of ProgressBar
     * 프로그래스바클래스를 확장하여 프로그래스바내의 매소드들을 사용 할수 있음.
     */
    fun ProgressBar.startJobCancel(job:Job){

        if(this.progress > 0){
            Log.d(TAG, "startJobCancel: $job is already active. Cancelling....")
            resetJob()
        }
        else{

            job_button.text = "Cancel Job #1"
            CoroutineScope(IO + job).launch {
                for(i in PROGRESS_START.. PROGRESS_MAX){
                    delay((JOB_TIME / PROGRESS_MAX).toLong())
                    job_progress_bar.progress = i
                    //this@startJobCancel.progress = i

                }
                updateJobCompleteTextView("Job is complete!")
            }
        }
    }

    private fun resetJob() {
        if(job.isActive || job.isCompleted){
            job.cancel(CancellationException("Resetting job"))
        }
        initJob()

    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }
}