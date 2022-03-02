package com.vedant.chattingapp.Activities;

import android.content.Context;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.vedant.chattingapp.Adapters.loginAdapter;
import com.vedant.chattingapp.R;

import java.util.Objects;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

public class login_Activity extends AppCompatActivity {

    //    FirebaseAuth mAuth;
//    GoogleSignInClient mGoogleSignInClient;
    TabLayout tabLayout;
    ViewPager2 viewPager;
    FloatingActionButton fab1, fab2, fab3;
//    FirebaseDatabase Database;


//    ActivityResultLauncher<Intent> someActivityResultLauncher = registerForActivityResult(
//            new ActivityResultContracts.StartActivityForResult(),
//            new ActivityResultCallback<ActivityResult>() {
//                @Override
//                public void onActivityResult(ActivityResult result) {
//                    if (result.getResultCode() == Activity.RESULT_OK) {
//                        Intent data = result.getData();
//                        if (data != null) {
//                            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
//                            try {
//                                GoogleSignInAccount account = task.getResult(ApiException.class);
//                                Log.d("TAG", "firebaseAuthWithGoogle:" + account.getId());
//                                firebaseAuthWithGoogle(account.getIdToken());
//                            } catch (ApiException e) {
//                                Log.w("TAG", "Google sign in failed", e);
//                            }
//                        }
//                    }
//                }
//            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Objects.requireNonNull(getSupportActionBar()).hide();

        setContentView(R.layout.activity_login);


//        Database = FirebaseDatabase.getInstance();
//        mAuth = FirebaseAuth.getInstance();


        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager2);
        fab1 = findViewById(R.id.fab);
        fab2 = findViewById(R.id.fab2);
        fab3 = findViewById(R.id.fab3);


        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        viewPager.setAdapter(new loginAdapter(getSupportFragmentManager(), getLifecycle(), this,
                tabLayout.getTabCount()));

        new TabLayoutMediator(tabLayout, viewPager,
                (tab, position) -> {
                    if (position == 0) {
                        tab.setText("Login");
                    } else {
                        tab.setText("Sign Up");
                    }
                }
        ).attach();

        // Configure Google Sign In
//        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//                .requestIdToken(getString(R.string.web_client_id))
//                .requestEmail()
//                .build();

//        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

//        fab1.setOnClickListener(v -> openSomeActivityForResult());

    }

//    public void openSomeActivityForResult() {
//        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
//        someActivityResultLauncher.launch(signInIntent);
//    }

//    private void firebaseAuthWithGoogle(String idToken) {
//        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
//        mAuth.signInWithCredential(credential)
//                .addOnCompleteListener(this, task -> {
//                    if (task.isSuccessful()) {
//                        Log.d("TAG", "signInWithCredential:success");
//                        FirebaseUser user = mAuth.getCurrentUser();
//                        Users users = new Users();
//                        // changes made
//                        assert user != null;
//                        users.setUserId(user.getUid());
//                        users.setUserName(user.getDisplayName());
//                        // changes made
//                        users.setProfilePic(Objects.requireNonNull(user.getPhotoUrl()).toString());
//                        Database.getReference().child("Users").child(user.getUid()).setValue(users);
//
//
//                        startActivity(new Intent(this, MainActivity.class));
//
//                        Toast.makeText(login_Activity.this, "Sign in with Google", Toast.LENGTH_SHORT).show();
//                        // updateUI(user);
//                    } else {
//                        // If sign in fails, display a message to the user.
//                        Log.w("TAG", "signInWithCredential:failure", task.getException());
//                        //  updateUI(null);
//                    }
//                });
//    }

    /**
     * USED FOR HIDING KEYBOARD OUTSIDE EDITTEXT FIELDS
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        View view = getCurrentFocus();
        boolean ret = super.dispatchTouchEvent(event);

        if (view instanceof EditText) {
            View w = getCurrentFocus();
            int[] Accords = new int[2];
            w.getLocationOnScreen(Accords);
            float x = event.getRawX() + w.getLeft() - Accords[0];
            float y = event.getRawY() + w.getTop() - Accords[1];

            if (event.getAction() == MotionEvent.ACTION_UP
                    && (x < w.getLeft() || x >= w.getRight()
                    || y < w.getTop() || y > w.getBottom())) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getWindow().getCurrentFocus().getWindowToken(), 0);
            }
        }
        return ret;
    }


}
