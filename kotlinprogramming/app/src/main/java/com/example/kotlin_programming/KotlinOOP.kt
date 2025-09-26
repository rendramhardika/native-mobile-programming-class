package com.example.kotlin_programming

/**
 * KotlinOOP.kt
 * Demonstrasi konsep Object-Oriented Programming dalam Kotlin
 */

// ========== BASIC CLASS ==========

// Basic class dengan constructor
class Person(val name: String, var age: Int) {
    // Secondary constructor
    constructor(name: String) : this(name, 0)
    
    // Properties dengan custom getter/setter
    var email: String = ""
        set(value) {
            if (value.contains("@")) {
                field = value
            } else {
                println("Email tidak valid!")
            }
        }
    
    // Method
    fun introduce() {
        println("Halo, nama saya $name, umur $age tahun")
    }
    
    fun celebrateBirthday() {
        age++
        println("Selamat ulang tahun! Sekarang umur saya $age tahun")
    }
}

// ========== DATA CLASS ==========

// Data class - otomatis generate equals(), hashCode(), toString(), copy()
data class Student(
    val id: String,
    val name: String,
    val major: String,
    var gpa: Double = 0.0
) {
    fun isHonorStudent(): Boolean = gpa >= 3.5
}

// ========== INHERITANCE ==========

// Base class (open class dapat di-inherit)
open class Animal(val name: String, val species: String) {
    open fun makeSound() {
        println("$name membuat suara")
    }
    
    open fun move() {
        println("$name bergerak")
    }
}

// Derived class
class Dog(name: String) : Animal(name, "Canis lupus") {
    override fun makeSound() {
        println("$name menggonggong: Woof! Woof!")
    }
    
    override fun move() {
        println("$name berlari dengan empat kaki")
    }
    
    fun fetch() {
        println("$name mengambil bola")
    }
}

class Bird(name: String, val canFly: Boolean) : Animal(name, "Aves") {
    override fun makeSound() {
        println("$name berkicau: Tweet! Tweet!")
    }
    
    override fun move() {
        if (canFly) {
            println("$name terbang di udara")
        } else {
            println("$name berjalan di tanah")
        }
    }
}

// ========== ABSTRACT CLASS ==========

abstract class Shape {
    abstract val area: Double
    abstract val perimeter: Double
    
    abstract fun draw()
    
    // Concrete method dalam abstract class
    fun displayInfo() {
        println("Area: $area, Perimeter: $perimeter")
    }
}

class Rectangle(val width: Double, val height: Double) : Shape() {
    override val area: Double
        get() = width * height
    
    override val perimeter: Double
        get() = 2 * (width + height)
    
    override fun draw() {
        println("Menggambar persegi panjang ${width}x${height}")
    }
}

class Circle(val radius: Double) : Shape() {
    override val area: Double
        get() = Math.PI * radius * radius
    
    override val perimeter: Double
        get() = 2 * Math.PI * radius
    
    override fun draw() {
        println("Menggambar lingkaran dengan radius $radius")
    }
}

// ========== INTERFACE ==========

interface Drawable {
    fun draw()
    fun resize(factor: Double)
    
    // Default implementation dalam interface
    fun displaySize() {
        println("Menampilkan ukuran objek")
    }
}

interface Movable {
    fun move(x: Int, y: Int)
}

// Class yang implement multiple interfaces
class GameCharacter(var name: String, var x: Int = 0, var y: Int = 0) : Drawable, Movable {
    override fun draw() {
        println("Menggambar karakter $name di posisi ($x, $y)")
    }
    
    override fun resize(factor: Double) {
        println("Mengubah ukuran karakter $name dengan faktor $factor")
    }
    
    override fun move(x: Int, y: Int) {
        this.x = x
        this.y = y
        println("$name pindah ke posisi ($x, $y)")
    }


}

// ========== OBJECT & SINGLETON ==========

// Object declaration (singleton)
object DatabaseManager {
    private var connectionCount = 0
    
    fun connect(): String {
        connectionCount++
        return "Koneksi database #$connectionCount berhasil"
    }
    
    fun getConnectionCount(): Int = connectionCount
}

// ========== ENUM CLASS ==========

enum class Priority(val level: Int) {
    LOW(1),
    MEDIUM(2),
    HIGH(3),
    CRITICAL(4);
    
    fun getDescription(): String {
        return when (this) {
            LOW -> "Prioritas rendah"
            MEDIUM -> "Prioritas sedang"
            HIGH -> "Prioritas tinggi"
            CRITICAL -> "Prioritas kritis"
        }
    }
}

// ========== SEALED CLASS ==========

sealed class Result<out T> {
    data class Success<T>(val data: T) : Result<T>()
    data class Error(val message: String) : Result<Nothing>()
    object Loading : Result<Nothing>()
}

// ========== DEMONSTRATION CLASS ==========

class KotlinOOPDemo {
    
    fun demonstrateBasicClasses() {
        println("=== BASIC CLASSES ===")
        
        val person1 = Person("Alice", 25)
        val person2 = Person("Bob")
        
        person1.introduce()
        person2.introduce()
        
        person1.email = "alice@example.com"
        person1.email = "invalid-email" // Akan menampilkan error
        
        person1.celebrateBirthday()
    }
    
    fun demonstrateDataClasses() {
        println("\n=== DATA CLASSES ===")
        
        val student1 = Student("S001", "Charlie", "Computer Science", 3.8)
        val student2 = Student("S002", "Diana", "Mathematics", 3.2)
        
        println("Student 1: $student1")
        println("Student 2: $student2")
        println("Student 1 honor student: ${student1.isHonorStudent()}")
        println("Student 2 honor student: ${student2.isHonorStudent()}")
        
        // Copy dengan perubahan
        val student1Updated = student1.copy(gpa = 3.9)
        println("Updated student 1: $student1Updated")
    }
    
    fun demonstrateInheritance() {
        println("\n=== INHERITANCE ===")
        
        val dog = Dog("Buddy")
        val bird = Bird("Tweety", true)
        val penguin = Bird("Pingu", false)
        
        val animals = listOf(dog, bird, penguin)
        
        animals.forEach { animal ->
            animal.makeSound()
            animal.move()
            println()
        }
        
        // Specific method untuk Dog
        dog.fetch()
    }
    
    fun demonstrateAbstractClasses() {
        println("\n=== ABSTRACT CLASSES ===")
        
        val rectangle = Rectangle(5.0, 3.0)
        val circle = Circle(4.0)
        
        val shapes = listOf(rectangle, circle)
        
        shapes.forEach { shape ->
            shape.draw()
            shape.displayInfo()
            println()
        }
    }
    
    fun demonstrateInterfaces() {
        println("\n=== INTERFACES ===")
        
        val character = GameCharacter("Hero")
        character.draw()
        character.move(10, 20)
        character.resize(1.5)
        character.displaySize()
    }
    
    fun demonstrateObjectAndSingleton() {
        println("\n=== OBJECT & SINGLETON ===")
        
        println(DatabaseManager.connect())
        println(DatabaseManager.connect())
        println("Total koneksi: ${DatabaseManager.getConnectionCount()}")
    }
    
    fun demonstrateEnums() {
        println("\n=== ENUM CLASSES ===")
        
        val priorities = Priority.values()
        priorities.forEach { priority ->
            println("${priority.name}: Level ${priority.level} - ${priority.getDescription()}")
        }
    }
    
    fun demonstrateSealedClasses() {
        println("\n=== SEALED CLASSES ===")
        
        val results = listOf(
            Result.Loading,
            Result.Success("Data berhasil dimuat"),
            Result.Error("Koneksi gagal")
        )
        
        results.forEach { result ->
            when (result) {
                is Result.Loading -> println("Sedang memuat...")
                is Result.Success -> println("Sukses: ${result.data}")
                is Result.Error -> println("Error: ${result.message}")
            }
        }
    }
    
    fun runAllDemonstrations() {
        demonstrateBasicClasses()
        demonstrateDataClasses()
        demonstrateInheritance()
        demonstrateAbstractClasses()
        demonstrateInterfaces()
        demonstrateObjectAndSingleton()
        demonstrateEnums()
        demonstrateSealedClasses()
    }
}
