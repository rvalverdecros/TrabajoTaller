package Modelo

import Clases.Cliente
import aplicacion.modelo.Modelo
import jakarta.persistence.EntityManager

class ModeloCliente() {

    private val manager : EntityManager = Modelo.getEMInstance()
    private val tableName = "Cliente"

    fun getAllClientes(): List<Cliente> {

        return manager.createQuery("FROM $tableName").resultList as List<Cliente>

    }

    fun insertCliente(cliente: Cliente) : Boolean{

        try {
            manager.transaction.begin()
            manager.persist(cliente)
            manager.transaction.commit()
            return true
        } catch (e: Exception) {
            manager.transaction.rollback()
            return false
        }
    }

}