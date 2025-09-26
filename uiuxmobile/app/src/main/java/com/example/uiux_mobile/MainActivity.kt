package com.example.uiux_mobile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.uiux_mobile.ui.AccessibilityScreen
import com.example.uiux_mobile.ui.DesignGuidelinesScreen
import com.example.uiux_mobile.ui.DesignPrinciplesScreen
import com.example.uiux_mobile.ui.MicrointeractionsScreen
import com.example.uiux_mobile.ui.NavigationPatternsScreen
import com.example.uiux_mobile.ui.GamificationScreen
import com.example.uiux_mobile.ui.theme.UiuxmobileTheme

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object DesignPrinciples : Screen("design_principles")
    object DesignGuidelines : Screen("design_guidelines")
    object NavigationPatterns : Screen("navigation_patterns")
    object Microinteractions : Screen("microinteractions")
    object Accessibility : Screen("accessibility")
    object Gamification : Screen("gamification")
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UiuxmobileTheme {
                val navController = rememberNavController()
                
                NavHost(
                    navController = navController,
                    startDestination = Screen.Home.route
                ) {
                    composable(Screen.Home.route) {
                        HomeScreen(
                            onNavigateToDesignPrinciples = { navController.navigate(Screen.DesignPrinciples.route) },
                            onNavigateToDesignGuidelines = { navController.navigate(Screen.DesignGuidelines.route) },
                            onNavigateToNavigationPatterns = { navController.navigate(Screen.NavigationPatterns.route) },
                            onNavigateToMicrointeractions = { navController.navigate(Screen.Microinteractions.route) },
                            onNavigateToAccessibility = { navController.navigate(Screen.Accessibility.route) },
                            onNavigateToGamification = { navController.navigate(Screen.Gamification.route) }
                        )
                    }
                    composable(Screen.DesignPrinciples.route) {
                        DesignPrinciplesScreen(
                            onBackClick = { navController.popBackStack() }
                        )
                    }
                    composable(Screen.DesignGuidelines.route) {
                        DesignGuidelinesScreen(
                            onBackClick = { navController.popBackStack() }
                        )
                    }
                    composable(Screen.NavigationPatterns.route) {
                        NavigationPatternsScreen(
                            onBackClick = { navController.popBackStack() }
                        )
                    }
                    composable(Screen.Microinteractions.route) {
                        MicrointeractionsScreen(
                            onBackClick = { navController.popBackStack() }
                        )
                    }
                    composable(Screen.Accessibility.route) {
                        AccessibilityScreen(
                            onBackClick = { navController.popBackStack() }
                        )
                    }
                    composable(Screen.Gamification.route) {
                        GamificationScreen(
                            onBackClick = { navController.popBackStack() }
                        )
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onNavigateToDesignPrinciples: () -> Unit,
    onNavigateToDesignGuidelines: () -> Unit,
    onNavigateToNavigationPatterns: () -> Unit,
    onNavigateToMicrointeractions: () -> Unit,
    onNavigateToAccessibility: () -> Unit,
    onNavigateToGamification: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("UI/UX Mobile") }
            )
        }
    ) { padding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Selamat Datang di Aplikasi UI/UX",
                style = MaterialTheme.typography.headlineMedium,
                textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                modifier = Modifier.padding(bottom = 24.dp)
            )
            
            Column(
                modifier = Modifier.fillMaxWidth(0.8f),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Button(
                    onClick = onNavigateToDesignPrinciples,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Lihat Prinsip Desain")
                }
                
                Button(
                    onClick = onNavigateToDesignGuidelines,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Mobile Design Guidelines")
                }
                
                Button(
                    onClick = onNavigateToNavigationPatterns,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Navigation Patterns")
                }
                
                Button(
                    onClick = onNavigateToMicrointeractions,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Microinteractions")
                }
                
                Button(
                    onClick = onNavigateToAccessibility,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Aksesibilitas")
                }
                
                Button(
                    onClick = onNavigateToGamification,
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.tertiaryContainer,
                        contentColor = MaterialTheme.colorScheme.onTertiaryContainer
                    )
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            Icons.Default.Star,
                            contentDescription = null,
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Gamification")
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    UiuxmobileTheme {
        HomeScreen(
            onNavigateToDesignPrinciples = {},
            onNavigateToDesignGuidelines = {},
            onNavigateToNavigationPatterns = {},
            onNavigateToMicrointeractions = {},
            onNavigateToAccessibility = {},
            onNavigateToGamification = {}
        )
    }
}