package com.csci.easypass.library;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.BreakIterator;

/**
 * Created by james on 05/08/14.
 */
public class HashGenerator {
 public StringBuffer computeMD5Hash(String password)
    {

        try {
            // Create MD5 Hash
            MessageDigest digest = java.security.MessageDigest.getInstance("MD5");
            digest.update(password.getBytes());
            byte messageDigest[] = digest.digest();

            StringBuffer MD5Hash = new StringBuffer();
            for (int i = 0; i < messageDigest.length; i++)
            {
                String h = Integer.toHexString(0xFF & messageDigest[i]);
                while (h.length() < 2)
                    h = "0" + h;
                MD5Hash.append(h);
            }

           return MD5Hash;

            }
            catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return null;

    }

}
