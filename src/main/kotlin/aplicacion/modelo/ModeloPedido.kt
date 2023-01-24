package aplicacion.modelo

import Clases.Pedido
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

}