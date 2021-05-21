package com.seo.rxdemo.hiltdemo

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.PermissionChecker
import androidx.leanback.animation.LogDecelerateInterpolator
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.*
import com.naver.maps.map.CameraUpdate.*
import com.naver.maps.map.util.FusedLocationSource
import com.seo.rxdemo.R
import kotlinx.android.synthetic.main.activity_naver_map.*
import java.util.*

class NaverMapActivity : AppCompatActivity(), OnMapReadyCallback , LocationListener {

    companion object{
        private const val TAG = "NaverMapActivity";
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1000


        private const val PERMISSION_REQUEST_CODE = 100
        private val PERMISSIONS = arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION)

    }

    var naverMap:NaverMap? = null
    private lateinit var locationSource: FusedLocationSource
    private var locationManager: LocationManager? = null
    private var tempLocation:Location? = null




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_naver_map)

        title = "Naver Map Demo"
        savedInstanceState?.let {
            mapView.onSaveInstanceState(it)
        }

//        mapView.getMapAsync {
//            Log.d(TAG, "onCreate: == getMapAsync ==")
//
//
//        }
        locationManager = getSystemService(LOCATION_SERVICE) as LocationManager?

        locationSource =
                FusedLocationSource(this, LOCATION_PERMISSION_REQUEST_CODE)
        mapView.getMapAsync(this)

        btnTest.setOnClickListener {
            val coord = LatLng(37.5670135, 126.9783740)
            Toast.makeText(this,
                "위도: ${coord.latitude}, 경도: ${coord.longitude}",
                Toast.LENGTH_SHORT).show()
        }
        btnMapTypeBasic.setOnClickListener {
            naverMap?.mapType = NaverMap.MapType.Basic
        }

        btnMapTypeNavi.setOnClickListener {
            naverMap?.mapType = NaverMap.MapType.Navi
        }

        btnMapTypeSatellite.setOnClickListener {
            naverMap?.mapType = NaverMap.MapType.Satellite
        }
        btnMapTypeHybrid.setOnClickListener {
            naverMap?.mapType = NaverMap.MapType.Hybrid
        }

        btnMapTypeTerrain.setOnClickListener {
            naverMap?.mapType = NaverMap.MapType.Terrain
        }

        btnCameraPosition.setOnClickListener {
            val cameraPosition = naverMap?.cameraPosition
            Log.d(TAG, "onCreate: cameraPosition = ${cameraPosition?.toString()}")
        }
        btnMoveCamera.setOnClickListener {
            val cameraUpdate = CameraUpdate.scrollTo(LatLng(37.5666102, 126.9783881))
                    .animate(CameraAnimation.Fly,2000)
                    .finishCallback {
                        Toast.makeText(this,"카메라이동 완료",Toast.LENGTH_SHORT).show()
                    }
                    .cancelCallback {
                        Toast.makeText(this,"카메라이동 취소",Toast.LENGTH_SHORT).show()
                    }

            naverMap?.moveCamera(cameraUpdate)

        }

        val calender = Calendar.getInstance()

        // 6월달
        calender.set(2021,6-1,1)
        val monthTemp = calender.get(Calendar.MONTH) + 1
        //calender.set(Calendar.DATE,1)
        val dayofWeek = calender.get(Calendar.DAY_OF_WEEK)
        val maxDayOfMonth = calender.getActualMaximum(Calendar.DAY_OF_MONTH)




        Log.d(TAG, "onCreate: calender : ${calender.toString()}")
        Log.d(TAG, "onCreate: monthTemp = ${monthTemp}")
        Log.d(TAG, "onCreate: dayofWeek : ${dayofWeek.toString()}")
        Log.d(TAG, "onCreate: maxDayOfMonth = ${maxDayOfMonth}")

        when(dayofWeek){
            Calendar.SUNDAY -> Log.d(TAG, "onCreate: 일요일")
            Calendar.MONDAY -> Log.d(TAG, "onCreate: 월요일")
            Calendar.TUESDAY-> Log.d(TAG, "onCreate: 화요일")
            Calendar.WEDNESDAY -> Log.d(TAG, "onCreate: 수요일")
            Calendar.THURSDAY -> Log.d(TAG, "onCreate: 목요일")
            Calendar.FEBRUARY -> Log.d(TAG, "onCreate: 금요일")
            Calendar.SATURDAY -> Log.d(TAG, "onCreate: 토요일")
        }


    }

    override fun onStatusChanged(provider: String, status: Int,
                                 extras: Bundle) {
    }

    override fun onProviderEnabled(provider: String) {
    }

    override fun onProviderDisabled(provider: String) {
    }

    private fun hasPermission(): Boolean {
        return PermissionChecker.checkSelfPermission(this, PERMISSIONS[0]) ==
                PermissionChecker.PERMISSION_GRANTED &&
                PermissionChecker.checkSelfPermission(this, PERMISSIONS[1]) ==
                PermissionChecker.PERMISSION_GRANTED
    }


    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<String>,
                                            grantResults: IntArray) {
//        if (locationSource.onRequestPermissionsResult(requestCode, permissions,
//                        grantResults)) {
//            if (!locationSource.isActivated) { // 권한 거부됨
//                naverMap?.locationTrackingMode = LocationTrackingMode.None
//            }
//            return
//        }

        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (hasPermission()) {
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return
                }
                locationManager?.requestLocationUpdates(
                        LocationManager.GPS_PROVIDER, 1000, 10f, this)
            }
            return
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun onLocationChanged(location: Location) {
        Log.d(TAG, "onLocationChanged: ====> ")
        if (location == null) {
            Log.d(TAG, "onLocationChanged: Location = null")
            return
        }
        tempLocation = location


        Log.d(TAG, "onLocationChanged: Location = ${location.toString()}")

        naverMap?.let {
            val coord = LatLng(location)

            val locationOverlay = it.locationOverlay
            locationOverlay.isVisible = true
            locationOverlay.position = coord
            locationOverlay.bearing = location.bearing

            it.moveCamera(CameraUpdate.scrollTo(coord))
        }
    }

    override fun onMapReady(map: NaverMap) {
        Log.d(TAG, "onMapReady: ====>")
        naverMap = map

        Log.d(TAG, "onMapReady: locationSource = ${locationSource.toString()}")

        tempLocation?.let {
            val cameraUpdate = CameraUpdate.scrollTo(LatLng(it.latitude, it.longitude))
            naverMap?.moveCamera(cameraUpdate)
        }


        naverMap?.locationSource = locationSource
        /// 카메라 이동시 이벤트
        naverMap?.addOnCameraChangeListener {reason, animated ->
            when(reason){
                REASON_DEVELOPER -> Log.d(TAG, "addOnCameraChangeListener: REASON_DEVELOPER")
                REASON_GESTURE -> Log.d(TAG, "addOnCameraChangeListener: REASON_GESTURE")
                REASON_CONTROL -> Log.d(TAG, "addOnCameraChangeListener: REASON_CONTROL")
                REASON_LOCATION -> Log.d(TAG, "addOnCameraChangeListener: REASON_LOCATION")
                else-> Log.d(TAG, "addOnCameraChangeListener: unknown reason : $reason")
            }
        }

        naverMap?.setOnMapClickListener { point, latLng ->
            Log.d(TAG, "setOnMapClickListener: point=${point.toString()}, latLng=${latLng.toString()}")
        }
        naverMap?.setOnMapLongClickListener { point, latLng ->
            Log.d(TAG, "setOnMapLongClickListener: point=${point.toString()}, latLng=${latLng.toString()}")
            Toast.makeText(this,"point=${point.toString()}, latLng=${latLng.toString()}",Toast.LENGTH_SHORT)
                    .show()
        }
    }



    override fun onStart() {
        super.onStart()
        mapView.onStart()

        if (hasPermission()) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED
                    && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED) {

                return
            }
            locationManager?.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER, 1000, 10f, this)
        } else {
            ActivityCompat.requestPermissions(
                    this, PERMISSIONS, PERMISSION_REQUEST_CODE)
        }
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mapView.onSaveInstanceState(outState)
    }

    override fun onStop() {
        super.onStop()
        mapView.onStop()

        locationManager?.removeUpdates(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }




}