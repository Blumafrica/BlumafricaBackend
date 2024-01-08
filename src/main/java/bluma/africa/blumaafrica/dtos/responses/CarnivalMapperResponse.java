package bluma.africa.blumaafrica.dtos.responses;

import bluma.africa.blumaafrica.data.models.Address;
import bluma.africa.blumaafrica.data.models.CarnivalFestival;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CarnivalMapperResponse {
    private CarnivalFestival carnivalFestival;
    private Address address;
}
