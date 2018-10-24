package net.superblaubeere27.clientbase.valuesystem;

import com.google.gson.JsonObject;

import java.util.function.Predicate;

public abstract class Value<T> {
    private String name;
    private T object;
    private T defaultVal;
    /**
     * The validator which is called every time the value is changed
     */
    private Predicate<T> validator;

    Value(String name, T defaultVal, Predicate<T> validator) {
        this.name = name;
        this.object = defaultVal;
        this.defaultVal = defaultVal;
        this.validator = validator;
    }

    public abstract void addToJsonObject(JsonObject obj);

    public abstract void fromJsonObject(JsonObject obj);

    public String getName() {
        return name;
    }

    public T getObject() {
        return object;
    }

    public boolean setObject(T object) {
        if (validator != null && !validator.test(object)) return false;

        this.object = object;

        return true;
    }

    public void setValidator(Predicate<T> validator) {
        this.validator = validator;
    }

    public Object getDefault() {
        return defaultVal;
    }
}
