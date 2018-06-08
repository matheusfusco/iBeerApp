package com.fiap.matheusfusco.matheusfusco.sobre

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fiap.matheusfusco.matheusfusco.R
import com.fiap.matheusfusco.matheusfusco.auth.login.LoginActivity
import com.iamhabib.easy_preference.EasyPreference
import kotlinx.android.synthetic.main.fragment_sobre.*

class SobreFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sobre, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnLoggout.setOnClickListener {
            EasyPreference.with(activity)
                    .clearAll()
                    .save()

            startActivity(Intent(activity, LoginActivity::class.java))
            activity?.finish()
        }
    }
}