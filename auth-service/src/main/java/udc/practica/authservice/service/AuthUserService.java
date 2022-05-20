package udc.practica.authservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import udc.practica.authservice.dto.AuthUserDto;
import udc.practica.authservice.dto.TokenDto;
import udc.practica.authservice.entity.AuthUser;
import udc.practica.authservice.repository.AuthUserRepository;
import udc.practica.authservice.security.JwtProvider;

import java.util.Optional;

@Service
public class AuthUserService {

    @Autowired
    AuthUserRepository authUserRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtProvider jwtProvider;

    public AuthUser save(AuthUserDto dto){
        Optional<AuthUser> user = authUserRepository.findByuserName(dto.getUserName());
        if(user.isPresent()){
            return null;
        }
        String password = passwordEncoder.encode(dto.getPassword());
        AuthUser authUser = AuthUser.builder()
                .userName(dto.getUserName())
                .password(password).build();
        return authUserRepository.save(authUser);
    }

    public TokenDto login(AuthUserDto dto){
        Optional<AuthUser> user = authUserRepository.findByuserName(dto.getUserName());
        if(!user.isPresent()){
            return null;
        }
        if(passwordEncoder.matches(dto.getPassword(), user.get().getPassword())){
            return new TokenDto(jwtProvider.createToken(user.get()));
        }
        return null;
    }

    public TokenDto validate(String token){
        if(!jwtProvider.validate(token)){
            return null;
        }
        String username = jwtProvider.getUserNameFromToken(token);
        if(!authUserRepository.findByuserName(username).isPresent()){
            return null;
        }
        return new TokenDto(token);
    }


}
