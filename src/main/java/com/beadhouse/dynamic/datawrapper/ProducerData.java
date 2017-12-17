package main.java.com.beadhouse.dynamic.datawrapper;

public class ProducerData implements Data {
    private String topic;
    private String producerName;
    private String key;
    private String value;

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getProducerName() {
        return producerName;
    }

    public void setProducerName(String producerName) {
        this.producerName = producerName;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public void setSource() {

    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("topic:").append(topic).append(",").
                append("producerName:").append(producerName).append(",").
                append("key:").append(key).append(",").
                append("value").append(value);
        return sb.toString();
    }

    @Override
    public String wrapDataToString() {
        return toString();
    }
}
