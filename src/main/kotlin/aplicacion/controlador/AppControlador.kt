package aplicacion.controlador

import Clases.Cliente
import Clases.Pedido
import Clases.Taller
import Modelo.ModeloCliente
import Modelo.ModeloTaller
import aplicacion.modelo.ModeloPedido
import aplicacion.vista.AppVista
import java.nio.charset.Charset

class AppControlador {

    private val gestorClientes = ModeloCliente()
    private val gestorTalleres = ModeloTaller()
    private val gestorPedidos = ModeloPedido()

    private val vista = AppVista()


    private var clienteLoged : Cliente? = null
    private var tallerLoged : Taller? = null


    fun isClienteLoged(dni : String, contrasenia : String) : Cliente?{

        val cliente = gestorClientes.getAllClientes().find{ it.dni == dni && descifrar(it.contrasenia) == contrasenia }

        clienteLoged = cliente

        return cliente
    }

    fun isTallerLoged(cif : String, contrasenia : String) : Taller?{

        val lisTalleres = gestorTalleres.getAllTalleres()

         val taller = lisTalleres.find{ it.cif == cif && descifrar(it.contrasenia) == contrasenia }

        tallerLoged = taller

        return taller
    }

    private fun cifrar(pass:String) :String{
        val con = pass.toByteArray()
        for(i in 0..con.size-1){
            con[i]= (con[i]+3).toByte()
        }
        val new = con.toString(Charset.defaultCharset())
        return new
    }
    private fun descifrar(pass:String) :String{
        val con = pass.toByteArray()
        for(i in 0..con.size-1){
            con[i]= (con[i]-3).toByte()
        }
        val new = con.toString(Charset.defaultCharset())
        return new
    }

    fun insertCliente(cliente: Cliente) {
        cliente.contrasenia = cifrar(cliente.contrasenia)
        val insert = gestorClientes.insertCliente(cliente)
        if (insert) {
            vista.insercionClienteCorrecta()
        } else {
            vista.insercionClienteFallida()
        }
    }


    fun insertTaller(taller: Taller) {
        taller.contrasenia = cifrar(taller.contrasenia)
        val insert = gestorTalleres.insertTaller(taller)
        if (insert) {
            vista.insercionTallerCorrecta()
        } else {
            vista.insercionTallerFallida()
        }
    }


    fun insertPedido(descripcion : String) {

        val pedido = Pedido(cliente = clienteLoged!!, descripcion = descripcion)

        val insert = gestorPedidos.insertPedido(pedido)
        if (insert) {
            vista.insercionPedidoCorrecta()
        } else {
            vista.insercionPedidoFallida()
        }
    }

    fun mostrarPedidosNoAsignados(){
        val pedidosSinAsignar = gestorPedidos.getAllPedidos().filter { it.taller == null }

        for (pedido in pedidosSinAsignar) {
            vista.imprimirPedido(pedido)
        }
    }

    fun asignarPedidoNoAsignado() {

        val pedidosSinAsignar = gestorPedidos.getAllPedidos().filter { it.taller == null }

        for (pedido in pedidosSinAsignar) {
            vista.imprimirPedido(pedido)
        }

        println("Introduzca la clave del pedido")

        val idPedido = readln().toIntOrNull()

        if (idPedido != null) {

            val pedido = pedidosSinAsignar.find { it.id == idPedido }

            if (pedido == null) {
                vista.pedidoNoExistente()
            } else {
                val update = gestorPedidos.modPedido(pedido, tallerLoged!!)

                if (update) {
                    vista.modificacionPedidoCorrecta()
                } else {
                    vista.modificacionPedidoIncorrecta()
                }
            }

        } else {
            vista.datoErroneo()
        }


        /*
        println("Introduzca la clave del pedido")

        val idPedido = readln().toIntOrNull()

        if (idPedido != null) {

            println("Introduzca el CIF del taller")
            val idTaller = readln()

            val pedido = gestorPedidos.getAllPedidos().find { it.id == idPedido }
            val taller = gestorTalleres.getAllTalleres().find { it.cif == idTaller }

            if (pedido == null) {
                vista.pedidoNoExistente()
            } else if (taller == null) {
                vista.tallerNoExistente()
            } else {
                val update = gestorPedidos.modPedido(pedido, taller)

                if (update) {
                    vista.modificacionPedidoCorrecta()
                } else {
                    vista.modificacionPedidoIncorrecta()
                }
            }

        } else {
            vista.datoErroneo()
        }

         */

    }

    fun allPedidosTaller() {
        val lisPedidos = gestorPedidos.getAllPedidos().filter { it.taller == tallerLoged}

        for (pedido in lisPedidos) {
            vista.imprimirPedido(pedido)
        }
    }

    fun allPedidosCliente() {
        val lisPedidos = gestorPedidos.getAllPedidos().filter { it.cliente == clienteLoged}

        for (pedido in lisPedidos) {
            vista.imprimirPedido(pedido)
        }
    }

    fun allClientesTaller(){
        val lisClientes = tallerLoged!!.clientes

        if (lisClientes != null) {
            for (cliente in lisClientes){
                vista.imprimirCliente(cliente)
            }
        }
    }

    fun allTallerCliente(){
        val lisTalleres = clienteLoged!!.talleres

        if (lisTalleres != null) {
            for (taller in lisTalleres){
                vista.imprimirTaller(taller)
            }
        }
    }
}