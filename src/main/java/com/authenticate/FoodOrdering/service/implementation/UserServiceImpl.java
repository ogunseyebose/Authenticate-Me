package com.authenticate.FoodOrdering.service.implementation;

import com.authenticate.FoodOrdering.dto.request.UserRequest;
import com.authenticate.FoodOrdering.dto.response.Response;
import com.authenticate.FoodOrdering.model.User;

import com.authenticate.FoodOrdering.repository.UserRepo;
import com.authenticate.FoodOrdering.service.UserService;
import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl  implements UserDetailsService,UserService {
    private final UserRepo userRepo;
    private final ModelMapper modelMapper;

    //private final JWTUtils jwtUtils;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    Response resp = new Response();

    Gson g = new Gson();


    @Override
    public Response signUp(UserRequest userRequest) {
        try {
            Optional<User> userOptional = userRepo.findByEmail(userRequest.getEmail());
            if (userOptional.isPresent()) {
                resp.setResp_Code("82");
                resp.setResp_Msg("User Already Exist, Please Login ");
                resp.setData(null);

            } else {
                User user = new User();
                userRequest.setPassword(bCryptPasswordEncoder.encode(userRequest.getPassword()));
                user = modelMapper.map(userRequest, User.class);
                userRepo.save(user);
                resp.setResp_Code("00");
                resp.setResp_Msg("Success");
                resp.setData(user.getUserId());
            }
        } catch (Exception e) {
            resp.setResp_Code("99");
            resp.setResp_Msg("Failed");
            throw new RuntimeException(e);


        }
        return resp;
    }

    @Override
    public Response login(UserRequest userRequest) {
        User user= (User) loadUserByUsername(userRequest.getEmail());
        if (user!=null) {
            if (bCryptPasswordEncoder.matches(userRequest.getPassword(), user.getPassword())) {
                resp.setResp_Code("00");
                resp.setResp_Msg("Login Successful");
               // resp.setToken(jwtUtils.generateToken(user));
            } else {
                resp.setResp_Code("84");
                resp.setResp_Msg("Invalid Password....Try Again");
            }

        } else {
            resp.setResp_Code("82");
            resp.setResp_Msg("Invalid Credentials");
        }
        return resp;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return user;

    }
}