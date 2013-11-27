package awana.database;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Renlar <liddev.com>
 */
public class BackupSystem implements Runnable, Shutdown {

    private int backupCutoff = 15;
    private static BackupSystem backupSystem;

    private BackupSystem() {
    }

    @Override
    public void run() {
    }

    private void backupDatabase() {
        renameAndTrimBackups();
        File directory = new File(DatabaseWrapper.storagePath + "Backup/");
        ArrayList<Path> paths = new ArrayList<>();
        try {
            DirectoryStream<Path> stream = Files.newDirectoryStream(FileSystems.getDefault().getPath(DatabaseWrapper.storagePath), "*.db");
            for (Path p : stream) {
                paths.add(p);
                Logger.getGlobal().log(Level.INFO, null, p);
            }
            stream.close();

            Files.copy(paths.get(0), FileSystems.getDefault().getPath(directory.getPath() + "/" + "Directory.h2.db.0.back"));

        } catch (IOException ex) {
            Logger.getGlobal().log(Level.SEVERE, null, ex);
        }
    }

    private void renameAndTrimBackups() {
        File directory = new File(DatabaseWrapper.storagePath + "Backup/");
        ArrayList<Path> paths = new ArrayList<>();
        if (!directory.exists()) {
            try {
                Files.createDirectory(directory.toPath());
            } catch (IOException ex) {
                Logger.getLogger(AwanaDatabase.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            try {
                DirectoryStream<Path> stream = Files.newDirectoryStream(FileSystems.getDefault().getPath(DatabaseWrapper.storagePath + "Backup/"), "*.back");
                for (Path p : stream) {
                    paths.add(p);
                    Logger.getGlobal().log(Level.INFO, p.toString());
                }
                stream.close();

                if (!paths.isEmpty()) {
                    for (int i = paths.size(); i > 0; i--) {
                        File file = paths.get(i - 1).toFile();
                        System.out.println(file.getPath());
                        if (i >= backupCutoff) {
                            file.delete();
                        } else {
                            File FileR = FileSystems.getDefault().getPath(file.getPath().substring(0, file.getPath().lastIndexOf("db") + 3).concat(i + ".back")).toFile();
                            file.renameTo(FileR);
                        }
                    }
                }
            } catch (IOException ex) {
                Logger.getGlobal().log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void stop() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    synchronized public static BackupSystem get() {
        if (backupSystem == null) {
            backupSystem = new BackupSystem();
        }
        return backupSystem;
    }
}
