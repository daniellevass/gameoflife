package gameoflife.danielle.dev.com.gameoflife;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

import cyd.awesome.material.AwesomeButton;

public class MainActivity extends AppCompatActivity {

    //interface items
    private AwesomeButton btnPlay;
    private AwesomeButton btnStop;
    private AwesomeButton btnNext;
    private AwesomeButton btnClear;

    private AwesomeButton btnGlider;
    private AwesomeButton btnBeacon;
    private AwesomeButton btnPulsar;
    private AwesomeButton btnRandom;

    private TextView lblCanvas;

    //game object with the logic to play the game
    private GameObject game;

    //thread for playing the next frames in sequence.
    private Thread threadPlayGame;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //hook the interface items up
        lblCanvas = (TextView)findViewById(R.id.lblCanvas);

        btnPlay = (AwesomeButton)findViewById(R.id.btnPlay);
        btnStop = (AwesomeButton)findViewById(R.id.btnStop);
        btnNext = (AwesomeButton)findViewById(R.id.btnStep);
        btnClear = (AwesomeButton)findViewById(R.id.btnClear);

        btnGlider = (AwesomeButton)findViewById(R.id.btnGlider);
        btnBeacon = (AwesomeButton)findViewById(R.id.btnBeacon);
        btnPulsar = (AwesomeButton)findViewById(R.id.btnPulsar);
        btnRandom = (AwesomeButton)findViewById(R.id.btnRandom);

        //enable the textview to scroll horizontally and vertically
        lblCanvas.setMovementMethod(new ScrollingMovementMethod());
        lblCanvas.setHorizontallyScrolling(true);

        //set up a new game which is 100 * 100
        //todo: we could allow this to be user configurable.
        game = new GameObject(100,100);

        //set the initial state of the game
        lblCanvas.setText(game.getText());


        //**** useful game buttons

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               //disable the game buttons, we don't want users to spawn a random pulsar in the middle!
                enableGameButtons(false);

                //set up a thread for running the game
                 threadPlayGame = new Thread(new Runnable()
                {

                    @Override
                    public void run()
                    {
                        while (!Thread.interrupted())
                            try
                            {
                                //calculate next frame in the background then sleep
                                game.calculateNextFrame();
                                Thread.sleep(300);
                                runOnUiThread(new Runnable() // start actions in UI thread
                                {

                                    @Override
                                    public void run()
                                    {
                                        //have to update the canvas on the UI thread
                                        lblCanvas.setText(game.getText());

                                        //check we have cells alive left
                                        if (game.getNumberOfCellsAlive() == 0){
                                            //stop the current thread
                                            threadPlayGame.interrupt();

                                            //enable the game again
                                            enableGameButtons(true);

                                            //tell the user what happened
                                            Toast.makeText(MainActivity.this, "All your cells died",
                                                    Toast.LENGTH_SHORT).show();

                                        } // end if we have cells alive
                                    } //end run loop
                                });
                            }
                            catch (InterruptedException e)
                            {
                                //stop the game running
                                //we set an interruption when they stop or leave the app
                                return;
                            }
                    }
                });

                threadPlayGame.start(); // the while thread will start in BG thread


            }
        });


        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //stop playing the next frame
                threadPlayGame.interrupt();

                //enable all the other game buttons again
                enableGameButtons(true);


            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (game.getNumberOfCellsAlive() > 0){
                    game.calculateNextFrame();
                    lblCanvas.setText(game.getText());
                } else {
                    Toast.makeText(MainActivity.this, "We don't have any alive cells",
                            Toast.LENGTH_SHORT).show();
                }

            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //clear the game
                game.clearGame();
                lblCanvas.setText(game.getText());
            }
        });


        //****** game presets


        btnRandom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //random game
                game.randomGame();
                lblCanvas.setText(game.getText());
            }
        });


        btnBeacon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //set beacon
                game.setBeacon();
                lblCanvas.setText(game.getText());
            }
        });

        btnPulsar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //set pulsar
                game.setPulsar();
                lblCanvas.setText(game.getText());
            }
        });

        btnGlider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //set up glider gun
                game.setGliderGun();
                lblCanvas.setText(game.getText());
            }
        });



    }


    private void enableGameButtons(boolean enabled){

        if (enabled){
            btnPlay.setVisibility(View.VISIBLE);
            btnStop.setVisibility(View.GONE);
        } else {
            btnPlay.setVisibility(View.GONE);
            btnStop.setVisibility(View.VISIBLE);
        }

        btnNext.setEnabled(enabled);
        btnClear.setEnabled(enabled);

        btnGlider.setEnabled(enabled);
        btnBeacon.setEnabled(enabled);
        btnPulsar.setEnabled(enabled);
        btnRandom.setEnabled(enabled);

    }


    @Override
    protected void onPause() {
        super.onPause();
        //app is about to go in the background, we should stop the game
        threadPlayGame.interrupt();
        enableGameButtons(true);

    }
}
