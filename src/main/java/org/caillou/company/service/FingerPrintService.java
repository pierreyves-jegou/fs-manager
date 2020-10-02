package org.caillou.company.service;

import lombok.extern.slf4j.Slf4j;
import org.caillou.company.exception.FSException;
import org.caillou.company.exception.FsItemSkippableException;
import org.eclipse.microprofile.config.inject.ConfigProperty;


import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import java.io.*;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Slf4j
@ApplicationScoped
public class FingerPrintService {

    @ConfigProperty(name = "app.fingerprint.internal-buffer-size")
    Integer bufferSize = 8192;

    MessageDigest digest = null;

    @PostConstruct
    void init() throws NoSuchAlgorithmException {
        digest = MessageDigest.getInstance("MD5");
    }

    public String process(File file, Integer byteToRead) throws FSException {
        String output = null;
        int byteReaded = 0;
        int localBufferSize = bufferSize;

        if (byteToRead != null && byteToRead < localBufferSize) {
            localBufferSize = byteToRead;
        }

        try (InputStream is = new FileInputStream(file)) {
            byte[] buffer = new byte[localBufferSize];
            int read = 0;

                do {
                    read = is.read(buffer);
                    if (!(read > 0)) {
                        break;
                    }

                    digest.update(buffer, 0, read);
                    byteReaded += read;

                    // If the buffer size is larger than the amount of data to be
                    // read : reduce the buffer size
                    if (byteToRead != null
                            && localBufferSize > (byteToRead - byteReaded)) {
                        localBufferSize = byteToRead - byteReaded;
                        buffer = new byte[localBufferSize];
                    }
                } while (true);

                byte[] md5sum = digest.digest();
                BigInteger bigInt = new BigInteger(1, md5sum);
                output = bigInt.toString(16);
            return output;
        } catch (IOException e) {
            throw new FsItemSkippableException(String.format("IOException occured while readind %s file ", file.getAbsolutePath()), e);
        }
    }

    public static void main(String[] args) throws NoSuchAlgorithmException, FSException {
        File file = new File("C:\\CGI\\perso\\projet_perso\\excelchart\\pom.xml");
        FingerPrintService fingerPrintService = new FingerPrintService();
        fingerPrintService.init();
        System.out.println(fingerPrintService.process(file, 500)
        );

    }
}
