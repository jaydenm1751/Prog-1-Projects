import java.util.Arrays;

public class Examples {
    public static int getDecodedLength(byte[] rleData) {
        int length = 0;
        for (int i = 0; i < rleData.length; i += 2) {
            length += rleData[i];
        }
        return length;
    }

    public static byte[] decodeRle(byte[] rleData) {
        int len = getDecodedLength(rleData);
        byte[] arr = new byte[len];
        int index = 0;
        for (int i = 0; i < rleData.length; i += 2) {
            for (int j = 0; j < rleData[i]; j++) {
                arr[index++] = rleData[i + 1];
            }
        }
        return arr;
    }

    public static void main(String[] args) {
       // System.out.println(Arrays.toString(decodeRle(new byte[]{ 1,1,1,2,1,3,1,4,1,1,1,2,1,3,1,4 })));
        System.out.println(Arrays.toString(stringToRle("15f:64")));
    }

    public static byte[] stringToRle(String rleString) {
        String[] tokens = rleString.split(":");
        byte[] result = new byte[2 * tokens.length];
        char ch;
        for (int i = 0; i < tokens.length; i++) {
            result[2 * i] = Byte.parseByte(tokens[i].substring(0, tokens[i].length() - 1));
            ch = tokens[i].charAt(tokens[i].length() - 1);
            if (ch <= '9') {
                result[2 * i + 1] = (byte) (ch - '0');
            } else {
                result[2 * i + 1] = (byte) (10 + (ch - 'a'));
            }
        }
        return result;
    }

    /*
    public static byte[] encodeRle(byte[] flatData) {
        int count = 0;
        for (int i = 0; i < flatData.length; ++i) {
            if (i != 0 && flatData[i] != flatData[i - 1]) {
                count++;
            }
        }
        count++;
        byte[] arr = new byte[2 * count];
        count = 0;
        int index = 0;
        for (int i = 0; i < flatData.length; ++i) {
            if (i != 0 && flatData[i] != flatData[i - 1]) {
                arr[index++] = (byte) count;
                arr[index++] = flatData[i - 1];
                count = 1;
            } else {
                count++;
            }
            if (i == flatData.length - 1) {
                arr[index++] = (byte) count;
                arr[index++] = flatData[i];
            }
        }
        return arr;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(encodeRle(new byte[]{ 1,1,1,2,1,3,1,4,1,1,1,2,1,3,1,4 })));
    }

     */
}


