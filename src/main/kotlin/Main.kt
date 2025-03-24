import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.graphics.toComposeImageBitmap
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.rememberWindowState
import java.io.File
import javax.imageio.ImageIO

@Composable
fun InterfacesieteYMedia() {

    val controlador = remember { Estado() }

    Imagenfondo()
    Column {
        Row() {
            Button(onClick = {
                controlador.addcarta()
                controlador.calculoPuntos()
            }, modifier = Modifier.padding(10.dp), enabled = controlador.puntosjugador < 7.5 && controlador.puntosBanca <= 0.0, colors = ButtonDefaults.buttonColors(backgroundColor = Color.Green)) {
                Text(text = "Añade Carta")
            }
            Text("Tus Puntos: "+controlador.puntosjugador.toString()+" VS Banca: "+controlador.puntosBanca.toString(), modifier = Modifier.padding(10.dp).background(Color.LightGray).padding(2.dp))
            Button(onClick = {
                controlador.pasarTurno()
            }, modifier = Modifier.padding(10.dp), enabled = controlador.puntosBanca <= 0.0 && controlador.puntosjugador < 7.5) {
                Text("Plantar")
            }
            Button(onClick = {
                controlador.reinicio()
            }, modifier = Modifier.padding(25.dp), colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red)) {
                Text("R")
            }
        }
        when {
            controlador.puntosjugador < 7.5 && controlador.puntosBanca <= 0.0 -> Text("Pide otra carta o Planta", modifier = Modifier.align(Alignment.CenterHorizontally).background(Color.LightGray).padding(2.dp))
            controlador.puntosjugador == 7.5 -> Text("Ganaste", modifier = Modifier.align(Alignment.CenterHorizontally).background(Color.Green).padding(2.dp), fontSize = 30.sp)
            controlador.puntosjugador > 7.5 -> Text("Perdiste", modifier = Modifier.align(Alignment.CenterHorizontally).background(Color.Red).padding(2.dp), fontSize = 30.sp)
            controlador.puntosjugador < controlador.puntosBanca && controlador.puntosBanca > 7.5  -> Text("Ganaste", modifier = Modifier.align(Alignment.CenterHorizontally).background(Color.Green).padding(2.dp), fontSize = 30.sp)
            controlador.puntosBanca > controlador.puntosjugador -> Text("Perdiste", modifier = Modifier.align(Alignment.CenterHorizontally).background(Color.Red).padding(2.dp), fontSize = 30.sp)
        }
        Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
            Column(modifier = Modifier.padding(20.dp)) {
                Text("Tus cartas:", modifier = Modifier.padding(10.dp).background(Color.LightGray).padding(2.dp))
                controlador.cartasjugador.forEach {
                    if (it != null) {
                        when (it.palo) {
                            "BASTOS" -> Text(it.toString(), modifier = Modifier.padding(3.dp).background(Color.Green).padding(2.dp).width(90.dp), fontSize = 10.sp)
                            "COPAS" -> Text(it.toString(), modifier = Modifier.padding(3.dp).background(Color.Red).padding(2.dp).width(90.dp), fontSize = 10.sp)
                            "ESPADAS" -> Text(it.toString(), modifier = Modifier.padding(3.dp).background(Color.Cyan).padding(2.dp).width(90.dp), fontSize = 10.sp)
                            "OROS" -> Text(it.toString(), modifier = Modifier.padding(3.dp).background(Color.Yellow).padding(2.dp).width(90.dp), fontSize = 10.sp)
                        }
                    }
                }
            }
            Column(modifier = Modifier.padding(20.dp)) {
                Text("Cartas de la Banca:", modifier = Modifier.padding(10.dp).background(Color.LightGray).padding(2.dp))
                controlador.cartasmaquina.forEach {
                    if (it != null) {
                        when (it.palo) {
                            "BASTOS" -> Text(it.toString(), modifier = Modifier.padding(3.dp).background(Color.Green).padding(2.dp).width(90.dp), fontSize = 10.sp)
                            "COPAS" -> Text(it.toString(), modifier = Modifier.padding(3.dp).background(Color.Red).padding(2.dp).width(90.dp), fontSize = 10.sp)
                            "ESPADAS" -> Text(it.toString(), modifier = Modifier.padding(3.dp).background(Color.Cyan).padding(2.dp).width(90.dp), fontSize = 10.sp)
                            "OROS" -> Text(it.toString(), modifier = Modifier.padding(3.dp).background(Color.Yellow).padding(2.dp).width(90.dp), fontSize = 10.sp)
                        }
                    }
                }
            }
        }
    }
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
