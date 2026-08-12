package designPatterns.abstractFactory;

import designPatterns.abstractFactory.components.buttons.Button;

public class Client {
    public static void main(String[] args) {
        Flutter flutter = new Flutter(SupportedPlatforms.ANDROID);
//        AndroidUIFactory androidUIFactory = flutter.createAndroidUIFactory();
//        IOSUIFactory iosuiFactory = flutter.createIOSUIFactory();

        UIFactory uiFactory = flutter.createUIFactory();

        Button button = uiFactory.createButton();
        button.changeSize();

    }
}
