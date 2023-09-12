package rs.ac.uns.ftn.siit.sw442019.graduate.service.implementation;


import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.siit.sw442019.graduate.dto.response.LoginResponse;
import rs.ac.uns.ftn.siit.sw442019.graduate.dto.response.UserResponse;
import rs.ac.uns.ftn.siit.sw442019.graduate.exception.EntityNotFoundException;
import rs.ac.uns.ftn.siit.sw442019.graduate.exception.InvalidCredsException;
import rs.ac.uns.ftn.siit.sw442019.graduate.exception.InvalidJWTException;
import rs.ac.uns.ftn.siit.sw442019.graduate.security.FingerprintProperties;
import rs.ac.uns.ftn.siit.sw442019.graduate.security.FingerprintUtils;
import rs.ac.uns.ftn.siit.sw442019.graduate.security.JWTUtils;
import rs.ac.uns.ftn.siit.sw442019.graduate.security.UserPrinciple;
import rs.ac.uns.ftn.siit.sw442019.graduate.service.interfaces.IAuthService;
import rs.ac.uns.ftn.siit.sw442019.graduate.service.interfaces.IUserService;

@Service

public class AuthService implements IAuthService {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private EmailService emailService;
    @Autowired
    private IUserService userService;

    @Override
    public void logout(HttpServletRequest request) throws InvalidJWTException {
        DecodedJWT jwt = JWTUtils.extractJWTFromRequest(request);
        String email = JWTUtils.extractEmailFromJWT(jwt);
    }

    public LoginResponse loginProcess(final String email, final String password, final HttpServletResponse response) throws InvalidCredsException {
        Authentication authenticate;

        try {

            authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));

        } catch (Exception ignored) {

            throw new InvalidCredsException("Invalid creds!");
        }

        SecurityContextHolder.getContext().setAuthentication(authenticate);
        UserPrinciple userPrinciple = (UserPrinciple) authenticate.getPrincipal();
        UserResponse userResponse = userPrinciple.getUser();


        String rawFingerprint = createCookie(response);

        return new LoginResponse(JWTUtils.generateJWT(email, rawFingerprint), userResponse);
    }


    private String createCookie(HttpServletResponse response) {
        String rawFingerprint = FingerprintUtils.generateRandomRawFingerprint();

        Cookie cookie = new Cookie(FingerprintProperties.FINGERPRINT_COOKIE, rawFingerprint);
        cookie.setDomain("localhost");
        cookie.setPath("/");
        cookie.setMaxAge(3600*4);
        response.addCookie(cookie);

        return rawFingerprint;
    }

}
