package aplicacion.vista

import Clases.Cliente
import Clases.Pedido
import Clases.Taller

class AppVista {

    fun roolback(){
        println("Se ha realizado un roolback")
    }
    fun imprimirCliente(cliente: Cliente) {
        println("********************")
        println("Nombre : ${cliente.nombre}")
        println("DNI : ${cliente.dni}")
        println("Dirección : ${cliente.direccion}")
    }

    fun insercionCLienteCorrecta() {
        println("Cliente insertado correctamente")
    }

    fun insercionClienteFallida() {
        println("Insercion del cliente fallida")
        roolback()
    }

    fun imprimirTaller(taller: Taller) {
        println("********************")
        println("Nombre : ${taller.nombre}")
        println("DNI : ${taller.cif}")
        println("Dirección : ${taller.direccion}")
    }

    fun insercionTallerCorrecta() {
        println("Taller insertado correctamente")
    }

    fun insercionTallerFallida() {
        println("Insercion del taller fallida")
        roolback()
    }

    fun imprimirPedido(pedido: Pedido) {

        if (pedido.taller != null) {
            println("********************")
            println("Cliente : " + pedido.cliente.nombre)
            println("Taller : " + pedido.taller!!.nombre)
            println("Descripción del pedido : " + pedido.descripcion)
        }else{
            println("********************")
            println("Cliente : " + pedido.cliente.nombre)
            println("Descripción del pedido : " + pedido.descripcion)
        }
    }

    fun insercionPedidoCorrecta() {
        println("Pedido insertado correctamente")
    }

    fun insercionPedidoFallida() {
        println("Insercion del pedido fallida")
        roolback()
    }
}