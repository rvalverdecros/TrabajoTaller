package Clases

import jakarta.persistence.*

@Entity
@Table(name = "clientes")
class Cliente(
    @Id @Column(name = "dni") var dni : String,
    @Column(name = "nombre") var nombre : String,
    @Column(name = "edad") var edad : Int,

    @OneToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "id_direccion")
    var direccion : Direccion,

    @OneToMany(mappedBy = "cliente", cascade = [CascadeType.ALL])
    var pedidos: MutableSet<Pedido>?=null,

    @ManyToMany
    @JoinTable(
        name = "cliente_taller" ,
        joinColumns = [JoinColumn(name = "dni_cliente")],
        inverseJoinColumns = [JoinColumn(name = "cif_taller")]
    )
    var talleres: MutableSet<Taller>? =null,

    @Column(name = "Contraseña") var contrasenia : String
)