package bitlab.final_project.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CountryView {
    private Long id;
    private String name;
    private String code;
}
