package com.greenGo.greenGo.service;

import com.greenGo.greenGo.modele.User;
import com.greenGo.greenGo.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;

    @Override
    public User creer(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> lire() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> lireUn(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public Boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }
    @Override
    public Optional<User> findByUsername(String username) { return  userRepository.findByUsername(username);}
    @Override
    public Boolean existsByUsername(String username) { return userRepository.existsByUsername(username);}

    @Override
    public User modifier(Long id, User user) {
        return userRepository.findById(id)
                .map(p-> {
                    p.setEmail(user.getEmail());
                    p.setName(user.getName());
                    p.setSurname(user.getSurname());
                    p.setRoles(user.getRoles());
                    return userRepository.save(p);
                }).orElseThrow(() -> new RuntimeException("Step non trouvé"));
    }

    @Override
    public String supprimer(Long id) {
        userRepository.deleteById(id);
        return "Step supprimé";
    }
}