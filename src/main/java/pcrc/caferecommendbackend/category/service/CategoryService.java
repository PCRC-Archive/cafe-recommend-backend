package pcrc.caferecommendbackend.category.service;

import org.springframework.stereotype.Service;
import pcrc.caferecommendbackend.cafe.entity.Cafe;
import pcrc.caferecommendbackend.category.Category;
import pcrc.caferecommendbackend.category.dto.CategoriesResponse;

import java.util.*;

@Service
public class CategoryService {

    /** 현재 시스템에서 존재하는 카테고리들을 알려준다. **/
    /** Controller 전달용 **/
    public CategoriesResponse getCategoryList() {
        List<String> categoryList = new ArrayList<>();
        for(Category category : Category.values()) {
            categoryList.add(category.toString());
        }
        return new CategoriesResponse(categoryList);
    }

    /** Cafe 엔터티 1개에서 카테고리를 판독 후 무슨 카테고리인지 알려준다. **/
    public List<String> detectCategory(Cafe cafe) {
        List<Integer> fetchedCategoryCounts = this.abstractCategoryCount(cafe);
        Integer theBiggest = Collections.max(fetchedCategoryCounts);    // 최대값
        Map<Category, Integer> fetchedCateogryColumn = this.abstractCategory(cafe);     // map으로
        List<String> detectedCategory = new ArrayList<>();              // 결과 보관하기 위한.
        fetchedCateogryColumn.forEach( (key, val) -> {                  // 이제 하나씩 비교하면서 같은거 찾으면 대입입
            if(val.equals(theBiggest)) {
                detectedCategory.add(key.toString());
            }
        });
        return detectedCategory;
    }

    /** 순수하게 정수값만 들은걸 반환한다. 최대 값을 찾기 위한 거임**/
    public List<Integer> abstractCategoryCount(Cafe cafe) {
        List<Integer> categoryCount = new ArrayList<>();
        categoryCount.add(cafe.getLargeCategory());
        categoryCount.add(cafe.getTakeoutCategory());
        categoryCount.add(cafe.getSogayCategory());
        categoryCount.add(cafe.getCoffeeCategory());
        categoryCount.add(cafe.getDesertCategory());
        categoryCount.add(cafe.getInstaCategory());
        categoryCount.add(cafe.getQuietCategory());
        categoryCount.add(cafe.getDateCategory());
        categoryCount.add(cafe.getDriveCategory());
        categoryCount.add(cafe.getSightseeCategory());
        categoryCount.add(cafe.getTemesCategory());
        categoryCount.add(cafe.getLearningCategory());

        return categoryCount;
    }

    /** Cafe 엔터티에서 cateogry 칼럼만 빼내서 반환
     *  이거는 위에 함수들을 위한 abstracted **/
    public Map<Category, Integer> abstractCategory(Cafe cafe) {
        Map<Category, Integer> abstractedCategoryAttributes = new HashMap<Category, Integer>();

        // 이게 최선인가?
        abstractedCategoryAttributes.put(Category.큰규모, cafe.getLargeCategory());
        abstractedCategoryAttributes.put(Category.테이크아웃, cafe.getTakeoutCategory());
        abstractedCategoryAttributes.put(Category.소개팅, cafe.getSogayCategory());
        abstractedCategoryAttributes.put(Category.커피맛집, cafe.getCoffeeCategory());
        abstractedCategoryAttributes.put(Category.디저트맛집, cafe.getDesertCategory());
        abstractedCategoryAttributes.put(Category.인스타감성, cafe.getInstaCategory());
        abstractedCategoryAttributes.put(Category.조용한, cafe.getQuietCategory());
        abstractedCategoryAttributes.put(Category.데이트코스, cafe.getDateCategory());
        abstractedCategoryAttributes.put(Category.드라이브, cafe.getDriveCategory());
        abstractedCategoryAttributes.put(Category.경치좋은, cafe.getSightseeCategory());
        abstractedCategoryAttributes.put(Category.테마있는, cafe.getTemesCategory());
        abstractedCategoryAttributes.put(Category.공부맛집, cafe.getLearningCategory());

        return abstractedCategoryAttributes;
    }

}
