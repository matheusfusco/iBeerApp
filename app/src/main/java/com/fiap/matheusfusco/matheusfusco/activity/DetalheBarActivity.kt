package com.fiap.matheusfusco.matheusfusco.activity

import android.arch.persistence.room.Room
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.SeekBar
import com.fiap.matheusfusco.matheusfusco.R
import com.fiap.matheusfusco.matheusfusco.dao.BarDao
import com.fiap.matheusfusco.matheusfusco.database.AppDatabase
import com.fiap.matheusfusco.matheusfusco.model.Bar
import kotlinx.android.synthetic.main.activity_detalhe_bar.*
import kotlinx.android.synthetic.main.bar_item.*

class DetalheBarActivity : AppCompatActivity() {

    private lateinit var barDao: BarDao
    private var notaAmbiente: Int = 0
    private var notaAtendimento: Int = 0
    private var notaRecomendacao: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalhe_bar)
        val database = Room.databaseBuilder(
                this,
                AppDatabase::class.java,
                "techstore-database")
                .allowMainThreadQueries()
                .build()
        barDao = database.barDao()
        configureSaveButton()


        sb_bar_ambient_grade.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {
                notaAmbiente = i
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }
        })

        sb_bar_attendance_grade.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {
                notaAtendimento = i
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }
        })

        sb_bar_recommendation_grade.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {
                notaRecomendacao = i
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }
        })
    }

    private fun configureSaveButton() {
        bt_save_button.setOnClickListener {
            saveBar()
            finish()
        }
    }

    private fun saveBar() {
        val createdBar = create()
        barDao.add(createdBar)
    }

    private fun create(): Bar {
        val name = et_bar_name.text.toString()
        val description = et_bar_general_comments.text.toString()
        val ntAmbiente = notaAmbiente.toDouble()
        val ntAtendimento = notaAtendimento.toDouble()
        val ntRecomendacao = notaRecomendacao.toDouble()
        val temCerveja = cb_bar_has_artesanal_beer.isChecked
        val temMusica = cb_bar_has_live_band.isChecked
        val cep = et_bar_cep.text.toString()
        val tel = et_bar_phone.text.toString()
        val cmt = et_bar_general_comments.text.toString()
        return Bar(null, name, ntAmbiente, ntAtendimento, ntRecomendacao, temCerveja, temMusica, cep, tel, cmt)
    }
}
