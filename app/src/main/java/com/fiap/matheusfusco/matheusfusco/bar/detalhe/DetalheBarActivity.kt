package com.fiap.matheusfusco.matheusfusco.bar.detalhe

import android.arch.persistence.room.Room
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.SeekBar
import android.widget.Toast
import com.fiap.matheusfusco.matheusfusco.R
import com.fiap.matheusfusco.matheusfusco.bar.BarDao
import com.fiap.matheusfusco.matheusfusco.bar.BarDatabase
import com.fiap.matheusfusco.matheusfusco.bar.Bar
import kotlinx.android.synthetic.main.activity_detalhe_bar.*

class DetalheBarActivity : AppCompatActivity() {
    private val idBarSelecionado: Long by lazy {
        intent.getLongExtra("barID", -1)
    }
    private var barSelecionado: Bar? = null

    private lateinit var barDao: BarDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalhe_bar)
        val database = Room.databaseBuilder(
                this,
                BarDatabase::class.java,
                "techstore-database")
                .allowMainThreadQueries()
                .build()
        barDao = database.barDao()
        configureSaveButton()

        barSelecionado = barDao.findById(idBarSelecionado)
        barSelecionado?.let {
            et_bar_name.setText(it.nome)
            et_bar_cep.setText(it.cep)
            et_bar_general_comments.setText(it.comentario)
            et_bar_phone.setText(it.telefone)
            cb_bar_has_artesanal_beer.isChecked = it.temCervejaArtesanal
            cb_bar_has_live_band.isChecked = it.temMusicaAoVivo
            sb_bar_recommendation_grade.progress = it.notaRecomendacao.toInt()
            sb_bar_attendance_grade.progress = it.notaAtendimento.toInt()
            sb_bar_ambient_grade.progress = it.notaAmbiente.toInt()
        }
    }

    private fun configureSaveButton() {
        bt_save_button.setOnClickListener {
            if (et_bar_name.text.toString().isEmpty() || et_bar_cep.text.toString().isEmpty() || et_bar_phone.text.toString().isEmpty()) {
                Toast.makeText(this, resources.getString(R.string.fill_all_fields_error), Toast.LENGTH_LONG).show()
            }
            else {
                if (barSelecionado == null) {
                    saveBar()
                    finish()
                }
                else {
                    val createdBar: Bar = create()
                    barDao.update(createdBar)
                    finish()
                }
            }
        }
    }

    private fun saveBar() {
        val createdBar = create()
        barDao.add(createdBar)
    }

    private fun create(): Bar {
        var id: Long? = barSelecionado?.id
        val name = et_bar_name.text.toString()
        val ntAmbiente = sb_bar_ambient_grade.progress
        val ntAtendimento = sb_bar_attendance_grade.progress
        val ntRecomendacao = sb_bar_recommendation_grade.progress
        val temCerveja = cb_bar_has_artesanal_beer.isChecked
        val temMusica = cb_bar_has_live_band.isChecked
        val cep = et_bar_cep.text.toString()
        val tel = et_bar_phone.text.toString()
        val cmt = et_bar_general_comments.text.toString()
        return Bar(id, name, ntAmbiente, ntAtendimento, ntRecomendacao, temCerveja, temMusica, cep, tel, cmt)
    }
}
