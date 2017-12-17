package main.java.com.beadhouse.dynamic.actionthandler;

import main.java.com.beadhouse.dynamic.datawrapper.Data;

import java.io.Serializable;

public interface ActionHandler extends Serializable {
    Data handle();
}
