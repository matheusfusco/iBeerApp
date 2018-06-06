package com.fiap.matheusfusco.matheusfusco.bar

import android.app.AlertDialog
import android.arch.persistence.room.Room
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.fiap.matheusfusco.matheusfusco.R
import com.fiap.matheusfusco.matheusfusco.bar.detalhe.DetalheBarActivity
import kotlinx.android.synthetic.main.fragment_bar_list.*


class ListaBarFragment : Fragment() {

    private lateinit var barDao: BarDao
    var adapter: ListaBarAdapter? = null
    var listaBares = mutableListOf<Bar>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val database = Room.databaseBuilder(
                this.context!!,
                BarDatabase::class.java,
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

        listaBares = bares()

        adapter = ListaBarAdapter(
                this.context!!,
                listaBares,
                {
                    //                    Toast.makeText(activity, "Selecionando ${it.nome}", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this.context!!, DetalheBarActivity::class.java)
                    intent.putExtra("barID", it.id)
                    startActivity(intent)
                },
                {
//                    Toast.makeText(activity, "Compartilhando ${it.nome}", Toast.LENGTH_SHORT).show()
                    val shareBody = "Esse bar é o melhor: ${it.nome}"
                    val sharingIntent = Intent(android.content.Intent.ACTION_SEND)
                    sharingIntent.type = "text/plain"
                    sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Bar show de bola")
                    sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody)
                    startActivity(Intent.createChooser(sharingIntent, "Compartilhar com: "))
                },
                {
                    val intent = Intent(Intent.ACTION_DIAL, Uri.fromParts("tel",it.telefone, null))
                    startActivity(intent)
                },
                {
                    AlertDialog.Builder(this.context!!).setMessage("Deseja excluir?").setPositiveButton("Sim") { _, _ ->
                        barDao.delete(it)
                    }.setNegativeButton("Não", null).show()
                }
        )

        recyclerView.adapter = adapter


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

    override fun onResume() {
        super.onResume()
        adapter?.updateList(bares())
    }

    private fun bares(): MutableList<Bar> {
        return barDao.all().toMutableList()
    }
}
