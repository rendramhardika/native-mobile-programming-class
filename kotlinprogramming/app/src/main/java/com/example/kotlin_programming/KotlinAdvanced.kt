package com.example.kotlin_programming

/**
 * KotlinAdvanced.kt
 * Demonstrasi null safety dan fitur-fitur advanced Kotlin
 */

class KotlinAdvanced {
    
    // ========== NULL SAFETY ==========
    
    fun demonstrateNullSafety() {
        println("=== NULL SAFETY ===")
        
        // Non-nullable types
        var nonNullString: String = "Hello Kotlin"
        // nonNullString = null // Compile error!
        println("Non-null string: $nonNullString")
        
        // Nullable types
        var nullableString: String? = "Initial value"
        nullableString = null // This is allowed
        println("Nullable string: $nullableString")
        
        // Safe call operator (?.)
        val length = nullableString?.length
        println("Length using safe call: $length")
        
        // Elvis operator (?:)
        val lengthOrDefault = nullableString?.length ?: 0
        println("Length with default: $lengthOrDefault")
        
        // Not-null assertion (!!)
        nullableString = "Not null anymore"
        val definiteLength = nullableString!!.length // Use with caution!
        println("Definite length: $definiteLength")
        
        // Safe casting (as?)
        val obj: Any = "This is a string"
        val stringValue = obj as? String
        val intValue = obj as? Int
        println("Safe cast to String: $stringValue")
        println("Safe cast to Int: $intValue")
        
        // let function with nullable
        nullableString?.let { str ->
            println("String is not null: $str")
            println("Processing: ${str.uppercase()}")
        }
        
        // Nullable collections
        val nullableList: List<String>? = listOf("A", "B", "C")
        val listSize = nullableList?.size ?: 0
        println("Nullable list size: $listSize")
        
        // List with nullable elements
        val listWithNulls: List<String?> = listOf("A", null, "C", null)
        val nonNullElements = listWithNulls.filterNotNull()
        println("List with nulls: $listWithNulls")
        println("Non-null elements: $nonNullElements")
    }
    
    // ========== EXTENSION FUNCTIONS ==========
    
    // Extension function untuk String
    fun String.isPalindrome(): Boolean {
        val cleaned = this.lowercase().replace(" ", "")
        return cleaned == cleaned.reversed()
    }
    
    // Extension function untuk Int
    fun Int.isEven(): Boolean = this % 2 == 0
    
    // Extension function untuk List
    fun <T> List<T>.secondOrNull(): T? = if (this.size >= 2) this[1] else null
    
    // Extension property
    val String.wordCount: Int
        get() = this.split(" ").size
    
    fun demonstrateExtensionFunctions() {
        println("\n=== EXTENSION FUNCTIONS ===")
        
        // Using string extension
        val text1 = "racecar"
        val text2 = "hello world"
        println("'$text1' is palindrome: ${text1.isPalindrome()}")
        println("'$text2' is palindrome: ${text2.isPalindrome()}")
        
        // Using int extension
        val number = 42
        println("$number is even: ${number.isEven()}")
        
        // Using list extension
        val fruits = listOf("Apple", "Banana", "Cherry")
        val emptyList = emptyList<String>()
        println("Second fruit: ${fruits.secondOrNull()}")
        println("Second from empty: ${emptyList.secondOrNull()}")
        
        // Using extension property
        val sentence = "Kotlin is awesome and powerful"
        println("'$sentence' has ${sentence.wordCount} words")
    }
    
    // ========== GENERICS ==========
    
    // Generic class
    class Box<T>(private var content: T) {
        fun get(): T = content
        fun set(value: T) {
            content = value
        }
        
        override fun toString(): String = "Box($content)"
    }
    
    // Generic function
    fun <T> swap(list: MutableList<T>, index1: Int, index2: Int) {
        val temp = list[index1]
        list[index1] = list[index2]
        list[index2] = temp
    }
    
    // Generic function with constraints
    fun <T : Comparable<T>> findMax(list: List<T>): T? {
        if (list.isEmpty()) return null
        var max = list[0]
        for (item in list) {
            if (item > max) max = item
        }
        return max
    }
    
    // Variance - covariance (out)
    interface Producer<out T> {
        fun produce(): T
    }
    
    // Variance - contravariance (in)
    interface Consumer<in T> {
        fun consume(item: T)
    }
    
    fun demonstrateGenerics() {
        println("\n=== GENERICS ===")
        
        // Generic class usage
        val stringBox = Box("Hello")
        val intBox = Box(42)
        println("String box: $stringBox")
        println("Int box: $intBox")
        
        stringBox.set("World")
        intBox.set(100)
        println("Updated string box: $stringBox")
        println("Updated int box: $intBox")
        
        // Generic function usage
        val numbers = mutableListOf(1, 2, 3, 4, 5)
        println("Before swap: $numbers")
        swap(numbers, 0, 4)
        println("After swap: $numbers")
        
        // Generic function with constraints
        val maxNumber = findMax(listOf(3, 7, 2, 9, 1))
        val maxString = findMax(listOf("apple", "zebra", "banana"))
        println("Max number: $maxNumber")
        println("Max string: $maxString")
        
        // Star projection
        val boxes: List<Box<*>> = listOf(Box("text"), Box(123), Box(true))
        boxes.forEach { box ->
            println("Box contains: ${box.get()}")
        }
    }
    
    // ========== COROUTINES BASICS ==========
    
    // Suspend function simulation (without actual coroutines)
    fun simulateAsyncOperation(name: String, delayMs: Long): String {
        // Simulate delay
        Thread.sleep(delayMs)
        return "Result from $name after ${delayMs}ms"
    }
    
    fun demonstrateCoroutineConcepts() {
        println("\n=== COROUTINE CONCEPTS ===")
        
        println("Simulating async operations...")
        
        // Sequential execution
        val start = System.currentTimeMillis()
        val result1 = simulateAsyncOperation("Task 1", 100)
        val result2 = simulateAsyncOperation("Task 2", 150)
        val end = System.currentTimeMillis()
        
        println("Sequential results:")
        println("- $result1")
        println("- $result2")
        println("Total time: ${end - start}ms")
        
        // Note: Actual coroutines would require kotlinx-coroutines dependency
        println("Note: Real coroutines require kotlinx-coroutines library")
    }
    
    // ========== DELEGATION ==========
    
    // Property delegation
    class LazyExample {
        val expensiveValue: String by lazy {
            println("Computing expensive value...")
            "Expensive Result"
        }
    }
    
    // Observable property
    class ObservableExample {
        var name: String = "Initial"
            set(value) {
                println("Name changed from '$field' to '$value'")
                field = value
            }
    }
    
    // Class delegation
    interface Printer {
        fun print(message: String)
    }
    
    class ConsolePrinter : Printer {
        override fun print(message: String) {
            println("Console: $message")
        }
    }
    
    class Logger(printer: Printer) : Printer by printer {
        fun log(message: String) {
            print("LOG: $message")
        }
    }
    
    fun demonstrateDelegation() {
        println("\n=== DELEGATION ===")
        
        // Lazy delegation
        val lazyExample = LazyExample()
        println("Before accessing lazy value")
        println("Lazy value: ${lazyExample.expensiveValue}")
        println("Accessing again: ${lazyExample.expensiveValue}")
        
        // Observable property
        val observable = ObservableExample()
        observable.name = "New Name"
        observable.name = "Another Name"
        
        // Class delegation
        val printer = ConsolePrinter()
        val logger = Logger(printer)
        logger.print("Direct print")
        logger.log("Logged message")
    }
    
    // ========== INLINE FUNCTIONS ==========
    
    inline fun measureTime(action: () -> Unit): Long {
        val start = System.currentTimeMillis()
        action()
        val end = System.currentTimeMillis()
        return end - start
    }
    
    inline fun <T> T.applyIf(condition: Boolean, block: T.() -> Unit): T {
        return if (condition) {
            this.apply(block)
        } else {
            this
        }
    }
    
    fun demonstrateInlineFunctions() {
        println("\n=== INLINE FUNCTIONS ===")
        
        // Measure execution time
        val time = measureTime {
            Thread.sleep(50)
            println("Task completed")
        }
        println("Execution time: ${time}ms")
        
        // Conditional apply
        val list = mutableListOf(1, 2, 3)
            .applyIf(true) {
                add(4)
                add(5)
            }
            .applyIf(false) {
                add(6) // This won't execute
            }
        println("Conditional list: $list")
    }
    
    // ========== SCOPE FUNCTIONS ==========
    
    fun demonstrateScopeFunctions() {
        println("\n=== SCOPE FUNCTIONS ===")
        
        data class Person(var name: String, var age: Int, var city: String)
        
        // let - returns lambda result
        val person = Person("John", 30, "Jakarta")
        val introduction = person.let { p ->
            "Hello, I'm ${p.name}, ${p.age} years old from ${p.city}"
        }
        println("let result: $introduction")
        
        // run - returns lambda result, this context
        val greeting = person.run {
            age += 1
            "Hi, I'm $name and I just turned $age"
        }
        println("run result: $greeting")
        
        // with - returns lambda result, this context
        val description = with(person) {
            city = "Bandung"
            "Person: $name, $age, $city"
        }
        println("with result: $description")
        
        // apply - returns object, this context
        val updatedPerson = person.apply {
            name = "Jane"
            age = 25
        }
        println("apply result: $updatedPerson")
        
        // also - returns object, it context
        val finalPerson = person.also { p ->
            println("Person before final update: $p")
        }.apply {
            city = "Surabaya"
        }
        println("also + apply result: $finalPerson")
        
        // takeIf and takeUnless
        val adult = person.takeIf { it.age >= 18 }
        val child = person.takeUnless { it.age >= 18 }
        println("Adult: $adult")
        println("Child: $child")
    }
    
    fun runAllDemonstrations() {
        demonstrateNullSafety()
        demonstrateExtensionFunctions()
        demonstrateGenerics()
        demonstrateCoroutineConcepts()
        demonstrateDelegation()
        demonstrateInlineFunctions()
        demonstrateScopeFunctions()
    }
}
