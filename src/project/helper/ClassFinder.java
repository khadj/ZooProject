package project.helper;

import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;

import java.util.*;

public class ClassFinder {
    public static Map<String, Class<?>> findClassByName(List<String> classNames){
        Map<String, Class<?>> classesByName = new HashMap<>();

        List<String> packageNames = new ArrayList<>();
        packageNames.add("project/entity/animals/predatory");
        packageNames.add("project/entity/animals/herbovorous");
        packageNames.add("project/entity/plants");

        for (String packageName : packageNames) {
            Reflections reflections = new Reflections(new ConfigurationBuilder()
                    .setUrls(ClasspathHelper.forPackage(packageName))
                    .setScanners(new SubTypesScanner(false)));

            Set<Class<?>> classes = reflections.getSubTypesOf(Object.class);

            for (String className : classNames) {
                for (Class<?> clazz : classes) {
                    if (clazz.getSimpleName().equals(className)) {
                        classesByName.put(className, clazz);
                    }
                }
            }
        }
        return classesByName;
    }
}
