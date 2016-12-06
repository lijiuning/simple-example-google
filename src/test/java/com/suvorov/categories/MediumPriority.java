package com.suvorov.categories;

import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;

/**
 * Created by vsuvorov on 12/5/16.
 */
@RunWith(Categories.class)
@Categories.IncludeCategory({MediumPriority.class})
public interface MediumPriority {}
