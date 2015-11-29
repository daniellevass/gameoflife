package gameoflife.danielle.dev.com.gameoflife;

import java.util.Random;

/**
 * Created by daniellevass on 25/11/2015.
 */
public class GameObject {

    private int height; //number of squares high
    private int width; //number of squares down

    private boolean[][] universe; //2 dimensional array to keep the current frames status

    private boolean[][] oldUniverse;

    private int numberOfCellsAlive; //integer for number of cells alive,
    // useful for if number of cells is 0 might as well end game


    private String text; // used to hold the string to be displayed on the screen


    /**
     * creates a new game object with a provided height and width
     * @param width number of cells in the x axis
     * @param height number of cells in the y axis
     */
    public GameObject(int width, int height) {

        this.width = width;
        this.height = height;



        //start a blank game
        clearGame();

    }


    /**
     * clear everything in the matrix
     */
    public void clearGame(){

        //initialise the universe with the number of items required
        universe = new boolean[width][height];

        numberOfCellsAlive = 0;
        text = "";

    }

    public int getNumberOfCellsAlive() {
        return numberOfCellsAlive;
    }

    public String getText() {

        //check if we don't already have the text set up
        //can happen on some of the custom presets
        if (text.equals("")){

            StringBuilder sb = new StringBuilder();

            for (int y = 1; y < (height-2); y++) {
                for (int x = 1; x < (width-2); x++) {

                    //append the correct cell alive or cell dead character
                    if (universe[x][y]){
                        sb.append("\uF0C9");
                    } else {
                        sb.append("\uF1F5");
                    }

                } //end for width

                //finished a row
                sb.append("\n");

            }//end for height

            //set text to be the string builder
            text = sb.toString();


        } //end if we didn't have any text


        return text;
    }


    //DIFFERENT PRESETS THAT WE CAN PLAY WITH

    /**
     * set up a completely random grid
     */
    public void randomGame(){

       clearGame();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int randomInt = new Random().nextInt(100);

                if (randomInt > 50){
                    universe[x][y] = true;
                    numberOfCellsAlive ++;
                } else {
                    universe[x][y] = false;
                }
            }
        }
    }

    /**
     * set up a repeating beacon formation
     * http://www.conwaylife.com/wiki/Beacon
     */
    public void setBeacon(){

        clearGame();


        for (int i = 0; i < (width -6); i+=6){

            for (int j = 0; j < (width -6); j+=6) {

                universe[1 + i][1 + j] = true;
                universe[1 + i][2 + j] = true;
                universe[2 + i][1 + j] = true;

                universe[3 + i][4 + j] = true;
                universe[4 + i][3 + j] = true;
                universe[4 + i][4 + j] = true;

                numberOfCellsAlive += 6;

            }

        }


    }


    /**
     * set up a single pulsar formation
     * http://www.conwaylife.com/wiki/Pulsar
     */
    public void setPulsar(){

        clearGame();

        universe[3][5] = true;
        universe[3][6] = true;
        universe[3][7] = true;
        universe[3][11] = true;
        universe[3][12] = true;
        universe[3][13] = true;
        universe[5][3] = true;
        universe[5][8] = true;
        universe[5][10] = true;
        universe[5][15] = true;
        universe[6][3] = true;
        universe[6][8] = true;
        universe[6][10] = true;
        universe[6][15] = true;
        universe[7][3] = true;
        universe[7][8] = true;
        universe[7][10] = true;
        universe[7][15] = true;
        universe[8][5] = true;
        universe[8][6] = true;
        universe[8][7] = true;
        universe[8][11] = true;
        universe[8][12] = true;
        universe[8][13] = true;
        universe[10][5] = true;
        universe[10][6] = true;
        universe[10][7] = true;
        universe[10][11] = true;
        universe[10][12] = true;
        universe[10][13] = true;
        universe[11][3] = true;
        universe[11][8] = true;
        universe[11][10] = true;
        universe[11][15] = true;
        universe[12][3] = true;
        universe[12][8] = true;
        universe[12][10] = true;
        universe[12][15] = true;
        universe[13][3] = true;
        universe[13][8] = true;
        universe[13][10] = true;
        universe[13][15] = true;
        universe[15][5] = true;
        universe[15][6] = true;
        universe[15][7] = true;
        universe[15][11] = true;
        universe[15][12] = true;
        universe[15][13] = true;

        numberOfCellsAlive = 48;


    }

    /**
     * set up a single gosper glider gun
     * http://www.conwaylife.com/wiki/Gosper_glider_gun
     */
    public void setGliderGun(){

        clearGame();

        universe[27][3] = true;
        universe[25][4] = true;
        universe[27][4] = true;
        universe[15][5] = true;
        universe[16][5] = true;
        universe[23][5] = true;
        universe[24][5] = true;
        universe[37][5] = true;
        universe[38][5] = true;
        universe[14][6] = true;
        universe[18][6] = true;
        universe[23][6] = true;
        universe[24][6] = true;
        universe[37][6] = true;
        universe[38][6] = true;
        universe[3][7] = true;
        universe[4][7] = true;
        universe[13][7] = true;
        universe[19][7] = true;
        universe[23][7] = true;
        universe[24][7] = true;
        universe[3][8] = true;
        universe[4][8] = true;
        universe[13][8] = true;
        universe[17][8] = true;
        universe[19][8] = true;
        universe[20][8] = true;
        universe[25][8] = true;
        universe[27][8] = true;
        universe[13][9] = true;
        universe[19][9] = true;
        universe[27][9] = true;
        universe[14][10] = true;
        universe[18][10] = true;
        universe[15][11] = true;
        universe[16][11] = true;

        numberOfCellsAlive = 36;
    }

    /**
     * move the game onto the next frame
     */
    public void calculateNextFrame(){

        //we need to keep a reference to what the previous frame was
        oldUniverse = universe;

        //clear our universe to start again
        universe = new boolean[width][height];

        //
        StringBuilder sb = new StringBuilder();

        //loop across the height
        for (int y = 1; y <= (height -2); y++) {

            //loop across the width
            for (int x = 1; x < (width -2); x++ ){

                //check how many cells around current cell are alive
                int cellsAliveNext = numberOfCellsAliveToCoordinate(x, y);

                //determine if we should live or die according to Conways original rules
                universe[x][y] = determineIfCellLivesOrDies(oldUniverse[x][y], cellsAliveNext);

                //append the correct cell alive or cell dead character
                if (universe[x][y]){
                    sb.append("\uF0C9");
                } else {
                    sb.append("\uF1F5");
                }


            }//end looping across width

            //finished a row
            sb.append("\n");

        }//end looping across height

        //set the game text
        text = sb.toString();

    }//end calculate next frame


    /**
     * function to calculate number of neighbours are alive to cell provided
     * @param x - the x coordinate
     * @param y - the y coordinate
     * @return int with number of neighbours alive
     */
    public int numberOfCellsAliveToCoordinate(int x, int y){

        int cellsAlive = 0;

        //loop at the row above current cell
        cellsAlive +=  oldUniverse[x-1][y-1] ? 1 : 0;
        cellsAlive +=  oldUniverse[x-1][y] ? 1 : 0;
        cellsAlive +=  oldUniverse[x-1][y+1] ? 1 : 0;

        //look at the cells either side on the current cell
        cellsAlive +=  oldUniverse[x][y-1] ? 1 : 0;
        cellsAlive +=  oldUniverse[x][y+1] ? 1 : 0;

        //look at the cells below current cell
        cellsAlive +=  oldUniverse[x+1][y-1] ? 1 : 0;
        cellsAlive +=  oldUniverse[x+1][y] ? 1 : 0;
        cellsAlive +=  oldUniverse[x+1][y+1] ? 1 : 0;



        return cellsAlive;
    }

    /**
     * function to determine whether cell lives and dies according to conways original rules
     * @param currentlyAlive - whether the current cell is alive or dead
     * @param numberOfNeighboursAlive - number of cells surrounding that were alive
     * @return boolean for whether it is alive or not
     */
    public boolean determineIfCellLivesOrDies(boolean currentlyAlive, int numberOfNeighboursAlive){

        //check if we current cell was already alive
        if (currentlyAlive){

            //less than 2 neighbours means we die of loneliness
            if (numberOfNeighboursAlive <2){
                return false;
            } else if(numberOfNeighboursAlive <4){
                //2 or 3 neighbours means we lived!
                return true;
            } else {
                //4 or higher we die of overcrowding
                return false;
            }

        } else {

            //did we have exactly 3 neighbours alive to be born?
            if (numberOfNeighboursAlive == 3){
                return true;
            } else {
                //we're still not alive
                return false;
            }

        }
    }




// old method to calculate the game string
// was more efficient to calculate it whilst we calculate next frame too
// @Override
//    public String toString() {
//
//        StringBuilder sb = new StringBuilder();
//
//
//        for (int y = 0; y < height; y++) {
//            for (int x = 0; x < width; x++) {
//                int randomInt = new Random().nextInt(100);
//
//                if (universe[y][x]){
//                    sb.append("\uF0C9");
//                } else {
//                    sb.append("\uF1F5");
//                }
//            }
//            sb.append("\n");
//
//        }
//
//        return sb.toString();
//    }


    public void setUniverse(boolean[][] universe) {
        this.universe = universe;
    }

    public void setOldUniverse(boolean[][] oldUniverse) {
        this.oldUniverse = oldUniverse;
    }
}
