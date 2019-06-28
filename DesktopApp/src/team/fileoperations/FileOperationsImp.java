package team.fileoperations;

import java.io.File;

public class FileOperationsImp implements FileOperationsInterface {
    static {
        System.load("ABSOLUTE_PATH_TO_NATIVE_LIBRARY");
    }

    private native String findURootDirCImp();

    private native String findBackupDirCImp();

    @Override
    public File[] findURootDirs() {
        String URootPath = findURootDirCImp();
        File[] URootDirs = null;
        if (URootPath != null) {
            URootDirs = new File[URootPath.length()];
            for (int idx = 0; idx < URootPath.length(); idx++) {
                URootDirs[idx] = new File("" + URootPath.charAt(idx) + ":/");
            }
        }
        return URootDirs;
    }

    @Override
    public File[] findBackupRootDirs() {
        return null;
    }
}