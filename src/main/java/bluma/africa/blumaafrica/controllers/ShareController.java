package bluma.africa.blumaafrica.controllers;

import bluma.africa.blumaafrica.dtos.requests.ShareRequest;
import bluma.africa.blumaafrica.dtos.responses.ShareResponse;
import bluma.africa.blumaafrica.exceptions.AuthorityException;
import bluma.africa.blumaafrica.exceptions.PostNotFound;
import bluma.africa.blumaafrica.service.ShareService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class ShareController {

    private final ShareService service;


    @PostMapping("/api/v1/share")
    public ResponseEntity<?> share(@RequestBody ShareRequest request ){

        try {
            ShareResponse response = service.share(request);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception | PostNotFound | AuthorityException e) {
            return new ResponseEntity<>(e, HttpStatus.CONFLICT);
        }

    }
}
