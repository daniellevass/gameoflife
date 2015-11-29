package gameoflife.danielle.dev.com.gameoflife;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class)

public class ExampleUnitTest {

    private GameObject game;

    @Before
    public void setUp(){
        game = new GameObject(5,5);
    }


    @Test
    public void shouldContainNoLiveCells() throws Exception {

        game.clearGame();
        assertEquals(0, game.getNumberOfCellsAlive());

    }

//    @Test
//    public void shouldHave0NeighboursLive() throws Exception {
//
//        int neighbours = game.numberOfCellsAliveToCoordinate();
//
//        assertEquals(0, game.getNumberOfCellsAlive());
//
//    }




    /*
    check how many neighbours are alive based on the previous grid
     */


    @Test
    public void shouldHave0NeighboursAlive(){

        //no live cells on universe -> should be 0 live cells
        game.setOldUniverse(new boolean[4][4]);
        assertEquals(0, game.numberOfCellsAliveToCoordinate(2, 2));

    }

    @Test
    public void shouldHave8NeighboursAlive(){

        //configure a full matrix
        boolean[][] matrix = new boolean[4][4];

        matrix[1][1] = true;
        matrix[2][1] = true;
        matrix[3][1] = true;

        matrix[1][2] = true;
        matrix[3][2] = true;

        matrix[1][3] = true;
        matrix[2][3] = true;
        matrix[3][3] = true;

        game.setOldUniverse(matrix);

        //should be 8 live cells around.
        assertEquals(8, game.numberOfCellsAliveToCoordinate(2, 2));

    }


    /*
    determine if cell should live -> starts off alive
     */


    @Test
    public void shouldDieIfCurrentlyAliveAnd0LiveNeighbours(){

        assertEquals(false, game.determineIfCellLivesOrDies(true, 0) );

    }
    @Test
    public void shouldDieIfCurrentlyAliveAnd1LiveNeighbour(){

        assertEquals(false, game.determineIfCellLivesOrDies(true, 1) );

    }
    @Test
    public void shouldLiveIfCurrentlyAliveAnd2LiveNeighbours(){

        assertEquals(true, game.determineIfCellLivesOrDies(true, 2) );

    }
    @Test
    public void shouldLiveIfCurrentlyAliveAnd3LiveNeighbours(){

        assertEquals(true, game.determineIfCellLivesOrDies(true, 3) );

    }
    @Test
    public void shouldDieIfCurrentlyAliveAnd4LiveNeighbours(){

        assertEquals(false, game.determineIfCellLivesOrDies(true, 4) );

    }
    @Test
    public void shouldDieIfCurrentlyAliveAnd5LiveNeighbours(){

        assertEquals(false, game.determineIfCellLivesOrDies(true, 5));

    }
    @Test
    public void shouldDieIfCurrentlyAliveAnd6LiveNeighbours(){

        assertEquals(false, game.determineIfCellLivesOrDies(true, 6));

    }
    @Test
    public void shouldDieIfCurrentlyAliveAnd7LiveNeighbours(){

        assertEquals(false, game.determineIfCellLivesOrDies(true, 7));

    }
    @Test
    public void shouldDieIfCurrentlyAliveAnd8LiveNeighbours(){

        assertEquals(false, game.determineIfCellLivesOrDies(true, 8));

    }

    /*
    determine if cell should live -> starts off dead
     */

    @Test
    public void shouldDieIfCurrentlyDeadAnd0LiveNeighbours(){

        assertEquals(false, game.determineIfCellLivesOrDies(false, 0) );

    }
    @Test
    public void shouldDieIfCurrentlyDeadAnd1LiveNeighbour(){

        assertEquals(false, game.determineIfCellLivesOrDies(false, 1) );

    }
    @Test
    public void shouldLiveIfCurrentlyDeadAnd2LiveNeighbours(){

        assertEquals(false, game.determineIfCellLivesOrDies(false, 2) );

    }
    @Test
    public void shouldLiveIfCurrentlyDeadAnd3LiveNeighbours(){

        assertEquals(true, game.determineIfCellLivesOrDies(false, 3) );

    }
    @Test
    public void shouldDieIfCurrentlyDeadAnd4LiveNeighbours(){

        assertEquals(false, game.determineIfCellLivesOrDies(false, 4) );

    }
    @Test
    public void shouldDieIfCurrentlyDeadAnd5LiveNeighbours(){

        assertEquals(false, game.determineIfCellLivesOrDies(false, 5));

    }
    @Test
    public void shouldDieIfCurrentlyDeadAnd6LiveNeighbours(){

        assertEquals(false, game.determineIfCellLivesOrDies(false, 6));

    }
    @Test
    public void shouldDieIfCurrentlyDeadAnd7LiveNeighbours(){

        assertEquals(false, game.determineIfCellLivesOrDies(false, 7));

    }
    @Test
    public void shouldDieIfCurrentlyDeadAnd8LiveNeighbours(){

        assertEquals(false, game.determineIfCellLivesOrDies(false, 8));

    }




}