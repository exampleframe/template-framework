package project.template.factories;

import org.reflections.Reflections;
import project.template.pages.AbstractPage;
import java.util.HashMap;
import java.util.Map;

public final class PageFactory {
    private static AbstractPage currentPage;
    private static final Map<String, Class<? extends AbstractPage>> PAGES_CACHE = new HashMap<>();

    static {
        Reflections reflections = new Reflections("project.template.pages");
        for (Class<? extends AbstractPage> clazz : reflections.getSubTypesOf(AbstractPage.class)) {
            PageEntry entry = clazz.getAnnotation(PageEntry.class);
            if (entry == null) {
                continue;
            }
            PAGES_CACHE.put(entry.title(), clazz);
        }
    }

    private <T> T getPage(Class<T> clazz, Boolean... checkError) {
        final T page = construct(clazz);
        set((AbstractPage) page);
        return page;
    }

    public AbstractPage getPage(String name) {
        return get(name);
    }

    public AbstractPage getCurrentPage() {
        return currentPage;
    }

    private static void set(AbstractPage page) {
        currentPage = page;
    }

    private AbstractPage get(String name, Boolean... checkError) {
        final Class<? extends AbstractPage> clazz = PAGES_CACHE.get(name);
        if (clazz == null) {
            throw new NullPointerException("Страница \"" + name + "\" не найдена.");
        }

        return getPage(clazz, checkError);
    }

    private <T> T construct(Class<T> clazz) {
        try {
            return clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new Error("При создании страницы произошла ошибка.", e);
        }
    }

}
