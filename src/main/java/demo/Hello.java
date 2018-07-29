package demo;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;

import java.io.File;

public class Hello {

    public static void main(String[] args) throws GitAPIException {
        String uri = "https://github.com/java-demos/java-hello-world-demo.git";
        File localDir = new File("./target/local-repo/java-hello-world-demo");

        Git.cloneRepository().setURI(uri)
                .setDirectory(localDir)
                .setCloneAllBranches(true)
                .call();

        System.out.println("check dir: " + localDir.getAbsolutePath());
    }

}
