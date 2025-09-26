# Lifecycle - Android Project

Ini adalah proyek Android terpisah yang berfokus pada demonstrasi dan implementasi siklus hidup (lifecycle) komponen Android, seperti Activity dan Fragment. 

## Struktur Proyek
- `app/`: Modul aplikasi utama yang berisi kode sumber dan resource
  - `src/main/`: Kode sumber utama aplikasi
    - `java/`: Kode sumber Kotlin/Java
    - `res/`: Resource aplikasi (layout, drawable, dll)
    - `AndroidManifest.xml`: Konfigurasi aplikasi
- `build.gradle.kts`: File konfigurasi build untuk proyek ini
- `settings.gradle.kts`: File konfigurasi settings untuk project Gradle
- `gradle/`: Direktori yang berisi wrapper Gradle

## Fitur Utama
- Demonstrasi siklus hidup Activity
- Demonstrasi siklus hidup Fragment
- Contoh penggunaan ViewModel dan LiveData untuk menangani perubahan state
- Implementasi LifecycleObserver

## Cara Mengimpor ke Android Studio
1. Buka Android Studio
2. Pilih "Open an Existing Project"
3. Arahkan ke folder `Lifecycle` (bukan folder parent-nya)
4. Pastikan Android Studio mendeteksi project dengan benar (ikon Android Studio akan muncul di sebelah nama folder)
5. Klik tombol "Open"
6. Tunggu proses sinkronisasi Gradle selesai (lihat progress di bagian bawah jendela Android Studio)
7. Setelah selesai, cari tombol Run (ikon segitiga hijau) di toolbar atas
8. Pilih perangkat emulator atau sambungkan perangkat fisik dengan USB debugging aktif
9. Klik tombol Run untuk menginstall dan menjalankan aplikasi

## Dependensi

Project ini menggunakan library dan plugin berikut:

### Plugin
- Android Gradle Plugin: 8.1.2
- Kotlin: 1.9.0
- Kotlin Compose Plugin: 1.9.0

### Library Utama
- AndroidX Core KTX: 1.12.0
- AndroidX Lifecycle Runtime KTX: 2.6.2
- AndroidX Activity Compose: 1.8.2
- Jetpack Compose BOM: 2023.08.00
  - Compose UI
  - Compose UI Graphics
  - Compose UI Tooling
  - Compose Material 3
  - Compose UI Test JUnit4
  - Compose UI Test Manifest

### Testing
- JUnit: 4.13.2
- AndroidX Test Ext JUnit: 1.1.5
- AndroidX Test Espresso Core: 3.5.1

## Persyaratan
- Android Studio Flamingo (2022.2.1) atau yang lebih baru
- JDK 17
- Android SDK 33 (Android 13) atau yang lebih baru
- Gradle 8.0
