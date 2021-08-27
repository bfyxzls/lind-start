package com.lind.lindmanager.theme.impl;

import com.lind.lindmanager.theme.Theme;
import com.lind.lindmanager.theme.ThemeSelectorProvider;
import org.springframework.stereotype.Component;

@Component
public class DefaultThemeSelectorProvider implements ThemeSelectorProvider {

    public static final String LOGIN_THEME_KEY = "login_theme";


    @Override
    public String getThemeName(Theme.Type type) {
        String name = null;

        switch (type) {
            case WELCOME:
                break;
            case LOGIN:
                break;
            case ACCOUNT:
                break;
            case EMAIL:
                break;
            case ADMIN:
                break;
        }

        if (name == null || name.isEmpty()) {
            name = "red";
        }

        return name;
    }


}

