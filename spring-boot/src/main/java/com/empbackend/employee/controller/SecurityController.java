package com.empbackend.employee.controller;

import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.RSAPublicKeySpec;

import java.math.BigInteger;

import java.util.Base64;

import java.security.KeyFactory;

import org.json.JSONObject;
import org.json.JSONArray;

public class SecurityController {
    public RSAPublicKey getPublicKey(String accessToken) {
        try {
            // Extract the kid (Key ID) from the access token
            System.out.println("Access token came in Security Controller " + accessToken);
            String kid = "GqQZa7aDUGG8UMeQgKgPl";
            System.out.println("Kid was extracted from token in Security COntroller " + kid);
            // Make a GET request to the JWKS URL
            // OkHttpClient client = new OkHttpClient();
            // Request request = new Request.Builder()
            // .url("https://dev-lucifer-morningstar-ptc.us.auth0.com/.well-known/jwks.json")
            // .get()
            // .build();

            // Response response = client.newCall(request).execute();
            String responseBody = "{\"keys\":[{\"kty\":\"RSA\",\"use\":\"sig\",\"n\":\"yYIcnqinaPe-MVOXwH0QIlgHEXZS-2YsEt7lCR_nfxTenp6_Z9o6ySVLhwPk7VEWdp64eSwRkB6WDhLUW4TSJPyN8ngYnQhYL6Zk66I5kIcsqswM4hEyJlmz_yqK5DKobKd-03IuJy5fxAAAUj8tAj8HMAEVCl0n_jXAGwM390ENtTF-N7FxFmQSvWob28yRxAdIY-YI-ZZRiCT4Wbew9DMxypy3J-bwledb6DbVr_xnQZ6N-MCFSnXB_dMToCFUismjgF74-RDU94Xv_eII4O6XMcuhyxkP9D_TsrEt_N1VqMHsC5PFzYpOaU4dCUAGF7Z8vNbXavyYN_vT0VpiLQ\",\"e\":\"AQAB\",\"kid\":\"GqQZa7aDUGG8UMeQgKgPl\",\"x5t\":\"pSurZIzr5B73UuqL3jJc7OmwTxQ\",\"x5c\":[\"MIIDKzCCAhOgAwIBAgIJIjSSCfXY+21pMA0GCSqGSIb3DQEBCwUAMDMxMTAvBgNVBAMTKGRldi1sdWNpZmVyLW1vcm5pbmdzdGFyLXB0Yy51cy5hdXRoMC5jb20wHhcNMjMxMDEyMDkwMTU4WhcNMzcwNjIwMDkwMTU4WjAzMTEwLwYDVQQDEyhkZXYtbHVjaWZlci1tb3JuaW5nc3Rhci1wdGMudXMuYXV0aDAuY29tMIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAyYIcnqinaPe+MVOXwH0QIlgHEXZS+2YsEt7lCR/nfxTenp6/Z9o6ySVLhwPk7VEWdp64eSwRkB6WDhLUW4TSJPyN8ngYnQhYL6Zk66I5kIcsqswM4hEyJlmz/yqK5DKobKd+03IuJy5fxAAAUj8tAj8HMAEVCl0n/jXAGwM390ENtTF+N7FxFmQSvWob28yRxAdIY+YI+ZZRiCT4Wbew9DMxypy3J+bwledb6DbVr/xnQZ6N+MCFSnXB/dMToCFUismjgF74+RDU94Xv/eII4O6XMcuhyxkP9D/TsrEt/N1VqMHsC5PFzYpOaU4dCUAGF7Z8vNbXavyYN/vT0VpiLQIDAQABo0IwQDAPBgNVHRMBAf8EBTADAQH/MB0GA1UdDgQWBBSAk7xmT8U3jXeXkFxUzhYKYmY8lDAOBgNVHQ8BAf8EBAMCAoQwDQYJKoZIhvcNAQELBQADggEBABmujrMbdHxKxmYwCBg33b6PvSpNGcS+smhIGmGr0nDH0mQIgzkXjPl+sDymGJPzz25skLl9Qjda9hs3hnVESIRfndqjI4UjX0vbFP1XyVevGmGxnGg9GxFmWo/VRjEuEwslhet0puCQqHm71BnYg6QRxsH19X3315aVKQvNxJ/fnfNeLnMRocCsbKCRvXxLX6FEaPZdWnJWZKQcYBlBRTGFjOdGm+R+EghrfabbS0+aVcLUE4kc4myvD9CtUu9/Xs3xH+QXYZxmvxhAnFtM/YKh12ChAzaQwar7B4gDXvINDMNZfO3xkfqCvTPX60AfyugpR3HtqhC8AAr0LLXeSWY=\"],\"alg\":\"RS256\"},{\"kty\":\"RSA\",\"use\":\"sig\",\"n\":\"o99sTTJ9Z2lG383LHnY6Xeq6Lkq4RSt6tyBXPROeXPB44d1cmnyk1sBIdhDw6MxLUk0o6kPk2rUdpRO3Wt4IsAnfF9cCIwQXRVgdn5yGNVs9rrVfminyaVTvA_DsdqbUZ3tYG0imPhd7yy-1CySKImmcWjnOjot_bnJal5j3_W1_4oJ6FImlwVD9BRisuNHNXQsZhXaFsUB4tBlM_66YCd8YyWm4D6DiAHwyFojwuHZPOteAdiozwtbDF_KEjwUPKUXpRC8BvRXRY2rF4R343AsV2C8OxIQw-5kx4Stqmzk6EU4-9AckPFNBsgs0ZrNn5fUCnOPZn7Z7dvMPNpFrxQ\",\"e\":\"AQAB\",\"kid\":\"LZB1R6pX1xm4RBSrSHpbD\",\"x5t\":\"DX4dJ0b8eV02Thw-96-D6uZwe-w\",\"x5c\":[\"MIIDKzCCAhOgAwIBAgIJH2+9hWm+vK7QMA0GCSqGSIb3DQEBCwUAMDMxMTAvBgNVBAMTKGRldi1sdWNpZmVyLW1vcm5pbmdzdGFyLXB0Yy51cy5hdXRoMC5jb20wHhcNMjMxMDEyMDkwMTU5WhcNMzcwNjIwMDkwMTU5WjAzMTEwLwYDVQQDEyhkZXYtbHVjaWZlci1tb3JuaW5nc3Rhci1wdGMudXMuYXV0aDAuY29tMIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAo99sTTJ9Z2lG383LHnY6Xeq6Lkq4RSt6tyBXPROeXPB44d1cmnyk1sBIdhDw6MxLUk0o6kPk2rUdpRO3Wt4IsAnfF9cCIwQXRVgdn5yGNVs9rrVfminyaVTvA/DsdqbUZ3tYG0imPhd7yy+1CySKImmcWjnOjot/bnJal5j3/W1/4oJ6FImlwVD9BRisuNHNXQsZhXaFsUB4tBlM/66YCd8YyWm4D6DiAHwyFojwuHZPOteAdiozwtbDF/KEjwUPKUXpRC8BvRXRY2rF4R343AsV2C8OxIQw+5kx4Stqmzk6EU4+9AckPFNBsgs0ZrNn5fUCnOPZn7Z7dvMPNpFrxQIDAQABo0IwQDAPBgNVHRMBAf8EBTADAQH/MB0GA1UdDgQWBBT1EnQ0XMTREmqJaq0Lfl45MbTG8TAOBgNVHQ8BAf8EBAMCAoQwDQYJKoZIhvcNAQELBQADggEBAB4scRmXNPVxtTVye6+xzGe0ixD6qOcRXkpcCxAXoQFNFUaoLtpgs90FDy94W4NLJ81Olcla8ZDGJKrq4CdcQVfV9Om7GjgZKpdWh8qxd7m6r2ud7nNJxtpltsT1veae32rgXGK/8yuW/FxWJlOZ2zELr0jNiie48K4oi1rol2Hx1mgyOmSZLdvS6lBAdhM00Mn6O5fmmewZaZKGVJ0wsc0UxTsD4vH9Ck32oSuopFfI57Me/VlHLGoCCj1KIl/jOD5bew80HbPxm1VDJ4kH6JU1lUkNZnJrrwUkQ2rdnq1wV/gfn3tErZ+rYnAJYiMkNmYaKAY4PdDUMlzglYFCTvg=\"],\"alg\":\"RS256\"}]}";

            // Parse the JSON response
            JSONObject jwksJson = new JSONObject(responseBody);
            System.out.println("jwks json was fetched dynamically in Security COntroller" + jwksJson);
            JSONArray keys = jwksJson.getJSONArray("keys");
            System.out.println("jkeys array was formed in Security COntroller" + keys);

            // Find the key with the matching kid
            RSAPublicKey publicKey = null;
            for (int i = 0; i < keys.length(); i++) {
                JSONObject key = keys.getJSONObject(i);
                if (kid.equals(key.getString("kid"))) {
                    publicKey = getRSAPublicKey(key);
                    break;
                }
            }
            System.out.println("Public key was made in Security COntroller" + publicKey);
            return publicKey;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // private String extractKidFromAccessToken(String accessToken) {
    // // Extract and decode the JWT header
    // String[] parts = accessToken.split("\\.");
    // String headerJson = new String(Base64.getUrlDecoder().decode(parts[0]));
    // JSONObject header = new JSONObject(headerJson);

    // // Get the kid from the header
    // return header.getString("kid");
    // }

    private RSAPublicKey getRSAPublicKey(JSONObject key) throws Exception {
        byte[] modulusBytes = Base64.getUrlDecoder().decode(key.getString("n"));
        byte[] exponentBytes = Base64.getUrlDecoder().decode(key.getString("e"));

        RSAPublicKeySpec rsaPublicKeySpec = new RSAPublicKeySpec(
                new BigInteger(1, modulusBytes),
                new BigInteger(1, exponentBytes));

        try {
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            return (RSAPublicKey) keyFactory.generatePublic(rsaPublicKeySpec);
        } catch (NoSuchAlgorithmException e) {
            throw new Exception("Failed to create RSA KeyFactory", e);
        }
    }
}
