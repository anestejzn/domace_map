package rs.ac.uns.ftn.siit.sw442019.graduate.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.siit.sw442019.graduate.exception.EntityNotFoundException;
import rs.ac.uns.ftn.siit.sw442019.graduate.exception.MailCannotBeSentException;
import rs.ac.uns.ftn.siit.sw442019.graduate.service.implementation.VerificationService;

import java.io.IOException;

import static rs.ac.uns.ftn.siit.sw442019.graduate.util.Constants.WRONG_VERIFY_HASH;

@RestController
@RequestMapping("verify")
public class VerificationController {
    @Autowired
    private VerificationService verificationService;

    @PostMapping("/send-code-again")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@Valid @NotNull(message = WRONG_VERIFY_HASH) @RequestBody String verifyHash) throws MailCannotBeSentException, IOException, EntityNotFoundException {
        this.verificationService.generateNewSecurityCode(verifyHash);
    }


}
