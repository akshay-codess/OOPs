package designPatterns.abstractFactory;

public class Flutter  {
    private SupportedPlatforms platform;

    public Flutter(SupportedPlatforms platform){
        this.platform = platform;
    }
    public void setTheme(){}

    public void setRefereshRate(){}

//    public UIFactory createUIFactory(SupportedPlatforms platform) {
//        if(platform == SupportedPlatforms.ANDROID) {
//            return new AndroidUIFactory();
//        } else if(platform == SupportedPlatforms.IOS) {
//            return new IOSUIFactory();
//        } else {
//            throw new IllegalArgumentException("No Such Platform");
//        }
//    }'
    public UIFactory createUIFactory() {
        return null;
    }

    public AndroidUIFactory createAndroidUIFactory() {
        UIFactoryFactory.getUIFactoryForPlatform(this.platform);
        return new AndroidUIFactory();
    }

    public IOSUIFactory createIOSUIFactory() {
        return new IOSUIFactory();
    }
}
