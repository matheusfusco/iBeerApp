package com.fiap.matheusfusco.matheusfusco.webservice

import com.google.gson.*
import java.lang.reflect.Type

class Deserializer : JsonDeserializer<Any> {
    @Throws(JsonParseException::class)
    override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): Any {

        var saleAction: JsonElement = json.asJsonObject

        if (json.asJsonObject.get("error") != null) {
            saleAction = json.asJsonObject.get("error")
        }

        return Gson().fromJson(saleAction, typeOfT)

    }
}