package rs.ac.uns.ftn.siit.sw442019.graduate.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.siit.sw442019.graduate.dto.request.VerifyRequest;
import rs.ac.uns.ftn.siit.sw442019.graduate.dto.response.HouseholdResponse;
import rs.ac.uns.ftn.siit.sw442019.graduate.exception.EntityNotFoundException;
import rs.ac.uns.ftn.siit.sw442019.graduate.exception.WrongVerifyTryException;
import rs.ac.uns.ftn.siit.sw442019.graduate.service.implementation.RegularUserService;
import rs.ac.uns.ftn.siit.sw442019.graduate.service.interfaces.IRegularUserService;

@RestController
@RequestMapping("regular-users")
public class RegularUserController {

    @Autowired
    private IRegularUserService regularUserService;

    @GetMapping("/get-household/{userId}")
    @PreAuthorize("hasAuthority('GET_HOUSEHOLD')")
    @ResponseStatus(HttpStatus.OK)
    public HouseholdResponse getHousehold(@Valid @PathVariable Long userId) throws EntityNotFoundException {
        return regularUserService.getHouseholdForUser(userId);
    }
}
