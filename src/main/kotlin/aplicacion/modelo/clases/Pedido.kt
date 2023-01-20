package Clases

import jakarta.persistence.*

@Entity
@Table(name = "pedidos")
class Pedido (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    var id : Int? = null,

    @ManyToOne(cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    @JoinColumn(name = "dni_cliente")
    var cliente : Cliente,

    @ManyToOne(cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    @JoinColumn(name = "cif_taller")
    var taller : Taller? = null,

    @Column(name = "descripcion") var descripcion : String
)