package rs.ac.uns.ftn.siit.sw442019.graduate.controller;


import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.siit.sw442019.graduate.dto.request.RegularUserRegistrationRequest;
import rs.ac.uns.ftn.siit.sw442019.graduate.dto.request.VerifyRequest;
import rs.ac.uns.ftn.siit.sw442019.graduate.dto.response.UserResponse;
import rs.ac.uns.ftn.siit.sw442019.graduate.exception.*;
import rs.ac.uns.ftn.siit.sw442019.graduate.service.interfaces.IUserService;

import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping("users")
public class UserController {
    @Autowired
    private IUserService userService;


    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse create(@Valid @RequestBody RegularUserRegistrationRequest request) throws PasswordsDoNotMatchException, EntityAlreadyExistsException, IOException, MailCannotBeSentException {
        return userService.create(
            request.getEmail(),
            request.getName(),
            request.getSurname(),
            request.getPassword(),
            request.getConfirmPassword()
        );
    }

    @PutMapping("/activate-account")
    @ResponseStatus(HttpStatus.OK)
    public boolean update(@Valid @RequestBody VerifyRequest verifyRequest) throws WrongVerifyTryException, EntityNotFoundException {
        return userService.activate(verifyRequest.getVerifyId(), verifyRequest.getSecurityCode());
    }

}
