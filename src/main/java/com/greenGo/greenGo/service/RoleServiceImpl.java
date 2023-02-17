package com.greenGo.greenGo.service;

import com.greenGo.greenGo.modele.ERole;
import com.greenGo.greenGo.modele.Role;
import com.greenGo.greenGo.repository.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RoleServiceImpl implements RoleService{
    private final RoleRepository roleRepository;

    @Override
    public Role creer(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public List<Role> lire() {
        return roleRepository.findAll();
    }

    @Override
    public Optional<Role> lireUn(Long id) {
        return roleRepository.findById(id);
    }

    @Override
    public Optional<Role> findByName(ERole name) {
        return roleRepository.findByName(name);
    }

    @Override
    public Role modifier(Long id, Role role) {
        return roleRepository.findById(id)
                .map(p-> {
                    p.setName(role.getName());
                    return roleRepository.save(p);
                }).orElseThrow(() -> new RuntimeException("Step non trouvé"));
    }

    @Override
    public String supprimer(Long id) {
        roleRepository.deleteById(id);
        return "Step supprimé";
    }
}
