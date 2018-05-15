package com.fiap.matheusfusco.matheusfusco.Fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.fiap.matheusfusco.matheusfusco.Adapter.BarListAdapter
import com.fiap.matheusfusco.matheusfusco.R
import model.Bar


class BarListFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    private fun bares(): List<Bar> {
        return listOf(Bar("teste", 10.0, 10.0, true, true, 100, 12345678, "muito bom"),
                Bar("bar 2", 8.0, 9.0, true, false, 1001, 12345678, "muito mais ou menos"))
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_bar_list, container, false)

        val recyclerView = root.findViewById<RecyclerView>(R.id.rvBares)

        recyclerView.adapter = BarListAdapter(
                this.context!!,
                bares(),
                {
                    Toast.makeText(activity, "Compartilhando ${it.nome}", Toast.LENGTH_SHORT)
                },
                {
                    Toast.makeText(activity, "Ligando para ${it.telefone}", Toast.LENGTH_SHORT)
                }
        )

        val layoutManager = LinearLayoutManager(activity)
        recyclerView.layoutManager = layoutManager

        return root
    }
}
