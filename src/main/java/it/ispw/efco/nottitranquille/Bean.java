package it.ispw.efco.nottitranquille;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public abstract class Bean {
    private Map<Field, String> errorMap = new HashMap<Field, String>();

    public String getErrorMessage(Field field) {
        return errorMap.get(field);
    }

    public Set<Field> getErrorFields() {
        return errorMap.keySet();
    }

    protected void setErrorMessage(Field field, String errorMessage) {
        errorMap.put(field, errorMessage);
    }

    protected abstract boolean validate() throws NoSuchFieldException;
}
