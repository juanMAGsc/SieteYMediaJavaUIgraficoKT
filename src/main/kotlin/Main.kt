import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.graphics.toComposeImageBitmap
import androidx.compose.ui.semantics.Role.Companion.Switch
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.rememberWindowState
import recursos.Carta
import java.io.File
import javax.imageio.ImageIO
import kotlin.system.exitProcess

class Estado {
    private val juego = SieteYMedia()

    var cartasjugador by mutableStateOf(juego.cartasJugador)
    var puntosjugador by mutableStateOf(juego.valorCartas(this.cartasjugador))
    var cartasmaquina by mutableStateOf("")

    init {
        juego.insertarCartaEnArray(juego.cartasJugador, juego.baraja.darCartas(1)[0])
    }

    fun addcarta() {
       var nuevoA = this.cartasjugador.toMutableList()
       nuevoA.add(juego.baraja.darCartas(1)[0])
        cartasjugador = nuevoA.toTypedArray()
        this.puntosjugador = juego.valorCartas(this.cartasjugador)
    }

    fun cartasStr(cartas: Array<Carta>): String {
        var retorno = ""
        cartas.takeIf { it != null }?.forEach { retorno+=it.toString()+"@" }
        return retorno
    }
}

@Composable
fun InterfacesieteYMedia() {

    val controlador = remember { Estado() }

    Imagenfondo()
        Column {
            Text(controlador.puntosjugador.toString())
            Row {
                controlador.cartasjugador.forEach {
                    if (it != null) {
                        when (it.palo) {
                            "BASTOS" -> Text(it.toString(), modifier = Modifier.background(Color.Green))
                            "COPAS" -> Text(it.toString(), modifier = Modifier.background(Color.Red))
                            "ESPADAS" -> Text(it.toString(), modifier = Modifier.background(Color.Blue))
                            "OROS" -> Text(it.toString(), modifier = Modifier.background(Color.Yellow))
                        }
                    }
                }
            }
            Button(onClick = {
                controlador.addcarta()
                controlador.cartasjugador.forEach { println(it) }
            }){
                Text(text = "Añade carta")
            }
        }
    /*
    turnojugador()
    turnobanca()
    acaba
    */

}

@Composable
fun Imagenfondo() {
    val bitmap = ImageIO.read(File("Recursos/mesa.png")).toComposeImageBitmap()
    Image(
        painter = BitmapPainter(bitmap),
        contentDescription = "Mesa")
}

fun main() = application {
    Window(onCloseRequest = ::exitApplication,
        title = "Siete y Media",
        resizable = false,
        state = rememberWindowState(width = 600.dp, height = 428.dp)
    ) {
        InterfacesieteYMedia()
    }
}
