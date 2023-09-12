package rs.ac.uns.ftn.siit.sw442019.graduate.service.interfaces;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import rs.ac.uns.ftn.siit.sw442019.graduate.dto.response.LoginResponse;
import rs.ac.uns.ftn.siit.sw442019.graduate.exception.*;

import java.io.IOException;


public interface IAuthService {
    void logout(HttpServletRequest request) throws InvalidJWTException;
    LoginResponse loginProcess(final String email, final String password, final HttpServletResponse response) throws InvalidCredsException;
}
