import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class RleProgramTest {
    @Test
    public void toHexString(){ //method 1
        byte[] data = { 3, 15, 6, 4 };
        String hexString = "3f64";
        assertEquals(hexString, RleProgram.toHexString(data));
    }

    @Test
    public void countRuns(){ //method 2
        byte[] flatData = { 1,2,3,4,5,1,2,3,4,5,1,2,3,4,5,1,2,3,4,5,1,2,3,4,5 };
        int groups = 25;
        assertEquals(groups, RleProgram.countRuns(flatData));
    }


    @Test
    public void encodeRle() { //method 3
        byte[] flatData = { 4,4,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1 };
        byte[] array = { 2,4,15,1,15,1,5,1 };
        byte[] result = RleProgram.encodeRle(flatData);
        assertArrayEquals(array, result);

    }


    @Test
    public void getDecodedLength(){ //method 4
        byte[] rleData = { 3, 15, 6, 4 };
        int groups = 9;
        assertEquals(groups, RleProgram.getDecodedLength(rleData));
    }

    @Test
    public void decodeRle(){ //method 5
        byte[] rleData = { 1,1,1,2,1,3,1,4,1,1,1,2,1,3,1,4 };
        byte[] array = { 1,2,3,4,1,2,3,4 };
        byte[] result = RleProgram.decodeRle(rleData);
        assertArrayEquals(array, result);

    }
    //  1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,


    @Test
    public void stringToData(){ // method 6
        String dataString = "3f64";
        byte[] decimalArray = { 3, 15, 6, 4  };
        byte[] result = RleProgram.stringToData(dataString);
        assertArrayEquals(decimalArray, result);
    }

    @Test
    public void toRleString(){
        byte[] rleData = { 15, 15, 6, 4 };
        String answer = "15f:64";
        String result = RleProgram.toRleString(rleData);
        assertEquals(answer, result);
    }

    @Test
    public void stringToRle(){
        String rleString = "15f:64";
        byte[] answer = { 15, 15, 6, 4 };
        byte[] result = RleProgram.stringToRle(rleString);
        assertArrayEquals(answer, result);
    }

}
