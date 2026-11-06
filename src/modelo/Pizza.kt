package modelo

class Pizza(
    nombre: String,
    precio: Double,
    ingredientes: Map<String, Int>
) : Producto(nombre, precio, ingredientes, CategoriaProducto.PIZZA)

@Promocionable("2x1 los martes")
class PizzaPromocionable(
    nombre: String,
    precio: Double,
    ingredientes: Map<String, Int>
) : Producto(nombre, precio, ingredientes, CategoriaProducto.PIZZA)
