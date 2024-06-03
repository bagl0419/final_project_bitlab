package bitlab.final_project.mapper;

import bitlab.final_project.dto.RoleCreate;
import bitlab.final_project.dto.RoleEdit;
import bitlab.final_project.dto.RoleView;
import bitlab.final_project.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface RoleMapper {
    RoleMapper INSTANCE = Mappers.getMapper(RoleMapper.class);
    RoleView toView(Role role);
    List<RoleView> toView(List<Role> roles);
    Role toEntity(RoleCreate roleCreate);
    void updateRoleFromDto(RoleEdit roleEdit, @MappingTarget Role role);
}
