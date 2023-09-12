package rs.ac.uns.ftn.siit.sw442019.graduate.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.siit.sw442019.graduate.dto.request.LoginRequest;
import rs.ac.uns.ftn.siit.sw442019.graduate.dto.response.LoginResponse;
import rs.ac.uns.ftn.siit.sw442019.graduate.exception.EntityNotFoundException;
import rs.ac.uns.ftn.siit.sw442019.graduate.exception.InvalidCredsException;
import rs.ac.uns.ftn.siit.sw442019.graduate.exception.InvalidJWTException;
import rs.ac.uns.ftn.siit.sw442019.graduate.service.interfaces.IAuthService;

import java.io.IOException;

@RestController
@RequestMapping("/auth")
@Validated
public class AuthController {
    @Autowired
    private IAuthService authService;

    @PostMapping(path="/login")
    @ResponseStatus(HttpStatus.OK)
    public LoginResponse loginAdmin(@Valid @RequestBody final LoginRequest loginRequest, HttpServletResponse response)
            throws InvalidCredsException {

        return authService.loginProcess(loginRequest.getEmail(), loginRequest.getPassword(), response);
    }

    @PostMapping(path = "/logout")
    @ResponseStatus(HttpStatus.OK)
    public void logout(final HttpServletRequest request) throws InvalidJWTException {
        authService.logout(request);
    }

}
