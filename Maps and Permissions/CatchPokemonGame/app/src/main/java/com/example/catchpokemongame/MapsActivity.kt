package com.example.catchpokemongame



import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions


class MapsActivity : AppCompatActivity(), OnMapReadyCallback {
//initialize maps
    private lateinit var mMap: GoogleMap



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.mapFragment) as SupportMapFragment
        mapFragment.getMapAsync(this)
        //check permissions to load the map
        Companion.checkPermission(this)
        //then load pokemons
        loadPockemon()
    }
//static for location access
    var ACCESSLOCATION = 123

    fun getUserLocation(){
        Toast.makeText(this,"User Location Access on",Toast.LENGTH_LONG).show()
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




    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when (requestCode) {
            //request code this
            ACCESSLOCATION -> {
                //and all permissions granted
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    getUserLocation()

                } else {
                    Toast.makeText(this, "We can't access your location", Toast.LENGTH_LONG).show()

                }
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }


    override fun onMapReady(googleMap: GoogleMap) {

        mMap = googleMap

    }

    var location:Location?=null

    // Get User location
//this is for location thats changing
    inner class MyLocationListener :LocationListener {

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
//this is for my thread
    var oldLocation:Location?=null

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

                        mMap.addMarker(MarkerOptions()
                            .position(myLocation)
                            .title("Me")
                            .snippet("This is my Location")
                            .icon(BitmapDescriptorFactory.fromResource(R.drawable.ash)))

                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(myLocation, 14f))



                        // for list of pokemons
                        for(i in 0 until listPockemons.size){
                            var newPockemon = listPockemons[i]
                            //if not caught
                            if(!newPockemon.isCatched){
                                val pockemonLoc = LatLng(newPockemon.location!!.latitude, newPockemon.location!!.longitude)

                                mMap.addMarker(MarkerOptions()
                                    .position(pockemonLoc)
                                    .title(newPockemon.name)
                                    .snippet(newPockemon.des+", Power: "+newPockemon.power)
                                    .icon(BitmapDescriptorFactory.fromResource(newPockemon.image!!)))
//if you are within 2 ft of pokemon he is yours
                                if(location!!.distanceTo((newPockemon.location))<2){

                                    newPockemon.isCatched = true
                                        // mark the pokemon caught
                                    listPockemons[i]= newPockemon
                                    //increase tho points
                                    playerPower += newPockemon.power!!

                                    Toast.makeText(applicationContext,
                                        "You have caught up a new pockemon, your new power is $playerPower",Toast.LENGTH_LONG).show()

                                }
                            }
                        }
                    }
                    Thread.sleep(1000)
                }catch (ex:Exception){
                }
            }
        }
    }
    //my points
    var playerPower = 0.0
//  pokemons
    var listPockemons = ArrayList<Pokemon>()
//load pokemons in the list (As many as you want)
    private fun loadPockemon(){
        listPockemons.add(Pokemon("Bulbasaur","Here is Bulbasaur",R.drawable.bulbasaur,50.0,37.778999,-122.401846))
        listPockemons.add(Pokemon("Charmander","Here is Charmander",R.drawable.charmander,100.0,37.794956,-122.410494))
        listPockemons.add(Pokemon("Squirtle","Here is Squirtle",R.drawable.squirtle,150.0,37.781662,-122.412253))

    }
//check permissions on older versions
    companion object {
        fun checkPermission(mapsActivity: MapsActivity){
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
}