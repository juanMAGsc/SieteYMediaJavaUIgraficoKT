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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.graphics.toComposeImageBitmap
import androidx.compose.ui.semantics.Role.Companion.Switch
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.rememberWindowState
import java.io.File
import javax.imageio.ImageIO


@Composable
fun InterfacesieteYMedia() {
    Imagenfondo()
    Column {
        Botoncontador(7)
    }
}

@Composable
fun Imagenfondo() {
    val bitmap = ImageIO.read(File("Recursos/mesa.png")).toComposeImageBitmap()
    Image(
        painter = BitmapPainter(bitmap),
        contentDescription = "Mesa")
}

@Composable
fun Botoncontador(num: Int) {
    var clics by remember { mutableStateOf(num) }
    Button(onClick = {
        clics++
    }) {
        Text( text = "Numero $clics")
    }
}

@Composable
fun Textocontador(num: Int) {
    var clics by remember { mutableStateOf(num) }
    Column(
        modifier = Modifier
            .background(Color.LightGray)
            .padding(20.dp)
            .fillMaxSize()
    )
    {
        Text(
            text = "Haz clic $clics",
            modifier = Modifier.clickable { clics++ }
        )
    }

}

fun main() = application {
    Window(onCloseRequest = ::exitApplication,
        title = "Siete y Media",
        state = rememberWindowState(width = 300.dp, height = 300.dp)
    ) {
        InterfacesieteYMedia()
    }
}
