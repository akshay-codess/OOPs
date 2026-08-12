package designPatterns.abstractFactory;

import designPatterns.abstractFactory.components.buttons.Button;
import designPatterns.abstractFactory.components.buttons.IOSButton;
import designPatterns.abstractFactory.components.dropdowns.Dropdown;
import designPatterns.abstractFactory.components.dropdowns.IOSDropdown;
import designPatterns.abstractFactory.components.menus.IOSMenu;
import designPatterns.abstractFactory.components.menus.Menu;

public class IOSUIFactory implements UIFactory {

    @Override
    public IOSButton createButton() {
        return new IOSButton();
    }

    @Override
    public IOSMenu createMenu() {
        return new IOSMenu();
    }

    @Override
    public IOSDropdown createDropDown() {
        return new IOSDropdown();
    }
}
