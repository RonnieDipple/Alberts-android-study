package com.stepasha.sprintmaps

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions


class MyMapActivity : AppCompatActivity(), OnMapReadyCallback {
    //initialize maps
    private lateinit var mMap: GoogleMap

    var ACCESSLOCATION = 1223
    var location: Location?=null
    var oldLocation:Location?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_map)


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.mapFragment) as SupportMapFragment
        mapFragment.getMapAsync(this)
        //check permissions to load the map
        Companion.checkPermission(this)


    }
    fun getUserLocation(){
        Toast.makeText(this,"User Location Access on", Toast.LENGTH_LONG).show()
        //listen for current location
        var myLocation = MyLocationListener()
        //then check permissions again
        var locationManager = getSystemService(Context.LOCATION_SERVICE)as LocationManager
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

        } else {
            //if all good, get my location every 3 minutes within 3 feet accuracy
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 3, 3f, myLocation)
        }
        //since my marker is constantly moving, i need my own custom thread
        var myThread = MyThread()
        myThread.start()

    }
  //  override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
  //      when (requestCode) {
  //          //request code this
  //          ACCESSLOCATION -> {
  //              //and all permissions granted
  //              if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//
  //                  getUserLocation()
//
  //              } else {
  //                  Toast.makeText(this, "We can't access your location", Toast.LENGTH_LONG).show()
//
  //              }
  //          }
  //      }
  //      super.onRequestPermissionsResult(requestCode, permissions, grantResults)
  //  }


    override fun onMapReady(p0: GoogleMap?) {

    }
    companion object {
        fun checkPermission(mapsActivity: MyMapActivity){
            //check permissions on older versions
            if(Build.VERSION.SDK_INT >=23) {

                if (ActivityCompat.checkSelfPermission(
                        mapsActivity,
                        Manifest.permission.ACCESS_FINE_LOCATION
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    mapsActivity.requestPermissions(
                        arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                        mapsActivity.ACCESSLOCATION
                    )
                    return

                }
            }
            mapsActivity.getUserLocation()

        }
    }
    // Get User location
//this is for location thats changing
    inner class MyLocationListener : LocationListener {

        //init stands for primary constructor, used when there are no other constructors
        init {
            location = Location("Start")
            location!!.longitude = 0.0
            location!!.latitude = 0.0
        }

        override fun onLocationChanged(p0: Location?) {
            location=p0
        }
        override fun onStatusChanged(p0: String?, p1: Int, p2: Bundle?) {
            //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }
        override fun onProviderEnabled(p0: String?) {
            //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }
        override fun onProviderDisabled(p0: String?) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }
    }
    inner class MyThread : Thread() {
        init {
            oldLocation = Location("Start")
            oldLocation!!.longitude = 0.0
            oldLocation!!.latitude = 0.0
        }



        override fun run(){
            while(true){
                try {
                    if(oldLocation!!.distanceTo(location)==0f){
                        continue //continue auto jumps to most updated loop
                    }
                    oldLocation = location

                    runOnUiThread {
                        mMap.clear()

                        //show my location
                        val myLocation = LatLng(location!!.latitude, location!!.longitude)

                        mMap.addMarker(
                            MarkerOptions()
                                .position(myLocation)
                                .title("Me")
                                .snippet("This is my Location")
                                .icon(BitmapDescriptorFactory.fromResource(R.drawable.pin)))

                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(myLocation, 14f))
                    }
                    Thread.sleep(1000)
                }catch (ex:Exception){
                }
            }
        }
    }
}
