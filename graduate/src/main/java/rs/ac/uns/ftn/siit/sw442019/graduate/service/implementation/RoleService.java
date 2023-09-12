package rs.ac.uns.ftn.siit.sw442019.graduate.service.implementation;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.siit.sw442019.graduate.model.Role;
import rs.ac.uns.ftn.siit.sw442019.graduate.repository.RoleRepository;
import rs.ac.uns.ftn.siit.sw442019.graduate.service.interfaces.IRoleService;

@Service
public class RoleService implements IRoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role getRoleByName(String name) {
        return roleRepository.findRoleByRoleName(name);
    }
}
