package tokyo.ibot.thor

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
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
        val liftKey = "lift"
        val database = FirebaseDatabase.getInstance()
        val ref = database.getReference(key)
        val liftRef = database.getReference(liftKey)

        fun sendMode(mode: String) {
            Log.d("push", mode)
            ref.setValue(mode)
        }

        fun sendLift(mode: String) {
            Log.d("lift", mode)
            liftRef.setValue(mode)
        }

        goButton.setOnTouchListener { _: View, event: MotionEvent ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                sendMode("go")
            } else if (event.action == MotionEvent.ACTION_UP) {
                sendMode("stop")
            }

            false
        }

        backButton.setOnTouchListener { _: View, event: MotionEvent ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                sendMode("back")
            } else if (event.action == MotionEvent.ACTION_UP) {
                sendMode("stop")
            }

            false
        }

        turnRightButton.setOnTouchListener { _: View, event: MotionEvent ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                sendMode("turnRight")
            } else if (event.action == MotionEvent.ACTION_UP) {
                sendMode("stop")
            }

            false
        }

        turnLeftButton.setOnTouchListener { _: View, event: MotionEvent ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                sendMode("turnLeft")
            } else if (event.action == MotionEvent.ACTION_UP) {
                sendMode("stop")
            }

            false
        }

        slideRightButton.setOnTouchListener { _: View, event: MotionEvent ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                sendMode("slideRight")
            } else if (event.action == MotionEvent.ACTION_UP) {
                sendMode("stop")
            }

            false
        }

        slideLeftButton.setOnTouchListener { _: View, event: MotionEvent ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                sendMode("slideLeft")
            } else if (event.action == MotionEvent.ACTION_UP) {
                sendMode("stop")
            }

            false
        }

        stopButton.setOnClickListener {
            sendMode("stop")
            sendLift("stop")
        }

        upButton.setOnTouchListener { _: View, event: MotionEvent ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                sendLift("up")
            } else if (event.action == MotionEvent.ACTION_UP) {
                sendLift("stop")
            }

            false
        }

        downButton.setOnTouchListener { _: View, event: MotionEvent ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                sendLift("down")
            } else if (event.action == MotionEvent.ACTION_UP) {
                sendLift("stop")
            }

            false
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

        liftRef.addValueEventListener(object : ValueEventListener {
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
