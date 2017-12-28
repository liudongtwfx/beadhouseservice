package main.java.com.beadhouse.dynamic.action.validateaction;

import java.io.Serializable;
import java.util.Map;

public interface ValidateData extends Serializable {
    Map<String, Object> getDataMap();
}
