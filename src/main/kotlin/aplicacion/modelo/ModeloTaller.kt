package Modelo

import Clases.Taller
import aplicacion.modelo.Modelo
import jakarta.persistence.EntityManager

class ModeloTaller(){

    private val manager : EntityManager = Modelo.getEMInstance()
    private val tableName = "Taller"

    fun getAllTalleres(): List<Taller> {
        return manager.createQuery("FROM $tableName").resultList as List<Taller>
    }

    fun insertTaller(taller: Taller) : Boolean{

        try {
            manager.transaction.begin()
            manager.persist(taller)
            manager.transaction.commit()
            return true
        } catch (e: Exception) {
            manager.transaction.rollback()
            return false

        }
    }

}