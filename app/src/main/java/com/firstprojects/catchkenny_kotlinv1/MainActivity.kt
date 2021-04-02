package com.firstprojects.catchkenny_kotlinv1

import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Bitmap
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

val SHAREDPREFERENCESFINALNAME = "com.firstprojects.catchkenny_kotlinv1"

class MainActivity : AppCompatActivity() {
    lateinit var scorePointText : TextView
    lateinit var leftTimeText : TextView
    lateinit var enterNameEditText : EditText
    lateinit var showS1 : TextView
    lateinit var showS2 : TextView
    lateinit var showS3 : TextView
    lateinit var arrayListAction : ArrayList<ImageView>
    lateinit var sharedPreferences : SharedPreferences
    var score = 0
    var username = ""
    var runnable : Runnable = Runnable {  }
    var handler : Handler = Handler()


    //image lateinit
    lateinit var v1 : ImageView
    lateinit var v2 : ImageView
    lateinit var v3 : ImageView
    lateinit var v4 : ImageView
    lateinit var v5 : ImageView
    lateinit var v6 : ImageView
    lateinit var v7 : ImageView
    lateinit var v8 : ImageView
    lateinit var v9 : ImageView
    lateinit var v10 : ImageView
    lateinit var v11 : ImageView
    lateinit var v12 : ImageView

    //



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPreferences = getSharedPreferences(SHAREDPREFERENCESFINALNAME, MODE_PRIVATE)
        arrayListAction = arrayListOf()

        if(sharedPreferences.getBoolean("check",true)) {
            setContentView(R.layout.activity_startgame)
            enterNameEditText = findViewById(R.id.startSection_editText)
            showS1 = findViewById(R.id.show_score1)
            showS2 = findViewById(R.id.show_score2)
            showS3 = findViewById(R.id.show_score3)

            var scoretableUserName0 : String? = sharedPreferences.getString("save0N","-")
            var scoretableUserScore0 : String? = sharedPreferences.getString("save0S","-")
            showS1.text = scoretableUserName0 + " = " + scoretableUserScore0

           var scoretableUserName1 :String?= sharedPreferences.getString("save1N","-")
           var scoretableUserScore1 : String? = sharedPreferences.getString("save1S","-")
            showS2.text = scoretableUserName1 + " = " + scoretableUserScore1


            var scoretableUserName2 :String? = sharedPreferences.getString("save2N","-")
            var scoretableUserScore2 :String? = sharedPreferences.getString("save2S","-")
            showS3.text = scoretableUserName2 + " = " + scoretableUserScore2




        }else {
            setContentView(R.layout.activity_main)
            scorePointText = findViewById(R.id.gameSection_scoreTextView)
            leftTimeText = findViewById(R.id.gameSection_timeTextView)


            //images initilization

            v1 = findViewById(R.id.iV1)
            v2 = findViewById(R.id.iV2)
            v3 = findViewById(R.id.iV3)
            v4 = findViewById(R.id.iV4)
            v5 = findViewById(R.id.iV5)
            v6 = findViewById(R.id.iV6)
            v7 = findViewById(R.id.iV7)
            v8 = findViewById(R.id.iV8)
            v9 = findViewById(R.id.iV9)
            v10 = findViewById(R.id.iV10)
            v11 = findViewById(R.id.iV11)
            v12 = findViewById(R.id.iV12)

            //
            
            arrayListAction.add(v1)
            arrayListAction.add(v2)
            arrayListAction.add(v3)
            arrayListAction.add(v4)
            arrayListAction.add(v5)
            arrayListAction.add(v6)
            arrayListAction.add(v7)
            arrayListAction.add(v8)
            arrayListAction.add(v9)
            arrayListAction.add(v10)
            arrayListAction.add(v11)
            arrayListAction.add(v12)

//get Started
            for (array in arrayListAction)  {
                array.visibility = View.INVISIBLE
            }


            countDownTimer()
            startGameMechanics()


        }

    }

    fun startGame(view : View) {
if(enterNameEditText.text.toString().length > 0) {
    username = enterNameEditText.text.toString()
    sharedPreferences.edit().putBoolean("check",false).apply()
    letGetStarted()
  //  var intentToGame : Intent = Intent(this@MainActivity,MainActivity::class.java)
  //  finish()
   // startActivity(intentToGame)
}else {
    Toast.makeText(this@MainActivity, "PLEASE, ENTER YOUR NAME!", Toast.LENGTH_SHORT).show()
}


    }

    fun startGameMechanics() {
            runnable = object : Runnable {
            override fun run() {


                for (array in arrayListAction)  {
                    array.visibility = View.INVISIBLE
                }
                arrayListAction[rand(0,11)].visibility = View.VISIBLE

                handler.postDelayed(this,450)

            }


        }
handler.post(runnable)
    }

    fun countDownTimer() {
       object : CountDownTimer(60000,1000) {
           override fun onTick(millisUntilFinished: Long) {
               leftTimeText.text = "TIME = ${millisUntilFinished/1000}"
           }

           override fun onFinish() {
               handler.removeCallbacks(runnable)
               if (sharedPreferences.getInt("saveControl",0) == 0) {
                   sharedPreferences.edit().putString("save0S",score.toString()).apply()
                   sharedPreferences.edit().putString("save0N",username).apply()
                   sharedPreferences.edit().putInt("saveControl",1).apply()
               }else if (sharedPreferences.getInt("saveControl",0) == 1) {
                   sharedPreferences.edit().putString("save1S",score.toString()).apply()
                   sharedPreferences.edit().putString("save1N",username).apply()
                   sharedPreferences.edit().putInt("saveControl",2).apply()
               }else if (sharedPreferences.getInt("saveControl",0) == 2) {
                   sharedPreferences.edit().putString("save2S",score.toString()).apply()
                   sharedPreferences.edit().putString("save2N",username).apply()
                   sharedPreferences.edit().putInt("saveControl",0).apply()
               }


               for (array in arrayListAction)  {
                   array.visibility = View.INVISIBLE

                 var alert = AlertDialog.Builder(this@MainActivity)

                   alert.setTitle("GAME OVER")
                   alert.setMessage("Mr.${username}, Do you want to try again?")
                   alert.setPositiveButton("Yes") { dialog, which ->

                   var intentToTryAgain : Intent = Intent(this@MainActivity,MainActivity::class.java)

                       sharedPreferences.edit().putBoolean("check",false).apply()
                       var intentToMainMenu: Intent = Intent(this@MainActivity, MainActivity::class.java)
                       finish()
                       sharedPreferences.edit().putBoolean("check", true).apply()
                       startActivity(intentToMainMenu)
                   }
                   alert.setNegativeButton("No") { dialog, which ->
                       var intentToMainMenu: Intent = Intent(this@MainActivity, MainActivity::class.java)
                       finish()
                       sharedPreferences.edit().putBoolean("check", true).apply()
                       startActivity(intentToMainMenu)
                   }
                  alert.setCancelable(false)
                   alert.show()
               }

           }

       }.start()

    }

    fun rand(start: Int, end: Int): Int {

        require(start <= end) {"ILLEGAL ARGUMENTS"}
        return (start..end).random()
    }

    fun scorePoint(view : View) {
        score++
        scorePointText.text = "SCORE = ${score}"
        for (array in arrayListAction)  {
            array.visibility = View.INVISIBLE
        }
        arrayListAction[rand(0,11)].visibility = View.VISIBLE
    }

    override fun onDestroy() {
        super.onDestroy()
        sharedPreferences.edit().putBoolean("check",true).apply()
    }
    fun letGetStarted() {
        setContentView(R.layout.activity_main)
        scorePointText = findViewById(R.id.gameSection_scoreTextView)
        leftTimeText = findViewById(R.id.gameSection_timeTextView)


        //images initilization

        v1 = findViewById(R.id.iV1)
        v2 = findViewById(R.id.iV2)
        v3 = findViewById(R.id.iV3)
        v4 = findViewById(R.id.iV4)
        v5 = findViewById(R.id.iV5)
        v6 = findViewById(R.id.iV6)
        v7 = findViewById(R.id.iV7)
        v8 = findViewById(R.id.iV8)
        v9 = findViewById(R.id.iV9)
        v10 = findViewById(R.id.iV10)
        v11 = findViewById(R.id.iV11)
        v12 = findViewById(R.id.iV12)

        //

        arrayListAction.add(v1)
        arrayListAction.add(v2)
        arrayListAction.add(v3)
        arrayListAction.add(v4)
        arrayListAction.add(v5)
        arrayListAction.add(v6)
        arrayListAction.add(v7)
        arrayListAction.add(v8)
        arrayListAction.add(v9)
        arrayListAction.add(v10)
        arrayListAction.add(v11)
        arrayListAction.add(v12)

//get Started
        for (array in arrayListAction)  {
            array.visibility = View.INVISIBLE
        }


        countDownTimer()
        startGameMechanics()



    }    }