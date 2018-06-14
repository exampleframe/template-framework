package project.template.factories;

import project.template.pages.AbstractPage;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)

public @interface PageEntry {
    String title();

    Class<? extends AbstractPage>[] containsPages() default {};
}
