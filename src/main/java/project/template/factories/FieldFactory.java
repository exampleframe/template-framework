package project.template.factories;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.reflections.Reflections;
import project.template.elements.AbstractElement;
import project.template.elements.CompositeElement;
import project.template.pages.AbstractPage;
import project.template.utils.Init;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.function.Predicate;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public final class FieldFactory {
    final static private Map<String, Class<? extends AbstractElement>> ELEMENT_CACHE = new HashMap<>();

    static {
        Reflections reflections = new Reflections("project.template.elements");
        for (Class<? extends AbstractElement> clazz : reflections.getSubTypesOf(AbstractElement.class)) {
            ELEMENT_CACHE.put(clazz.getName().split("^.*\\.")[1], clazz);
        }
    }

    public static AbstractElement getPageLoadElement(Class<? extends AbstractPage> page) {
        return getField(f -> f.getAnnotation(PageLoadElement.class) != null, page)
                .map(FieldFactory::getAbstractField).orElse(null);
    }

    public static AbstractElement getElement(String name, Class<? extends AbstractPage> page) {
        Optional<Field> field = getFields(f -> f.getAnnotation(FindBy.class) != null, page).stream()
                .filter(f -> f.getAnnotation(FindBy.class).name().equals(name)).findFirst();
        return getAbstractField(field.get());

    }

    public static List<AbstractElement> getElements(String name, Class<? extends AbstractPage> page) {
        Optional<Field> field = getFields(f -> f.getAnnotation(FindBy.class) != null, page).stream()
                .filter(f -> f.getAnnotation(FindBy.class).name().equals(name)).findFirst();
        return getAbstractFields(field.get());

    }

    private static AbstractElement getAbstractField(Field field) {
        SelenideElement element = $x(field.getAnnotation(FindBy.class).xpath());
        return contruct(field, element);
    }

    private static AbstractElement getAbstractField(Field field, SelenideElement parentEl) {
        if (field != null) {
            SelenideElement element = parentEl.$x(field.getAnnotation(FindBy.class).xpath());
            return contruct(field, element);
        } else return null;

    }

    private static AbstractElement contruct(Field field, SelenideElement element) {
        try {
            return (AbstractElement) field.getType().getConstructor(SelenideElement.class, String.class)
                    .newInstance(element, field.getAnnotation(FindBy.class).name());
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new Error("!!!!");
        }
    }

    private static List<AbstractElement> getAbstractFields(Field field) {
        List<SelenideElement> listField = $$x(field.getAnnotation(FindBy.class).xpath());
        List<AbstractElement> result = new ArrayList<>();

        for (SelenideElement e : listField) {
            try {
                result.add((AbstractElement) field.getType().getConstructor(SelenideElement.class, String.class)
                        .newInstance(e, field.getAnnotation(FindBy.class).name()));
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException er) {
                throw new Error("!!!!");
            }
        }
        return result;
    }


    private static List<Field> getFields(Predicate<Field> filter, Class<? extends AbstractPage> page) {
        List<Field> fields = new ArrayList<>();
        Collections.addAll(fields, page.getDeclaredFields());
        Arrays.stream(page.getAnnotation(PageEntry.class).containsPages())
                .flatMap(aClass -> Arrays.stream(aClass.getDeclaredFields()))
                .forEach(fields::add);

        return fields;
    }

    private static Optional<Field> getField(Predicate<Field> filter, Class<? extends AbstractPage> page) {
        return getFields(filter, page).stream().filter(filter).findFirst();
    }

    public static AbstractElement getSubElement(String elName, AbstractElement parentEl, Class<? extends AbstractPage> aClass) {
        Field findedField = getFields(f -> f.getAnnotation(FindBy.class) != null, aClass).stream()
                .filter(f -> f.getAnnotation(FindBy.class).name().equals(elName)).findAny().orElse(null);
        return getAbstractField(findedField, parentEl.element);
    }

    public static AbstractElement generator(String elName, AbstractElement parentEl) {
        Map<String, String> map = new HashMap<>();
        map.put("Button", ".//*[@*='" + elName + "' or text()='" + elName + "']/ancestor-or-self::button/parent::div");

        List<AbstractElement> tempList = new ArrayList<>();

        for (String key : map.keySet()) {
            List<SelenideElement> tmp = parentEl.element.$$x(map.get(key));
            for (SelenideElement el : tmp) {
                try {
                    tempList.add(ELEMENT_CACHE.get(key)
                            .getDeclaredConstructor(SelenideElement.class, String.class).newInstance(el, key));
                } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
                    throw new Error();
                }
            }
        }
        return tempList.size()==1?tempList.get(0):null;


    }
}
