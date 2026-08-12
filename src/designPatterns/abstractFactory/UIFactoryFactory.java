package designPatterns.abstractFactory;

public class UIFactoryFactory {
    public static UIFactory getUIFactoryForPlatform(SupportedPlatforms platform){
        return switch (platform) {
            case IOS -> new IOSUIFactory();
            case ANDROID -> new AndroidUIFactory();
        };
    }
}
