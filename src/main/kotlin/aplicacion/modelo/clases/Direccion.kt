package Clases

import jakarta.persistence.*

@Entity
@Table(name = "direcciones")
class Direccion(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name  = "id")
    var id: Int? = null,

    @Column(name  = "calle")
    var calle: String,

    @Column(name  = "numero")
    var numero: Int,

    @Column(name  = "cp")
    var cp: Int,

    @Column(name = "ciudad")
    var ciudad : String,

    @OneToOne(mappedBy = "direccion", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    var cliente : Cliente? = null,

    @OneToOne(mappedBy = "direccion", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    var talleres : Taller? = null

){
    override fun toString(): String {
        return "$calle $numero $cp $ciudad"
    }

}