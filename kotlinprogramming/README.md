# Kotlin Programming - Android Project

Ini adalah proyek Android terpisah yang berisi berbagai contoh dan latihan pemrograman Kotlin untuk pengembangan Android.

## Struktur Proyek
- `app/`: Modul aplikasi utama yang berisi kode sumber dan resource
  - `src/main/`: Kode sumber utama aplikasi
    - `java/`: Kode sumber Kotlin
    - `res/`: Resource aplikasi (layout, drawable, dll)
    - `AndroidManifest.xml`: Konfigurasi aplikasi
- `build.gradle.kts`: File konfigurasi build untuk proyek ini
- `settings.gradle.kts`: File konfigurasi settings untuk project Gradle
- `gradle/`: Direktori yang berisi wrapper Gradle

## Topik yang Dibahas
### Dasar-dasar Kotlin
- Variabel dan tipe data
- Control flow (if, when, loops)
- Collections
- Null safety
- Functions

### Pemrograman Berorientasi Objek
- Class dan object
- Inheritance
- Interface
- Data classes
- Sealed classes

### Fitur Lanjutan
- Extension functions
- Higher-order functions dan lambda
- Coroutines
- Scope functions (let, run, with, apply, also)

## Cara Mengimpor ke Android Studio
1. Buka Android Studio
2. Pilih "Open an Existing Project"
3. Arahkan ke folder `kotlinprogramming` (bukan folder parent-nya)
4. Pastikan Android Studio mendeteksi project dengan benar (ikon Android Studio akan muncul di sebelah nama folder)
5. Klik tombol "Open"
6. Tunggu proses sinkronisasi Gradle selesai (lihat progress di bagian bawah jendela Android Studio)
7. Untuk menjalankan contoh kode:
   - Buka file Kotlin yang ingin dipelajari di direktori `app/src/main/java/`
   - Klik kanan pada file atau di dalam editor
   - Pilih "Run 'NamaFileKt'" (atau tekan Shift+F10)
   - Atau gunakan tombol Run (ikon segitiga hijau) di toolbar atas untuk menjalankan aplikasi penuh

## Dependensi

Project ini menggunakan library dan plugin berikut:

### Plugin
- Android Gradle Plugin: 8.1.2
- Kotlin: 2.0.21
- Kotlin Compose Plugin: 2.0.21

### Library Utama
- AndroidX Core KTX: 1.17.0
- AndroidX Lifecycle Runtime KTX: 2.9.2
- AndroidX Activity Compose: 1.10.1
- Jetpack Compose BOM: 2024.09.00
  - Compose UI
  - Compose UI Graphics
  - Compose UI Tooling
  - Compose Material 3
  - Compose UI Test JUnit4
  - Compose UI Test Manifest

### Testing
- JUnit: 4.13.2
- AndroidX Test Ext JUnit: 1.3.0
- AndroidX Test Espresso Core: 3.7.0

## Persyaratan
- Android Studio Flamingo (2022.2.1) atau yang lebih baru
- JDK 17
- Android SDK 33 (Android 13) atau yang lebih baru
- Gradle 8.0
