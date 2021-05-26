package com.seo.rxdemo.hiltdemo.bottom_navi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment

import com.seo.rxdemo.R
import kotlinx.android.synthetic.main.activity_bottom_navigation_demo.*

/**
 * 참고 링크
 * https://choheeis.github.io/%EC%95%88%EB%93%9C%EB%A1%9C%EC%9D%B4%EB%93%9C/2020/03/01/BottomNavigation.html
 */
class BottomNavigationDemoActivity : AppCompatActivity() {

    companion object{
        private const val TAG = "BottomNavigationDemoActivity";
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bottom_navigation_demo)
        Log.d(TAG, "onCreate: ==== start ====")

        bottomNavi.setOnItemSelectedListener{
            Log.d(TAG, "onCreate: ${it.title}")

            when(it.itemId){
                R.id.menu_home->replaceFragment(HomeFragment())
                R.id.menu_search->replaceFragment(SearchFragment())
                R.id.menu_myinfo->replaceFragment(MyInfoFragment())
                R.id.menu_setting->replaceFragment(SettingFragment())
            }

            return@setOnItemSelectedListener true
        }

        bottomNavi.selectedItemId = R.id.menu_home
    }
    private fun replaceFragment(frg: Fragment){
        val beginTransaction = supportFragmentManager.beginTransaction()
        beginTransaction.replace(R.id.frameLayout,frg)
        beginTransaction.commit()
    }
}