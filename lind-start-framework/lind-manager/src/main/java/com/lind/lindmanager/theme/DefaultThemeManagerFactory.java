package com.lind.lindmanager.theme;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Component
public class DefaultThemeManagerFactory {

    @Value("${theme.cacheThemes}"
    )
    Boolean cacheThemes;
    private ConcurrentHashMap<ThemeKey, Theme> themeCache;

    public DefaultThemeManagerFactory() {
        if (cacheThemes) {
            themeCache = new ConcurrentHashMap<>();
        }
    }


    public Theme getCachedTheme(String name, Theme.Type type) {
        if (themeCache != null) {
            DefaultThemeManagerFactory.ThemeKey key = DefaultThemeManagerFactory.ThemeKey.get(name, type);
            return themeCache.get(key);
        } else {
            return null;
        }
    }

    public Theme addCachedTheme(String name, Theme.Type type, Theme theme) {
        if (theme == null) {
            return null;
        }

        if (themeCache == null) {
            return theme;
        }

        DefaultThemeManagerFactory.ThemeKey key = DefaultThemeManagerFactory.ThemeKey.get(name, type);
        if (themeCache.putIfAbsent(key, theme) != null) {
            theme = themeCache.get(key);
        }

        return theme;
    }

    public boolean isCacheEnabled() {
        return themeCache != null;
    }

    public void clearCache() {
        if (themeCache != null) {
            themeCache.clear();
            log.info("Cleared theme cache");
        }
    }

    public static class ThemeKey {

        private String name;
        private Theme.Type type;

        private ThemeKey(String name, Theme.Type type) {
            this.name = name;
            this.type = type;
        }

        public static ThemeKey get(String name, Theme.Type type) {
            return new ThemeKey(name, type);
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Theme.Type getType() {
            return type;
        }

        public void setType(Theme.Type type) {
            this.type = type;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            ThemeKey themeKey = (ThemeKey) o;

            if (name != null ? !name.equals(themeKey.name) : themeKey.name != null) return false;
            if (type != themeKey.type) return false;

            return true;
        }

        @Override
        public int hashCode() {
            int result = name != null ? name.hashCode() : 0;
            result = 31 * result + (type != null ? type.hashCode() : 0);
            return result;
        }

    }

}
