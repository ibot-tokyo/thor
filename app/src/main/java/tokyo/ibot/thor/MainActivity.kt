package tokyo.ibot.thor

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val key = "mode"
        val database = FirebaseDatabase.getInstance()
        val ref = database.getReference(key)

        goButton.setOnClickListener {
            val mode = "go"
            Log.d("push", mode)
            ref.setValue(mode)
        }

        backButton.setOnClickListener {
            val mode = "back"
            Log.d("push", mode)
            ref.setValue(mode)
        }

        turnRightButton.setOnClickListener {
            val mode = "turnRight"
            Log.d("push", mode)
            ref.setValue(mode)
        }

        turnLeftButton.setOnClickListener {
            val mode = "turnLeft"
            Log.d("push", mode)
            ref.setValue(mode)
        }

        slideRightButton.setOnClickListener {
            val mode = "slideRight"
            Log.d("push", mode)
            ref.setValue(mode)
        }

        slideLeftButton.setOnClickListener {
            val mode = "slideLeft"
            Log.d("push", mode)
            ref.setValue(mode)
        }

        stopButton.setOnClickListener {
            val mode = "stop"
            Log.d("push", mode)
            ref.setValue(mode)
        }


        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val value = dataSnapshot.value
                Log.d("onDataChange", value.toString())
                modeText.text = value.toString()
            }

            override fun onCancelled(error: DatabaseError) {
                Log.w("onCancelled", "Failed to read value.", error.toException())
            }
        })
    }
}
