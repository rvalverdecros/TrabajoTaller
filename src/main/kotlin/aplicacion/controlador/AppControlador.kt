package aplicacion.controlador

import Clases.Cliente
import Clases.Taller
import Modelo.ModeloCliente
import Modelo.ModeloTaller
import aplicacion.vista.AppVista

class AppControlador {

    private val gestorClientes = ModeloCliente()
    private val gestorTalleres = ModeloTaller()

    private val vista = AppVista()

    fun allClientes(){
        val lisclientes = gestorClientes.getAllClientes()

        for(cliente in lisclientes){
            vista.imprimirCliente(cliente)
        }
    }

    fun insertCliente(cliente: Cliente){
        val insert = gestorClientes.insertCliente(cliente)
        if (insert){
            vista.insercionCLienteCorrecta()
        }else{
            vista.insercionClienteFallida()
        }
    }

    fun allTalleres(){
        val lisTalleres = gestorTalleres.getAllTalleres()

        for (taller in lisTalleres){
            vista.imprimirTaller(taller)
        }
    }

    fun insertTaller(taller: Taller){
        val insert = gestorTalleres.insertTaller(taller)
        if (insert){
            vista.insercionTallerCorrecta()
        }else{
            vista.insercionTallerFallida()
        }
    }

}