package hr.algebra.chess.utils;

import java.lang.reflect.*;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class ReflectionUtils {

    private ReflectionUtils() {
    }

    public static void readClassInfo(Class<?> clazz, StringBuilder classInfo) {
        appendPackage(clazz, classInfo);
        appendModifiers(clazz, classInfo);
        classInfo
                .append(" ")
                .append(clazz.getSimpleName());
        appendParent(clazz, classInfo, true);
        appendInterfaces(clazz, classInfo);
    }

    public static void appendPackage(Class<?> clazz, StringBuilder classInfo) {
        classInfo
                .append(clazz.getPackage())
                .append(System.lineSeparator())
                .append(System.lineSeparator())
                .append("<br>");
    }

    public static void appendModifiers(Class<?> clazz, StringBuilder classInfo) {
        classInfo.append(Modifier.toString(clazz.getModifiers()));
    }

    public static void appendParent(Class<?> clazz, StringBuilder classInfo, boolean first) {
        Class<?> parent = clazz.getSuperclass();
        if(parent == null) {
            return;
        }
        classInfo
                .append(first ? " extends " : " -> ")
                .append(parent.getSimpleName())
                .append("<br>");
        appendParent(parent, classInfo, false);
    }
    public static void appendInterfaces(Class<?> clazz, StringBuilder classInfo) {
        if (clazz.getInterfaces().length > 0) {
            classInfo
                    .append(" implements ")
                    .append(Stream.of(clazz.getInterfaces())
                            .map(Class::getSimpleName)
                            .collect(Collectors.joining(", ")))
                    .append("<br>");
        }
    }

    public static void readClassAndMembersInfo(Class<?> clazz, StringBuilder classAndMembersInfo) {
        classAndMembersInfo
                .append("<!DOCTYPE html>\n")
                .append("<html>\n")
                .append("<head>\n")
                .append("<title>").append(clazz.getSimpleName()).append("</title>\n")
                .append("</head>\n")
                .append("<body>\n")
                .append("<h1>").append(clazz.getSimpleName()).append("</h1>\n")
                .append("<p>\n");
        readClassInfo(clazz, classAndMembersInfo);
        classAndMembersInfo
                .append("<br>");
        appendFields(clazz, classAndMembersInfo);
        classAndMembersInfo
                .append("<br>");
        appendMethods(clazz, classAndMembersInfo);
        classAndMembersInfo
                .append("<br>");
        appendConstructors(clazz, classAndMembersInfo);
        classAndMembersInfo
                .append("</p>\n")
                .append("</body>\n")
                .append("</html>\n");
    }
    private static void appendFields(Class<?> clazz, StringBuilder classAndMembersInfo) {
        //Field[] fields = clazz.getFields();
        Field[] fields = clazz.getDeclaredFields(); // no encapsulation in reflection!
        classAndMembersInfo
                .append(System.lineSeparator())
                .append(System.lineSeparator())
                .append(Stream.of(fields)
                        .map(Objects::toString)
                        .collect(Collectors.joining("<br>")));
    }

    private static void appendMethods(Class<?> clazz, StringBuilder classAndMembersInfo) {
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            classAndMembersInfo
                    .append(System.lineSeparator())
                    .append(System.lineSeparator())
                    .append("<br>");
            appendAnnotations(method, classAndMembersInfo);
            classAndMembersInfo
                    .append(System.lineSeparator())
                    .append(Modifier.toString(method.getModifiers()))
                    .append(" ")
                    .append(method.getReturnType())
                    .append(" ")
                    .append(method.getName());
            appendParameters(method, classAndMembersInfo);
            appendExceptions(method, classAndMembersInfo);
        }
    }

    private static void appendConstructors(Class<?> clazz, StringBuilder classAndMembersInfo) {
        Constructor<?>[] constructors = clazz.getDeclaredConstructors();
        for (Constructor<?> constructor : constructors) {
            classAndMembersInfo
                    .append(System.lineSeparator())
                    .append(System.lineSeparator())
                    .append("<br>");
            appendAnnotations(constructor, classAndMembersInfo);
            classAndMembersInfo
                    .append(System.lineSeparator())
                    .append(Modifier.toString(constructor.getModifiers()))
                    .append(" ")
                    .append(constructor.getName());
            appendParameters(constructor, classAndMembersInfo);
            appendExceptions(constructor, classAndMembersInfo);
        }
    }


    private static void appendAnnotations(Executable executable, StringBuilder classAndMembersInfo) {
        classAndMembersInfo.append(
                Stream.of(executable.getAnnotations())
                        .map(Objects::toString)
                        .collect(Collectors.joining(System.lineSeparator())))
                .append("<br>");
    }

    private static void appendParameters(Executable executable, StringBuilder classAndMembersInfo) {
        classAndMembersInfo.append(
                Stream.of(executable.getParameters())
                        .map(Objects::toString)
                        .collect(Collectors.joining(", ", "(", ")"))

        )
                .append("<br>");
    }

    private static void appendExceptions(Executable executable, StringBuilder classAndMembersInfo) {
        if (executable.getExceptionTypes().length > 0) {
            classAndMembersInfo.append(
                    Stream.of(executable.getExceptionTypes())
                            .map(Class::getSimpleName)
                            .collect(Collectors.joining(", ", " throws ", "")));
        }
    }
}
