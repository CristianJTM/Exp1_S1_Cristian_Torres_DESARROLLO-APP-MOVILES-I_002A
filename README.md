# Pizzería Kotlin — Actividad formativa (v2)

Versión refactorizada de la Entrega Formativa 1. La entrega evaluada originalmente quedó conservada en la etiqueta `formativa-1`.

Aplicación de consola en Kotlin organizada por paquetes y responsabilidades.

## Estructura

```text
src/
├── Main.kt
├── modelo/
│   ├── Cliente.kt
│   ├── Pizza.kt
│   └── Pedido.kt
└── utils/
    ├── Validaciones.kt
    └── Calculos.kt
```

## Funcionalidad

- Registra nombre, dirección y teléfono del cliente.
- Valida el teléfono y vuelve a preguntar si el formato es incorrecto.
- Permite elegir entre varias pizzas.
- Vuelve a solicitar la cantidad si el valor no es un entero mayor que cero.
- Calcula subtotal, descuento del 10% desde 4 pizzas y total.
- Verifica el stock, actualiza el inventario y genera un resumen completo.

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
