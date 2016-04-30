package com.dotdex.frenzy;

import android.app.ProgressDialog;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.dotdex.frenzy.model.Basket;
import com.dotdex.frenzy.util.Constants;
import com.facebook.login.LoginManager;
import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.plus.Plus;

import java.util.HashMap;
import java.util.Map;

public class OrderDealActivity extends AppCompatActivity implements
        AuthFragment.OnFragmentInteractionListener,
        PlaceOrderFragment.OnFragmentInteractionListener{

    private static final String TAG = MainActivity.class.getSimpleName();
    private static final String TAG_1 = "placeOrder_frag";
    private static final String TAG_2 = "auth_frag";
    private boolean orderPosted;

    /* *************************************
     *              GENERAL                *
     ***************************************/
    /* A dialog that is presented until the Firebase authentication finished. */
    private ProgressDialog mAuthProgressDialog;

    /* A reference to the Firebase */
    private Firebase mFirebaseRef;

    /* Data from the authenticated user */
    private AuthData mAuthData;

    /* Listener for Firebase session changes */
    private Firebase.AuthStateListener mAuthStateListener;
    private GoogleApiClient mGoogleApiClient;
    private Basket myBasket;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /* Load the view and display it */
        setContentView(R.layout.activity_order_deal);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //show the back icon
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //init basket
        myBasket = null;
//
//        try {
//            PackageInfo info = getPackageManager().getPackageInfo(
//                    "com.dotdex.frenzy",
//                    PackageManager.GET_SIGNATURES);
//            for (Signature signature : info.signatures) {
//                MessageDigest md = MessageDigest.getInstance("SHA");
//                md.update(signature.toByteArray());
//                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
//            }
//        } catch (PackageManager.NameNotFoundException e) {
//            e.printStackTrace();
//
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//
//        }

        if (this.getIntent()!=null)
        {
            Bundle bundle = getIntent().getExtras();
            myBasket = bundle.getParcelable("basket");
        }

          /* Setup the Google API object to allow Google+ logins */
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(Plus.API)
                .build();

        /* Setup the progress dialog that is displayed later when authenticating with Firebase */
        mAuthProgressDialog = new ProgressDialog(this);
        mAuthProgressDialog.setTitle("Loading");
        mAuthProgressDialog.setMessage("Authenticating with Firebase...");
        mAuthProgressDialog.setCancelable(false);
//        mAuthProgressDialog.show();

         /* Create the Firebase ref that is used for all authentication with Firebase */
        mFirebaseRef = new Firebase(Constants.APP_URL);

        mAuthStateListener = new Firebase.AuthStateListener() {
            @Override
            public void onAuthStateChanged(AuthData authData) {
                setAuthenticatedUser(authData);
            }
        };
        /* Check if the user is authenticated with Firebase already. If this is the case we can set the authenticated
         * user and hide hide any login buttons */
        mFirebaseRef.addAuthStateListener(mAuthStateListener);


        //get the basket from the calling activity


    }


    private void showPlaceOrderFragment() {
        getSupportActionBar().setTitle("Place MyOrder.::");
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        PlaceOrderFragment fragment = PlaceOrderFragment.newInstance(myBasket,"");
        transaction.replace(R.id.fragment, fragment, TAG_1);
        transaction.commitAllowingStateLoss();

    }

    private void showLoginFragment() {

        getSupportActionBar().setTitle("Login.::");
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        AuthFragment fragment = new AuthFragment();
        transaction.replace(R.id.fragment, fragment, TAG_2);
        transaction.commitAllowingStateLoss();

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        /* If a user is currently authenticated, display a logout menu */
        if (this.mAuthData != null) {
            getMenuInflater().inflate(R.menu.menu_order_deal, menu);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id)
        {
            case R.id.action_logout:
                logout();
                return true;
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Once a user is logged in, take the mAuthData provided from Firebase and "use" it.
     */
    private void setAuthenticatedUser(AuthData authData) {
        if (authData != null) {
            /* Hide all the login buttons */
            /* show a provider specific status text */
            String name = null;
            if (authData.getProvider().equals("facebook")
                    || authData.getProvider().equals("google")
                    || authData.getProvider().equals("twitter")) {
                name = (String) authData.getProviderData().get("displayName");
            } else if (authData.getProvider().equals("anonymous")
                    || authData.getProvider().equals("password")) {
                name = authData.getUid();
            } else {
                Log.e(TAG, "Invalid provider: " + authData.getProvider());
            }
            if (name != null) {
                Toast.makeText(this, "Logged in as:" + name + " (" + authData.getProvider() + ")",Toast.LENGTH_LONG).show();

                myBasket.setUserName(authData.getProviderData().get("displayName").toString());
//            myBasket.setUserEmail(authData.getProviderData().get("email").toString());
//            myBasket.setProfileImageUrl(authData.getProviderData().get("profileImageUrl").toString());

            }
            postOrder();

        } else {
            /* No authenticated user show all the login buttons */
            showLoginFragment();
        }
        this.mAuthData = authData;

        /* invalidate options menu to hide/show the logout button */
        supportInvalidateOptionsMenu();
    }

    private void postOrder() {

        mAuthProgressDialog.setMessage("Processing Order...");
        mAuthProgressDialog.show();
        if (!isOrderPosted()) {
            //insert the basket
            Firebase ref = mFirebaseRef.child("baskets");
            String id = ref.push().getKey();
            Firebase nRef = ref.child(id);
            myBasket.setBasketId(id);
            nRef.setValue(myBasket, new Firebase.CompletionListener() {
                @Override
                public void onComplete(FirebaseError firebaseError, Firebase firebase) {
                    if (firebaseError != null) {
                    } else {

                        mAuthProgressDialog.hide();
                        Toast.makeText(OrderDealActivity.this, "Posted", Toast.LENGTH_LONG).show();
                        setOrderPosted(true);
                        showPlaceOrderFragment();
//                    MyBasket myLocalBasket = MyBasket.fromBasket(myBasket);
//                    myLocalBasket.save();

                    }
                }
            });
        }

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onAccessToken(String provider, String token) {
        Map<String, String> options = new HashMap<>();
        options.put("oauth_token",token);
        authWithFirebase(provider, options);
    }


    /**
     * This method will attempt to authenticate a user to firebase given an oauth_token (and other
     * necessary parameters depending on the provider)
     */
    private void authWithFirebase(final String provider, Map<String, String> options) {
        if (options.containsKey("error")) {
            // TODO: 10-Mar-16 Show Error Dialog
            showErrorDialog(options.get("error"));
        } else {

            // TODO: 10-Mar-16 Show progress Dialog
            mAuthProgressDialog.show();

            if (provider.equals("twitter")) {
                // if the provider is twitter, we pust pass in additional options, so use the options endpoint
                mFirebaseRef.authWithOAuthToken(provider, options, new AuthResultHandler(provider));
            } else {
                // if the provider is not twitter, we just need to pass in the oauth_token
                mFirebaseRef.authWithOAuthToken(provider, options.get("oauth_token"), new AuthResultHandler(provider));
            }
        }
    }

    public boolean isOrderPosted() {
        return orderPosted;
    }

    public void setOrderPosted(boolean orderPosted) {
        this.orderPosted = orderPosted;
    }


    /**
     * Utility class for authentication results
     */
    private class AuthResultHandler implements Firebase.AuthResultHandler {

        private final String provider;

        public AuthResultHandler(String provider) {
            this.provider = provider;
        }

        @Override
        public void onAuthenticated(AuthData authData) {
            // TODO: 10-Mar-16 hide P Bar
            mAuthProgressDialog.hide();
            Log.i(TAG, provider + " auth successful");
            setAuthenticatedUser(authData);
        }

        @Override
        public void onAuthenticationError(FirebaseError firebaseError) {
            // TODO: 10-Mar-16 hide P bar and Show Error

//            mAuthProgressDialog.hide();
            showErrorDialog(firebaseError.toString());
        }
    }


    private void showErrorDialog(String message) {
        new AlertDialog.Builder(this)
                .setTitle("Error")
                .setMessage(message)
                .setPositiveButton(android.R.string.ok, null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        // if changing configurations, stop tracking firebase session.
        mFirebaseRef.removeAuthStateListener(mAuthStateListener);
//        setResult();
    }


    /**
     * Unauthenticate from Firebase and from providers where necessary.
     */
    private void logout() {
        if (this.mAuthData != null) {
            /* logout of Firebase */
            mFirebaseRef.unauth();
            /* Logout of any of the Frameworks. This step is optional, but ensures the user is not logged into
             * Facebook/Google+ after logging out of Firebase. */
            if (this.mAuthData.getProvider().equals("facebook")) {
                /* Logout from Facebook */
                LoginManager.getInstance().logOut();
            } else if (this.mAuthData.getProvider().equals("google")) {
                /* Logout from Google+ */
                if (mGoogleApiClient.isConnected()) {
                    Plus.AccountApi.clearDefaultAccount(mGoogleApiClient);
                    mGoogleApiClient.disconnect();

                }
            }
            /* Update authenticated user and show login buttons */
            setAuthenticatedUser(null);
        }
    }



}