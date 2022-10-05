package com.authenticate.AuthenticateMe.service.implementation;

import com.authenticate.AuthenticateMe.dto.request.UserRequest;
import com.authenticate.AuthenticateMe.dto.response.Response;
import com.authenticate.AuthenticateMe.model.User;
import com.authenticate.AuthenticateMe.repository.UserRepo;
import com.authenticate.AuthenticateMe.security.EncryptionUtil;
import com.authenticate.AuthenticateMe.security.JWTUtils;
import com.authenticate.AuthenticateMe.service.UserService;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import java.util.Optional;
@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;
    private final ModelMapper modelMapper;

  JWTUtils jwtUtils;
    private  final BCryptPasswordEncoder bCryptPasswordEncoder;


    Response resp = new Response();

    Gson g= new Gson();


    @Override
    public Response signUp(UserRequest userRequest) {
        try {


            /*String cipherText = EncryptionUtil.encrypt(userRequest);
            log.info("Encrypted text "+ cipherText);
            String plainText = EncryptionUtil.decrypt(cipherText);
            log.info("Decrypted text "+ plainText);*/
            Optional<User> userOptional = userRepo.findByEmail(userRequest.getEmail());
            if (userOptional.isPresent()) {
                resp.setResp_Code("82");
                resp.setResp_Msg("User Already Exist, Please Login ");
                resp.setData(null);

            } else {
                User user = new User();
                userRequest.setPassword(bCryptPasswordEncoder.encode(userRequest.getPassword()));
                user= modelMapper.map(userRequest, User.class);
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
        Optional<User> userOptional = userRepo.findByEmail(userRequest.getEmail());
        if(userOptional.isPresent()){
            if(bCryptPasswordEncoder.matches(userRequest.getPassword(),userOptional.get().getPassword())){
                resp.setResp_Code("00");
                resp.setResp_Msg("Login Successful");
                resp.setToken(jwtUtils.generateToken(userOptional.get()));
            }
            else{
                resp.setResp_Code("84");
                resp.setResp_Msg("Invalid Password....Try Again");
            }

        }
        else{
            resp.setResp_Code("82");
            resp.setResp_Msg("Invalid Credentials");
        }
        return resp;

    }
}
