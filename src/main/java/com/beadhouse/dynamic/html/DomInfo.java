package main.java.com.beadhouse.dynamic.html;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import main.java.com.beadhouse.System.CommonFinalVariable;
import main.java.com.beadhouse.System.LogType;
import main.java.com.beadhouse.business.redisclient.RedisClientConnector;
import main.java.com.beadhouse.dynamic.dataencapsulation.StructAnalysis;
import main.java.com.beadhouse.dynamic.dataencapsulation.StructAreaData;
import main.java.com.beadhouse.dynamic.dataencapsulation.TableAreaData;
import main.java.com.beadhouse.dynamic.html.areatemplate.AreaType;
import main.java.com.beadhouse.exception.sqlexception.NeedElementIdExcetpion;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Element;
import org.omg.CORBA.INTERNAL;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.*;

public class DomInfo extends AbstractDomInfo implements Serializable {
    private static final Set<AreaType> needDataRender = new HashSet<>();

    static {
        needDataRender.add(AreaType.MAIN_ARTICLE);
        needDataRender.add(AreaType.FORM);
        needDataRender.add(AreaType.TABLE);
        needDataRender.add(AreaType.PICTURE_LIST);
    }

    private final Map<String, String> needDataInputMap;

    public DomInfo(String viewname) {
        super(viewname);
        if (RedisClientConnector.getLocalRedis().keys(absoluteFilePath).size() == 0) {
            needDataInputMap = new HashMap<>();
        } else {
            needDataInputMap = RedisClientConnector.getLocalRedis().hgetAll(absoluteFilePath);
        }
    }

    public void createNodesInfo() {
        if (document == null) {
            return;
        }
        preorderTranverse(document, 0);
    }

    private void preorderTranverse(Element parent, int depth) {
        if (parent.tagName().equals("html")) {
            rootid = id;
        }
        List<Integer> list = new ArrayList<>();
        int parentid = id;
        for (Element element : parent.children()) {
            NodeInfo node = new NodeInfo();
            node.setTagName(element.tagName());
            node.setClasses(element.className());
            node.setPreId(element.id());
            node.setElementId(++id);
            node.setDepth(depth);
            node.setParentId(parentid);
            node.setText(element.text());
            node.setAreaType(AreaType.NULL);
            Map<String, String> attr = new HashMap<>();
            for (Attribute attribute : element.attributes()) {
                attr.put(attribute.getKey(), attribute.getValue());
            }
            node.setAttr(attr);
            elements.put(id, element);
            nodeInfoMap.put(id, node);
            list.add(id);
            preorderTranverse(element, depth + 1);
        }
        childList.put(parentid, list);
    }

    public String createHTMLTree(HttpServletRequest request) throws UnsupportedEncodingException {
        try {
            JSONObject childlist = JSON.parseObject(request.getParameter("childlist"));
            JSONObject nodesinfo = JSON.parseObject(request.getParameter("nodesinfo"));
            JSONArray altered = JSON.parseArray(request.getParameter("altered"));
            JSONArray removed = JSON.parseArray(request.getParameter("removed"));
            childlist.forEach((k, v) -> LogType.DEBUGINFO.getLOGGER().debug(k + " " + v));
            nodesinfo.forEach((k, v) -> LogType.DEBUGINFO.getLOGGER().debug(k + " " + v));
            for (Object alteredId : altered) {
                LogType.DEBUGINFO.getLOGGER().debug(alteredId);
                updateElements(childlist, nodesinfo, alteredId);
            }
            removed.forEach(removedId -> {
                Element element = elements.get(Integer.valueOf((String) removedId));
                element.remove();
                if (needDataInputMap.containsKey(element.id())) {
                    needDataInputMap.remove(element.id());
                }
            });
            LogType.DEBUGINFO.getLOGGER().debug(document.toString());
            OutputStream stream = new FileOutputStream(new File(absoluteFilePath));
            stream.write(document.toString().getBytes());
            OutputStream tomcatStream = new FileOutputStream(new File(CommonFinalVariable.TOMCAT_FILE_PATH + reletivepath + ".html"));
            tomcatStream.write(document.toString().getBytes());
            if (needDataInputMap.size() != 0) {
                RedisClientConnector.getLocalRedis().hmset(absoluteFilePath, needDataInputMap);
            }
            return "success";
        } catch (NeedElementIdExcetpion e) {
            return new String(e.toString().getBytes(), "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
            LogType.EXCETPION.getLOGGER().error(e);
            return "failed";
        }
    }

    private void updateElements(JSONObject childlist, JSONObject nodesinfo, Object alteredId) throws NeedElementIdExcetpion {
        int id = Integer.valueOf(String.valueOf(alteredId));
        if (!nodesinfo.containsKey(String.valueOf(id))) {
            return;
        }
        if (elements.containsKey(id)) {
            updateElement(elements.get(id), nodesinfo.getJSONObject(String.valueOf(id)));
        } else {
            addNewElement(nodesinfo.getJSONObject(String.valueOf(id)));
        }
    }

    private void updateElement(Element element, JSONObject nodeInfo) {
        String id = "";
        String originId = element.id();
        for (Map.Entry<String, Object> kv : nodeInfo.getJSONObject("attr").entrySet()) {
            if (kv.getKey().equals("id")) {
                id = (String) kv.getValue();
            }
            element.attr(kv.getKey(), (String) kv.getValue());
        }

        if (!id.equals(originId) && needDataInputMap.containsKey(originId)) {
            if (needDataRender.contains(AreaType.valueOf((String) nodeInfo.get("areaType")))) {
                needDataInputMap.putIfAbsent(id, needDataInputMap.get(originId));
                needDataInputMap.remove(originId);
            }
        }

        element.text((String) nodeInfo.get("text"));
    }

    private void addNewElement(JSONObject nodeInfo) throws NeedElementIdExcetpion {
        Element element = null;
        if (nodeInfo.containsKey("areaType") && !nodeInfo.get("areaType").equals("")) {
            AreaType areaType = AreaType.valueOf((String) nodeInfo.get("areaType"));
            if (areaType != AreaType.NULL) {
                element = new Element("div");
            } else {
                element = new Element((String) nodeInfo.get("tagName"));
            }
            if (areaType == AreaType.HTML) {
                element.append(String.valueOf(nodeInfo.get("text")));
            }
            if (needDataRender.contains(areaType)) {
                if (!nodeInfo.getJSONObject("attr").containsKey("id")) {
                    throw new NeedElementIdExcetpion(areaType + "需要有一个id属性");
                }
                //todo add builder patterns for structdata;
                needDataInputMap.put(String.valueOf(nodeInfo.getJSONObject("attr").get("id")),
                        StructAnalysis.getStruct(areaType).toString());
            }
            if (areaType != AreaType.NULL && areaType != AreaType.HTML) {
                element.attr("th:include", getFragement(areaType));
            }
        }
        if (element == null) {
            element = new Element((String) nodeInfo.get("tagName"));
        }
        elements.get(Integer.valueOf((String) nodeInfo.get("parentId"))).appendChild(element);
        updateElement(element, nodeInfo);
    }

    private String getFragement(AreaType areaType) {
        if (areaType == null) {
            return "";
        }
        String base = "fragments/";
        switch (areaType) {
            case ARTICLE_INFO:
                return base + "articleinfo::articleinfo";
            case COPYRIGHT:
                return base + "copyright::copyright";
            case FORM:
                return base + "form::form";
            case LOGO:
                return base + "logo::logo";
            case MAIN_ARTICLE:
                return base + "mainarticle::mainarticle";
            case NAVIGATION_BLOCK:
                return base + "navigation::navigation";
            case PAGETURNING:
                return base + "pageturning::pageturning";
            case PICTURE_LIST:
                return base + "picturelist::picturelist";
            case TITLE:
                return base + "title::title";
            case TABLE:
                return base + "table::table";
            default:
                return "";
        }
    }

    public List<NodeInfo> filterButtonAndInput() {
        List<NodeInfo> nodeInfos = new ArrayList<>();
        nodeInfoMap.forEach((k, v) -> {
            String tagName = v.getTagName();
            if (tagName.equals("button") || tagName.equals("input")) {
                nodeInfos.add(v);
            }
        });
        return nodeInfos;
    }
}
