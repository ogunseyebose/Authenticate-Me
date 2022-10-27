package com.authenticate.FoodOrdering.service.implementation;

import com.authenticate.FoodOrdering.config.EmailService;
import com.authenticate.FoodOrdering.dto.request.UserRequest;
import com.authenticate.FoodOrdering.dto.response.Response;
import com.authenticate.FoodOrdering.exception.BadRequestException;
import com.authenticate.FoodOrdering.exception.GenericException;
import com.authenticate.FoodOrdering.exception.MailingException;
import com.authenticate.FoodOrdering.exception.SaveException;
import com.authenticate.FoodOrdering.model.Credentials;
import com.authenticate.FoodOrdering.model.Token;
import com.authenticate.FoodOrdering.model.User;
import com.authenticate.FoodOrdering.repository.UserRepo;
import com.authenticate.FoodOrdering.service.UserService;
import com.authenticate.FoodOrdering.utils.JwtUtils;
import com.authenticate.FoodOrdering.utils.Tokenize;
import com.authenticate.FoodOrdering.utils.encryption.EncryptionService;
import com.authenticate.FoodOrdering.utils.encryption.ResponseCodes;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Optional;


@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    @Value("${sender-mail}")
    private String sender;
    @Value("${picture}")
    private String picture;
    @Value("${signupSubject}")
    private String signUpSubject;
    @Value("${signupcontent}")
    private String signUpContent;
    private final UserRepo userRepo;
    private final ModelMapper modelMapper;
    private  AuthenticationManager authenticationManager;

    private final PasswordEncoder bCryptPasswordEncoder;
    private final JwtUtils jwtUtils;
    private final EncryptionService encryptionService;
    private final HttpServletRequest httpServletRequest;
    private final EmailService emailService;
    private final Tokenize tokenize;


    Response resp = new Response();

    @Override
    public Response signUp(UserRequest userRequest) throws BadRequestException {
        try {
        if(userRequest.getEmail()==null || userRequest.getPassword()==null){
            resp.setResp_Code(ResponseCodes.BAD_DATA.getCode());
            resp.setResp_Msg(ResponseCodes.BAD_DATA.getMessage());
            throw new BadRequestException(ResponseCodes.BAD_DATA,"Username or password cannot be empty ",HttpStatus.BAD_REQUEST);
        }
            Optional<User> userOptional = userRepo.findByEmail(userRequest.getEmail());
            if (userOptional.isPresent()) {
                resp.setResp_Code("82");
                resp.setResp_Msg("User already exist");
                //throw new GenericException(ResponseCodes.ALREADY_EXIST,"User already exist",HttpStatus.RESET_CONTENT);
            } else {
                User user = new User();
                userRequest.setPassword(bCryptPasswordEncoder.encode(userRequest.getPassword()))
                        .setAccountStatus("Y").setDtCreated(LocalDateTime.now());
                user = modelMapper.map(userRequest, User.class);
                userRepo.save(user);
               if(userRepo.findByEmail(userRequest.getEmail()).isPresent()){
                   resp.setResp_Code("00");
                   resp.setResp_Msg("Success");
                   resp.setData(userRepo.findByEmail(userRequest.getEmail()).get());
                       emailService.sendmail(sender, userRequest.getEmail(), signUpSubject, signUpContent);
               }
               else{
                   resp.setResp_Code("99");
                   resp.setResp_Msg("Couldn't Save the request");
                   //throw new SaveException(ResponseCodes.PROCESS_ERROR,"Couldn't Save the request",HttpStatus.INTERNAL_SERVER_ERROR);
               }
            }
        }/*catch (BadRequestException ex) {
            throw new GenericException(ResponseCodes.BAD_DATA, ex.getMessage(), HttpStatus.BAD_REQUEST);
        }*//*catch (GenericException ex) {
            throw new GenericException(ResponseCodes.ALREADY_EXIST, ex.getMessage(), HttpStatus.CREATED);
        }*//*catch (SaveException ex) {
            throw new GenericException(ResponseCodes.PROCESS_ERROR, ex.getMessage(), HttpStatus.SERVICE_UNAVAILABLE);
        }*/
        catch (Exception e) {
                throw new GenericException(ResponseCodes.PROCESS_ERROR, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        return resp;
    }

    @Override
    public Response login(UserRequest userRequest) {
        Token token = new Token();
        resp= new Response();
        Optional<User> user = userRepo.findByEmail(userRequest.getEmail());
        if (user.isPresent()) {
            if (bCryptPasswordEncoder.matches(userRequest.getPassword(), user.get().getPassword())) {
                resp.setResp_Code("00");
                resp.setResp_Msg("Login Successful");
                token = tokenize.extractToken(httpServletRequest);
                resp.setToken(token.getAccessToken());
            } else {
                resp.setResp_Code("84");
                resp.setResp_Msg("Invalid Credentials");
            }

        } else {
            resp.setResp_Code("82");
            resp.setResp_Msg("User does  not exist ");
        }
        return resp;
    }




}