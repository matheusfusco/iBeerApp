package com.fiap.matheusfusco.matheusfusco.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.Adapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fiap.matheusfusco.matheusfusco.R
import com.fiap.matheusfusco.matheusfusco.model.Bar
import kotlinx.android.synthetic.main.bar_item.view.*

class ListaBarAdapter(private val context: Context,
                      private var bares: List<Bar> = listOf(),
                      val selectBarListener: (Bar) -> Unit,
                      val shareListener: (Bar) -> Unit,
                      val callListener: (Bar) -> Unit,
                      val longpressListener: (Bar) -> Unit) : Adapter<ListaBarAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.bar_item, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return bares.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val bar = bares[position]

        holder.let {
            it.bindView(bar, selectBarListener, shareListener, callListener, longpressListener)
        }
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        fun bindView(bar: Bar,
                     selectBarListener: (Bar) -> Unit,
                     shareListener: (Bar) -> Unit,
                     callListener: (Bar) -> Unit,
                     longpressListener: (Bar) -> Unit) {
            val title = itemView.tvBarName
            val descr = itemView.tvBarDescription
            val btShare = itemView.btCompartilhar
            val btCall = itemView.btLigar

            title.text = bar.nome
            descr.text = bar.comentario

            itemView.setOnLongClickListener {
                longpressListener(bar)
             true
            }
            itemView.setOnClickListener { selectBarListener(bar) }
            btShare.setOnClickListener { shareListener(bar) }
            btCall.setOnClickListener { callListener(bar) }
        }
    }
}