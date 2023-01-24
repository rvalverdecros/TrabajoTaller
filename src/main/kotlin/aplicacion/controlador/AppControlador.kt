package aplicacion.controlador

import Clases.Cliente
import Clases.Pedido
import Clases.Taller
import Modelo.ModeloCliente
import Modelo.ModeloTaller
import aplicacion.modelo.ModeloPedido
import aplicacion.vista.AppVista

class AppControlador {

    private val gestorClientes = ModeloCliente()
    private val gestorTalleres = ModeloTaller()
    private val gestorPedidos = ModeloPedido()

    private val vista = AppVista()

    fun allClientes(){
        val lisClientes = gestorClientes.getAllClientes()

        for(cliente in lisClientes){
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

    fun allPedidos(){
        val lisPedidos = gestorPedidos.getAllPedidos()

        for (pedido in lisPedidos){
            vista.imprimirPedido(pedido)
        }
    }

    fun insertPedido(pedido: Pedido){
        val insert = gestorPedidos.insertPedido(pedido)
        if (insert){
            vista.insercionPedidoCorrecta()
        }else{
            vista.insercionPedidoFallida()
        }
    }

}