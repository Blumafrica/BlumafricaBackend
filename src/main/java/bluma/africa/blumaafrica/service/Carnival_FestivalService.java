package bluma.africa.blumaafrica.service;

import bluma.africa.blumaafrica.dtos.requests.CreateCarnivalFestivalRequest;
import bluma.africa.blumaafrica.exceptions.AuthorityException;
import org.springframework.stereotype.Service;

@Service
public interface Carnival_FestivalService {

    String createCarnivalAndFestival(CreateCarnivalFestivalRequest request) throws AuthorityException;
}
