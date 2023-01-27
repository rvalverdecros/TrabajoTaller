package Clases

import jakarta.persistence.*

@Entity
@Table(name = "talleres")
class Taller (
    @Id @Column(name = "cif") var cif : String,
    @Column(name = "nombre") var nombre : String,

    @OneToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "id_direccion")
    var direccion : Direccion,

    @OneToMany(mappedBy = "taller", cascade = [CascadeType.ALL])
    var pedidos: MutableSet<Pedido>? = null,

    @ManyToMany(mappedBy = "talleres", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    var clientes : MutableSet<Cliente>? = null,

    @Column(name = "Contraseña") var contrasenia : String

)