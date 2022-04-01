package pcrc.caferecommendbackend.category.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class CategoriesResponse {
    List<String> categoryList;
}
