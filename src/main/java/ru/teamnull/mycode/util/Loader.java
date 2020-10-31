package ru.teamnull.mycode.util;

import java.io.IOException;

public class Loader {

    private static boolean nativeLibLoaded = false;

    private Loader() {}

    private static OperatingSystem detectOS() {
        String name = System.getProperty("os.name").toLowerCase();

        if (name.contains("win"))
            return OperatingSystem.WINDOWS;
        if (name.contains("mac"))
            return OperatingSystem.MAC;
        if (name.contains("nix") || name.contains("bsd"))
            return OperatingSystem.UNIX;
        return OperatingSystem.UNSPECIFIED;
    }

    private static String getLibraryName() {
        switch (detectOS()) {
            case WINDOWS:
                return "cppnative.dll";
            case MAC:
                return "libcppnative.dylib";
            case UNIX:
                return "libcppnative.so";
            case UNSPECIFIED:
            default:
                throw new RuntimeException("Unsupported OS");
        }
    }

    private static void loadLibraryFromJar() {
        String name = getLibraryName();

        try {
            NativeUtils.loadLibraryFromJar(name);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static void loadNativeLibrary() {
        if (nativeLibLoaded)
            return;
        try {
            System.loadLibrary("cppnative");
        } catch (UnsatisfiedLinkError e) {
            e.printStackTrace();
            loadLibraryFromJar();
        }
        nativeLibLoaded = true;
    }
}
