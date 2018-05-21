package com.fiap.matheusfusco.matheusfusco.Adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.Adapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fiap.matheusfusco.matheusfusco.R
import kotlinx.android.synthetic.main.bar_item.view.*
import model.Bar

class ListaBarAdapter(private val context: Context,
                      private val bares: List<Bar>,
                      val shareListener: (Bar) -> Unit,
                      val callListener: (Bar) -> Unit) : Adapter<ListaBarAdapter.ViewHolder>() {


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
            it.bindView(bar, shareListener, callListener)
        }
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        fun bindView(bar: Bar,
                     shareListener: (Bar) -> Unit,
                     callListener: (Bar) -> Unit) {
            val title = itemView.tvBarName
            val descr = itemView.tvBarDescription
            val btShare = itemView.btCompartilhar
            val btCall = itemView.btLigar

            title.text = bar.nome
            descr.text = bar.comentario


            btShare.setOnClickListener { shareListener(bar) }
            btCall.setOnClickListener { callListener(bar) }
        }
    }
}