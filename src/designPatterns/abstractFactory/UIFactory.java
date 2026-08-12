package designPatterns.abstractFactory;

import designPatterns.abstractFactory.components.buttons.Button;
import designPatterns.abstractFactory.components.dropdowns.Dropdown;
import designPatterns.abstractFactory.components.menus.Menu;

public interface UIFactory {
    public Button createButton();
    public Menu createMenu();
    public Dropdown createDropDown();

}
