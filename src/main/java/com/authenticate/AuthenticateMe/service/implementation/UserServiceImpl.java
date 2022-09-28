package com.authenticate.AuthenticateMe.service.implementation;


import com.authenticate.AuthenticateMe.dto.request.UserRequest;
import com.authenticate.AuthenticateMe.dto.response.Response;
import com.authenticate.AuthenticateMe.model.User;
import com.authenticate.AuthenticateMe.repository.UserRepo;
import com.authenticate.AuthenticateMe.service.UserService;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
@NoArgsConstructor
//@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private UserRepo userRepo;
    private ModelMapper modelMapper;
    private  BCryptPasswordEncoder bCryptPasswordEncoder;
    Response resp = new Response();

    public UserServiceImpl(UserRepo userRepo, ModelMapper modelMapper, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepo = userRepo;
        this.modelMapper = modelMapper;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public Response signUp(UserRequest userRequest) {
        try {
            Optional<User> userOptional = userRepo.findByEmail(userRequest.getEmail());
            if (userOptional.isPresent()) {
                resp.setResp_Code("82");
                resp.setResp_Msg("User Already Exist, Please Login ");

            } else {
                userRequest.setPassword(bCryptPasswordEncoder.encode(userRequest.getPassword()));
                User user = modelMapper.map(userRequest, User.class);
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
}
