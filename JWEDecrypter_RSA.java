import java.text.ParseException;

import java.io.*;
import java.security.*;
import java.security.spec.*;
import java.security.interfaces.*;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.*;
import com.nimbusds.jose.util.*;
import com.nimbusds.jwt.*;

public class JWEDecrypter_RSA {
  public static void main(String[] args) throws Exception {
    File keyFile = new File(args[1]);
    byte[] encodedKey = new byte[(int)keyFile.length()];
    new FileInputStream(keyFile).read(encodedKey);
    KeyFactory keyFactory = KeyFactory.getInstance("RSA");
    PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(encodedKey);
    PrivateKey privateKey = keyFactory.generatePrivate(privateKeySpec);

    EncryptedJWT jwe = EncryptedJWT.parse(args[0]);
    RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) privateKey;
    RSADecrypter decrypter = new RSADecrypter(rsaPrivateKey);
    jwe.decrypt(decrypter);
    System.out.print(jwe.getPayload());
  }
}
