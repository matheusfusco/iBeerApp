package com.fiap.matheusfusco.matheusfusco.map


import android.Manifest
import android.content.DialogInterface
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.os.Build
import android.os.Bundle
import android.os.Looper
import android.support.v4.app.ActivityCompat
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v7.app.AlertDialog
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fiap.matheusfusco.matheusfusco.R

import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.location.*
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.MapsInitializer
import com.google.android.gms.maps.model.BitmapDescriptorFactory

class MapFragment : Fragment(), OnMapReadyCallback {

    private var mLocationRequest: LocationRequest? = null
    private val UPDATE_INTERVAL = (10 * 1000).toLong()  /* 10 secs */
    private val FASTEST_INTERVAL: Long = 2000 /* 2 sec */

    private var latitude = 0.0
    private var longitude = 0.0

    private lateinit var mGoogleMap: GoogleMap

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_map, container, false)

        var mapView = view.findViewById<MapView>(R.id.mapview)
        mapView.onCreate(savedInstanceState);

        mapView.onResume();
        try {
            MapsInitializer.initialize(activity!!.applicationContext)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        mapView.getMapAsync(this)
        return view
    }

    override fun onStart() {
        super.onStart()
        startLocationUpdates()
    }

    override fun onMapReady(googleMap: GoogleMap) {

        mGoogleMap = googleMap;

        if (mGoogleMap != null) {
            mGoogleMap!!.addMarker(MarkerOptions().position(LatLng(latitude, longitude)).title(resources.getString(R.string.current_location)))
        }

    }

    // 3.
    protected fun startLocationUpdates() {
        // initialize location request object
        mLocationRequest = LocationRequest.create()
        mLocationRequest!!.run {
            setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
            setInterval(UPDATE_INTERVAL)
            setFastestInterval(FASTEST_INTERVAL)
        }

        // initialize location setting request builder object
        val builder = LocationSettingsRequest.Builder()
        builder.addLocationRequest(mLocationRequest!!)
        val locationSettingsRequest = builder.build()

        // initialize location service object
        val settingsClient = LocationServices.getSettingsClient(this.activity!!)
        settingsClient!!.checkLocationSettings(locationSettingsRequest)

        // call register location listener
        registerLocationListner()
    }

    private fun registerLocationListner() {
        // initialize location callback object
        val locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult?) {
                onLocationChanged(locationResult!!.getLastLocation())
            }
        }
        // 4. add permission if android version is greater then 23
        if(Build.VERSION.SDK_INT >= 23 && checkPermission()) {
            LocationServices.getFusedLocationProviderClient(this.activity!!).requestLocationUpdates(mLocationRequest, locationCallback, Looper.myLooper())
        }
    }

    //
    fun onLocationChanged(location: Location) {
        // create message for toast with updated latitude and longitudefa
        var msg = "Updated Location: " + location.latitude  + " , " +location.longitude

        // show toast message with updated location
        //Toast.makeText(this,msg, Toast.LENGTH_LONG).show()
        val location = LatLng(location.latitude, location.longitude)
        mGoogleMap!!.clear()
        mGoogleMap!!.addMarker(MarkerOptions().position(location).title(resources.getString(R.string.current_location)))
        mGoogleMap.moveCamera(CameraUpdateFactory.newLatLng(location))
    }

    private fun checkPermission() : Boolean {
        if (ContextCompat.checkSelfPermission(this.context!! , android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            requestPermissions()
            return false
        }
    }

    private fun requestPermissions() {
        ActivityCompat.requestPermissions(this.activity!!, arrayOf("Manifest.permission.ACCESS_FINE_LOCATION"),1)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 1) {
            if (permissions[0] == android.Manifest.permission.ACCESS_FINE_LOCATION) {
                registerLocationListner()
            }
        }
    }
}