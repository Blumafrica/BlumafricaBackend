package bluma.africa.blumaafrica.controllers;

import bluma.africa.blumaafrica.dtos.requests.*;
import bluma.africa.blumaafrica.dtos.responses.DeleteResponse;
import bluma.africa.blumaafrica.dtos.responses.FetchAdminPost;
import bluma.africa.blumaafrica.dtos.responses.FindUserResponse;
import bluma.africa.blumaafrica.dtos.responses.PostResponse;
import bluma.africa.blumaafrica.exceptions.AuthorityException;
import bluma.africa.blumaafrica.exceptions.BlumaException;
import bluma.africa.blumaafrica.exceptions.UserNotFound;
import bluma.africa.blumaafrica.service.AdminService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
@AllArgsConstructor
@Slf4j
public class AdminControllers {


    private AdminService adminService;


   @PostMapping("/api/v1/login/")
    public ResponseEntity<?> loginAsAdmin(@RequestBody LoginAsAdminRequest request){
       System.out.println("e enter the end point ooo ====> ");
       log.info("response ==> e enter the end point ooo ");
       LoginAsAdminResponse response = null;
       try {
           response = adminService.logInAsAdmin(request);
       } catch (BlumaException e) {
           return new ResponseEntity<>("incorrect details", HttpStatus.BAD_REQUEST);
       }
       System.out.println("No occurs  ====> ");
       log.info("passed::  error occurs ");
       return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
   }

   @PostMapping("/api/v1/post/")
    public ResponseEntity<?> post(@RequestBody PostRequest request){
       PostResponse response = null;
       try {
           response = adminService.post(request);
       } catch (BlumaException e) {
           return new ResponseEntity<>("error occurs", HttpStatus.CONFLICT);
       }

       return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
   }

   @GetMapping("/api/v1/getAdminPost")
   public ResponseEntity<?> getAllAdminPost(){
       FetchAdminPost response = adminService.fetchAllPost();
       return new ResponseEntity<>(response, HttpStatus.ACCEPTED );

   }

   @DeleteMapping("/api/v1/deletePost/")
    public ResponseEntity<?> deletePost(@RequestBody DeletePost deletePost){
     DeleteResponse response = null;

       try {
           response = adminService.deletePost(deletePost);
       } catch (BlumaException e) {
           return new ResponseEntity<>(e, HttpStatus.CONFLICT);
       }
       return new ResponseEntity<>(response, HttpStatus.OK);
   }

   @GetMapping("/api/getUser/")
    public ResponseEntity<?> getUser(@RequestBody FindUserRequest request ){

       try {
           FindUserResponse response = adminService.findUser(request);
           return new ResponseEntity<>(response, HttpStatus.FOUND);
       } catch (UserNotFound | AuthorityException e) {
           return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
       }
   }





}
