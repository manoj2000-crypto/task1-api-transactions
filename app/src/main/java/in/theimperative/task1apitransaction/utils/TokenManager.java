package in.theimperative.task1apitransaction.utils;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.security.crypto.EncryptedSharedPreferences;
import androidx.security.crypto.MasterKey;

import java.io.IOException;
import java.security.GeneralSecurityException;

public class TokenManager {

    private static SharedPreferences getPrefs(Context context) {
        try {
            MasterKey masterKey = new MasterKey.Builder(context).setKeyScheme(MasterKey.KeyScheme.AES256_GCM).build();

            return EncryptedSharedPreferences.create(context, "secure_prefs", masterKey, EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV, EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM);
        } catch (GeneralSecurityException | IOException e) {
            throw new RuntimeException("SecurePrefs failure", e);
        }
    }

    public static void saveToken(Context context, String token) {
        getPrefs(context).edit().putString("auth_token", token).apply();
    }

    public static String getToken(Context context) {
        return getPrefs(context).getString("auth_token", null);
    }

    public static void clearToken(Context context) {
        getPrefs(context).edit().clear().apply();
    }
}