package com.seo.rxdemo


import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.Gravity
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.jakewharton.rxbinding3.view.clicks
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.rxkotlin.toObservable
import io.reactivex.subjects.*
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.TimeUnit


class MainActivity : AppCompatActivity() {

    private val disposables = CompositeDisposable()
    lateinit var source1:Observable<Int>
    lateinit var source2:Observable<Int>

    companion object{
        private const val TAG = "MainActivity";
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val subscribe = btnTest.clicks()
                .debounce(500, TimeUnit.MILLISECONDS)
                //.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {

            Log.d(TAG, "onCreate: btnClicked!!!")
            Toast.makeText(this, "clicked!!", Toast.LENGTH_SHORT).show()


        }

        disposables.add(subscribe)
        test_Observable()
        test_single_maybe()
        test_AsyncSubject()
        test_BehaviorSubject()
        test_PublishSubject()
        test_ReplaySubject()
        test_UnicastSubject()
        test_ConnectableObservable()

//
//        editText.textChanges().subscribe {
//            Log.d(TAG, "onCreate: textChanges text = $it")
//        }
//
//        editText.afterTextChangeEvents().subscribe {
//            Log.d(TAG, "onCreate: afterTextChangeEvents text = ${it.view.text.toString()}")
//        }
//
//        editText.beforeTextChangeEvents().subscribe {
//            Log.d(TAG, "onCreate: beforeTextChangeEvents text = ${it.text}")
//
//        }

        val subscribe1 = Observable.merge(btnTest1.clicks().map { btnTest1 },
                btnTest2.clicks().map { btnTest2 },
                btnTest3.clicks().map { btnTest3 }
        ).subscribe {
            when (it) {
                btnTest1 -> {
                    Log.d(TAG, "Observable.merge : btn 1 ")

                }
                btnTest2 -> {
                    Log.d(TAG, "Observable.merge : btn 2 ")
                }
                btnTest3 -> {
                    Log.d(TAG, "Observable.merge : btn 3 ")
                }
            }
        }
        disposables.add(subscribe1)

        val editObservable = PublishSubject.create<String> {

            val watcher = object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    it.onNext(p0.toString())
                }

                override fun afterTextChanged(p0: Editable?) {

                }

            }
            editText.addTextChangedListener(watcher)
        }


        editObservable.subscribe {
            Log.d(TAG, "onCreate: editObservable.subscribe : it = $it <=====")
            btnTest1.text = it


            val toast = Toast.makeText(applicationContext,
                    it, Toast.LENGTH_SHORT)
            toast.setGravity(Gravity.TOP or Gravity.CENTER_HORIZONTAL, 0, 0)
            toast.show()


        }

    }

    private fun test_ConnectableObservable() {
        Log.d(TAG, "test_ConnectableObservable: start ==========>")
        val b_subject = PublishSubject.create<String>()
        val connectOB = b_subject.publish()
        b_subject.onNext("1")
        connectOB.subscribe { data ->
            Log.d(TAG, "test_ConnectableObservable: subscribe1 data = $data")
        }

        b_subject.subscribe { data ->
            Log.d(TAG, "test_ConnectableObservable:PublishSubject1 data = ${data} ")
        }

        connectOB.subscribe { data ->
            Log.d(TAG, "test_ConnectableObservable: subscribe2 data = $data")
        }


        b_subject.subscribe { data ->
            Log.d(TAG, "test_ConnectableObservable:PublishSubject2 data = ${data} ")
        }
        connectOB.connect()

        b_subject.onNext("2")
        b_subject.onNext("3")

        connectOB.subscribe { data ->
            Log.d(TAG, "test_ConnectableObservable: subscribe3 data = $data")
        }

        b_subject.subscribe { data ->
            Log.d(TAG, "test_ConnectableObservable:PublishSubject3 data = ${data} ")
        }


        b_subject.onNext("4")
        b_subject.onComplete()

        Log.d(TAG, "test_ConnectableObservable: end <============")
    }

    private fun test_UnicastSubject() {
        Log.d(TAG, "test_UnicastSubject: start ==========>")
        val b_subject = UnicastSubject.create<String>()
        b_subject.onNext("AA")
        b_subject.onNext("BB")
        b_subject.onNext("CC")
        Log.d(TAG, "test_UnicastSubject: subject start -->")
        b_subject.subscribe { data ->
            Log.d(TAG, "test_UnicastSubject: subscribe2 data = ${data}")
        }
        b_subject.onNext("EE")
        b_subject.onNext("FF")


        Log.d(TAG, "test_UnicastSubject: end <=============")
    }

    private fun test_ReplaySubject() {
        Log.d(TAG, "test_ReplaySubject: start =========>")
        val b_subject = ReplaySubject.create<String>()

        b_subject.subscribe { data ->
            Log.d(TAG, "test_ReplaySubject: subscribe1 data = $data")
        }
        b_subject.onNext("AA")
        b_subject.onNext("BB")

        b_subject.onNext("CC")
        b_subject.subscribe { data ->
            Log.d(TAG, "test_ReplaySubject: subscribe2 data = ${data}")
        }
        b_subject.onNext("EE")
        b_subject.onNext("FF")



        Log.d(TAG, "test_ReplaySubject: end <==============")
    }

    private fun test_PublishSubject() {
        Log.d(TAG, "test_PublishSubject: start ========>")

        val b_subject = PublishSubject.create<String>()
        b_subject.subscribe { data ->
            Log.d(TAG, "test_PublishSubject: subscribe1 data = $data")
        }
        b_subject.onNext("AA")
        b_subject.onNext("BB")
        b_subject.subscribe { data ->
            Log.d(TAG, "test_PublishSubject: subscribe2 data = ${data}")
        }
        b_subject.onNext("CC")
        b_subject.onNext("DD")
        b_subject.onNext("EE")
        b_subject.onComplete()



        Log.d(TAG, "test_PublishSubject: end <===========")
    }

    private fun test_BehaviorSubject() {
        Log.d(TAG, "test_BehaviorSubject: start ========>")
        val b_subject = BehaviorSubject.createDefault("AA")
        b_subject.subscribe { data ->
            Log.d(TAG, "test_BehaviorSubject: subscribe1 data = $data")
        }
        b_subject.onNext("BB")
        b_subject.onNext("CC")
        b_subject.subscribe { data ->
            Log.d(TAG, "test_BehaviorSubject: subscribe2 data = ${data}")
        }
        b_subject.onNext("dd")
        b_subject.onComplete()



        Log.d(TAG, "test_BehaviorSubject: end <===========")
    }

    private fun test_AsyncSubject() {
        Log.d(TAG, "test_aa: start ============>")
        val a_subject = AsyncSubject.create<String>()
        a_subject.subscribe { data ->
            Log.d(TAG, "test_aa: #1 data  => $data")
        }
        a_subject.onNext("AA")
        a_subject.onNext("BB")
        a_subject.onNext("CC")
        a_subject.subscribe { data ->
            Log.d(TAG, "test_aa: #2 data  => $data")
        }
        a_subject.onComplete() // <----- 까지 받음
        a_subject.onNext("DD")  /// complete 이후기 때문에 구독 안됨


        a_subject.subscribe { data ->
            Log.d(TAG, "test_aa: #3 data  => $data")
        }

        Log.d(TAG, "test_aa: ===========================================")

        val dataArray = arrayOf(10.1f, 13.4f, 12.5f)
        val observable = dataArray.toObservable()
        val subject = AsyncSubject.create<Float>()
        subject.subscribe { data ->
            Log.d(TAG, "test_aa: data sub 1 = $data")
        }

        subject.subscribe { data ->
            Log.d(TAG, "test_aa: data sub 2 = $data")
        }

        observable.subscribe(subject)





        Log.d(TAG, "test_aa: end <=====================")

    }

    private fun test_single_maybe() {
        Log.d(TAG, "test_single_maybe: start =====>")
        val single = Single.just("hellow single")
        val subscribe1 = single.subscribe { data ->
            Log.d(TAG, "test_single_maybe: data = $data")
        }
        disposables.add(subscribe1)

        val subscribe2 = single.subscribeBy {
            Log.d(TAG, "test_single_maybe: it = $it")
        }
        disposables.add(subscribe2)

        Log.d(TAG, "test_single_maybe: ======================")

        val source1 = Observable.just("Hello single first")
        val subscribe3 =  Single.fromObservable(source1).subscribe { it -> Log.d(TAG, "test_single_maybe: it = $it") }
        disposables.add(subscribe3)

        val colors = arrayListOf<String>("Red", "Blue", "Green")
        val subscribe4 = colors.toObservable().first("default value").subscribe { data ->
            Log.d(TAG, "test_single_maybe: data = $data")
        }
        disposables.add(subscribe4)

        val subscribe5 = Observable.empty<Any>().single("default Item")
                .subscribe { data ->
                    Log.d(TAG, "test_single_maybe: data -> $data")
                }
        disposables.add(subscribe5)

        val subscribe6  = Observable.just(Order("A_1"), "B_2")
                .take(1)
                .single(Order("Default id"))
                .subscribeBy {
                    Log.d(TAG, "test_single_maybe: it = ${it.toString()}")
                }
        disposables.add(subscribe6)





        Log.d(TAG, "test_single_maybe: <========")
    }


    fun test_Observable(){


        Log.d(TAG, "main: start ===> ")

        Log.d(TAG, "main: subscribe1 start => ")
        val subscribe1 = Observable.just(1, 2, 3, 4)
                .subscribe() {
                    Log.d(TAG, "main: i = $it")
                }
        disposables.add(subscribe1)

        Log.d(TAG, "main: subscribe2 start => ")
        val subscribe2 = Observable.just(1, 2, 3, 4)
                .subscribeWith(object : DisposableObserver<Int>() {
                    override fun onNext(t: Int) {
                        Log.d(TAG, "onNext: t = $t")
                    }

                    override fun onError(e: Throwable) {
                        Log.d(TAG, "onError: E = ${e.toString()}")
                    }

                    override fun onComplete() {
                        Log.d(TAG, "onComplete: <====")
                    }

                })
        disposables.add(subscribe2)


        Log.d(TAG, "main: subscribe3 start => ")
        val arrayList = ArrayList<String>()
        arrayList.add("test1")
        arrayList.add("test2")
        arrayList.add("test3")

        val subscribe3 = Observable.fromIterable(arrayList)
                .subscribe {
                    Log.d(TAG, "main: it")
                }
        disposables.add(subscribe3)
        Log.d(TAG, "main: subscribe4 start => ")
        val subscribe4 = arrayList.toObservable()
                .subscribeWith(object : DisposableObserver<String>() {
                    override fun onNext(t: String) {
                        Log.d(TAG, "onNext: t = $t")
                    }

                    override fun onError(e: Throwable) {
                        Log.d(TAG, "onError: e = ${e.toString()}")
                    }

                    override fun onComplete() {
                        Log.d(TAG, "onComplete: <====")
                    }

                })
        disposables.add(subscribe4)

        Log.d(TAG, "main: end <=== ")

    }


    override fun onDestroy() {
        super.onDestroy()
        disposables.clear()
    }
}


data class Order(val id: String){
    override fun toString(): String {
        return "Order(id='$id')"
    }
}