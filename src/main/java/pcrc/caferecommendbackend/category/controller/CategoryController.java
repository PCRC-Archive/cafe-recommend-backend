package pcrc.caferecommendbackend.category.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pcrc.caferecommendbackend.category.dto.CategoriesResponse;
import pcrc.caferecommendbackend.category.service.CategoryService;

@RestController
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/category")
    public ResponseEntity<CategoriesResponse> getCategories() {
        CategoriesResponse response = categoryService.getCategoryList();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
