package bluma.africa.blumaafrica.service;

import bluma.africa.blumaafrica.data.models.Address;
import bluma.africa.blumaafrica.data.models.Carnival_Festival;
import bluma.africa.blumaafrica.data.repositories.AddressRepository;
import bluma.africa.blumaafrica.data.repositories.Carnival_FestivalRepository;
import bluma.africa.blumaafrica.dtos.requests.CreateCarnivalFestivalRequest;
import bluma.africa.blumaafrica.dtos.responses.CarnivalMapperResponse;
import bluma.africa.blumaafrica.dtos.responses.CarnivalResponse;
import bluma.africa.blumaafrica.exceptions.AuthorityException;
import bluma.africa.blumaafrica.mapper.Mapper;
import bluma.africa.blumaafrica.validators.Validate;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BlumaCarnival_FestivalService implements Carnival_FestivalService{

    private final Carnival_FestivalRepository repository;
    private final Validate validate;
    private final AddressRepository addressRepository;
    @Override
    public String createCarnivalAndFestival(CreateCarnivalFestivalRequest request) throws AuthorityException {
        validate.validateCarnivalAndFestivalRequest(request);
        CarnivalMapperResponse response = Mapper.map(request);
        Carnival_Festival carnivalFestival = response.getCarnivalFestival();
        Address address = response.getAddress();
        Address savedAddress = addressRepository.save(address);
        carnivalFestival.setAddress(savedAddress);
        repository.save(carnivalFestival);
        return "created";
    }
}
