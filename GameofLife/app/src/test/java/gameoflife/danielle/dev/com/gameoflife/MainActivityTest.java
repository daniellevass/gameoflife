package gameoflife.danielle.dev.com.gameoflife;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import cyd.awesome.material.AwesomeButton;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by daniellevass on 29/11/2015.
 */

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class)


public class MainActivityTest {

    private MainActivity activity;


    @Before
    public void setUp() throws Exception
    {
        activity = Robolectric.setupActivity(MainActivity.class);
    }

    @Test
    public void shouldNotBeNull() {
        assertNotNull(activity);
    }


    @Test
    public void shouldClearAllCells() {

        AwesomeButton btnClear = (AwesomeButton) activity.findViewById(R.id.btnClear);

        btnClear.performClick();

        assertEquals(0, activity.getGame().getNumberOfCellsAlive());

    }


    /*
        CHECK PRESETS
     */


    @Test
    public void shouldDisplayPulsar() {

        AwesomeButton btnPulsar = (AwesomeButton) activity.findViewById(R.id.btnPulsar);

        btnPulsar.performClick();

        assertEquals(48, activity.getGame().getNumberOfCellsAlive());

    }

    @Test
    public void shouldDisplayGliderGun() {

        AwesomeButton btnGlider = (AwesomeButton) activity.findViewById(R.id.btnGlider);

        btnGlider.performClick();

        assertEquals(36, activity.getGame().getNumberOfCellsAlive());

    }

}
