package dk.sdu.cbse;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DeletePlayerModuleTest {
    private static final String PROJECT_ROOT = new File(System.getProperty("user.dir")).getParent();

    @Test
    void testGameRunsWithoutPlayerModule() throws IOException, InterruptedException {

        File original = new File(PROJECT_ROOT, "mods-mvn/Player-1.0-SNAPSHOT.jar");

        if (!original.exists()) {
            throw new IOException("Missing module jar: " + original.getAbsolutePath());
        }

        assertTrue(original.delete(), "Failed to delete module jar");

        ProcessBuilder gameBuilder = new ProcessBuilder("java", "--module-path", "mods-mvn", "--class-path", "libs/*", "--module", "Core/dk.sdu.cbse.main.Main");
        gameBuilder.directory(new File(PROJECT_ROOT));
        gameBuilder.redirectErrorStream(true);
        Process process = gameBuilder.start();

        int exitCode = process.waitFor();
        assertEquals(exitCode, 0, "Game did not exit cleanly");

        ProcessBuilder mvnBuilder = new ProcessBuilder("mvn", "install", "-pl", "Player", "-am");
        mvnBuilder.directory(new File(PROJECT_ROOT));
        Process mvnProcess = mvnBuilder.start();

        int mvnExit = mvnProcess.waitFor();
        assertEquals(mvnExit, 0, "Failed to rebuild Player module.");
    }
}
