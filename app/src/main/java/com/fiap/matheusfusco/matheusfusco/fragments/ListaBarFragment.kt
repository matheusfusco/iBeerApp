package com.fiap.matheusfusco.matheusfusco.fragments

import android.app.AlertDialog
import android.arch.persistence.room.Room
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.fiap.matheusfusco.matheusfusco.adapter.ListaBarAdapter
import com.fiap.matheusfusco.matheusfusco.R
import com.fiap.matheusfusco.matheusfusco.activity.DetalheBarActivity
import com.fiap.matheusfusco.matheusfusco.dao.BarDao
import com.fiap.matheusfusco.matheusfusco.database.AppDatabase
import com.fiap.matheusfusco.matheusfusco.model.Bar
import kotlinx.android.synthetic.main.fragment_bar_list.*


class ListaBarFragment : Fragment() {

    private lateinit var barDao: BarDao
    private lateinit var adapter: ListaBarAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val database = Room.databaseBuilder(
                this.context!!,
                AppDatabase::class.java,
                "techstore-database")
                .allowMainThreadQueries()
                .build()
        barDao = database.barDao()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_bar_list, container, false)

        val recyclerView = root.findViewById<RecyclerView>(R.id.rvBares)

        recyclerView.adapter = ListaBarAdapter(
                this.context!!,
                bares(),
                {
                    Toast.makeText(activity, "Selecionando ${it.nome}", Toast.LENGTH_SHORT).show()
                },
                {
                    Toast.makeText(activity, "Compartilhando ${it.nome}", Toast.LENGTH_SHORT).show()
                },
                {
                    Toast.makeText(activity, "Ligando para ${it.telefone}", Toast.LENGTH_SHORT).show()
                },
                {
                    AlertDialog.Builder(this.context!!).setMessage("Deseja excluir?").setPositiveButton("Sim") { _,_ ->
                        barDao.delete(it)
                    }.setNegativeButton("Não", null).show()
                }
        )

        val layoutManager = LinearLayoutManager(activity)
        recyclerView.layoutManager = layoutManager

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnAction.setOnClickListener{
            startActivity(Intent(activity, DetalheBarActivity::class.java))
        }
    }

    private fun bares(): List<Bar> {
    return barDao.all()
//        return mutableListOf(Bar(null,"bar 1", 10.0, 9.0, 9.0, true, false, "0", "12345678", "muito bom"),
//                Bar(null, "bar 2", 8.0, 7.0, 8.0, true, false, "0", "87654321", "mais ou menos"))
    }
}
