package aplicacion.modelo

import Clases.Cliente
import Clases.Pedido
import Clases.Taller
import aplicacion.modelo.Modelo
import jakarta.persistence.EntityManager

class ModeloPedido() {

    private val manager : EntityManager = Modelo.getEMInstance()
    private val tableName = "Pedido"

    fun getAllPedidos(): List<Pedido> {

        return manager.createQuery("FROM $tableName").resultList as List<Pedido>

    }

    fun insertPedido(pedido: Pedido) : Boolean{

        try {
            manager.transaction.begin()
            manager.persist(pedido)
            manager.transaction.commit()
            return true
        } catch (e: Exception) {
            manager.transaction.rollback()
            return false
        }
    }

    fun modPedido(pedido: Pedido,taller: Taller) : Boolean{

        pedido.taller = taller

        if (taller.clientes == null){
            taller.clientes = mutableSetOf()
        }

        taller.clientes!!.add(pedido.cliente)
        val cliente: Cliente? = manager.find(Cliente::class.java, pedido.cliente.dni)
        if (cliente != null) {
            cliente.talleres?.add(taller)
        }
        try {
            manager.transaction.begin()
            manager.merge(cliente)
            manager.persist(pedido)
            manager.merge(taller)
            manager.transaction.commit()
            return true
        } catch (e: Exception) {
            manager.transaction.rollback()
            return false
        }
    }

}