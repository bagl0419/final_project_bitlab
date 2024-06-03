package bitlab.final_project.mapper;

import bitlab.final_project.dto.UserCreate;
import bitlab.final_project.dto.UserUpdate;
import bitlab.final_project.dto.UserView;
import bitlab.final_project.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    UserView toView(User user);
    List<UserView> toView(List<User> users);
    User toEntity(UserCreate userCreate);
}
