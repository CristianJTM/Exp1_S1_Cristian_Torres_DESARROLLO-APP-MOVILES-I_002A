import modelo.Cliente
import modelo.EstadoPedido
import modelo.ItemPedido
import modelo.Pedido
import modelo.Producto
import modelo.Repartidor
import modelo.Usuario
import servicio.Inventario
import servicio.Pizzeria
import utils.leerCantidad
import utils.leerCorreo
import utils.leerNotas
import utils.leerOpcion
import utils.leerSiNo
import utils.leerTelefono
import utils.leerTexto

fun main() {
    val pizzeria: Pizzeria = crearPizzeria()

    while (true) {
        mostrarMenuPrincipal()
        val opcion: Int = leerOpcion(1, 7, "Seleccione una opción: ") ?: return

        when (opcion) {
            1 -> registrarPedido(pizzeria)
            2 -> pizzeria.inventario.mostrar()
            3 -> reabastecerInventario(pizzeria)
            4 -> asignarRepartidor(pizzeria)
            5 -> cambiarEstadoPedido(pizzeria)
            6 -> pizzeria.generarReporte()
            7 -> {
                println("Gracias por usar el sistema. Hasta luego.")
                return
            }
        }
    }
}

fun crearPizzeria(): Pizzeria {
    val productos: List<Producto> = listOf(
        Producto(
            "Pepperoni Familiar",
            12990.0,
            mapOf("Masa" to 1, "Salsa" to 1, "Queso" to 2, "Pepperoni" to 1)
        ),
        Producto(
            "Margarita Familiar",
            10990.0,
            mapOf("Masa" to 1, "Salsa" to 1, "Queso" to 2, "Tomate" to 1)
        ),
        Producto(
            "Cuatro Quesos",
            14990.0,
            mapOf("Masa" to 1, "Salsa" to 1, "Queso" to 4)
        )
    )

    val inventario: Inventario = Inventario(
        mapOf(
            "Masa" to 30,
            "Salsa" to 30,
            "Queso" to 80,
            "Pepperoni" to 20,
            "Tomate" to 20
        )
    )

    val repartidores: List<Repartidor> = listOf(
        Repartidor("Camila Rojas", "+56 9 1111 1111", "Centro", 1.2),
        Repartidor("Diego Soto", "+56 9 2222 2222", "Centro", 2.5),
        Repartidor("Valentina Díaz", "+56 9 3333 3333", "Norte", 3.0),
        Repartidor("Martín Silva", "+56 9 4444 4444", "Sur", 2.0)
    )

    return Pizzeria(productos, inventario, repartidores)
}

fun mostrarMenuPrincipal() {
    println()
    println("============================================================")
    println("                     PIZZERÍA")
    println("============================================================")
    println("1. Registrar pedido")
    println("2. Ver inventario")
    println("3. Reabastecer ingrediente")
    println("4. Asignar repartidor")
    println("5. Cambiar estado de pedido")
    println("6. Generar reporte del día")
    println("7. Salir")
}

fun registrarPedido(pizzeria: Pizzeria) {
    println()
    println("=== REGISTRO DE PEDIDO ===")

    val nombre: String = leerTexto("Nombre del cliente: ", "Cliente sin nombre")
    val telefono: String = leerTelefono()
    val correo: String? = leerCorreo()

    println("Zonas: 1. Centro, 2. Norte, 3. Sur")
    val zonaOpcion: Int = leerOpcion(1, 3, "Seleccione zona: ") ?: return
    val zona: String = when (zonaOpcion) {
        1 -> "Centro"
        2 -> "Norte"
        else -> "Sur"
    }

    val notas: String? = leerNotas()
    val cliente: Cliente = Cliente(nombre, telefono, correo, zona)
    val items: MutableList<ItemPedido> = mutableListOf()

    var agregarOtro: Boolean = true
    while (agregarOtro) {
        pizzeria.mostrarProductos()
        val opcion: Int = leerOpcion(1, pizzeria.productos.size, "Seleccione una pizza: ") ?: return
        val producto: Producto? = pizzeria.productos.getOrNull(opcion - 1)
        if (producto == null) {
            println("La pizza seleccionada ya no está disponible.")
            return
        }

        val cantidad: Int = leerCantidad("Cantidad de ${producto.nombre}: ")
        items.add(ItemPedido(producto, cantidad))
        agregarOtro = leerSiNo("¿Agregar otro producto? (s/n): ")
    }

    val pedido: Pedido? = pizzeria.registrarPedido(cliente, items, notas)

    if (pedido != null) {
        println()
        println(pedido.generarResumen())
        pedido.notificarCliente()
        mostrarParticipantes(pedido)
    }
}

fun reabastecerInventario(pizzeria: Pizzeria) {
    println()
    println("=== REABASTECER INVENTARIO ===")

    val ingredientes: List<String> = pizzeria.inventario.ingredientesDisponibles().sorted()
    if (ingredientes.isEmpty()) {
        println("No hay ingredientes registrados.")
        return
    }

    for ((indice, ingrediente) in ingredientes.withIndex()) {
        println("${indice + 1}. $ingrediente")
    }

    val opcion: Int = leerOpcion(1, ingredientes.size, "Seleccione el ingrediente: ") ?: return
    val ingrediente: String? = ingredientes.getOrNull(opcion - 1)
    if (ingrediente == null) {
        println("El ingrediente seleccionado ya no está disponible.")
        return
    }

    val cantidad: Int = leerCantidad("Cantidad a reponer: ")
    pizzeria.inventario.reponer(ingrediente, cantidad)
}

fun asignarRepartidor(pizzeria: Pizzeria) {
    val pedido: Pedido = seleccionarPedido(pizzeria) ?: return
    pizzeria.asignarRepartidor(pedido)
    println()
    println(pedido.generarResumen())
    mostrarParticipantes(pedido)
}

fun cambiarEstadoPedido(pizzeria: Pizzeria) {
    val pedido: Pedido = seleccionarPedido(pizzeria) ?: return

    println("Estado actual: ${pedido.estado.descripcion}")
    println("1. En preparación")
    println("2. Listo")
    println("Para pasar a En camino, asigne un repartidor desde la opción 4.")

    val opcionEstado: Int = leerOpcion(1, 2, "Seleccione el nuevo estado: ") ?: return
    val nuevoEstado: EstadoPedido = when (opcionEstado) {
        1 -> EstadoPedido.EN_PREPARACION
        else -> EstadoPedido.LISTO
    }

    val aplicado: Boolean = pedido.cambiarEstado(nuevoEstado)
    if (aplicado) {
        println("Estado actualizado: ${pedido.estado.descripcion}")
    } else {
        println("Transición no permitida: ${pedido.estado.descripcion} -> ${nuevoEstado.descripcion}.")
        println("Secuencia permitida: En preparación -> Listo -> En camino.")
    }

    println()
    println(pedido.generarResumen())
}

fun seleccionarPedido(pizzeria: Pizzeria): Pedido? {
    val pedidos: List<Pedido> = pizzeria.listarPedidos()

    if (pedidos.isEmpty()) {
        println("No hay pedidos registrados.")
        return null
    }

    println()
    println("=== PEDIDOS REGISTRADOS ===")
    for ((indice, pedido) in pedidos.withIndex()) {
        println("${indice + 1}. Pedido #${pedido.id} - ${pedido.cliente.nombre} - ${pedido.estado.descripcion}")
    }

    val opcion: Int = leerOpcion(1, pedidos.size, "Seleccione un pedido: ") ?: return null
    val pedido: Pedido? = pizzeria.obtenerPedido(opcion - 1)
    if (pedido == null) {
        println("El pedido seleccionado ya no existe.")
    }
    return pedido
}

fun mostrarParticipantes(pedido: Pedido) {
    val usuarios: List<Usuario> = listOfNotNull<Usuario>(pedido.cliente, pedido.repartidor)
    println()
    println("Participantes del pedido:")
    usuarios.forEach { println("  ${it.describir()}") }
}
