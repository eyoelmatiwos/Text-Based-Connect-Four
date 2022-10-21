package connectfour;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/* you will need to add test methods and likely change the
setup method as well.  The samples that are here are just so that
you can see how junit works.

Tests are run on build unless specifically excluded with -x test.
The test results are reported in the reports subfolder of the build directory */


public class BoardTest{
    private Board tester;
    private Board tester2;
    private ArrayList<String> testStrArray;

    @Before
    public void setup(){
        //set up for the test
        tester = new Board();
        testStrArray = new ArrayList<String>(Arrays.asList("1,2,0,1,1,2,1", 
                                                                    "2,2,2,1,1,2,1", 
                                                                    "0,0,0,0,0,0,0", 
                                                                    "0,0,0,0,0,0,0", 
                                                                    "1,2,0,1,1,2,1", 
                                                                    "2,2,2,1,1,2,1"));
        tester2 = new Board();
        try {
            tester2.setHoles(Board.boardStringToArray(testStrArray)); //this should never throw an exception
        } catch(FileFormatException | InvalidContentException e) {
            tester2.setHoles(new int[][]{{0,0,0,0,0,0,0},
                                         {0,0,0,0,0,0,0},
                                         {0,0,0,0,0,0,0},
                                         {0,0,0,0,0,0,0},
                                         {0,0,0,0,0,0,0},
                                         {0,0,0,0,0,0,0},});
        }
            

    }

    @Test
    /* 
     * Tests whether the default constructor initializes it's hole's Attribute properly
     */
    public void defaultConstructorTest() {
        Board actual = tester; //tester was initialized using the default constructor this function tests
        int[][] expectedBoard = new int[][]{{0,0,0,0,0,0,0},
                                            {0,0,0,0,0,0,0},
                                            {0,0,0,0,0,0,0},
                                            {0,0,0,0,0,0,0},
                                            {0,0,0,0,0,0,0},
                                            {0,0,0,0,0,0,0},};
        
        
        Board expected = new Board();
        expected.setHoles(expectedBoard);

        Assert.assertTrue(Arrays.deepEquals(actual.getHoles(), expected.getHoles()));
    }

    @Test
    public void readBoardFileTest() {
        ArrayList<String> actual;
        String actualString = "assets/exampleboard.csv";
        ArrayList<String> expected = new ArrayList<String>();
        expected.add("0,0,0,0,0,0,0");
        expected.add("0,0,0,0,0,0,0");
        expected.add("0,0,0,0,0,0,0");
        expected.add("0,0,0,0,0,0,0");
        expected.add("0,0,0,0,0,1,0");
        expected.add("0,0,0,1,2,2,1");

        File actualFile = new File(actualString);

        try {
            BufferedReader bReader = new BufferedReader(new FileReader(actualFile));
            actual = Board.readBoardFile(bReader);
        } catch(IOException | FileFormatException e) {
            actual = null;
        }

        Assert.assertEquals(expected, actual);
    }
    
    @Test
    public void resetBoardTest() {
        int[][] expectedBoard = new int[][]{{0,0,0,0,0,0,0},
                                            {0,0,0,0,0,0,0},
                                            {0,0,0,0,0,0,0},
                                            {0,0,0,0,0,0,0},
                                            {0,0,0,0,0,0,0},
                                            {0,0,0,0,0,0,0},};
        tester2.resetBoard();
        int[][] actual = tester2.getHoles();
        Assert.assertArrayEquals(expectedBoard, actual);
    }
    
    @Test
    public void boardStringToArrayTest() {
        int[][] actual;
        int[][] expectedArray = {{1,2,0,1,1,2,1}, 
                                {2,2,2,1,1,2,1}, 
                                {0,0,0,0,0,0,0}, 
                                {0,0,0,0,0,0,0}, 
                                {1,2,0,1,1,2,1}, 
                                {2,2,2,1,1,2,1}};
        try{
            actual = Board.boardStringToArray(testStrArray);
        } catch (FileFormatException | InvalidContentException e) {
            actual = null;
        }
        

        Assert.assertArrayEquals(expectedArray, actual);
        
    }

    @Test
    /*
     * Tests whether or not Board.toString() properly converts array of 
     * 1 digit integeers into a string
     */
    public void toStringTest() {
        String expected =   "---------------\n"
                          + "|1|2|0|1|1|2|1|\n"
                          + "---------------\n"
                          + "|2|2|2|1|1|2|1|\n"
                          + "---------------\n"
                          + "|0|0|0|0|0|0|0|\n"
                          + "---------------\n"
                          + "|0|0|0|0|0|0|0|\n"
                          + "---------------\n"
                          + "|1|2|0|1|1|2|1|\n"
                          + "---------------\n"
                          + "|2|2|2|1|1|2|1|\n"
                          + "---------------\n";
        String actual = tester2.toString();

        Assert.assertEquals(expected, actual);
    }

}