package model

import android.os.Parcel
import android.os.Parcelable

data class Bar(val nome: String,
               val notaAmbiente: Double,
               val notaAtendimento: Double,
               val temCervejaArtesanal: Boolean,
               val temMusicaAoVivo: Boolean,
               val cep: Int,
               val telefone: Int,
               val comentario: String) : Parcelable {

    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readDouble(),
            parcel.readDouble(),
            parcel.readByte() != 0.toByte(),
            parcel.readByte() != 0.toByte(),
            parcel.readInt(),
            parcel.readInt(),
            parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(nome)
        parcel.writeDouble(notaAmbiente)
        parcel.writeDouble(notaAtendimento)
        parcel.writeByte(if (temCervejaArtesanal) 1 else 0)
        parcel.writeByte(if (temMusicaAoVivo) 1 else 0)
        parcel.writeInt(cep)
        parcel.writeInt(telefone)
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