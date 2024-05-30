package bitlab.final_project.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemEdit {
    private Long id;
    private String name;
    private String description;
    private Double price;
}
