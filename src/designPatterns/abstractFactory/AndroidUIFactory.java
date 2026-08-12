package designPatterns.abstractFactory;

import designPatterns.abstractFactory.components.buttons.AndroidButton;
import designPatterns.abstractFactory.components.dropdowns.AndroidDropdown;
import designPatterns.abstractFactory.components.menus.AndroidMenu;

public class AndroidUIFactory implements UIFactory {
    @Override
    public AndroidButton createButton() {
        return new AndroidButton();
    }

    @Override
    public AndroidMenu createMenu() {
        return new AndroidMenu();
    }

    @Override
    public AndroidDropdown createDropDown() {
        return new AndroidDropdown();
    }

    public UIFactory createUIFactory() {
        return this;
    }
}
