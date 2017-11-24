package main.java.com.beadhouse.dynamic.html.areatemplate;

import org.jsoup.nodes.Element;

public abstract class AbstractAreaStructNode implements AreaStruct {
    protected Element baseStructNode;

    AbstractAreaStructNode() {
        this(null);
    }

    AbstractAreaStructNode(Element element) {
        this.baseStructNode = element;
    }

    @Override
    public Element getElementNode() {
        return baseStructNode;
    }
}
