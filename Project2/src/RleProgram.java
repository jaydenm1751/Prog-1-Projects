import java.util.Scanner;

public class RleProgram {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int menuSelect;

        System.out.println("Welcome to the RLE image encoder!");
        System.out.println("\nDisplaying Spectrum Image:");
        ConsoleGfx.displayImage(ConsoleGfx.testRainbow);

        byte[] imageData = null;
        String rleString;
        String hexStringRle;
        String hexStringFlat;

        do {
            Menu();
            System.out.print("Select a Menu Option: ");
            menuSelect = input.nextInt();

            if (menuSelect == 1) { //accepts a file name to load
                System.out.print("Enter name of file to load: ");
                imageData = ConsoleGfx.loadFile(input.next());

            } else if (menuSelect == 2) { //loads test image
                System.out.print("Test image data loaded. ");
                imageData = ConsoleGfx.testImage;

            } else if (menuSelect == 3){ //Reads RLE data from the user in decimal notation with delimiters
                System.out.print("Enter an RLE string to be decoded: ");
                rleString = input.next();
                imageData = decodeRle(stringToRle(rleString));

            } else if (menuSelect == 4){ //Reads RLE data from the user in hexadecimal notation without delimiters
                System.out.print("Enter the hex string holding RLE data: ");
                hexStringRle = input.next();
                imageData = decodeRle(stringToData(hexStringRle));

            } else if (menuSelect == 5){ //Reads raw (flat) data from the user in hexadecimal notation
                System.out.print("Enter the hex string holding flat data: ");
                hexStringFlat = input.next();
                imageData = stringToData(hexStringFlat);

            } else if (menuSelect == 6) { //displays image
                System.out.println("Displaying image...");
                if (imageData != null){
                    ConsoleGfx.displayImage(imageData);
                } else {
                    System.out.println("(no data)");
                }

            } else if (menuSelect == 7) { //Converts the current data into a human-readable RLE representation
                if (imageData != null) {
                    System.out.println("RLE representation: " + toRleString(encodeRle(imageData)));
                } else {
                    System.out.println("RLE representation: (no data)");
                }

            } else if (menuSelect == 8) { //Converts the current data into RLE hexadecimal representation (without delimiters)
                if (imageData != null) {
                    System.out.println("RLE hex values: " + toHexString(encodeRle(imageData)));
                } else {
                    System.out.println("RLE hex values: (no data)");
                }

            } else if (menuSelect == 9) { //Displays the current raw (flat) data in hexadecimal representation (without delimiters)
                if (imageData != null) {
                    System.out.println("Flat hex values: " + toHexString(imageData));
                } else {
                    System.out.println("Flat hex values: (no data)");
                }

            } else if (menuSelect == 0) { //break point
                break;

            } else { //if a different input than requested
                System.out.println("Error! Invalid input. ");
            }

        } while (true); //continuous loop until 0

}

    public static void Menu() { //menu
        System.out.println("\n\nRLE Menu\n--------");
        System.out.println("0. Exit\n1. Load File\n2. Load Test Image\n3. Read RLE String\n4. Read RLE Hex String\n5. Read Data Hex String");
        System.out.println("6. Display Image\n7. Display RLE String\n8. Display Hex RLE Data\n9. Display Hex Flat Data\n");
    }

    //method 1
    public static String toHexString(byte[] data) {

        // declaration of variables
        int remainder = 0;
        char remainderAboveTen = 'a';
        String hexString = "";

        //iterate through data array
        for (int i : data) {
            remainder = i % 16;
            //if remainder needs a character
            if (remainder > 9) {
                String sRemainder = Integer.toString(remainder);
                switch (sRemainder) { //print characters instead of numbers
                    case "10":
                        remainderAboveTen = 'a';
                        break;
                    case "11":
                        remainderAboveTen = 'b';
                        break;
                    case "12":
                        remainderAboveTen = 'c';
                        break;
                    case "13":
                        remainderAboveTen = 'd';
                        break;
                    case "14":
                        remainderAboveTen = 'e';
                        break;
                    case "15":
                        remainderAboveTen = 'f';
                        break;
                }

                //add to string
                hexString = hexString + remainderAboveTen;
            } else {
                //if remainder doesnt need a character
                String sRemainder = Integer.toString(remainder);
                hexString = hexString + sRemainder; //add to string

            }

        }
        return hexString;
    }

    //method 2
    public static int countRuns(byte[] flatData) {

        //initialize variables
        int groups = 1;
        int count = 1;
        //whenever two consecutive indices dont match add another group
        for (int j = 0; j < flatData.length - 1; j++) {
            if (flatData[j] == flatData[j + 1]) {
                count++;
            }
            else{
                groups++;
                count = 1;
            }

            if (count > 15){
                groups++;
                count = 1;
            }
        }
        return groups;
    }


    //method 3
    public static byte[] encodeRle(byte[] flatData){
        //length of integer is twice the groups
        byte[] groupIntegers = new byte[countRuns(flatData) * 2];
        int count = 1;
        int index = 0;
        int same;


        for (int i = 0; i < flatData.length - 1; i++){
            //count of consecutive integers
            if (flatData[i] == flatData[i + 1]){
                count++;

                if (count >= 15){
                    //if consecutive integers exceed 15 start a new branch
                    same = flatData[i];
                    groupIntegers[index++] = (byte) count; // even indices hold count
                    groupIntegers[index++] = (byte) same;
                    count = 0; //reset
                }
                continue;
            }

            else {
                //the number to be held
                same = flatData[i];
                groupIntegers[index++] = (byte) count; // even indices hold count
                groupIntegers[index++] = (byte) same; //odd holds number

                count = 1; //resets for next count
                }
            }

        //the last two indices
        same = flatData[flatData.length - 1];
        groupIntegers[groupIntegers.length - 2] = (byte) count;
        groupIntegers[groupIntegers.length - 1] = (byte) same;

        return groupIntegers;

    }


        //method 4
        public static int getDecodedLength(byte[] rleData){
            //adds number of groups of integers**
            int groups = 0;
            for (int i = 0; i < rleData.length; i++) {
                if (i % 2 == 0) {
                    groups += rleData[i];
                }
            }

            return groups;
        }


    //method 5
    public static byte[] decodeRle(byte[] rleData){
        int size= 0;
        //determine size of return array
        for(int i = 0; i < rleData.length; i++){
            if(i % 2 == 0){
                size += rleData[i]; //increment size of the group array
            }
        }

        //index of flatDataArray
        int index = 0;
        byte[] flatDataArray = new byte[size];
        for (int i = 0; i < rleData.length; i += 2) { //loop only looks at even [group] indices of rleData

            //loop to add the amount of numbers to appropriate index. ex 15 15 15
            for (int j = 0; j < rleData[i]; j++) { //ends at the group index
                flatDataArray[index++] = rleData[i + 1]; //flat data index is the number index of rleData [not the group index]
            }
        }
        return flatDataArray;
    }

    //method 6
    public static byte[] stringToData(String dataString){
        //create new array
        byte[] hexArray = new byte[dataString.length()];
        //assign values to array from hex string
        for (int i = 0; i < dataString.length(); i++){
            //each element of array is character of string at index i
            hexArray[i] =  (byte) dataString.charAt(i);

            //if letters in string with corresponding values
            if (hexArray[i] >= 'a' && hexArray[i] <= 'f'){
                switch (hexArray[i]){ // i remembered switch statement :D
                    //each have a decimal representation of the hexadecimal value
                    case 'a':
                        hexArray[i] = 10;
                        break;
                    case 'b':
                        hexArray[i] = 11;
                        break;
                    case 'c':
                        hexArray[i] = 12;
                        break;
                    case 'd':
                        hexArray[i] = 13;
                        break;
                    case 'e':
                        hexArray[i] = 14;
                        break;
                    case 'f':
                        hexArray[i] = 15;
                        break;
                }

            } else { // i didnt remember switch statement :(
                //translating character value to integer value
                if (hexArray[i] == '0') {
                    hexArray[i] = 0;
                }
                if (hexArray[i] == '1') {
                    hexArray[i] = 1;
                }
                if (hexArray[i] == '2') {
                    hexArray[i] = 2;
                }
                if (hexArray[i] == '3') {
                    hexArray[i] = 3;
                }
                if (hexArray[i] == '4') {
                    hexArray[i] = 4;
                }
                if (hexArray[i] == '5') {
                    hexArray[i] = 5;
                }
                if (hexArray[i] == '6') {
                    hexArray[i] = 6;
                }
                if (hexArray[i] == '7') {
                    hexArray[i] = 7;
                }
                if (hexArray[i] == '8') {
                    hexArray[i] = 8;
                }
                if (hexArray[i] == '9') {
                    hexArray[i] = 9;
                }
            }
        }
        return hexArray;
    }

    //method 7
    public static String toRleString(byte[] rleData){
        String rleString = "";
        int length = 0;
        String value;
        for (int i = 0; i < rleData.length; i++){

            if (i % 2 == 0){
                length = rleData[i];
                rleString += length;

            }else {
                value = Integer.toString(rleData[i]);
                switch (value){
                    case "10":
                        value = "a";
                        break;
                    case "11":
                        value = "b";
                        break;
                    case "12":
                        value = "c";
                        break;
                    case "13":
                        value = "d";
                        break;
                    case "14":
                        value = "e";
                        break;
                    case "15":
                        value = "f";
                        break;

                }
                rleString += value;
                if (i != rleData.length - 1){
                    rleString += ':';
                }

            }
        }

        return rleString;
    }

    //method 8
    public static byte[] stringToRle(String rleString){

        //rleByteData[] is twice the amount of each string split
        String[] split = rleString.split(":");
        byte[] rleByteData = new byte[2 * split.length];

        //initializing variables
        byte value1 = 0;
        byte value2 = 0;
        String sub;
        int index = 0;

        for (int i = 0; i < split.length; i++){

            //iterate over each element of the split
            if (split[i].length() == 3){
                //substring of 1st value in split array
                sub = split[i].substring(0, 2);
                value1 = Byte.parseByte(sub);
                //substring in 2nd value of array is a letter convert to number
                sub = split[i].substring(2, 3);
                value2 = Byte.parseByte(sub, 16);
                //store in rleByteData[]
                rleByteData[index++] = value1;
                rleByteData[index++] = value2;


            } else {
                //substring of 1st value in split array
                sub = split[i].substring(0, 1);
                value1 = Byte.parseByte(sub);
                //substring in 2nd value of array is a letter convert to number
                sub = split[i].substring(1, 2);
                value2 = Byte.parseByte(sub, 16);
                //store in rleByteData[]
                rleByteData[index++] = value1;
                rleByteData[index++] = value2;


            }
        }
        return rleByteData;
    }

}
