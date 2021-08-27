package com.lind.lindmanager.theme;

public interface ThemeSelectorProvider {

    /**
     * Return the theme name to use for the specified type
     *
     * @param type
     * @return
     */
    String getThemeName(Theme.Type type);

}