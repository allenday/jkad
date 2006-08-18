import jKad.protocol.KadProtocol;

import java.math.BigInteger;
import java.nio.ByteBuffer;


public class Teste {
    public static void main(String[] args)
    {
        byte[] array = new byte[KadProtocol.NODE_ID_LENGTH];
        for(int i = 0; i < KadProtocol.NODE_ID_LENGTH; i++) {
            if(i % 2 == 0)array[i] = (byte)0xFF;
        }
        BigInteger b1 = new BigInteger(1, array);
        int bitCount = b1.bitLength();
        byte[] array2 = b1.toByteArray();
        byte[] array3 = formatByteArray(array2, KadProtocol.NODE_ID_LENGTH);
        System.out.println(toBinaryString(b1, KadProtocol.NODE_ID_LENGTH * 8));
    }
    
    public static byte[] formatByteArray(byte[] array, int bytes) {
        byte[] result = new byte[bytes];
        if(array.length >= bytes) {
            System.arraycopy(array, array.length - bytes, result, 0, bytes);
        } else {
            System.arraycopy(array, 0, result, bytes - array.length, array.length);
        }
        
        return result;
    }
    
    public static String toBinaryString(BigInteger data, int length) {
        String result = "";
        for(int i = 0; i < length; i++) {
            char bit;
            if(data.testBit(i)) bit = '1';
            else bit = '0';
            result = bit + result;
        }
        return result;
    }
}
