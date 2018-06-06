package com.fiap.matheusfusco.matheusfusco.map


import android.Manifest
import android.content.DialogInterface
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.os.Bundle
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
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.MapsInitializer
import com.google.android.gms.maps.model.BitmapDescriptorFactory

class MapFragment : Fragment(), OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener, LocationListener {

    private lateinit var mMap: GoogleMap
    private lateinit var mGoogleApiCliente: GoogleApiClient
    private val REQUEST_GPS: Int = 0

    override fun onConnected(p0: Bundle?) {
        checkPermission()

        val minhaLocalizacao = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiCliente)
        if (minhaLocalizacao != null) {
            adicionarMarcador(minhaLocalizacao.latitude, minhaLocalizacao.longitude, "Não sou Shakira, mas estou aqui")

        }
    }

    fun adicionarMarcador(latitude: Double, longitude: Double, descrition: String){
        val sydney = LatLng(latitude, longitude)
        mMap.clear()
        mMap.addMarker(MarkerOptions()
                .position(sydney)
                .title(descrition)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_beer)))

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 15F))
    }

    override fun onConnectionSuspended(p0: Int) {
    }

    override fun onConnectionFailed(p0: ConnectionResult) {
    }

    override fun onLocationChanged(location: Location?) {
    }

    override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {
    }

    override fun onProviderEnabled(provider: String?) {
    }

    override fun onProviderDisabled(provider: String?) {
    }

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

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        callConnection()
    }



    @Synchronized fun callConnection (){
        mGoogleApiCliente = GoogleApiClient.Builder(context!!)
                .addOnConnectionFailedListener(this)
                .addConnectionCallbacks(this)
                .addApi(LocationServices.API).build()
        mGoogleApiCliente.connect()
    }


    private fun checkPermission() {
        val permission = ContextCompat.checkSelfPermission(context!!, Manifest.permission.ACCESS_FINE_LOCATION)

        if (permission != PackageManager.PERMISSION_GRANTED) {
            Log.i("", "Permissão para gravar negada")

            if (ActivityCompat.shouldShowRequestPermissionRationale(requireActivity(),
                            Manifest.permission.ACCESS_FINE_LOCATION)) {

                val builder = AlertDialog.Builder(context!!)

                builder.setMessage("Necessária a permissao para GPS")
                        .setTitle("Permissao Requerida")

                builder.setPositiveButton("OK", DialogInterface.OnClickListener { dialog, id ->
                    requestPermission()
                })

                val dialog = builder.create()
                dialog.show()

            } else {
                requestPermission()
            }
        }
    }

    fun requestPermission() {
        ActivityCompat.requestPermissions(requireActivity(),
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                REQUEST_GPS)
    }
}