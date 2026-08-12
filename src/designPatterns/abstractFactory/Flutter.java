package designPatterns.abstractFactory;

public class Flutter  {
    public void setTheme(){}

    public void setRefereshRate(){}

    public UIFactory createUIFactory(SupportedPlatforms platform) {
        if(platform == SupportedPlatforms.ANDROID) {
            return new AndroidUIFactory();
        } else if(platform == SupportedPlatforms.IOS) {
            return new IOSUIFactory();
        } else {
            throw new IllegalArgumentException("No Such Platform");
        }
    }

    public AndroidUIFactory createAndroidUIFactory() {
        return new AndroidUIFactory();
    }

    public IOSUIFactory createIOSUIFactory() {
        return new IOSUIFactory();
    }
}
