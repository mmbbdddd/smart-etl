package cn.hz.ddbm.setl.exception;

public class ConfigException extends Exception {
    private   String component;


    public ConfigException(Exception e, String component) {
        super(e);
        this.component = component;
    }
}
