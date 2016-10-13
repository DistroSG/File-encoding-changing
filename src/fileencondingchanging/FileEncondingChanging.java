/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fileencondingchanging;

import java.io.File;
import java.io.IOException;

/**
 *
 * @author distro
 */
public class FileEncondingChanging {

    private final String filename;
    private final File file;
    private String isReadOnly;

    public FileEncondingChanging(String filename) {
        this.filename = filename;
        file = new File(filename);
    }

    private void changeTheReadOnlyAttribute() {
        if (file.canWrite() == false) {
            file.setWritable(true);
        } else {
            file.setWritable(false);
        }
    }

    private String isReadOnly() {

        if (file.canWrite() == true) {
            isReadOnly = "Read Only is on";
        } else {
            isReadOnly = "Read Only is off";
        }
        return isReadOnly;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        FileEncondingChanging test = new FileEncondingChanging("C:\\test\\Пример"
                + " (2016).cue");
        System.out.println(test.isReadOnly());
        test.changeTheReadOnlyAttribute();
        System.out.println(test.isReadOnly());

    }

}
