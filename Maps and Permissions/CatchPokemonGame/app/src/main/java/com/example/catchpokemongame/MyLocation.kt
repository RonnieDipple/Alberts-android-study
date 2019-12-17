package com.example.catchpokemongame


import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory
import com.google.android.exoplayer2.extractor.ExtractorsFactory
import com.google.android.exoplayer2.source.ExtractorMediaSource
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.trackselection.TrackSelector
import com.google.android.exoplayer2.upstream.BandwidthMeter
import com.google.android.exoplayer2.upstream.DataSource
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.LatLng
import kotlinx.android.synthetic.main.activity_my_location.*


//another way of pulling someones location.
//instead of steadily fetching locations, it will return the result on request which is easier to pass around activities
// referenced from androidclarified.com/fusedlocationproviderclient-current-location-example/
private val TAG = "key"
class MyLocation : AppCompatActivity(), GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, com.google.android.gms.location.LocationListener{
    //declaring instances
    private var mLatitudeTextView: TextView? = null
    private var mLongitudeTextView: TextView? = null
    //tools that I need API client for connection listening
    private var mGoogleApiClient: GoogleApiClient? = null
    //extracting location
    private var mLocation: Location? = null
    //location manager
    private var mLocationManager: LocationManager? = null
    //location listeners
    private var mLocationRequest: LocationRequest? = null
    private val listener: com.google.android.gms.location.LocationListener? = null
    //update intervals
    private val UPDATE_INTERVAL = (4 * 1000).toLong()  /* 40 secs */
    private val FASTEST_INTERVAL: Long = 10000 /* 10 sec */

    private var locationManager: LocationManager? = null

    private val isLocationEnabled: Boolean
        get() {
            locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
            return locationManager!!.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager!!.isProviderEnabled(
                LocationManager.NETWORK_PROVIDER
            )
        }
    //exo player
    private lateinit var simpleExoplayer: SimpleExoPlayer
    private lateinit var context: Context
    //where to start video from
    private var playbackPosition = 0L
    private val dashUrl =
        "https://ia801302.us.archive.org/18/items/gametrailers-2043_20130109/Pokemon%20X%20%26%20Pokemon%20Y%20-%20Debut%20Trailer-aajnLebXcI0.mp4"
    // "http://rdmedia.bbc.co.uk/dash/ondemand/bbb/2/client_manifest-separate_init.mpd"
    //now android built in player for sound effects
    var player: MediaPlayer? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_location)

        //normal player initialization
        player = MediaPlayer.create(this, R.raw.accomplished)

        //play the sound where needed
        player = MediaPlayer.create(this, R.raw.accomplished)

        //by far the easiest way to implement exo
        // reference: https://www.blueappsoftware.com/android-exoplayer-example/
try {
    //connection meeter
    val bandwidthMeter: BandwidthMeter = DefaultBandwidthMeter()
    //track selector
    val trackSelector: TrackSelector = DefaultTrackSelector(bandwidthMeter)
    //create an instance and parse the video
    simpleExoplayer = ExoPlayerFactory.newSimpleInstance(this, trackSelector)
    val videoURI = Uri.parse(dashUrl)
    val dataSourceFactory =
        DefaultHttpDataSourceFactory("exoplayer_video")
    val extractorsFactory: ExtractorsFactory = DefaultExtractorsFactory()
    //event listeners and handlers are for user interaction with video
    val mediaSource: MediaSource =
        ExtractorMediaSource(videoURI, dataSourceFactory, extractorsFactory, null, null)
//then play the video once parsed
    myEXo.player = simpleExoplayer
    simpleExoplayer.prepare(mediaSource)
    simpleExoplayer.playWhenReady = true
    progressBar.visibility = View.GONE
}catch (e: Exception){
    Log.e("MainAcvtivity", " exoplayer error $e")
    progressBar.visibility = View.VISIBLE
}
        mLatitudeTextView = findViewById<TextView>(R.id.latitude_textview)
        mLongitudeTextView = findViewById<TextView>(R.id.longitude_textview)
//set callbacks on connection to API
        mGoogleApiClient = GoogleApiClient.Builder(this)
            .addConnectionCallbacks(this)
            .addOnConnectionFailedListener(this)
            .addApi(LocationServices.API)
            .build()

        mLocationManager = this.getSystemService(Context.LOCATION_SERVICE) as LocationManager

        Log.d("no", "no connection to API")
        checkLocation() //check whether location service is enable or not in your  phone


    }

    //this is the same as overriding onRequest permission result
    override fun onConnected(p0: Bundle?) {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        }

        startLocationUpdates()

        mLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient)

        if (mLocation == null) {
            startLocationUpdates()
        }
        if (mLocation != null) {

            // mLatitudeTextView.setText(String.valueOf(mLocation.getLatitude()));
            //mLongitudeTextView.setText(String.valueOf(mLocation.getLongitude()));
        } else {
            Toast.makeText(this, "Location not Detected", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onConnectionSuspended(i: Int) {
        Log.i(TAG, "Connection Suspended")
        mGoogleApiClient!!.connect()
    }

    override fun onConnectionFailed(connectionResult: ConnectionResult) {
        Log.i(TAG, "Connection failed. Error: " + connectionResult.errorCode)
    }

    //good practice to stop any services on stop and on destroy, so they dont continue running when the user navigates away from the app
    override fun onStart() {
        super.onStart()

        if (mGoogleApiClient != null) {
            mGoogleApiClient!!.connect()
        }
    }

    override fun onStop() {
        super.onStop()


        if (mGoogleApiClient!!.isConnected) {
            mGoogleApiClient!!.disconnect()
        }
    }

    //location listener
    override fun onLocationChanged(location: Location) {

        val msg = "Updated Location: " +
                location.latitude.toString() + "," +
                location.longitude.toString()
        mLatitudeTextView!!.text = location.latitude.toString()
        mLongitudeTextView!!.text = location.longitude.toString()
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()


        // You can now create a LatLng Object for use with maps
        val latLng = LatLng(location.latitude, location.longitude)
    }

    //location request is being used here for location updates
    private fun startLocationUpdates() {
        // Create the location request
        mLocationRequest = LocationRequest.create()
            .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
            .setInterval(UPDATE_INTERVAL)
            .setFastestInterval(FASTEST_INTERVAL)
        // Request location updates
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        }
        LocationServices.FusedLocationApi.requestLocationUpdates(
            mGoogleApiClient,
            mLocationRequest, this
        )
        Log.d("reque", "--->>>>")
    }

    //function to build custom alert box
    private fun showAlert() {
        val dialog = AlertDialog.Builder(this)
        dialog.setTitle("Enable Location")
            .setMessage("Your Locations Settings is set to 'Off'.\nPlease Enable Location to " + "use this app")
            .setPositiveButton("Location Settings") { paramDialogInterface, paramInt ->
                //send user to the settings screen to change the permission
                val myIntent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                startActivity(myIntent)
            }
            .setNegativeButton("Cancel") { paramDialogInterface, paramInt -> }
        dialog.show()
    }

    private fun checkLocation(): Boolean {
        if (!isLocationEnabled)
            showAlert()
        return isLocationEnabled
    }


}

