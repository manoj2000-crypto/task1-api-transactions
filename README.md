# 🧾 Task 1 - API Integration with Transactions

This Android app demonstrates secure login, biometric authentication, and transaction fetching using a REST API. It is developed in **Java** using the **MVVM architecture**, **Retrofit**, and **Material UI components**.

---

## 🚀 Project Overview

**Features:**

- 🔐 User login using `POST /login` endpoint  
- 🔑 Secure token storage using `EncryptedSharedPreferences`  
- 👆 Biometric authentication using `BiometricPrompt` API  
- 📦 Fetch and display transaction list from `GET /transactions`  
- 🛡️ Handles session expiry (401 Unauthorized)  
- 📲 Logout functionality with secure token clearing  
- ✅ MVVM architecture with proper separation of concerns  
- ✨ Material UI components for clean and modern design  

---

## 🛠️ Setup Instructions

### Prerequisites:
- Android Studio Giraffe or newer  
- Java 11  
- Minimum SDK 24 (Android 7.0+)

### Clone the repo:
```bash
git clone https://github.com/manoj2000-crypto/task1-api-transactions.git
cd task1-api-transactions
```

### Open in Android Studio:
1. Open Android Studio.
2. Select **Open an existing project**.
3. Navigate to and select the cloned project folder.

### Run the App:
- Connect an Android device or start an emulator.
- Click **Run > Run 'app'** in Android Studio.

---

## 🧪 Login Credentials

```
Username: admin
Password: A7ge#hu&dt(wer
```

---

## 📦 APK Build Instructions

1. **Build a Debug APK:**
   - In Android Studio, go to **Build > Build Bundle(s)/APK(s) > Build APK(s)**.
   - After the build completes, the APK will be located in:
     ```
     app/build/outputs/apk/debug/app-debug.apk
     ```

2. **Generate a Signed APK:**
   - In Android Studio, navigate to **Build > Generate Signed Bundle/APK**.
   - Follow the wizard:
     - Select **APK** and click **Next**.
     - Provide your keystore information (or create a new one).
     - Choose your build type (release) and click **Finish**.
   - The signed APK will be generated and can be found in the specified output folder.

---

## 📂 Folder Structure

```
in.theimperative.task1apitransaction
├── data
│   ├── model              # Data models (Login, Transaction)
│   ├── network            # Retrofit API service and client
│   └── repository         # Auth and Transaction repositories
├── ui
│   ├── login              # LoginActivity and ViewModel
│   ├── biometric          # BiometricActivity
│   └── transaction        # TransactionActivity, Adapter, ViewModel
├── utils                  # TokenManager for secure storage
```

---

## 🧠 Libraries Used

- [Retrofit 2](https://square.github.io/retrofit/)  
- [Gson Converter](https://github.com/square/retrofit/tree/master/retrofit-converters/gson)  
- [EncryptedSharedPreferences](https://developer.android.com/topic/security/data)  
- [BiometricPrompt API](https://developer.android.com/training/sign-in/biometric-auth)  
- [Material Components for Android](https://m3.material.io/)  
- [AndroidX Lifecycle ViewModel & LiveData](https://developer.android.com/topic/libraries/architecture/viewmodel)  

---

## ✍️ Author

**Manoj Kale**  
📧 [your.email@example.com](mailto:your.email@example.com)  
🔗 [https://github.com/manoj2000-crypto](https://github.com/manoj2000-crypto)
