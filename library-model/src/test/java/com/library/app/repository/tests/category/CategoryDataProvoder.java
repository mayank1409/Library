package com.library.app.repository.tests.category;

import com.library.app.model.Category;
import org.junit.Ignore;

import java.util.List;

@Ignore
public class CategoryDataProvoder {

    public static Category health() {
        return new Category("Health");
    }

    public static Category education() {
        return new Category("Education");
    }
    public static Category engineering() {
        return new Category("Engineering");
    }
    public static Category business() {
        return new Category("Business");
    }
    public static Category technology() {
        return new Category("Technology");
    }

    public static Category programming() {
        return new Category("Programming");
    }

    public static Category sports() {
        return new Category("Sports");
    }

}
