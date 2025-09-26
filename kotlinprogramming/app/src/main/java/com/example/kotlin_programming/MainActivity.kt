package com.example.kotlin_programming

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kotlin_programming.ui.theme.KotlinprogrammingTheme

class MainActivity : ComponentActivity() {
    
    private val kotlinBasics = KotlinBasics()
    private val kotlinOOP = KotlinOOPDemo()
    private val kotlinControlFlow = KotlinControlFlow()
    private val kotlinCollections = KotlinCollections()
    private val kotlinAdvanced = KotlinAdvanced()
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        
        // Run all Kotlin demonstrations in the background
        runKotlinDemonstrations()
        
        setContent {
            KotlinprogrammingTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    KotlinLearningApp(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
    
    private fun runKotlinDemonstrations() {
        Log.d("KotlinDemo", "=== STARTING KOTLIN PROGRAMMING DEMONSTRATIONS ===")
        
        try {
            // Run basic demonstrations
            kotlinBasics.demonstrateVariablesAndDataTypes()
            kotlinBasics.demonstrateFunctions()
            kotlinBasics.demonstrateLambdas()
            
            // Run OOP demonstrations
            kotlinOOP.runAllDemonstrations()
            
            // Run control flow demonstrations
            kotlinControlFlow.runAllDemonstrations()
            
            // Run collections demonstrations
            kotlinCollections.runAllDemonstrations()
            
            // Run advanced demonstrations
            kotlinAdvanced.runAllDemonstrations()
            
            Log.d("KotlinDemo", "=== ALL DEMONSTRATIONS COMPLETED ===")
        } catch (e: Exception) {
            Log.e("KotlinDemo", "Error running demonstrations: ${e.message}")
        }
    }
}

@Composable
fun KotlinLearningApp(modifier: Modifier = Modifier) {
    var selectedDemo by remember { mutableStateOf("basics") }
    
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Header
        Text(
            text = "Kotlin Programming",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        
        Text(
            text = "Belajar Dasar-Dasar Pemrograman Kotlin",
            fontSize = 16.sp,
            modifier = Modifier.padding(bottom = 24.dp)
        )
        
        // Demo selection buttons
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            DemoButton(
                text = "Basics",
                isSelected = selectedDemo == "basics",
                onClick = { selectedDemo = "basics" },
                modifier = Modifier.weight(1f)
            )
            DemoButton(
                text = "OOP",
                isSelected = selectedDemo == "oop",
                onClick = { selectedDemo = "oop" },
                modifier = Modifier.weight(1f)
            )
        }
        
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            DemoButton(
                text = "Control Flow",
                isSelected = selectedDemo == "control",
                onClick = { selectedDemo = "control" },
                modifier = Modifier.weight(1f)
            )
            DemoButton(
                text = "Collections",
                isSelected = selectedDemo == "collections",
                onClick = { selectedDemo = "collections" },
                modifier = Modifier.weight(1f)
            )
        }
        
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            DemoButton(
                text = "Advanced",
                isSelected = selectedDemo == "advanced",
                onClick = { selectedDemo = "advanced" },
                modifier = Modifier.fillMaxWidth(0.5f)
            )
        }
        
        // Content based on selection
        Card(
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                when (selectedDemo) {
                    "basics" -> BasicsContent()
                    "oop" -> OOPContent()
                    "control" -> ControlFlowContent()
                    "collections" -> CollectionsContent()
                    "advanced" -> AdvancedContent()
                }
            }
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Footer
        Text(
            text = "Lihat Logcat untuk output dari semua demonstrasi",
            fontSize = 12.sp,
            modifier = Modifier.padding(8.dp)
        )
    }
}

@Composable
fun DemoButton(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isSelected) MaterialTheme.colorScheme.primary 
                           else MaterialTheme.colorScheme.secondary
        )
    ) {
        Text(text, fontSize = 12.sp)
    }
}

@Composable
fun BasicsContent() {
    Column {
        Text(
            text = "Kotlin Basics",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Text("• Variables & Data Types")
        Text("• Constants (val vs var)")
        Text("• String Templates")
        Text("• Nullable Types")
        Text("• Functions & Parameters")
        Text("• Lambda Expressions")
        Text("• Higher-order Functions")
    }
}

@Composable
fun OOPContent() {
    Column {
        Text(
            text = "Object-Oriented Programming",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Text("• Classes & Constructors")
        Text("• Data Classes")
        Text("• Inheritance")
        Text("• Abstract Classes")
        Text("• Interfaces")
        Text("• Object & Singleton")
        Text("• Enum Classes")
        Text("• Sealed Classes")
    }
}

@Composable
fun ControlFlowContent() {
    Column {
        Text(
            text = "Control Flow",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Text("• If Expressions")
        Text("• When Expressions")
        Text("• For Loops")
        Text("• While Loops")
        Text("• Break & Continue")
        Text("• Exception Handling")
        Text("• Return & Jump")
    }
}

@Composable
fun CollectionsContent() {
    Column {
        Text(
            text = "Collections & Data Structures",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Text("• Arrays")
        Text("• Lists (Mutable & Immutable)")
        Text("• Sets")
        Text("• Maps")
        Text("• Collection Operations")
        Text("• Sequences")
        Text("• Destructuring")
    }
}

@Composable
fun AdvancedContent() {
    Column {
        Text(
            text = "Advanced Kotlin",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Text("• Null Safety")
        Text("• Extension Functions")
        Text("• Generics")
        Text("• Coroutine Concepts")
        Text("• Delegation")
        Text("• Inline Functions")
        Text("• Scope Functions")
    }
}

@Preview(showBackground = true)
@Composable
fun KotlinLearningAppPreview() {
    KotlinprogrammingTheme {
        KotlinLearningApp()
    }
}