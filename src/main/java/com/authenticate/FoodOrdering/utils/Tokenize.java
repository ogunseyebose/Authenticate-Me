package com.authenticate.FoodOrdering.utils;

import com.authenticate.FoodOrdering.exception.GenericException;
import com.authenticate.FoodOrdering.model.Credentials;
import com.authenticate.FoodOrdering.model.Token;
import com.authenticate.FoodOrdering.utils.encryption.EncryptionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
@Service
@Slf4j
@RequiredArgsConstructor
public class Tokenize {
    private final EncryptionService encryptionService;
    private final JwtUtils jwtUtils;
    public com.authenticate.FoodOrdering.model.Token extractToken(HttpServletRequest httpServletRequest) throws GenericException {

        Token token = new Token();
        try {
            Credentials credentials = encryptionService.extractKeys(httpServletRequest);
            encryptionService.isCredentialValid(credentials.getClientid(), credentials.getClientsecret(),
                    credentials.getSourcecode());

            String clientId = credentials.getClientid();

            token = jwtUtils.createToken(clientId);

        } catch (Exception ex) {
            log.error("User Add Failed", ex);
            log.error("error extracting token in the service ");
            //throw new GenericException(ResponseCodes.PROCESS_ERROR, ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);

        }
        return token;
    }}

