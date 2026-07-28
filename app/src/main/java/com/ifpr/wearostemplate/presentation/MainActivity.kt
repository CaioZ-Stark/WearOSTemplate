/* While this template provides a good starting point for using Wear Compose, you can always
 * take a look at https://github.com/android/wear-os-samples/tree/main/ComposeStarter to find the
 * most up to date changes to the libraries and their usages.
 */

package com.ifpr.wearostemplate.presentation
import android.widget.Button
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.google.firebase.database.FirebaseDatabase
import com.ifpr.wearostemplate.R
import com.ifpr.wearostemplate.presentation.baseclasses.Corrida
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)

        setTheme(android.R.style.Theme_DeviceDefault)
        setContentView(R.layout.activity_main)

        val buttonPerfil = findViewById<Button>(R.id.btnPerfil)
        buttonPerfil.setOnClickListener {
            val intent = Intent(this, Perfil::class.java)
            startActivity(intent)
        }

        val btnStop = findViewById<Button>(R.id.btnStop)
        btnStop.setOnClickListener {
            val distanciaKm = 2.5
            val tempoSegundos = 900L

            salvarCorrida(distanciaKm, tempoSegundos)

            Toast.makeText(this, "Corrida salva!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun salvarCorrida(distanciaKm: Double, tempoSegundos: Long) {
        val database = FirebaseDatabase.getInstance()
        val referencia = database.getReference("corridas")

        val id = referencia.push().key ?: return

        val data = SimpleDateFormat(
            "dd/MM/yyyy HH:mm",
            Locale.getDefault()
        ).format(Date())

        val ritmo = calcularRitmo(distanciaKm, tempoSegundos)

        val corrida = Corrida(
            distanciaKm,
            tempoSegundos,
            ritmo,
            data
        )

        referencia.child(id).setValue(corrida)
    }

    private fun calcularRitmo(distanciaKm: Double, tempoSegundos: Long): String {
        if (distanciaKm <= 0.0) return "0:00"

        val segundosPorKm = (tempoSegundos / distanciaKm).toInt()
        val minutos = segundosPorKm / 60
        val segundos = segundosPorKm % 60

        return "$minutos:${segundos.toString().padStart(2, '0')}"
    }
}
    private fun salvarCorrida(distanciaKm: Double, tempoSegundos:
    Long) {

    }



