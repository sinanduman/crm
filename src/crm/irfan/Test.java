package crm.irfan;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Test {

    public static void main(String[] args) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        String digest = "123";
        MessageDigest md = MessageDigest.getInstance("MD5");
        ;
        byte[] hash = md.digest(digest.getBytes("UTF-8"));
        StringBuilder sb = new StringBuilder(2 * hash.length);
        for (byte b : hash) {
            sb.append(String.format("%02x", b & 0xff));
        }
        digest = sb.toString();

        System.out.println(digest);

    }

}
