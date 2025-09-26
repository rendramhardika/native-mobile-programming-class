# Mobile Layout - Android Project

Ini adalah proyek Android terpisah yang berfokus pada berbagai contoh implementasi tata letak (layout) dan komponen antarmuka pengguna di Android.

## Struktur Proyek
- `app/`: Modul aplikasi utama yang berisi kode sumber dan resource
  - `src/main/`: Kode sumber utama aplikasi
    - `java/`: Kode sumber Kotlin/Java
    - `res/layout/`: File XML untuk tata letak
    - `res/values/`: Resource untuk styles, colors, dimens, dll.
    - `AndroidManifest.xml`: Konfigurasi aplikasi
- `build.gradle.kts`: File konfigurasi build untuk proyek ini
- `settings.gradle.kts`: File konfigurasi settings untuk project Gradle
- `gradle/`: Direktori yang berisi wrapper Gradle

## Topik yang Dibahas
### Layout Manager
- ConstraintLayout
- LinearLayout
- RelativeLayout
- FrameLayout
- GridLayout
- CoordinatorLayout

### Komponen UI
- RecyclerView dengan berbagai LayoutManager
- ViewPager2
- CardView
- Material Components
- Custom Views
- Responsive Design untuk berbagai ukuran layar

### Praktik Terbaik
- Penggunaan ConstraintLayout secara optimal
- Penggunaan ViewBinding/DataBinding
- Mendukung orientasi layar portrait/landscape
- Mendukung berbagai ukuran layar dan density

## Cara Mengimpor ke Android Studio
1. Buka Android Studio
2. Pilih "Open an Existing Project"
3. Arahkan ke folder `mobilelayout` (bukan folder parent-nya)
4. Pastikan Android Studio mendeteksi project dengan benar (ikon Android Studio akan muncul di sebelah nama folder)
5. Klik tombol "Open"
6. Tunggu proses sinkronisasi Gradle selesai (lihat progress di bagian bawah jendela Android Studio)
7. Setelah selesai, cari tombol Run (ikon segitiga hijau) di toolbar atas
8. Pilih perangkat emulator atau sambungkan perangkat fisik dengan USB debugging aktif
9. Klik tombol Run untuk menginstall dan menjalankan aplikasi
10. Untuk melihat contoh layout tertentu, navigasi melalui aplikasi sesuai dengan contoh yang tersedia

## Dependensi

Project ini menggunakan library dan plugin berikut:

### Plugin
- Android Gradle Plugin: 8.1.2
- Kotlin: 2.0.21
- Kotlin Compose Plugin: 2.0.21

### Library Utama
- AndroidX Core KTX: 1.17.0
- AndroidX AppCompat: 1.7.1
- AndroidX Activity KTX: 1.11.0
- AndroidX Lifecycle:
  - Lifecycle Runtime KTX: 2.9.3
  - Lifecycle ViewModel KTX: 2.9.3
- Jetpack Compose BOM: 2024.09.00
  - Compose UI
  - Compose UI Graphics
  - Compose UI Tooling
  - Compose Material 3
  - Compose UI Test JUnit4
  - Compose UI Test Manifest
- ConstraintLayout: 2.2.1
- Material Design Components: 1.12.0

### Testing
- JUnit: 4.13.2
- AndroidX Test Ext JUnit: 1.3.0
- AndroidX Test Espresso Core: 3.7.0

## Persyaratan
- Android Studio Flamingo (2022.2.1) atau yang lebih baru
- JDK 17
- Android SDK 33 (Android 13) atau yang lebih baru
- Gradle 8.0
