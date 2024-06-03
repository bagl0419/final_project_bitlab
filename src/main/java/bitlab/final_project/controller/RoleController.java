package bitlab.final_project.controller;

import bitlab.final_project.dto.RoleCreate;
import bitlab.final_project.dto.RoleEdit;
import bitlab.final_project.dto.RoleView;
import bitlab.final_project.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/role")
@RequiredArgsConstructor
public class RoleController {
    private final RoleService roleService;

    @GetMapping("{id}")
    public RoleView getRoleById(@PathVariable Long id) {
        return roleService.getRoleById(id);
    }

    @GetMapping
    public List<RoleView> getAllRoles() {
        return roleService.getAllRoles();
    }

    @PostMapping
    public RoleView createRole(@RequestBody RoleCreate roleCreate) {
        return roleService.createRole(roleCreate);
    }

    @PutMapping
    public RoleView editRole(@RequestBody RoleEdit roleEdit) {
        return roleService.editRole(roleEdit);
    }

    @DeleteMapping("{id}")
    public void deleteRole(@PathVariable Long id) {
        roleService.deleteRoleById(id);
    }
}
