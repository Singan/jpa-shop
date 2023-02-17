package jpabook.jpashop.etc;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class MemberForm {
    
    
    @NotBlank(message ="아이디는 필수입니다.")
    private String name;
    
    
    
    private String city;
    private String street;
    private String zipcode;
}