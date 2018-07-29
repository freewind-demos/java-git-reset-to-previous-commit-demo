package demo;

import org.apache.commons.io.FileUtils;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.PullCommand;
import org.eclipse.jgit.api.PullResult;
import org.eclipse.jgit.api.ResetCommand;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.lib.*;

import java.io.File;
import java.io.IOException;

public class Hello {
    private static final String uri = "https://github.com/java-demos/java-hello-world-demo.git";
    private static final File localDir = new File("./target/local-repo/java-hello-world-demo");

    public static void main(String[] args) throws Exception {
        if (localDir.exists()) {
            FileUtils.forceDelete(localDir);
        }

        cloneToLocal();

        System.out.println("head commit: " + getHeadCommit());

        resetToPreviousCommit();

        System.out.println("after reset to HEAD~1: " + getHeadCommit());

    }

    private static String getHeadCommit() throws IOException {
        Repository repository = Git.open(localDir).getRepository();
        ObjectId id = repository.resolve(Constants.HEAD);
        return id.getName();
    }

    private static void resetToPreviousCommit() throws IOException, GitAPIException {
        Git.open(localDir).reset()
                .setMode(ResetCommand.ResetType.HARD)
                .setRef("HEAD~1")
                .call();
    }

    private static void cloneToLocal() throws GitAPIException {
        Git.cloneRepository().setURI(uri)
                .setDirectory(localDir)
                .setCloneAllBranches(true)
                .call();
    }

}
