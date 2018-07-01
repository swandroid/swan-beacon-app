package nl.michelmooiweer.hellobeacon.firebase;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import com.google.firebase.functions.FirebaseFunctions;
import com.google.firebase.functions.HttpsCallableResult;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import java.util.HashMap;
import java.util.Map;


public class FirebaseService extends FirebaseInstanceIdService {
    private FirebaseFunctions mFunctions;
    public FirebaseService(){
        mFunctions = FirebaseFunctions.getInstance();
    }

    @Override
    public void onTokenRefresh() {
        // Get updated InstanceID token.
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d("Firebase", "Refreshed token: " + refreshedToken);

        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // Instance ID token to your app server.
    }

    public Task<String> sendAlert(){
        Map<String, Object> data = new HashMap<>();
        data.put("targetDevices","ezd6ZB_TLaU:APA91bHyfdJFia661h_ieYO7JDMeg7AYzplmM-PWEqSI1vvveRITp8cHE1nYCg8ad1JIjcRnql6h3bJzJ33ANM1sZPsCN-MOD66R8ZBsS0ohnkw0NsuZWD4ECmK-6G0a3m6k_g35S2YOe8hztNEi1gjkyO8OLfRRQg");
        data.put("alertMessage", "ALERT");

        return mFunctions.getHttpsCallable("sendAlert").call(data)
                    .continueWith(new Continuation<HttpsCallableResult, String>() {
            @Override
            public String then(@NonNull Task<HttpsCallableResult> task) throws Exception {
                // This continuation runs on either success or failure, but if the task
                // has failed then getResult() will throw an Exception which will be
                // propagated down.
                String result = (String) task.getResult().getData();
                return result;
            }
        });

    }


}
