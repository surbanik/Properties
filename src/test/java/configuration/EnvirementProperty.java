package configuration;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class EnvirementProperty {

    private final String APP_ENV;

    public static EnvirementProperty getInstance(){
        return EnvirementPropertySingleton.INSTANCE;
    }

    private void initEnv(){
        if(!this.APP_ENV.isEmpty()){
            loadAllEnvirementPropertiesToSystem(this.APP_ENV);
        }else{
            assertThat(false,equalTo(true));
        }
    }
    private EnvirementProperty(){
        this.APP_ENV=initAppEnv();
        this.initEnv();
    }

    private static String initAppEnv() {
        return PropertiesStore.ENVIREMENT.isSpecified()? PropertiesStore.ENVIREMENT.getValue(): " ";
    }

    private void loadAllEnvirementPropertiesToSystem(String app_env) {
        setSystemPropertiesFromPathUrl(app_env);
    }

    private static void setSystemPropertiesFromPathUrl(String dirName) {
        URL url = EnvirementProperty.class.getClassLoader().getResource(dirName);
        if (url != null) {
            Properties environmentProperties = new Properties();

            try {
                Stream<Path> files = Files.walk(Paths.get(url.toURI()));


                try {
                    ((List)files.filter((x$0) -> {
                        return Files.isRegularFile(x$0, new LinkOption[0]);
                    }).collect(Collectors.toList())).forEach((path) -> {
                        try {
                            environmentProperties.load(new FileInputStream(path.toString()));
                        } catch (IOException var3) {
                            System.out.println("error 1");

                        }

                    });
                } catch (Exception e) {
                    System.out.println("error 2");

                } finally {
                    if (files != null) {
                        try {
                            files.close();
                        } catch (Throwable var13) {
                            System.out.println("error 3");
                        }
                    } else {
                        files.close();
                    }
                }

            } catch (Exception r) {
                System.out.println("error 4");

            }
            System.out.println("#### Loading property from uri "+url.toString());
            environmentProperties.forEach((propertyName, propertyValue) -> {
                if (System.getProperty(propertyName.toString()) == null) {
                    System.setProperty(propertyName.toString(), propertyValue.toString());
                    System.out.println("****Loading environment property "+ propertyName.toString() +" = "+ propertyValue.toString());
                }

            });
            System.out.println("#### Properties loaded from "+dirName+" : "+environmentProperties.size());

        } else {
            System.out.println("No such property directory "+dirName+"present in the resources ,make sure you are providing correct directory name.");
        }

    }


    private static class EnvirementPropertySingleton{
        private static final EnvirementProperty INSTANCE = new EnvirementProperty();
    }

}
