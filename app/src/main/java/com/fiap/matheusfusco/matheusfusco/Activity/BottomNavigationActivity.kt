package com.fiap.matheusfusco.matheusfusco.Activity

import android.support.v4.app.Fragment
import android.os.Bundle
import android.support.design.widget.BottomNavigationView.OnNavigationItemSelectedListener
import android.support.v7.app.AppCompatActivity
import com.fiap.matheusfusco.matheusfusco.Fragments.BarListFragment
import com.fiap.matheusfusco.matheusfusco.Fragments.HomeFragment
import com.fiap.matheusfusco.matheusfusco.Fragments.SobreFragment
import com.fiap.matheusfusco.matheusfusco.R
import kotlinx.android.synthetic.main.activity_bottom_navigation.*

class BottomNavigationActivity : AppCompatActivity() {

    private val mOnNavigationItemSelectedListener = OnNavigationItemSelectedListener { item ->

        val fragmento: Fragment

        when (item.itemId) {
            R.id.navigation_home -> {
                fragmento = HomeFragment()
                trocaFragmento(fragmento)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_bares -> {
                fragmento = BarListFragment()
                trocaFragmento(fragmento)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_mapa -> {
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
    }
    private fun trocaFragmento(fragmento: Fragment) {
        supportFragmentManager
                .beginTransaction()
                .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                .replace(R.id.content, fragmento)
                .commit()
    }
}
