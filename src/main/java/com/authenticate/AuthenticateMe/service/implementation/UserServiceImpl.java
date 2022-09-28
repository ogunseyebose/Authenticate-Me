package com.authenticate.AuthenticateMe.service.implementation;

import com.authenticate.AuthenticateMe.dto.request.UserRequest;
import com.authenticate.AuthenticateMe.dto.response.Response;
import com.authenticate.AuthenticateMe.model.User;
import com.authenticate.AuthenticateMe.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;
    private final ModelMapper modelMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    Response resp = new Response();

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
