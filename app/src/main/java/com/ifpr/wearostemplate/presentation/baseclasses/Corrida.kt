package com.ifpr.wearostemplate.presentation.baseclasses

import com.google.firebase.database.FirebaseDatabase
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import android.widget.Toast
data class Corrida(
    val distanciaKm: Double = 0.0,
    val tempoSegundos: Long = 0,
    val ritmoMedio: String = "",
    val dataHora: String = ""
)
