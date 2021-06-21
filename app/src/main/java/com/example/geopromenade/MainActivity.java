/***
 *
 * Le contrôleur : activité ou fragment, dédié à la manipulation interface graphique
 *
 *
 */

package com.example.geopromenade;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.geopromenade.models.User;
import com.example.geopromenade.viewmodels.UserViewModel;

public class MainActivity extends AppCompatActivity {

    EditText etFirstname, etLastname;
    Button btnValider;
    UserViewModel userViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userViewModel = new UserViewModel();

        initUI();

        btnValider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = new User();
                user.setFirstname(etFirstname.getText().toString());
                user.setLastname(etLastname.getText().toString());

                userViewModel.createUser(user);
                if(user.isCreated()){
                    Toast.makeText(MainActivity.this, "Creation succes", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    void initUI(){
        etFirstname = findViewById(R.id.etFirstname);
        etLastname = findViewById(R.id.etLastname);
        btnValider = findViewById(R.id.btnValider);
    }
}