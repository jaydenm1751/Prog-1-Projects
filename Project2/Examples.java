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
        System.out.println(Arrays.toString(decodeRle(new byte[]{3, 15, 6, 4})));
    }
}
}
