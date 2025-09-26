# UI/UX Mobile - Android Project

Ini adalah proyek Android terpisah yang berfokus pada implementasi prinsip-prinsip desain antarmuka dan pengalaman pengguna (UI/UX) untuk aplikasi mobile.

## Struktur Proyek
- `app/`: Modul aplikasi utama yang berisi kode sumber dan resource
  - `src/main/`: Kode sumber utama aplikasi
    - `java/`: Kode sumber Kotlin/Java
    - `res/`: Resource aplikasi
      - `anim/`: File animasi
      - `drawable/`: Gambar dan vector drawables
      - `navigation/`: File navigasi
      - `transition/`: File transisi
      - `values/`: Resource untuk themes, styles, colors, dll.
    - `AndroidManifest.xml`: Konfigurasi aplikasi
- `build.gradle.kts`: File konfigurasi build untuk proyek ini
- `settings.gradle.kts`: File konfigurasi settings untuk project Gradle
- `gradle/`: Direktori yang berisi wrapper Gradle

## Topik yang Dibahas
### Material Design
- Material Components (Buttons, Cards, Chips, dll.)
- Material Theming (warna, tipografi, bentuk)
- Material Motion
- Dark Theme

### Animasi & Transisi
- Property Animation
- View Animation
- MotionLayout
- Shared Element Transition
- AnimatedVectorDrawable

### Navigasi
- Navigation Component
- Bottom Navigation
- Navigation Drawer
- Deep Linking

### Praktik UI/UX Terbaik
- Desain yang responsif dan adaptif
- Aksesibilitas
- Desain untuk berbagai ukuran layar
- Pola navigasi yang intuitif
- Umpan balik visual

## Cara Mengimpor ke Android Studio
1. Buka Android Studio
2. Pilih "Open an Existing Project"
3. Arahkan ke folder `uiuxmobile` (bukan folder parent-nya)
4. Pastikan Android Studio mendeteksi project dengan benar (ikon Android Studio akan muncul di sebelah nama folder)
5. Klik tombol "Open"
6. Tunggu proses sinkronisasi Gradle selesai (lihat progress di bagian bawah jendela Android Studio)
7. Setelah selesai, cari tombol Run (ikon segitiga hijau) di toolbar atas
8. Pilih perangkat emulator atau sambungkan perangkat fisik dengan USB debugging aktif
9. Pastikan perangkat/emulator mendukung fitur yang dibutuhkan (seperti animasi hardware)
10. Klik tombol Run untuk menginstall dan menjalankan aplikasi
11. Jelajahi berbagai contoh UI/UX yang tersedia di aplikasi

## Dependensi

Project ini menggunakan library dan plugin berikut:

### Plugin
- Android Gradle Plugin: 8.1.2
- Kotlin: 2.0.21
- Kotlin Compose Plugin: 2.0.21

### Library Utama
- AndroidX Core KTX: 1.17.0
- AndroidX Lifecycle Runtime KTX: 2.9.3
- AndroidX Activity Compose: 1.11.0
- Jetpack Compose BOM: 2024.09.00
  - Compose UI
  - Compose UI Graphics
  - Compose UI Tooling
  - Compose Material 3
  - Compose UI Test JUnit4
  - Compose UI Test Manifest
- Navigation Compose: 2.9.4
- UI Tooling: 1.9.1
- UI Tooling Preview: 1.9.1

### Testing
- JUnit: 4.13.2
- AndroidX Test Ext JUnit: 1.3.0
- AndroidX Test Espresso Core: 3.7.0

## Persyaratan
- Android Studio Flamingo (2022.2.1) atau yang lebih baru
- JDK 17
- Android SDK 33 (Android 13) atau yang lebih baru
- Gradle 8.0
