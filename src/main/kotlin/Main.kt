import Clases.Cliente
import Clases.Direccion
import Clases.Taller
import aplicacion.controlador.AppControlador
import kotlin.system.exitProcess

fun main() {

    val controlador = AppControlador()

    println("Bienvenido usuari@")

    println("Elija una opcion")
    println("1. Taller")
    println("2. Cliente")

    var opcion = readln().toIntOrNull()

    while (opcion == null || (opcion < 1 || opcion > 2)){
        println("Elija una opcion")
        println("1. Taller")
        println("2. Cliente")
        opcion = readln().toIntOrNull()
    }

    when(opcion){

        1 -> {
            loginTaller(controlador)
        }

        2 -> {
            loginCliente(controlador)
        }

    }

}

fun loginCliente(controlador: AppControlador) {
    println("Introduzca el dni del cliente")
    val dni = readln()
    println("Introduzca la contraseña del cliente")
    val contrasenia = readln()

    val cliente = controlador.isClienteLoged(dni, contrasenia)

    if (cliente != null){
        println("Sesión iniciada correctamente")
        menuCliente(controlador)
    }else{
        println("DNI y/o contraseña incorrecto(s)")

        println("Que quieres hacer?")
        println("1. Intentarlo de nuevo")
        println("2. Crear un nuevo cliente")
        var opcion = readln().toIntOrNull()

        while (opcion == null || (opcion < 1 || opcion > 2)){
            println("Que quieres hacer?")
            println("1. Intentarlo de nuevo")
            println("2. Crear un nuevo cliente")
            opcion = readln().toIntOrNull()
        }

        when(opcion){

            1 -> {
                loginCliente(controlador)
            }

            2 -> {
                crearCliente(controlador)
            }
        }
    }
}

fun loginTaller(controlador: AppControlador){
    println("Introduzca el cif del taller")
    val cif = readln()
    println("Introduzca la contraseña del taller")
    val contrasenia = readln()

    val taller = controlador.isTallerLoged(cif, contrasenia)

    if (taller != null){
        println("Sesión iniciada correctamente")
        menuTaller(controlador)
    }else{
        println("CIF y/o contraseña incorrecto(s)")

        println("Que quieres hacer?")
        println("1. Intentarlo de nuevo")
        println("2. Crear un nuevo taller")
        var opcion = readln().toIntOrNull()

        while (opcion == null || (opcion < 1 || opcion > 2)){
            println("Que quieres hacer?")
            println("1. Intentarlo de nuevo")
            println("2. Crear un nuevo taller")
            opcion = readln().toIntOrNull()
        }

        when(opcion){

            1 -> {
                loginTaller(controlador)
            }

            2 -> {
                crearTaller(controlador)
            }
        }
    }
}

fun crearTaller(controlador: AppControlador) {
    println("Introduzca el cif del taller")
    val cif = readln()
    println("Introduzca el nombre del taller")
    val nombre = readln()
    val direccion = crearDireccion()

    val contrasenia = contrasenia()

    val taller = Taller(cif = cif, nombre = nombre, direccion = direccion, contrasenia = contrasenia)

    controlador.insertTaller(taller)
}

fun crearCliente(controlador: AppControlador) {
    println("Introduzca el dni del cliente")
    val dni = readln()
    println("Introduzca el nombre del cliente")
    val nombre = readln()
    println("Introduzca la edad")
    var edad = readln().toIntOrNull()

    while (edad == null){
        println("Introduzca la edad")
        edad = readln().toIntOrNull()
    }

    val direccion = crearDireccion()

    val contrasenia = contrasenia()

    val cliente = Cliente(dni = dni,nombre = nombre, edad = edad, direccion = direccion, contrasenia = contrasenia)

    controlador.insertCliente(cliente)
}

fun contrasenia(): String {
    println("Introduzca la contraseña")
    val contrasenia = readln()
    println("Introduzca de nuevo la contraseña")
    val contrasenia2 = readln()
    return if (contrasenia == contrasenia2){
        contrasenia
    }else{
        println("No es la misma contraseña")
        contrasenia()
    }
}

fun crearDireccion(): Direccion {
    println("Introduzca la calle")
    val calle = readln()
    println("Introduzca el número")
    var numero = readln().toIntOrNull()

    while (numero == null){
        println("Introduzca el número")
        numero = readln().toIntOrNull()
    }

    println("Introduzca el código postal")
    var cp = readln().toIntOrNull()

    while (cp == null){
        println("Introduzca el código postal")
        cp = readln().toIntOrNull()
    }

    println("Introduzca la ciudad")
    val ciudad = readln()
    return Direccion(calle = calle, numero = numero, cp = cp, ciudad = ciudad)
}

fun opcionesTaller(){
    println("********************")
    println("Elija una opción")
    println("1. Consultar pedidos no asignados")
    println("2. Asignar pedido no asignado")
    println("3. Consultar pedidos asignados al taller")
    println("4. Consultar clientes asignados al taller")
    println("5. Salir del programa")
}

fun menuTaller(controlador: AppControlador) {

    opcionesTaller()

    var opcion = readln().toIntOrNull()

    while (opcion == null || (opcion < 1 || opcion > 5)){
        opcionesTaller()
        opcion = readln().toIntOrNull()
    }

    when(opcion){

        1 -> {
            controlador.mostrarPedidosNoAsignados()
            menuTaller(controlador)
        }

        2 -> {
            controlador.asignarPedidoNoAsignado()
            menuTaller(controlador)
        }

        3 -> {
            controlador.allPedidosTaller()
            menuTaller(controlador)
        }

        4 -> {
            controlador.allClientesTaller()
            menuTaller(controlador)
        }

        5 -> {
            println("Adios!")
            exitProcess(0)
        }
    }

}

fun opcionesCliente(){
    println("********************")
    println("Elija una opción")
    println("1. Dar de alta un pedido")
    println("2. Consultar pedidos realizados")
    println("3. Consultar talleres asignados")
    println("4. Salir del programa")
}

fun menuCliente(controlador: AppControlador) {

    opcionesCliente()

    var opcion = readln().toIntOrNull()

    while (opcion == null || (opcion < 1 || opcion > 5)){
        opcionesCliente()
        opcion = readln().toIntOrNull()
    }

    when(opcion){

        1 -> {
            println("Introduzca la descripcion del pedido")
            val descripcion = readln()
            controlador.insertPedido(descripcion)
            menuCliente(controlador)
        }

        2 -> {
            controlador.allPedidosCliente()
            menuCliente(controlador)
        }

        3 -> {
            controlador.allTallerCliente()
            menuCliente(controlador)
        }

        4 -> {
            println("Adios!")
            exitProcess(0)
        }
    }

}
