import Clases.Cliente
import Clases.Direccion
import aplicacion.controlador.AppControlador

fun main() {

    val controlador = AppControlador()


    val direccion = Direccion(calle = "Rodriguez Zapatero",numero = 12, cp = 11009, ciudad = "Cádiz")

    controlador.insertCliente(Cliente(dni ="67567572M", nombre = "Alejandro", edad = 20, direccion = direccion))
    controlador.allClientes()

}