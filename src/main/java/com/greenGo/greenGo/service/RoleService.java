package com.greenGo.greenGo.service;

import com.greenGo.greenGo.modele.ERole;
import com.greenGo.greenGo.modele.Role;

import java.util.List;
import java.util.Optional;

public interface RoleService {

    Role creer(Role role);

    List<Role> lire();

    Optional<Role> lireUn(Long id);

    Optional<Role> findByName(ERole name);

    Role modifier(Long id, Role role);

    String supprimer(Long id);
}
