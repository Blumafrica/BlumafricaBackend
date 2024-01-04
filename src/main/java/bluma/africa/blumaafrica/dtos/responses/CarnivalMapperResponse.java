package bluma.africa.blumaafrica.dtos.responses;

import bluma.africa.blumaafrica.data.models.Address;
import bluma.africa.blumaafrica.data.models.Carnival_Festival;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CarnivalMapperResponse {
    private Carnival_Festival carnivalFestival;
    private Address address;
}
