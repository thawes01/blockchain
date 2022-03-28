/*
The following code was taken from the JetBrains Academy platform:
  * Declaration of the StringUtil class (the original version was package-scope,
    whereas the code below includes the public access modifier).
  * Declaration and implementation of the applySha256 static method within
    StringUtil
  * The required java.security.MessageDigest import statement

Specifically, this code was taken from

https://hyperskill.org/projects/50/stages/271/implement

as accessed on 2022-02-02. This code is supplied to the package author for
non-commercial use only.
 */

package Blockchain.utilities;

import java.security.MessageDigest;

public class StringUtil {
    /* Applies Sha256 to a string and returns a hash. */
    public static String applySha256(String input){
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            /* Applies sha256 to our input */
            byte[] hash = digest.digest(input.getBytes("UTF-8"));
            StringBuilder hexString = new StringBuilder();
            for (byte elem: hash) {
                String hex = Integer.toHexString(0xff & elem);
                if(hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        }
        catch(Exception e) {
            throw new RuntimeException(e);
        }
    }
}
