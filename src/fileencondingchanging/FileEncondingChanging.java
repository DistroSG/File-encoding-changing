/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fileencondingchanging;

import com.ibm.icu.text.CharsetDetector;
import com.ibm.icu.text.CharsetMatch;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.nio.charset.UnsupportedCharsetException;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author distro
 */
public class FileEncondingChanging {

    private final String filename;
    private final File file;
    private String isReadOnly;
    private Reader reader;
    private String charset;

    public FileEncondingChanging(String filename) {
        this.filename = filename;
        file = new File(filename);
    }

    private void turOnReadOnlyAttribute() {
        if (file.canWrite() == true) {
            file.setWritable(false);
        }
    }

    private void turOffReadOnlyAttribute() {
        if (file.canWrite() == false) {
            file.setWritable(true);
        }
    }

    private String isReadOnly() {

        if (file.canWrite() == true) {
            isReadOnly = "Read Only is off";
        } else {
            isReadOnly = "Read Only is on";
        }
        return isReadOnly;
    }

    private String getEncode() throws FileNotFoundException, IOException {
        FileInputStream is = new FileInputStream(file);
        BufferedInputStream bis = new BufferedInputStream(is);
        CharsetDetector cd = new CharsetDetector();
        cd.setText(bis);
        CharsetMatch cm = cd.detect();

        if (cm != null) {
            charset = cm.getName();
        } else {
            throw new UnsupportedCharsetException(filename);
        }
        return charset;
    }

    private void changeToUTF8() throws IOException {
        String content = FileUtils.readFileToString(file, charset)  + '\ufeff';
        FileUtils.write(file, content, "UTF-8");

    }

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        FileEncondingChanging test = new FileEncondingChanging("C:\\test\\ДДТ - Свинья на радуге, 1982.cue");
        System.out.println(test.isReadOnly());
        test.turOffReadOnlyAttribute();
        System.out.println(test.isReadOnly());
        System.out.println(test.getEncode());
        test.changeToUTF8();
        System.out.println(test.getEncode());
        test.turOnReadOnlyAttribute();

    }

}
