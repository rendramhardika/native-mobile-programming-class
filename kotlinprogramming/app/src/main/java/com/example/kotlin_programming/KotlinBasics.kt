package com.example.kotlin_programming

/**
 * KotlinBasics.kt
 * Demonstrasi konsep-konsep dasar pemrograman Kotlin
 */

// ========== VARIABLES & DATA TYPES ==========

class KotlinBasics {
    
    // Constants (compile-time constants)
    companion object {
        const val APP_NAME = "Kotlin Programming"
        const val VERSION_CODE = 1
        const val PI = 3.14159
    }
    
    // Runtime constants
    val readOnlyValue = "Ini adalah val (read-only)"
    
    // Mutable variables
    var mutableValue = "Ini adalah var (mutable)"
    
    fun demonstrateVariablesAndDataTypes() {
        println("=== VARIABLES & DATA TYPES ===")
        
        // Basic data types
        val intNumber: Int = 42
        val longNumber: Long = 1234567890L
        val floatNumber: Float = 3.14f
        val doubleNumber: Double = 2.718281828
        val booleanValue: Boolean = true
        val charValue: Char = 'K'
        val stringValue: String = "Kotlin Programming"
        
        // Type inference (Kotlin dapat menentukan tipe secara otomatis)
        val inferredInt = 100
        val inferredString = "Auto-detected string"
        val inferredDouble = 99.99
        
        println("Integer: $intNumber")
        println("Long: $longNumber")
        println("Float: $floatNumber")
        println("Double: $doubleNumber")
        println("Boolean: $booleanValue")
        println("Char: $charValue")
        println("String: $stringValue")
        println("Inferred Int: $inferredInt")
        println("Inferred String: $inferredString")
        println("Inferred Double: $inferredDouble")
        
        // String templates
        println("Nama aplikasi: $APP_NAME, Versi: $VERSION_CODE")
        println("Hasil perhitungan: ${intNumber + inferredInt}")
        
        // Nullable types
        var nullableString: String? = null
        nullableString = "Sekarang ada nilai"
        println("Nullable string: $nullableString")

        
        // Safe call operator
        println("Panjang string: ${nullableString?.length}")
        // Elvis operator
        val length = nullableString?.length ?: 0
        println("Panjang dengan elvis operator: $length")
    }
    
    // ========== FUNCTIONS ==========
    
    // Basic function
    fun greetUser(name: String): String {
        return "Halo, $name! Selamat datang di Kotlin Programming."
    }
    
    // Function with default parameter
    fun calculateArea(width: Double, height: Double = 1.0): Double {
        return width * height
    }
    
    // Single expression function
    fun multiply(a: Int, b: Int) = a * b
    
    // Function with vararg (variable arguments)
    fun sumNumbers(vararg numbers: Int): Int {
        var total = 0
        for (number in numbers) {
            total += number
        }
        return total
    }
    
    // Higher-order function (function that takes another function as parameter)
    fun processNumbers(numbers: List<Int>, operation: (Int) -> Int): List<Int> {
        return numbers.map { operation(it) }
    }
    
    fun demonstrateFunctions() {
        println("\n=== FUNCTIONS ===")
        
        // Basic function call
        println(greetUser("Budi"))
        
        // Function with default parameter
        println("Area persegi panjang: ${calculateArea(5.0, 3.0)}")
        println("Area persegi: ${calculateArea(4.0)}")
        
        // Single expression function
        println("Perkalian: ${multiply(6, 7)}")
        
        // Vararg function
        println("Jumlah angka: ${sumNumbers(1, 2, 3, 4, 5)}")
        
        // Higher-order function with lambda
        val numbers = listOf(1, 2, 3, 4, 5)
        val doubled = processNumbers(numbers) { it * 2 }
        println("Angka dikali dua: $doubled")
    }
    
    // ========== LAMBDA EXPRESSIONS ==========
    
    fun demonstrateLambdas() {
        println("\n=== LAMBDA EXPRESSIONS ===")
        
        val numbers = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        
        // Lambda untuk filter
        val evenNumbers = numbers.filter { it % 2 == 0 }
        println("Angka genap: $evenNumbers")
        // Lambda untuk map
        val squares = numbers.map { it * 10 }
        println("Kuadrat: $squares")
        
        // Lambda untuk reduce
        val sum = numbers.reduce { acc, n -> acc + n }
        println("Jumlah total: $sum")
        
        // Lambda dengan multiple parameters
        val pairs = listOf(Pair(1, 2), Pair(3, 4), Pair(5, 6))
        val sums = pairs.map { (first, second) -> first + second }
        println("Jumlah pasangan: $sums")
        
        // Lambda sebagai variable
        val isPositive: (Int) -> Boolean = { it > 0 }
        val positiveNumbers = numbers.filter(isPositive)
        println("Angka positif: $positiveNumbers")
        
        // Recursive function (proper way)
        fun calculateFactorial(n: Int): Int {
            return if (n <= 1) 1 else n * calculateFactorial(n - 1)
        }
        
        // Lambda with tail recursion using lateinit
        lateinit var factorial: (Int) -> Int
        factorial = { n ->
            if (n <= 1) 1 else n * factorial(n - 1)
        }
        
        println("Faktorial 5 (function): ${calculateFactorial(5)}")
        println("Faktorial 5 (lambda): ${factorial(5)}")
    }
}
