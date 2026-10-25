# Pizzería Kotlin — Actividad formativa

Aplicación de consola en Kotlin para simular el pedido de una pizzería. Todo el código está en `src/Main.kt`.

## Funcionalidad

- Registra nombre, dirección y teléfono del cliente.
- Valida el teléfono: dígitos, `+` opcional al inicio y separadores simples con espacio o guion.
- Calcula el subtotal, aplica un 10% de descuento desde 4 pizzas y muestra el total.
- Verifica el stock, actualiza el inventario y genera un resumen del pedido.

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
kotlinc src/Main.kt -include-runtime -d pizzeria.jar
java -jar pizzeria.jar
```
