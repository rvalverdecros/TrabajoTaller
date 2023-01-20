package aplicacion.modelo

import jakarta.persistence.EntityManager
import jakarta.persistence.Persistence

class Modelo {

   companion object{
      @Volatile
      private var instance: EntityManager? = null
      fun getEMInstance(): EntityManager {
         if (instance == null){
            instance = Persistence.createEntityManagerFactory("PersistenciaDB").createEntityManager()
         }
         return instance!!
      }
   }

}