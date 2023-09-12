package rs.ac.uns.ftn.siit.sw442019.graduate.config;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.siit.sw442019.graduate.dto.response.UserResponse;
import rs.ac.uns.ftn.siit.sw442019.graduate.exception.EntityNotFoundException;
import rs.ac.uns.ftn.siit.sw442019.graduate.security.UserPrinciple;
import rs.ac.uns.ftn.siit.sw442019.graduate.service.implementation.UserService;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserService userService;

    public CustomUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
        public UserDetails loadUserByUsername(final String email) {
            UserResponse userDTO;
            try {
                userDTO = new UserResponse(userService.getVerifiedUser(email));
            } catch (EntityNotFoundException e) {
                return null;
            }

            return new UserPrinciple(userDTO);
        }


}
