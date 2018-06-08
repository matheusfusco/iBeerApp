package com.fiap.matheusfusco.matheusfusco.webservice.user

data class UserResponse (
        var nome: String,
        var email: String,
        var login: String,
        var senha: String,
        var ativo: Short)