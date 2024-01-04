package bluma.africa.blumaafrica.dtos.requests;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Setter
@Getter
public class CreateCarnivalFestivalRequest {

    private String name;
    private String authority;
    private String id;
    private String Day;
    private String month;
    private String year;
    private String time;
    private String State;
    private String city;
    private String street;
    private String about;
    private List<String> filesUrl;

}
