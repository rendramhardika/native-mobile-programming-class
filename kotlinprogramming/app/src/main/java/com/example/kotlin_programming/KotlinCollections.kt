package com.example.kotlin_programming

/**
 * KotlinCollections.kt
 * Demonstrasi collections dan data structures dalam Kotlin
 */

class KotlinCollections {
    
    // ========== ARRAYS ==========
    
    fun demonstrateArrays() {
        println("=== ARRAYS ===")
        
        // Array creation
        val numbers = arrayOf(1, 2, 3, 4, 5)
        val strings = arrayOf("Kotlin", "Java", "Python", "JavaScript")
        val mixedArray = arrayOf(1, "Hello", 3.14, true)
        
        // Typed arrays
        val intArray = intArrayOf(10, 20, 30, 40, 50)
        val doubleArray = doubleArrayOf(1.1, 2.2, 3.3)
        
        // Array with size and initializer
        val squares = Array(5) { i -> i * i }
        
        println("Numbers: ${numbers.contentToString()}")
        println("Strings: ${strings.contentToString()}")
        println("Int array: ${intArray.contentToString()}")
        println("Squares: ${squares.contentToString()}")
        
        // Array operations
        println("First element: ${numbers.first()}")
        println("Last element: ${numbers.last()}")
        println("Array size: ${numbers.size}")
        println("Contains 3: ${numbers.contains(3)}")
        
        // Modifying arrays
        numbers[0] = 100
        println("Modified array: ${numbers.contentToString()}")
    }
    
    // ========== LISTS ==========
    
    fun demonstrateLists() {
        println("\n=== LISTS ===")
        
        // Immutable list (read-only)
        val readOnlyList = listOf("Apple", "Banana", "Cherry", "Date")
        println("Read-only list: $readOnlyList")
        
        // Mutable list
        val mutableList = mutableListOf("Red", "Green", "Blue")
        println("Original mutable list: $mutableList")
        
        // Adding elements
        mutableList.add("Yellow")
        mutableList.add(1, "Orange") // Insert at index
        println("After adding: $mutableList")
        
        // Removing elements
        mutableList.remove("Green")
        mutableList.removeAt(0)
        println("After removing: $mutableList")
        
        // List operations
        val fruits = listOf("Apple", "Banana", "Cherry", "Apple", "Date")
        println("Fruits: $fruits")
        println("Size: ${fruits.size}")
        println("First: ${fruits.first()}")
        println("Last: ${fruits.last()}")
        println("Get by index: ${fruits[2]}")
        println("Index of Apple: ${fruits.indexOf("Apple")}")
        println("Last index of Apple: ${fruits.lastIndexOf("Apple")}")
        println("Contains Banana: ${fruits.contains("Banana")}")
        
        // Sublist
        println("Sublist (1-3): ${fruits.subList(1, 4)}")
        
        // List transformations
        val upperCaseFruits = fruits.map { it.uppercase() }
        println("Uppercase: $upperCaseFruits")
        
        val longFruits = fruits.filter { it.length > 5 }
        println("Long names: $longFruits")
    }
    
    // ========== SETS ==========
    
    fun demonstrateSets() {
        println("\n=== SETS ===")
        
        // Immutable set
        val readOnlySet = setOf("Kotlin", "Java", "Python", "Kotlin") // Duplicate will be ignored
        println("Read-only set: $readOnlySet")
        
        // Mutable set
        val mutableSet = mutableSetOf("Red", "Green", "Blue")
        println("Original mutable set: $mutableSet")
        
        // Adding elements
        mutableSet.add("Yellow")
        mutableSet.add("Red") // Duplicate, won't be added
        println("After adding: $mutableSet")
        
        // Removing elements
        mutableSet.remove("Green")
        println("After removing: $mutableSet")
        
        // Set operations
        val set1 = setOf(1, 2, 3, 4, 5)
        val set2 = setOf(4, 5, 6, 7, 8)
        
        println("Set 1: $set1")
        println("Set 2: $set2")
        println("Union: ${set1.union(set2)}")
        println("Intersection: ${set1.intersect(set2)}")
        println("Difference (set1 - set2): ${set1.subtract(set2)}")
        
        // LinkedHashSet (maintains insertion order)
        val linkedSet = linkedSetOf("First", "Second", "Third")
        println("LinkedHashSet: $linkedSet")
        
        // HashSet (no guaranteed order)
        val hashSet = hashSetOf("C", "A", "B")
        println("HashSet: $hashSet")
    }
    
    // ========== MAPS ==========
    
    fun demonstrateMaps() {
        println("\n=== MAPS ===")
        
        // Immutable map
        val readOnlyMap = mapOf(
            "name" to "John Doe",
            "age" to 30,
            "city" to "Jakarta"
        )
        println("Read-only map: $readOnlyMap")
        
        // Mutable map
        val mutableMap = mutableMapOf(
            "apple" to 5,
            "banana" to 3,
            "orange" to 8
        )
        println("Original mutable map: $mutableMap")
        
        // Adding/updating elements
        mutableMap["grape"] = 12
        mutableMap.put("apple", 7) // Update existing
        println("After adding/updating: $mutableMap")
        
        // Removing elements
        mutableMap.remove("banana")
        println("After removing: $mutableMap")
        
        // Map operations
        println("Keys: ${mutableMap.keys}")
        println("Values: ${mutableMap.values}")
        println("Entries: ${mutableMap.entries}")
        println("Size: ${mutableMap.size}")
        println("Contains key 'apple': ${mutableMap.containsKey("apple")}")
        println("Contains value 12: ${mutableMap.containsValue(12)}")
        println("Get apple count: ${mutableMap["apple"]}")
        println("Get pear count (with default): ${mutableMap.getOrDefault("pear", 0)}")
        
        // Iterating over map
        println("Iterating over map:")
        for ((key, value) in mutableMap) {
            println("$key: $value")
        }
        
        // Map transformations
        val doubledValues = mutableMap.mapValues { it.value * 2 }
        println("Doubled values: $doubledValues")
        
        val filteredMap = mutableMap.filter { it.value > 5 }
        println("Values > 5: $filteredMap")
    }
    
    // ========== COLLECTION OPERATIONS ==========
    
    fun demonstrateCollectionOperations() {
        println("\n=== COLLECTION OPERATIONS ===")
        
        val numbers = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        println("Original numbers: $numbers")
        
        // Filter operations
        val evenNumbers = numbers.filter { it % 2 == 0 }
        println("Even numbers: $evenNumbers")
        
        val numbersGreaterThan5 = numbers.filter { it > 5 }
        println("Numbers > 5: $numbersGreaterThan5")
        
        // Map operations
        val squared = numbers.map { it * it }
        println("Squared: $squared")
        
        val strings = numbers.map { "Number: $it" }
        println("Strings: $strings")
        
        // Reduce operations
        val sum = numbers.reduce { acc, n -> acc + n }
        println("Sum using reduce: $sum")
        
        val product = numbers.fold(1) { acc, n -> acc * n }
        println("Product using fold: $product")
        
        // Find operations
        val firstEven = numbers.find { it % 2 == 0 }
        println("First even: $firstEven")
        
        val firstGreaterThan15 = numbers.find { it > 15 }
        println("First > 15: $firstGreaterThan15")
        
        // Any, all, none
        println("Any even: ${numbers.any { it % 2 == 0 }}")
        println("All positive: ${numbers.all { it > 0 }}")
        println("None negative: ${numbers.none { it < 0 }}")
        
        // Grouping
        val grouped = numbers.groupBy { if (it % 2 == 0) "even" else "odd" }
        println("Grouped by even/odd: $grouped")
        
        // Sorting
        val randomNumbers = listOf(5, 2, 8, 1, 9, 3)
        println("Random numbers: $randomNumbers")
        println("Sorted: ${randomNumbers.sorted()}")
        println("Sorted descending: ${randomNumbers.sortedDescending()}")
        
        // Distinct
        val duplicates = listOf(1, 2, 2, 3, 3, 3, 4, 4, 4, 4)
        println("With duplicates: $duplicates")
        println("Distinct: ${duplicates.distinct()}")
        
        // Take and drop
        println("Take 3: ${numbers.take(3)}")
        println("Drop 3: ${numbers.drop(3)}")
        println("Take last 3: ${numbers.takeLast(3)}")
        println("Drop last 3: ${numbers.dropLast(3)}")
    }
    
    // ========== SEQUENCES ==========
    
    fun demonstrateSequences() {
        println("\n=== SEQUENCES ===")
        
        // Sequence for lazy evaluation
        val sequence = sequenceOf(1, 2, 3, 4, 5)
        println("Sequence: ${sequence.toList()}")
        
        // Generate sequence
        val fibonacci = generateSequence(1 to 1) { (a, b) -> b to (a + b) }
            .map { it.first }
            .take(10)
        println("Fibonacci (first 10): ${fibonacci.toList()}")
        
        // Infinite sequence
        val naturalNumbers = generateSequence(1) { it + 1 }
        val firstTenEvens = naturalNumbers
            .filter { it % 2 == 0 }
            .take(10)
        println("First 10 even numbers: ${firstTenEvens.toList()}")
        
        // Performance comparison
        val largeList = (1..1000000).toList()
        
        // Using list (eager evaluation)
        val listResult = largeList
            .filter { it % 2 == 0 }
            .map { it * it }
            .take(5)
        println("List result: $listResult")
        
        // Using sequence (lazy evaluation)
        val sequenceResult = largeList.asSequence()
            .filter { it % 2 == 0 }
            .map { it * it }
            .take(5)
            .toList()
        println("Sequence result: $sequenceResult")
    }
    
    // ========== DESTRUCTURING ==========
    
    fun demonstrateDestructuring() {
        println("\n=== DESTRUCTURING ===")
        
        // Destructuring with Pair
        val pair = Pair("Kotlin", 2023)
        val (language, year) = pair
        println("Language: $language, Year: $year")
        
        // Destructuring with Triple
        val triple = Triple("John", "Doe", 30)
        val (firstName, lastName, age) = triple
        println("Name: $firstName $lastName, Age: $age")
        
        // Destructuring with data class
        data class Person(val name: String, val age: Int, val city: String)
        val person = Person("Alice", 25, "Jakarta")
        val (personName, personAge, personCity) = person
        println("Person: $personName, $personAge, $personCity")
        
        // Destructuring in loops
        val people = listOf(
            Person("Bob", 30, "Bandung"),
            Person("Charlie", 35, "Surabaya"),
            Person("Diana", 28, "Medan")
        )
        
        println("People list:")
        for ((name, age, city) in people) {
            println("$name ($age) from $city")
        }
        
        // Destructuring with maps
        val scores = mapOf("Math" to 95, "Science" to 87, "English" to 92)
        println("Scores:")
        for ((subject, score) in scores) {
            println("$subject: $score")
        }
    }
    
    fun runAllDemonstrations() {
        demonstrateArrays()
        demonstrateLists()
        demonstrateSets()
        demonstrateMaps()
        demonstrateCollectionOperations()
        demonstrateSequences()
        demonstrateDestructuring()
    }
}
