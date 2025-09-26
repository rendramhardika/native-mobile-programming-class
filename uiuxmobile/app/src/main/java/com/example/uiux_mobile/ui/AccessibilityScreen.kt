package com.example.uiux_mobile.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.uiux_mobile.ui.theme.UiuxmobileTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccessibilityScreen(
    onBackClick: () -> Unit = {}
) {
    // State untuk contoh interaktif
    var isHighContrast by remember { mutableStateOf(false) }
    var isLargeText by remember { mutableStateOf(false) }
    var isScreenReaderEnabled by remember { mutableStateOf(false) }
    var isDyslexicFont by remember { mutableStateOf(false) }
    var isReducedMotion by remember { mutableStateOf(false) }
    var isColorBlindMode by remember { mutableStateOf(false) }
    var isKeyboardNavigation by remember { mutableStateOf(false) }
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Accessibility & Inclusive Design") },
                navigationIcon = {
                    IconButton(
                        onClick = onBackClick,
                        modifier = Modifier.semantics {
                            contentDescription = "Back to home"
                        }
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = null
                        )
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            // Section 1: Contoh Implementasi WCAG
            AccessibilitySection(
                title = "Contoh Implementasi WCAG",
                description = "Panduan aksesibilitas untuk konten web"
            ) {
                // Contoh 1: Kontras Warna
                AccessibilityItem(
                    icon = Icons.Default.Info,
                    title = "Kontras Warna (4.5:1)",
                    description = "Teks hitam di atas putih (21:1) memenuhi standar AAA",
                    isEnabled = true
                )
                
                // Contoh 2: Dukungan Screen Reader
                AccessibilityItem(
                    icon = Icons.Default.CheckCircle,
                    title = "Label untuk Screen Reader",
                    description = "Semua elemen interaktif memiliki deskripsi yang bermakna",
                    isEnabled = true
                )
                
                // Contoh 3: Ukuran Target Sentuh
                AccessibilityItem(
                    icon = Icons.Default.Info,
                    title = "Ukuran Target Sentuh (48dp)",
                    description = "Tombol dan elemen interaktif mudah disentuh",
                    isEnabled = true
                )
                
                // Contoh 4: Navigasi Keyboard
                AccessibilityItem(
                    icon = Icons.Default.Info,
                    title = "Navigasi dengan Keyboard",
                    description = "Semua fungsi dapat diakses menggunakan keyboard",
                    isEnabled = true
                )
            }
            
            Spacer(modifier = Modifier.height(24.dp))
            
            // Section 2: Penyesuaian Visual
            AccessibilitySection(
                title = "Penyesuaian Visual",
                description = "Sesuaikan tampilan untuk kebutuhan pengguna"
            ) {
                // Mode Kontras Tinggi
                AccessibilityToggleItem(
                    icon = Icons.Default.Info,
                    title = "Mode Kontras Tinggi",
                    description = "Meningkatkan visibilitas teks dan elemen UI",
                    isChecked = isHighContrast,
                    onCheckedChange = { isHighContrast = it }
                )
                
                // Teks Besar
                AccessibilityToggleItem(
                    icon = Icons.Default.Info,
                    title = "Teks Besar",
                    description = "Meningkatkan ukuran teks untuk keterbacaan",
                    isChecked = isLargeText,
                    onCheckedChange = { isLargeText = it }
                )
                
                // Font Dyslexic
                AccessibilityToggleItem(
                    icon = Icons.Default.Info,
                    title = "Font Ramah Disleksia",
                    description = "Menggunakan font yang mudah dibaca pengguna disleksia",
                    isChecked = isDyslexicFont,
                    onCheckedChange = { isDyslexicFont = it }
                )
                
                // Animasi Berkurang
                AccessibilityToggleItem(
                    icon = Icons.Default.Info,
                    title = "Animasi Berkurang",
                    description = "Mengurangi gerakan untuk pengguna yang sensitif",
                    isChecked = isReducedMotion,
                    onCheckedChange = { isReducedMotion = it }
                )
            }
            
            Spacer(modifier = Modifier.height(24.dp))
            
            // Section 3: Navigasi & Interaksi Inklusif
            AccessibilitySection(
                title = "Navigasi & Interaksi Inklusif",
                description = "Berbagai cara untuk berinteraksi dengan aplikasi"
            ) {
                // Mode Screen Reader
                AccessibilityToggleItem(
                    icon = Icons.Default.CheckCircle,
                    title = "Mode Pembaca Layar",
                    description = "Optimalkan untuk pembaca layar seperti TalkBack",
                    isChecked = isScreenReaderEnabled,
                    onCheckedChange = { isScreenReaderEnabled = it }
                )
                
                // Navigasi Gestur
                AccessibilityItem(
                    icon = Icons.Default.Info,
                    title = "Navigasi Gestur",
                    description = "Navigasi dengan gerakan tangan sederhana",
                    isEnabled = true
                )
                
                // Perintah Suara
                AccessibilityItem(
                    icon = Icons.Default.Info,
                    title = "Kontrol Suara",
                    description = "Kendalikan aplikasi menggunakan perintah suara",
                    isEnabled = true
                )
                
                // Navigasi dengan Switch Control
                AccessibilityItem(
                    icon = Icons.Default.Info,
                    title = "Dukungan Switch Control",
                    description = "Navigasi menggunakan perangkat switch eksternal",
                    isEnabled = true
                )
            }
            
            Spacer(modifier = Modifier.height(24.dp))
            
            // Section 4: Prinsip WCAG 2.1
            AccessibilitySection(
                title = "Prinsip Dasar WCAG 2.1",
                description = "Empat pilar aksesibilitas digital"
            ) {
                // 1. Dapat Dipersepsikan
                AccessibilityPrinciple(
                    icon = Icons.Default.Info,
                    title = "Dapat Dipersepsikan",
                    description = "Informasi dan komponen antarmuka harus dapat disajikan dalam cara yang dapat dipersepsikan oleh pengguna."
                )
                
                // 2. Dapat Dioperasikan
                AccessibilityPrinciple(
                    icon = Icons.Default.Info,
                    title = "Dapat Dioperasikan",
                    description = "Komponen antarmuka dan navigasi harus dapat dioperasikan oleh semua pengguna."
                )
                
                // 3. Dapat Dipahami
                AccessibilityPrinciple(
                    icon = Icons.Default.Info,
                    title = "Dapat Dipahami",
                    description = "Informasi dan pengoperasian antarmuka harus dapat dipahami oleh pengguna."
                )
                
                // 4. Kuat
                AccessibilityPrinciple(
                    icon = Icons.Default.Info,
                    title = "Kuat",
                    description = "Konten harus cukup kuat untuk dapat diinterpretasikan dengan andal oleh berbagai agen pengguna, termasuk teknologi pendukung."
                )
            }
            
            // Section 5: Contoh Praktik Terbaik
            AccessibilitySection(
                title = "Praktik Terbaik Inklusif",
                description = "Contoh implementasi desain inklusif"
            ) {
                // Contoh 1: Alt Text
                AccessibilityItem(
                    icon = Icons.Default.Info,
                    title = "Teks Alternatif",
                    description = "Selalu sertakan teks alternatif untuk gambar informatif",
                    isEnabled = true
                )
                
                // Contoh 2: Fokus Keyboard
                AccessibilityItem(
                    icon = Icons.Default.ArrowForward,
                    title = "Indikator Fokus",
                    description = "Tampilkan indikator fokus yang jelas untuk navigasi keyboard",
                    isEnabled = true
                )
                
                // Contoh 3: Kontras Warna
                AccessibilityItem(
                    icon = Icons.Default.Info,
                    title = "Tidak Hanya Warna",
                    description = "Jangan hanya mengandalkan warna untuk menyampaikan informasi",
                    isEnabled = true
                )
                
                // Contoh 4: Struktur Dokumen
                AccessibilityItem(
                    icon = Icons.Default.List,
                    title = "Struktur yang Bermakna",
                    description = "Gunakan heading dan landmark untuk navigasi yang mudah",
                    isEnabled = true
                )
            }
        }
    }
}

@Composable
private fun AccessibilitySection(
    title: String,
    description: String,
    content: @Composable () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(bottom = 4.dp)
        )
        
        Text(
            text = description,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(bottom = 12.dp)
        )
        
        Surface(
            shape = MaterialTheme.shapes.medium,
            color = MaterialTheme.colorScheme.surfaceVariant,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(8.dp)
            ) {
                content()
            }
        }
    }
}

@Composable
private fun AccessibilityItem(
    icon: ImageVector,
    title: String,
    description: String,
    isEnabled: Boolean
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = if (isEnabled) MaterialTheme.colorScheme.primary 
                  else MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.38f),
            modifier = Modifier.size(24.dp)
        )
        
        Spacer(modifier = Modifier.width(16.dp))
        
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.bodyLarge,
                color = if (isEnabled) MaterialTheme.colorScheme.onSurface 
                       else MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f)
            )
            
            Text(
                text = description,
                style = MaterialTheme.typography.bodyMedium,
                color = if (isEnabled) MaterialTheme.colorScheme.onSurfaceVariant 
                       else MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.38f)
            )
        }
        
        if (isEnabled) {
            Icon(
                imageVector = Icons.Default.CheckCircle,
                contentDescription = "Enabled",
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(24.dp)
            )
        } else {
            Icon(
                imageVector = Icons.Default.Info,
                contentDescription = "Info",
                tint = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.38f),
                modifier = Modifier.size(24.dp)
            )
        }
    }
}

@Composable
private fun AccessibilityToggleItem(
    icon: ImageVector,
    title: String,
    description: String,
    isChecked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .toggleable(
                value = isChecked,
                onValueChange = onCheckedChange,
                role = Role.Switch
            )
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = if (isChecked) MaterialTheme.colorScheme.primary 
                  else MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.size(24.dp)
        )
        
        Spacer(modifier = Modifier.width(16.dp))
        
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.bodyLarge,
                color = if (isChecked) MaterialTheme.colorScheme.onSurface 
                       else MaterialTheme.colorScheme.onSurfaceVariant
            )
            
            Text(
                text = description,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
        
        Switch(
            checked = isChecked,
            onCheckedChange = null, // null because we're handling the toggle in the parent Row
            modifier = Modifier.semantics {}
        )
    }
}

@Composable
private fun AccessibilityPrinciple(
    icon: ImageVector,
    title: String,
    description: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Surface(
            color = MaterialTheme.colorScheme.primaryContainer,
            shape = CircleShape,
            modifier = Modifier.size(40.dp)
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onPrimaryContainer,
                modifier = Modifier.padding(8.dp)
            )
        }
        
        Spacer(modifier = Modifier.width(16.dp))
        
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurface
            )
            
            Text(
                text = description,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AccessibilityScreenPreview() {
    UiuxmobileTheme {
        AccessibilityScreen()
    }
}
