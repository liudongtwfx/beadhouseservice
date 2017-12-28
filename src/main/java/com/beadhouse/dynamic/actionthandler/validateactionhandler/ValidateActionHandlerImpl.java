package main.java.com.beadhouse.dynamic.actionthandler.validateactionhandler;

import main.java.com.beadhouse.dynamic.action.validateaction.ValidateAction;
import main.java.com.beadhouse.dynamic.action.validateaction.ValidateDataType;
import main.java.com.beadhouse.dynamic.action.validateaction.ValidateType;
import main.java.com.beadhouse.dynamic.datawrapper.Data;
import main.java.com.beadhouse.dynamic.datawrapper.ExceptionOrFailedData;
import main.java.com.beadhouse.dynamic.datawrapper.SQLResultData;
import main.java.com.beadhouse.dynamic.datawrapper.SuccessData;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class ValidateActionHandlerImpl implements ValidateActionHandler {
    private ValidateAction action;
    private String comparingType;
    private String expectedValue;
    private String comparingValue;

    public ValidateActionHandlerImpl(ValidateAction action) {
        this.action = action;
    }

    @Override
    public boolean equals() {
        return comparingValue.equals(expectedValue);
    }

    @Override
    public boolean notEquals() {
        return !equals();
    }

    @Override
    public boolean lessThan() {
        switch (comparingType) {
            case "STRING":
                return comparingValue.compareTo(expectedValue) < 0;
            case "INTEGER":
                return Integer.valueOf(comparingValue) < Integer.valueOf(expectedValue);
            case "DOUBLE":
                return Double.valueOf(comparingValue) < Double.valueOf(expectedValue);
            case "FLOAT":
                return Float.valueOf(comparingValue) < Float.valueOf(expectedValue);
            case "DATE":
                try {
                    return new SimpleDateFormat().parse(comparingValue).
                            before(new SimpleDateFormat().parse(expectedValue));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            default:
                throw new IllegalStateException("comparing exception");
        }
    }

    @Override
    public boolean greaterThan() {
        switch (comparingType) {
            case "STRING":
                return comparingValue.compareTo(expectedValue) > 0;
            case "INTEGER":
                return Integer.valueOf(comparingValue) > Integer.valueOf(expectedValue);
            case "DOUBLE":
                return Double.valueOf(comparingValue) > Double.valueOf(expectedValue);
            case "FLOAT":
                return Float.valueOf(comparingValue) > Float.valueOf(expectedValue);
            case "DATE":
                try {
                    return new SimpleDateFormat().parse(comparingValue).
                            after(new SimpleDateFormat().parse(expectedValue));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            default:
                throw new IllegalStateException("comparing exception");
        }
    }

    @Override
    public Data handle(Data in) {
        if (in instanceof SQLResultData) {
            SQLResultData data = (SQLResultData) in;
            if (!data.isSingleResult()) {
                return new ExceptionOrFailedData("验证失败");
            }
            analysizeData(data);
            if (invokeMethod(action.getValidateType())) {
                return new SuccessData("YES");
            } else {
                return new ExceptionOrFailedData("NO");
            }
        }
        return null;
    }

    private void analysizeData(SQLResultData data) {
        List<String> columns = data.getColumns();
        List<String> values = data.getResultValues().get(0);
        if (columns.size() != values.size()) {
            throw new IllegalStateException("columns and its corresponding values size not match");
        }
        comparingType = (String) action.getValidateData().getDataMap().get("comparingType");
        if (action.getValidateDataType() == ValidateDataType.FIXED) {
            comparingValue = values.get(columns.indexOf(action.getValidateData().getDataMap().get("exactcolumn_columnName")));
            expectedValue = values.get(columns.indexOf(action.getValidateData().getDataMap().get("expectedValue")));
        } else if (action.getValidateDataType() == ValidateDataType.TABLE_COLUMN_SINGLE_RESULT) {
            comparingValue = values.get(columns.indexOf(action.getValidateData().getDataMap().get("comparing_column")));
            expectedValue = values.get(columns.indexOf(action.getValidateData().getDataMap().get("expect_column")));
        }
    }

    private boolean invokeMethod(ValidateType type) {
        switch (type) {
            case EQUAL:
                return equals();
            case NOT_EQUAL:
                return notEquals();
            case LESSTHAN:
                return lessThan();
            case GREATERTHAN:
                return greaterThan();
            case NOTLESSTHAN:
                return notLessThan();
            case NOTGREATERTHAN:
                return greaterThan();
            default:
                throw new IllegalArgumentException("validate type not found:" + type.toString());
        }
    }
}
