package com.automation.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class Logging {
    private static Logging instance = null;
    protected final static Logger log = Logger.getLogger(Logging.class);
    static File file;
    static String LogPropertyPath = System.getProperty("user.dir") + "/Log4j.properties";

    private Logging() {
        super();
    }

    public static void setLogPath(String logpath) throws IOException {
        FileInputStream in;
        in = new FileInputStream(LogPropertyPath);

        Properties props = new Properties();
        props.load(in);
        in.close();

        FileOutputStream out = new FileOutputStream(LogPropertyPath);
        props.setProperty("log4j.appender.file.File", logpath);
        props.store(out, null);
        out.close();
    }

    public static Logging getInstance() {
        if (instance == null) {
            instance = new Logging();
            PropertyConfigurator.configure(LogPropertyPath);
            log.setLevel(Level.ALL);
        }
        return instance;
    }


    public static void setinstanceNull() {
        instance = null;
    }


    public void info(String value, String msg) {
        log.info("[" + value + "] " + msg);

    }

    public void error(String value, String msg, Exception ce) {
        log.error("[" + value + "] " + msg, ce);
    }

    public void warning(String value, String msg) {
        log.warn("[" + value + "] " + msg);
    }

    public static void WriteLogPropertiesFile(Properties p) {
        FileOutputStream fos = null;

        try {
            fos = new FileOutputStream(file);
        } catch (FileNotFoundException e) {

            e.printStackTrace();
        }
        try {
            p.store(fos, "Properties");
        } catch (IOException e) {

            e.printStackTrace();
        }
        try {
            fos.close();
        } catch (IOException e) {

            e.printStackTrace();
        }
    }

}
