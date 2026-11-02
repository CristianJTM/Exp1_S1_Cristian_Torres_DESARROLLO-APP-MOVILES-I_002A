# Pizzería Kotlin — Actividad Formativa 2

Aplicación de consola en Kotlin para gestionar pedidos, clientes, inventario y repartidores. La entrega de la Semana 1 quedó conservada en la etiqueta `formativa-1`.

## Funcionalidades

- Registro de clientes con validación de teléfono y correo.
- Valores por defecto para teléfono y correo inválidos.
- Catálogo de pizzas con ingredientes y precios.
- Pedidos con varios productos y cantidades.
- Verificación de ingredientes antes de aceptar un pedido.
- Inventario administrado con un `Map`.
- Cálculo de subtotal, descuento del 10% desde 4 pizzas, IVA del 19% y total final.
- Asignación del repartidor más cercano según zona y distancia.
- Cambio de estado: En preparación, Listo y En camino.
- Reporte diario con arreglo, ciclo `for` y agrupación con `filter`.
- Resumen por pedido con plantillas de strings.

## Estructura

```text
src/
├── Main.kt
├── modelo/
│   ├── Usuario.kt
│   ├── Cliente.kt
│   ├── Repartidor.kt
│   ├── Producto.kt
│   ├── ItemPedido.kt
│   ├── EstadoPedido.kt
│   └── Pedido.kt
├── servicio/
│   ├── Inventario.kt
│   └── Pizzeria.kt
└── utils/
    ├── Validaciones.kt
    ├── Calculos.kt
    └── Formato.kt
```

## Menú

1. Registrar pedido.
2. Ver inventario.
3. Reabastecer ingrediente.
4. Asignar repartidor.
5. Cambiar estado de pedido.
6. Generar reporte del día.
7. Salir.

## Reglas de negocio

- El descuento es del 10% cuando el pedido tiene más de 3 pizzas.
- El IVA es del 19% y se aplica después del descuento.
- Si faltan ingredientes, el pedido no se registra y se muestran los faltantes y los pasos para reabastecer.
- Si el teléfono es inválido, se usa `(000) 000-0000`.
- Si el correo es inválido, se muestra `correo@invalido.com` y no se envía notificación.
- Si no se ingresan notas, se muestra `Sin notas`.

## Requisitos

- JDK 21 o superior.
- IntelliJ IDEA con el plugin de Kotlin, o `kotlinc` instalado.

No requiere Gradle ni librerías externas.

## Ejecutar en IntelliJ IDEA

1. Abrir la carpeta del proyecto.
2. Verificar que `src` esté marcada como **Sources Root**.
3. Abrir `src/Main.kt` y ejecutar `main()` con el botón Run.

## Ejecutar con kotlinc

```powershell
$archivos = Get-ChildItem src -Recurse -Filter *.kt | ForEach-Object { $_.FullName }
kotlinc $archivos -include-runtime -d pizzeria.jar
java -jar pizzeria.jar
```

Si `kotlinc` no está en el `PATH`, compila desde IntelliJ IDEA o usa el `kotlinc.bat` incluido en la instalación del IDE.

## Entrega

Comprimir en formato ZIP:

```text
src/
README.md
.gitignore
```
