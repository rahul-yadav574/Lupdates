package in.location;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;

import java.net.URISyntaxException;

public class Landing extends AppCompatActivity {

    private Socket socket;
    private static final String TAG = "LandingActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);

        try {

            socket = IO.socket("http://192.168.43.193:3000");
            socket.connect();
            Log.d(TAG,"Connecting to socket server");

        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                Log.d(TAG,"Connection to socket established.");
            }
        });

        socket.on("response", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                Log.d(TAG,"server responded");
            }
        });

        socket.emit("update","hello i am android client");
    }
}
