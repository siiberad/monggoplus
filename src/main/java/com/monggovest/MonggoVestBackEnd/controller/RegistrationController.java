package com.monggovest.MonggoVestBackEnd.controller;

import com.monggovest.MonggoVestBackEnd.model.UserModel;
import com.monggovest.MonggoVestBackEnd.repository.UserRepository;
import com.monggovest.MonggoVestBackEnd.service.EmailService;
import com.monggovest.MonggoVestBackEnd.service.MapValidationErrorService;
import com.monggovest.MonggoVestBackEnd.service.UserService;
import com.monggovest.MonggoVestBackEnd.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api" )
public class RegistrationController {
    @Autowired
    private MapValidationErrorService mapValidationErrorService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private EmailService emailService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody UserModel userModel, BindingResult result, HttpServletRequest request){

        userValidator.validate(userModel,result);

        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
        if(errorMap != null) {
            return errorMap;
        } else {

            // Disable user until they click on confirmation link in email
            userModel.setEnabled(false);

            // Generate random 36-character string token for confirmation link
            userModel.setConfirmationToken(UUID.randomUUID().toString());

            //Activate this if you're running on localhost
//            String appUrl = request.getScheme() + "://" + request.getServerName() + ":" +request.getServerPort();

            //If app is deployed, use this
            String appUrl = request.getScheme() + "://" + request.getServerName();

            SimpleMailMessage registrationEmail = new SimpleMailMessage();
            registrationEmail.setTo(userModel.getUsername());
            registrationEmail.setSubject("Selamat Datang di Monggovestplus - Konfirmasi E-mail Anda");
            registrationEmail.setText("Halo, " + userModel.getUserFullName() + "!\n\n" +
                    "Terimakasih telah bergabung dengan Monggovestplus.\n\n" +
                    "Mohon lakukan konfirmasi e-mail untuk mengaktifkan akun Anda, cukup dengan mengakses tautan di bawah ini:\n"
                    + appUrl + "/api/register/confirm?token=" + userModel.getConfirmationToken() + "\n\nHormat Kami, \nTim Monggovestplus");
            registrationEmail.setFrom("noreply@domain.com");

            emailService.sendEmail(registrationEmail);

            UserModel newUser = userService.saveUser(userModel);
            return new ResponseEntity<UserModel>(newUser, HttpStatus.CREATED);
        }
    }

    @RequestMapping(value = "register/confirm", method = {RequestMethod.GET, RequestMethod.POST})
    public String confirmRegistration(@RequestParam("token") String token) {

        UserModel userToken = userRepository.findByConfirmationToken(token);

        String LinkUrl = "<a href=\"http://monggovestplus.herokuapp.com\" target=\"_blank\">disini</a>";

        if (userToken != null) { // Token found
             userToken.setEnabled(true);
             userRepository.save(userToken);
             return "Akun Anda telah teraktivasi. Silahkan login melalui situs kami "+ LinkUrl;
            } else { // No token found in database
             return "invalidToken " + "Oops!  This is an invalid confirmation link.";
        }


    }
}
