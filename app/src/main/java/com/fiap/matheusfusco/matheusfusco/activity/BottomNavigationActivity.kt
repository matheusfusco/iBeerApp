package com.fiap.matheusfusco.matheusfusco.activity

import android.support.v4.app.Fragment
import android.os.Bundle
import android.support.design.widget.BottomNavigationView.OnNavigationItemSelectedListener
import android.support.v7.app.AppCompatActivity
import com.fiap.matheusfusco.matheusfusco.fragments.ListaBarFragment
import com.fiap.matheusfusco.matheusfusco.fragments.HomeFragment
import com.fiap.matheusfusco.matheusfusco.fragments.MapFragment
import com.fiap.matheusfusco.matheusfusco.fragments.SobreFragment
import com.fiap.matheusfusco.matheusfusco.R
import kotlinx.android.synthetic.main.activity_bottom_navigation.*

class BottomNavigationActivity : AppCompatActivity() {

    lateinit var fragmento: Fragment

    private val mOnNavigationItemSelectedListener = OnNavigationItemSelectedListener { item ->

        when (item.itemId) {
            R.id.navigation_home -> {
                fragmento = HomeFragment()
                trocaFragmento(fragmento)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_bares -> {
                fragmento = ListaBarFragment()
                trocaFragmento(fragmento)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_mapa -> {
                fragmento = MapFragment()
                trocaFragmento(fragmento)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_sobre -> {
                fragmento = SobreFragment()
                trocaFragmento(fragmento)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bottom_navigation)

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        fragmento = HomeFragment()
        trocaFragmento(fragmento)
    }
    private fun trocaFragmento(fragmento: Fragment) {
        supportFragmentManager
                .beginTransaction()
                .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                .replace(R.id.content, fragmento)
                .commit()
    }
}
