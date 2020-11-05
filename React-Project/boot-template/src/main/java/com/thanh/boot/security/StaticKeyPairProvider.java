package com.thanh.boot.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class StaticKeyPairProvider implements KeyPairProvider {
    private static final String publicKeyContent = "-----BEGIN PUBLIC KEY-----\n" +
            "MIICIjANBgkqhkiG9w0BAQEFAAOCAg8AMIICCgKCAgEAsoDU92ch9AGM0ME6zZf/\n" +
            "rT9xVMToDEIYRrJCtnO5ngNwRjWCIC1jPFGHFEvCsGzwqFq16iBSkkD95ZMmRnax\n" +
            "cuK83hFCbCu77o+Yq3Raatv02aUJOWojzaeJsAsAmnMGOztTu2MsCncizi49/Bxw\n" +
            "OvSdxvZb0YPtSsfFQLFKWHG762clEUN8psmR3KF/N+lUW8gbO16Zibn0/MSqdazV\n" +
            "CO6iES6uzncDvbkq6q1jYX7yeyW8bjR00f6rMR1uU0gizETMGaMNQQUTLbQUAVwx\n" +
            "nqw70M//C/KrgiosALVNKTEd5Qg8A0fe0XUzQWUL/fUhNHnJp6Gsz5g/L6vzUuSq\n" +
            "idGK9BzL5tDccoLrmp47zsE40k3uBSDq8sXjqzdnDXoL+lxeua4iXcraJ1uv49Tg\n" +
            "GYf8N24/D4vYhG4zM1pfeUbuU44GhOTFKykbT/DtYo2xgHPDBGoYDl8kiCanmgyi\n" +
            "D6et+Zrt4j+oOLbkSua3xHXFSTg8YozqMGc7XKmk7fSGmSePTRZtMABJijIbrsYw\n" +
            "8n1F3l+B8V9SS+yRGfYR1vjBeNsaes/RPR04Y7h8X/zipjqrBCeo4nYpKrppqzU7\n" +
            "wcHT/IWY0sUl9A6WLfBzxLxXEE9iY77QoQgVBITAQjBn+4FqO664cNR/5zc14JQa\n" +
            "LT+1yjEIkw2mxwlyYnjX31MCAwEAAQ==\n" +
            "-----END PUBLIC KEY-----";

    private static final String privateKeyContent = "-----BEGIN PRIVATE KEY-----\n" +
            "MIIJQQIBADANBgkqhkiG9w0BAQEFAASCCSswggknAgEAAoICAQCygNT3ZyH0AYzQ\n" +
            "wTrNl/+tP3FUxOgMQhhGskK2c7meA3BGNYIgLWM8UYcUS8KwbPCoWrXqIFKSQP3l\n" +
            "kyZGdrFy4rzeEUJsK7vuj5irdFpq2/TZpQk5aiPNp4mwCwCacwY7O1O7YywKdyLO\n" +
            "Lj38HHA69J3G9lvRg+1Kx8VAsUpYcbvrZyURQ3ymyZHcoX836VRbyBs7XpmJufT8\n" +
            "xKp1rNUI7qIRLq7OdwO9uSrqrWNhfvJ7JbxuNHTR/qsxHW5TSCLMRMwZow1BBRMt\n" +
            "tBQBXDGerDvQz/8L8quCKiwAtU0pMR3lCDwDR97RdTNBZQv99SE0ecmnoazPmD8v\n" +
            "q/NS5KqJ0Yr0HMvm0NxyguuanjvOwTjSTe4FIOryxeOrN2cNegv6XF65riJdyton\n" +
            "W6/j1OAZh/w3bj8Pi9iEbjMzWl95Ru5TjgaE5MUrKRtP8O1ijbGAc8MEahgOXySI\n" +
            "JqeaDKIPp635mu3iP6g4tuRK5rfEdcVJODxijOowZztcqaTt9IaZJ49NFm0wAEmK\n" +
            "MhuuxjDyfUXeX4HxX1JL7JEZ9hHW+MF42xp6z9E9HThjuHxf/OKmOqsEJ6jidikq\n" +
            "ummrNTvBwdP8hZjSxSX0DpYt8HPEvFcQT2JjvtChCBUEhMBCMGf7gWo7rrhw1H/n\n" +
            "NzXglBotP7XKMQiTDabHCXJieNffUwIDAQABAoICAD3hOXK2lueerfEkOz1QZvrp\n" +
            "FmGbjRHKy92eKFBVl9A2QvKj1ANRmKgtXxVjGg/NBDRolc2e/ODklN/D/i3JMcSw\n" +
            "42ZcN1mGY7TUpMkzIbEPpTbZ5nO33seJP8r+VWw1WcCo1WMNgxPxrugo0aYXObTG\n" +
            "Xfe+KaBdCqK01CWUEEDO1KVLcWclrQ2orN7+QTrhsTPMD9yp87Hx/AKwLqNykbtx\n" +
            "9k7cjw7V5T8c9Mq75srClk6oi0Ts/F5b2X7CRB+szxte8h4lDxAuMTLTzWeXTUBD\n" +
            "Vs0RfcnnixspgW2RbDlOZJ2kGCHPBWg5J9aQr/KOyr8FRSnOezjP/3+ILWbcDF37\n" +
            "+yaqhD7v+mJMWh187SO3R6IlTDyrJy6MJ7bbvyt5C0VsEX+B+QfmHTI34VQ1AXsc\n" +
            "gXx3gJhlcGJT91B+bZ2NgqbFjHn7FSNsMxZr00rhIjHrHjNye1pjWMVsSv9ao4qL\n" +
            "0Bhqdas333YhyvYCzHgfK7JEIwyicRPVqdC56q5lFZEutsmGmNixj3y5F24nxlzp\n" +
            "5ngm/1uCKL7Pv3HQDiOVVMLDALyNeOuEhnhxm5jVmBupqrftynQBUsy9K41r7b3W\n" +
            "6MmBYTrScchZKDAVi9eB9gUGgC0aN7HN+Qf9LN9vwZinNYwB7oPOlITvCnMVW0RR\n" +
            "jyb3ePW8eBNM2KXmxbohAoIBAQDjtpPtOeVdFCTsDIteOmjsTbxy3x15fmhyx4ff\n" +
            "BPhEXPw6Vz3usQlbFZBc163zfXbvL57PzUrSek47FS6H+xZxYkgdy0/EwNcrNtay\n" +
            "x2twUrmDwEU7C91iFl13wE1TwcKdc8uIp4LD4LRj9gSMkAlU0Ea1Oq/OWOoiq9SY\n" +
            "NeMSC0nagaVQwgPy/W5KcDGWgssZHCNkqVxT55NOtlrQnrY+3Tu/TdGMhNSdi0/6\n" +
            "3XZjGxqlJyOBfqrhu+LIOUXQ9K/qebEAb3Cm6rzp/V2nZHehMLi/0jOtkijSefdL\n" +
            "Gzo6CwFwxNT89gH9XhQcaUsS4nMBb0zEu6BtfnXbNvnkL2aDAoIBAQDIrVjU3PS6\n" +
            "Yn4fwGafVnnZtAwTZPiV512y/LvUziFU7tI9Zn1R7ucrDdFF846irowJzN3t3H1i\n" +
            "prO47hRDFUukm311Q+xN5jbK1LmeUEH/azSJ8WM3lG95N31M2uLfrGXh0uqgI6u9\n" +
            "a28UqYThrrlGB/r5qt5p/grXv6FyZwcC2BabphCj2U/KLrUTtxTDL2cKs4Qh/FSH\n" +
            "ZZ32RbshFAd1L6HtTTlVoFgqjpYCphARXlUD4cSV9QLtLTifLHTykcHdOkQxqwJd\n" +
            "jR1CxFEpYs75IBxlu9hIzy+ABaX5JcjpBXmzdOaavoDbY0Z2EQOHV/U71g0HKK9F\n" +
            "dnEDjJBxR8rxAoIBADTNmJ3Q6BPeYPVvrz2pc70gBYvpmu8aVeQPyrz3bQ6FNWPd\n" +
            "6RmZad76MCCnTsoAlPy/4Sa0px4JiGyA6+pSWhDuxmRKSfQI95wouj54qbxBIo2f\n" +
            "ny4jATIGl6P2Lrg2XQef21mT5mm/iHUJjmJ93/wP0+WgdzX1Pk3N5J1rGX2MqpKF\n" +
            "r6z7YGkPRWzzeTw6lhf7g2WLNEKsl2cQOBzaQM5xBLLGu8nG33qs3F4CzroKhfI4\n" +
            "Ei6sDboMlHjzdvIOW2CW2WmNbeT219m2i+oODQOOxbiNaLheBSNRbvu2k5j0BmB8\n" +
            "9Bq4fRugni88Z1E4u4xgn7+a9oeNZIzxwZ6dMzsCggEAECiNF463Ee7uUuYuHTRc\n" +
            "WWA6/LDLp15a2BerlCYI3wrf2NvQyXEgIgJZ0HUdDhJZ6kwZrpZrbi8YF5LsBL7J\n" +
            "OXbfsjqG43re/JQGYi36MnPSZi0OnSIRm8QRDcguLm52Qam3BK/6myT0a8h+j6F+\n" +
            "/5mS5tmQUR6IhvjCKjELinoVz/CeNGNCHniGUhES1eiSNnVf9VCZCdHv1y2kNLSl\n" +
            "KRVtx2rJbBCeKMcUYyA1+QAMfmSGbMCuvKRAg+iTfhiocITJiupHJXtAWOjv1BJF\n" +
            "sh+YycRHVQ2BuKA7zgTx2Xl8ZCvMibc/LuseeFFEZHh7oZbJs8Ld4gz4ap2937E1\n" +
            "MQKCAQBRcgaxdNenGuG4coIIQQ9XZvrNdrudLS0KKcTR5808f2b1WEp+gmiZ3tsB\n" +
            "ytat0Yw6e8IjkhAXvV46s96vuHJW0nkg2/aZMr6e8zhKhysCk8KWzU4NPi/5dewL\n" +
            "9iA1SMgPx6lsykPw69yNg3lCksZ6VbZrw8VW5yJEqw7t0RrUjxTscoQ+E+r4kFyR\n" +
            "knDhyT5eed/35krIcv2BaiLrdfhsxB7TAIFxbJxp5Gkc1emTTADjYenC/m2kK2zh\n" +
            "MfcW6fqJNogiyzfAKEJolhSyaY+67AhRrtqqFtEmKfk1Xmn54FdbmsqOJuwHLR/A\n" +
            "j1K9f2xkTN0dBlk6Y5hNWnmdfTtO\n" +
            "-----END PRIVATE KEY-----";
    private static final Logger logger = LoggerFactory.getLogger(StaticKeyPairProvider.class);

    @Override
    public RSAPublicKey getRsaPublicKey() {
        //ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        //URL url = classLoader.getResource("/keys/public_key.pem");


        //if (url != null) {
        //
        //} else {
        //    logger.debug("Could not get url for public key file");
        //    return null;
        //}


        try {
            //Resource resource = new ClassPathResource("classpath:/keys/public_key.pem");
            //InputStream inputStream = resource.getInputStream();
            //
            //BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            //String line;
            //StringBuffer sb = new StringBuffer();
            //while ((line = reader.readLine()) != null){
            //    sb.append(line);
            //}
            String content = publicKeyContent;

            content = content.replaceAll("\\n", "").replace("-----BEGIN PUBLIC KEY-----", "").replace("-----END PUBLIC KEY-----", "");
            KeyFactory kf = KeyFactory.getInstance("RSA");
            X509EncodedKeySpec keySpecX509 = new X509EncodedKeySpec(Base64.getDecoder().decode(content));
            return (RSAPublicKey) kf.generatePublic(keySpecX509);
        } catch (Exception e) {
            logger.error("", e);
            return null;
        }
    }

    @Override
    public RSAPrivateKey getRsaPrivateKey() {
        try {
            String content = privateKeyContent;
            content = content.replaceAll("\\n", "").replace("-----BEGIN PRIVATE KEY-----", "").replace("-----END PRIVATE KEY-----", "");
            KeyFactory kf = KeyFactory.getInstance("RSA");

            PKCS8EncodedKeySpec keySpecPKCS8 = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(content));
            return (RSAPrivateKey) kf.generatePrivate(keySpecPKCS8);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            logger.error("", e);
            return null;
        }
    }
}
