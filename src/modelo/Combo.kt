package modelo

@Promocionable("10% en combos familiares")
class ComboPromocionable(
    nombre: String,
    precio: Double,
    ingredientes: Map<String, Int>
) : Producto(nombre, precio, ingredientes, CategoriaProducto.COMBO)
