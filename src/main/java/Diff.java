import org.apache.tools.ant.Task;

import org.eclipse.jgit.api.*;
import org.eclipse.jgit.api.errors.GitAPIException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Set;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Set;

public class Diff extends Task{
    public void execute() {
        // use of the reference to Project-instance
        String message = getProject().getProperty("ant.project.name");

        // Task's log method
        log("Here is project '" + message + "'.");

        // where this task is used?
        log("I am used in: " +  getLocation() );


        try {
            Git git = Git.open(new File("../webapp"));
            AddCommand add = git.add();
            StatusCommand statusCommand = git.status();

            LinkedList<String> conf = new LinkedList<>();
            BufferedReader reader1 = new BufferedReader(new FileReader("file.txt"));
            String line2 = null;
            line2 = reader1.readLine();
            while (line2 != null) {
                conf.add(line2.trim());
                line2 = reader1.readLine();
            }

            Status status = statusCommand.call();

            Set<String> modified = status.getModified();
            for (String s: conf) {
                if(modified.contains(s)){
                    return;
                }
            }

            add.addFilepattern(".").call();
            git.commit().setMessage("commit").call();
        } catch (IOException | GitAPIException e) {
            e.printStackTrace();
        }


    }
}
