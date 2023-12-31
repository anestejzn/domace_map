package rs.ac.uns.ftn.siit.sw442019.graduate.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.servlet.http.HttpServletRequest;
import rs.ac.uns.ftn.siit.sw442019.graduate.exception.InvalidJWTException;
import static rs.ac.uns.ftn.siit.sw442019.graduate.security.JwtProperties.*;
import java.util.Date;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;
public class JWTUtils {
    public static String generateJWT(String email, String rawFingerPrint) {
        return JWT.create()
                .withSubject(email)
                .withExpiresAt(new Date(System.currentTimeMillis() + JwtProperties.EXPIRATION_TIME))
                .withClaim(FingerprintProperties.FINGERPRINT_CLAIM, FingerprintUtils.generateFingerprint(rawFingerPrint))
                .sign(HMAC512(JwtProperties.SECRET.getBytes()));
    }

    public static String extractTokenFromRequest(HttpServletRequest request) {
        return request.getHeader(JwtProperties.HEADER_STRING).replace(TOKEN_PREFIX, "");
    }

    public static DecodedJWT decodeToken(String token) {
        return JWT.require(HMAC512(SECRET.getBytes())).build().verify(token);
    }

    public static DecodedJWT extractJWTFromRequest(HttpServletRequest request) {
        return decodeToken(extractTokenFromRequest(request));
    }

    public static String extractEmailFromJWT(DecodedJWT jwt) throws InvalidJWTException {
        String email = jwt.getSubject();
        if (email == null)
            throw new InvalidJWTException("Cannot extract email/subject from jwt!");
        return email;
    }

}
