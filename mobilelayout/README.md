# Mobile Layout - Android Project

Ini adalah proyek Android terpisah yang berfokus pada berbagai contoh implementasi tata letak (layout) dan komponen antarmuka pengguna di Android. Proyek ini mencakup implementasi dengan XML tradisional dan Jetpack Compose.

## Struktur Proyek
- `app/`: Modul aplikasi utama yang berisi kode sumber dan resource
  - `src/main/`: Kode sumber utama aplikasi
    - `java/com/example/mobile_layout/`: Kode sumber Kotlin
      - `adapter/`: Adapter untuk RecyclerView dan komponen lainnya
      - `model/`: Model data untuk aplikasi
      - `navigation/`: Konfigurasi navigasi untuk Jetpack Compose
      - `screens/`: Implementasi layar dengan Jetpack Compose
      - `screennative/`: Implementasi layar dengan XML tradisional
    - `res/layout/`: File XML untuk tata letak tradisional
    - `res/values/`: Resource untuk styles, colors, strings, dll.
    - `AndroidManifest.xml`: Konfigurasi aplikasi
- `build.gradle.kts`: File konfigurasi build untuk proyek ini
- `settings.gradle.kts`: File konfigurasi settings untuk project Gradle
- `gradle/`: Direktori yang berisi wrapper Gradle

## Topik yang Dibahas

### Implementasi XML Tradisional
#### Layout Manager
- ConstraintLayout
- LinearLayout
- RelativeLayout
- FrameLayout

#### Komponen UI
- RecyclerView dengan berbagai implementasi:
  - RecyclerView dasar dengan logging untuk memvisualisasikan recycling
  - Timeline Instagram-like dengan multiple view types (post dan iklan)
- CardView
- Material Components
- Toolbar dan Navigation

### Implementasi Jetpack Compose
#### Layout
- Column dan Row (pengganti LinearLayout)
- Box dengan positioning (pengganti RelativeLayout)
- ConstraintLayout dengan Compose

#### Komponen UI
- LazyColumn (pengganti RecyclerView)
  - Implementasi dasar dengan logging untuk memvisualisasikan komposisi
  - Timeline Instagram-like dengan multiple item types
- Card
- Material 3 Components
- TopAppBar dan Navigation

### Praktik Terbaik
- Penggunaan ConstraintLayout secara optimal
- Logging komprehensif untuk memahami lifecycle dan recycling
- Penanganan multiple view types
- Implementasi pola desain yang sama dengan pendekatan berbeda (XML vs Compose)

## Fitur Utama

### RecyclerView dengan Visualisasi Recycling (XML)
- Implementasi RecyclerView dasar dengan ViewHolder ID unik
- Logging komprehensif untuk memvisualisasikan proses recycling
- Log untuk onCreate, onBind, dan onRecycled ViewHolder
- Visualisasi penggunaan kembali ViewHolder saat scrolling

### Timeline Instagram-like (XML)
- Implementasi timeline dengan dua jenis item: post dan iklan
- Penggunaan sealed class untuk model data
- Iklan muncul setelah setiap 4 postingan
- Penanganan multiple view types dengan getItemViewType
- Perhitungan posisi yang tepat untuk menyisipkan iklan

### LazyColumn dengan Visualisasi Komposisi (Compose)
- Implementasi LazyColumn sebagai pengganti RecyclerView
- Logging untuk memvisualisasikan proses komposisi dan rekomposisi
- Penjelasan perbedaan antara RecyclerView tradisional dan Compose
- Visualisasi item yang terlihat saat scrolling

### Timeline Instagram-like (Compose)
- Implementasi timeline dengan sealed class untuk post dan iklan
- Pendekatan deklaratif untuk menangani multiple item types
- Pembuatan daftar gabungan untuk postingan dan iklan
- Logging untuk memvisualisasikan struktur timeline

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

## Navigasi dan Struktur Aplikasi

### Aplikasi Native (XML)
1. **MainActivity**: Halaman utama dengan tombol navigasi ke berbagai contoh layout
   - Linear Layout Example
   - Relative Layout Example
   - Constraint Layout Example
   - RecyclerView Example
   - Instagram Timeline

2. **LinearLayoutActivity**: Contoh penggunaan LinearLayout (vertical dan horizontal)

3. **RelativeLayoutActivity**: Contoh penggunaan RelativeLayout untuk membuat tampilan media sosial

4. **ConstraintLayoutActivity**: Contoh penggunaan ConstraintLayout untuk membuat tampilan detail produk

5. **SimpleRecyclerViewActivity**: Contoh implementasi RecyclerView dasar dengan logging

6. **TimelineActivity**: Implementasi timeline Instagram-like dengan multiple view types

### Aplikasi Compose
1. **MainScreen**: Halaman utama dengan tombol navigasi ke berbagai contoh layout
   - Linear Layout Example
   - Relative Layout Example
   - Constraint Layout Example
   - RecyclerView Example
   - Instagram Timeline

2. **LinearLayoutScreen**: Implementasi LinearLayout dengan Column dan Row

3. **RelativeLayoutScreen**: Implementasi RelativeLayout dengan Box dan positioning

4. **ConstraintLayoutScreen**: Implementasi ConstraintLayout dengan ConstraintLayout Compose

5. **RecyclerViewScreen**: Implementasi RecyclerView dengan LazyColumn dan logging

6. **TimelineScreen**: Implementasi timeline Instagram-like dengan LazyColumn

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
- ConstraintLayout Compose: 1.1.0-alpha13
- Material Design Components: 1.12.0
- Navigation Compose: 2.7.7
- CircleImageView: 3.1.0 (untuk avatar di timeline)

### Testing
- JUnit: 4.13.2
- AndroidX Test Ext JUnit: 1.3.0
- AndroidX Test Espresso Core: 3.7.0

## Persyaratan
- Android Studio Flamingo (2022.2.1) atau yang lebih baru
- JDK 17
- Android SDK 33 (Android 13) atau yang lebih baru
- Gradle 8.0
