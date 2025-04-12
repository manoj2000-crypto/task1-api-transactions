package in.theimperative.task1apitransaction;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import in.theimperative.task1apitransaction.ui.biometric.BiometricActivity;
import in.theimperative.task1apitransaction.ui.login.LoginActivity;
import in.theimperative.task1apitransaction.utils.TokenManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String token = TokenManager.getToken(this);

        if (token != null) {
            // If user is already logged in then goes to verify the biometric.
            startActivity(new Intent(this, BiometricActivity.class));
        } else {
            // If not then logged in and then go to login screen...
            startActivity(new Intent(this, LoginActivity.class));
        }
        finish();

    }
}