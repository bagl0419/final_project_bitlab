package bitlab.final_project.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemCreate {
    private String name;
    private String description;
    private Double price;
}
