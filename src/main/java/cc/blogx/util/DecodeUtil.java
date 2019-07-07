package cc.blogx.util;

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;

import cc.blogx.factory.NettyConfig;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class DecodeUtil {

    private static final Logger logger = LoggerFactory.getLogger(NettyConfig.class);

    public String signSHA256withRSAS(byte[] data, String privateKey) {
        String res = "";
        try {
            byte[] keyBytes = Base64.decodeBase64(privateKey);
            PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PrivateKey privateK = keyFactory.generatePrivate(pkcs8KeySpec);
            Signature signature = Signature.getInstance("SHA256withRSA");
            signature.initSign(privateK);
            signature.update(data);
            res = Base64.encodeBase64String(signature.sign());
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }
        return res;
    }

}
