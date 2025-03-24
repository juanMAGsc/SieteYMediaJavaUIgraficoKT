import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class Estado {
    private var juego = SieteYMedia()

    var cartasjugador by mutableStateOf(juego.cartasJugador)
    var puntosjugador by mutableStateOf(0.0)
    var puntosBanca by mutableStateOf(0.0)
    var cartasmaquina by mutableStateOf(juego.cartasBanca)

    init {
        juego.insertarCartaEnArray(juego.cartasJugador, juego.baraja.darCartas(1)[0])
        calculoPuntos()
    }

    fun addcarta() {
        val nuevoA = this.cartasjugador.toMutableList()
        nuevoA.add(juego.baraja.darCartas(1)[0])
        cartasjugador = nuevoA.toTypedArray()
        this.puntosjugador = juego.valorCartas(this.cartasjugador)
    }

    fun calculoPuntos() {
        var nuevores = 0.0
        cartasjugador.forEach { nuevores += when(it?.numero?.toDouble()) {
            10.0 -> 0.5
            11.0 -> 0.5
            12.0 -> 0.5
            null -> 0.0
            else -> it.numero.toDouble()
        }}
        puntosjugador = nuevores
    }

    fun pasarTurno() {

        while (puntosBanca <= puntosjugador ) {
            var nuevopunto = 0.0
            val nuevoA = this.cartasmaquina.toMutableList()
            nuevoA.add(juego.baraja.darCartas(1)[0])
            cartasmaquina = nuevoA.toTypedArray()
            cartasmaquina.forEach { nuevopunto += when(it?.numero?.toDouble()) {
                10.0 -> 0.5
                11.0 -> 0.5
                12.0 -> 0.5
                null -> 0.0
                else -> it.numero.toDouble()
            } }
            puntosBanca = nuevopunto
        }
    }

    fun reinicio() {
        juego = SieteYMedia()
        cartasjugador = juego.cartasJugador
        cartasmaquina = juego.cartasBanca
        puntosjugador = 0.0
        puntosBanca = 0.0
    }

}