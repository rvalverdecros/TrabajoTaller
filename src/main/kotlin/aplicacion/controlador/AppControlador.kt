package aplicacion.controlador

import Clases.Cliente
import Clases.Taller
import Modelo.ModeloCliente
import Modelo.ModeloTaller

class AppControlador {

    private val gestorClientes = ModeloCliente()
    private val gestorTalleres = ModeloTaller()

    fun allClientes(){
        val lisclientes = gestorClientes.getAllClientes()

        for(cliente in lisclientes){
            println("********************")
            println("Nombre : ${cliente.nombre}")
            println("DNI : ${cliente.dni}")
            println("Dirección : ${cliente.direccion}")
        }
    }

    fun insertCliente(cliente: Cliente){
        gestorClientes.insertCliente(cliente)
    }

    fun allTalleres(){
        val lisTalleres = gestorTalleres.getAllTalleres()

        for (taller in lisTalleres){
            println("********************")
            println("Nombre : ${taller.nombre}")
            println("DNI : ${taller.cif}")
            println("Dirección : ${taller.direccion}")
        }
    }

    fun insertTaller(taller: Taller){
        gestorTalleres.insertTaller(taller)
    }

}