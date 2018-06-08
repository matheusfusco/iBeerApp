package com.fiap.matheusfusco.matheusfusco.bar

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.os.Parcel
import android.os.Parcelable

@Entity
data class Bar(@PrimaryKey(autoGenerate = true)
               val id: Long?,
               val nome: String,
               val notaAmbiente: Int,
               val notaAtendimento: Int,
               val notaRecomendacao: Int,
               val temCervejaArtesanal: Boolean,
               val temMusicaAoVivo: Boolean,
               val cep: String,
               val telefone: String,
               val comentario: String) : Parcelable {

    constructor(parcel: Parcel) : this(
            parcel.readLong(),
            parcel.readString(),
            parcel.readInt(),
            parcel.readInt(),
            parcel.readInt(),
            parcel.readByte() != 0.toByte(),
            parcel.readByte() != 0.toByte(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        id?.let { parcel.writeLong(it) }
        parcel.writeString(nome)
        parcel.writeInt(notaAmbiente)
        parcel.writeInt(notaAtendimento)
        parcel.writeInt(notaRecomendacao)
        parcel.writeByte(if (temCervejaArtesanal) 1 else 0)
        parcel.writeByte(if (temMusicaAoVivo) 1 else 0)
        parcel.writeString(cep)
        parcel.writeString(telefone)
        parcel.writeString(comentario)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Bar> {
        override fun createFromParcel(parcel: Parcel): Bar {
            return Bar(parcel)
        }

        override fun newArray(size: Int): Array<Bar?> {
            return arrayOfNulls(size)
        }
    }


}