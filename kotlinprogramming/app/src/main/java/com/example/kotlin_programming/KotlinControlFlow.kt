package com.example.kotlin_programming

/**
 * KotlinControlFlow.kt
 * Demonstrasi control flow dalam Kotlin (if/when/loops)
 */

class KotlinControlFlow {
    
    // ========== IF EXPRESSIONS ==========
    
    fun demonstrateIfExpressions() {
        println("=== IF EXPRESSIONS ===")
        
        val score = 85
        val grade = if (score >= 90) {
            "A"
        } else if (score >= 80) {
            "B"
        } else if (score >= 70) {
            "C"
        } else if (score >= 60) {
            "D"
        } else {
            "F"
        }
        
        println("Nilai: $score, Grade: $grade")
        
        // If sebagai expression (single line)
        val status = if (score >= 60) "Lulus" else "Tidak Lulus"
        println("Status: $status")
        
        // If dengan multiple statements
        val result = if (score >= 80) {
            println("Selamat! Nilai bagus")
            "Memuaskan"
        } else {
            println("Perlu belajar lebih giat")
            "Perlu Perbaikan"
        }
        println("Hasil: $result")
    }
    
    // ========== WHEN EXPRESSIONS ==========
    
    fun demonstrateWhenExpressions() {
        println("\n=== WHEN EXPRESSIONS ===")
        
        val dayOfWeek = 3
        val dayName = when (dayOfWeek) {
            1 -> "Senin"
            2 -> "Selasa"
            3 -> "Rabu"
            4 -> "Kamis"
            5 -> "Jumat"
            6 -> "Sabtu"
            7 -> "Minggu"
            else -> "Hari tidak valid"
        }
        println("Hari ke-$dayOfWeek: $dayName")
        
        // When dengan range
        val age = 25
        val category = when (age) {
            in 0..12 -> "Anak-anak"
            in 13..17 -> "Remaja"
            in 18..59 -> "Dewasa"
            in 60..100 -> "Lansia"
            else -> "Usia tidak valid"
        }
        println("Umur $age tahun: $category")
        
        // When dengan multiple conditions
        val number = 15
        val description = when {
            number % 15 == 0 -> "Kelipatan 15"
            number % 5 == 0 -> "Kelipatan 5"
            number % 3 == 0 -> "Kelipatan 3"
            number % 2 == 0 -> "Bilangan genap"
            else -> "Bilangan ganjil"
        }
        println("Angka $number: $description")
        
        // When dengan type checking
        val obj: Any = "Hello Kotlin"
        val typeInfo = when (obj) {
            is String -> "String dengan panjang ${obj.length}"
            is Int -> "Integer dengan nilai $obj"
            is Boolean -> "Boolean dengan nilai $obj"
            else -> "Tipe tidak dikenal"
        }
        println("Object info: $typeInfo")
    }
    
    // ========== FOR LOOPS ==========
    
    fun demonstrateForLoops() {
        println("\n=== FOR LOOPS ===")
        
        // For loop dengan range
        println("Counting 1 to 5:")
        for (i in 1..5) {
            print("$i ")
        }
        println()
        
        // For loop dengan until (exclusive)
        println("Counting 1 until 5:")
        for (i in 1 until 5) {
            print("$i ")
        }
        println()
        
        // For loop dengan step
        println("Counting 1 to 10 step 2:")
        for (i in 1..10 step 2) {
            print("$i ")
        }
        println()
        
        // For loop downTo
        println("Countdown 5 to 1:")
        for (i in 5 downTo 1) {
            print("$i ")
        }
        println()
        
        // For loop dengan array/list
        val fruits = arrayOf("Apel", "Jeruk", "Mangga", "Pisang")
        println("Buah-buahan:")
        for (fruit in fruits) {
            println("- $fruit")
        }
        
        // For loop dengan index
        println("Buah dengan index:")
        for ((index, fruit) in fruits.withIndex()) {
            println("$index: $fruit")
        }
        
        // For loop dengan indices
        println("Menggunakan indices:")
        for (i in fruits.indices) {
            println("Index $i: ${fruits[i]}")
        }
    }
    
    // ========== WHILE LOOPS ==========
    
    fun demonstrateWhileLoops() {
        println("\n=== WHILE LOOPS ===")
        
        // While loop
        println("While loop - countdown:")
        var count = 5
        while (count > 0) {
            print("$count ")
            count--
        }
        println()
        
        // Do-while loop
        println("Do-while loop - input validation simulation:")
        var attempts = 0
        do {
            attempts++
            println("Percobaan ke-$attempts")
        } while (attempts < 3)
    }
    
    // ========== BREAK & CONTINUE ==========
    
    fun demonstrateBreakAndContinue() {
        println("\n=== BREAK & CONTINUE ===")
        
        // Continue - skip even numbers
        println("Angka ganjil 1-10:")
        for (i in 1..10) {
            if (i % 2 == 0) continue
            print("$i ")
        }
        println()
        
        // Break - stop at first number > 5
        println("Angka sampai yang pertama > 5:")
        for (i in 1..10) {
            if (i > 5) break
            print("$i ")
        }
        println()
        
        // Labeled break dan continue
        println("Nested loops dengan labeled break:")
        outer@ for (i in 1..3) {
            for (j in 1..3) {
                if (i == 2 && j == 2) {
                    println("Breaking outer loop at i=$i, j=$j")
                    break@outer
                }
                print("($i,$j) ")
            }
            println()
        }
    }
    
    // ========== EXCEPTION HANDLING ==========
    
    fun demonstrateExceptionHandling() {
        println("\n=== EXCEPTION HANDLING ===")
        
        // Try-catch
        fun divideNumbers(a: Int, b: Int): String {
            return try {
                val result = a / b
                "Hasil: $result"
            } catch (e: ArithmeticException) {
                "Error: Tidak bisa membagi dengan nol"
            } catch (e: Exception) {
                "Error: ${e.message}"
            } finally {
                println("Operasi pembagian selesai")
            }
        }
        
        println(divideNumbers(10, 2))
        println(divideNumbers(10, 0))
        
        // Try sebagai expression
        val parseResult = try {
            "123abc".toInt()
        } catch (e: NumberFormatException) {
            -1
        }
        println("Parse result: $parseResult")
        
        // Custom exception
        class CustomException(message: String) : Exception(message)
        
        fun validateAge(age: Int) {
            try {
                if (age < 0) throw CustomException("Umur tidak boleh negatif")
                if (age > 150) throw CustomException("Umur tidak realistis")
                println("Umur valid: $age")
            } catch (e: CustomException) {
                println("Validasi error: ${e.message}")
            }
        }
        
        validateAge(25)
        validateAge(-5)
        validateAge(200)
    }
    
    // ========== RETURN & JUMP ==========
    
    fun demonstrateReturnAndJump() {
        println("\n=== RETURN & JUMP ===")
        
        // Return dari function
        fun findFirstEven(numbers: List<Int>): Int? {
            for (number in numbers) {
                if (number % 2 == 0) {
                    return number
                }
            }
            return null
        }
        
        val numbers = listOf(1, 3, 5, 8, 9, 12)
        val firstEven = findFirstEven(numbers)
        println("Bilangan genap pertama: $firstEven")
        
        // Return dengan label
        fun processLists() {
            val lists = listOf(
                listOf(1, 2, 3),
                listOf(4, 5, 6),
                listOf(7, 8, 9)
            )
            
            lists.forEach lit@{ list ->
                list.forEach { number ->
                    if (number == 5) {
                        println("Found 5, skipping rest of this list")
                        return@lit
                    }
                    print("$number ")
                }
                println()
            }
        }
        
        processLists()
    }
    
    fun runAllDemonstrations() {
        demonstrateIfExpressions()
        demonstrateWhenExpressions()
        demonstrateForLoops()
        demonstrateWhileLoops()
        demonstrateBreakAndContinue()
        demonstrateExceptionHandling()
        demonstrateReturnAndJump()
    }
}
