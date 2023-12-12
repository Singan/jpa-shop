package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Member;
import lombok.NoArgsConstructor;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
public interface MapperTest {


    @Select("select * from member")
//    @Results( id="userMap",value = {
//            @Result(property = "id",column = "member_id")
//    })
    List<Member> selectAll();
}
