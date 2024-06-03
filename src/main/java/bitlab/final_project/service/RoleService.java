package bitlab.final_project.service;

import bitlab.final_project.dto.RoleCreate;
import bitlab.final_project.dto.RoleEdit;
import bitlab.final_project.dto.RoleView;
import bitlab.final_project.entity.Role;
import bitlab.final_project.mapper.RoleMapper;
import bitlab.final_project.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;

    public RoleView getRoleById(Long id) {
        Role role = roleRepository.findById(id).orElse(null);
        return RoleMapper.INSTANCE.toView(role);
    }

    public List<RoleView> getAllRoles() {
        List<Role> roles = roleRepository.findAll();
        return RoleMapper.INSTANCE.toView(roles);
    }

    public RoleView createRole(RoleCreate roleCreate) {
        Optional<Role> optionalRole = roleRepository.findByName(roleCreate.getName());
        if (optionalRole.isPresent()) {
            throw new RuntimeException("Эта роль уже существует!");
        }
        Role role = RoleMapper.INSTANCE.toEntity(roleCreate);
        Role newRole = roleRepository.save(role);
        return RoleMapper.INSTANCE.toView(newRole);
    }

    public RoleView editRole(RoleEdit roleEdit) {
        Optional<Role> optionalRole = roleRepository.findById(roleEdit.getId());

        if (optionalRole.isPresent()) {
            Role role = optionalRole.get();
            RoleMapper.INSTANCE.updateRoleFromDto(roleEdit, role);
            Role updatedRole = roleRepository.save(role);
            return RoleMapper.INSTANCE.toView(updatedRole);
        } else {
            throw new RuntimeException("Роль с ID: " + roleEdit.getId() + " не найден!");
        }
    }

    public void deleteRoleById(Long id) {
        roleRepository.deleteById(id);
    }
}
