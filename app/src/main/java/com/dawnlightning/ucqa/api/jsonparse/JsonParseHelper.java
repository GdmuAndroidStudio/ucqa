package com.dawnlightning.ucqa.api.jsonparse;

import android.util.Log;

import com.dawnlightning.ucqa.bean.others.ConsultMessageBean;
import com.dawnlightning.ucqa.bean.response.consult.detailed.CommentBean;
import com.dawnlightning.ucqa.bean.response.consult.detailed.DetailedBean;
import com.dawnlightning.ucqa.bean.response.consult.detailed.PicsBean;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 作者：Administrator on 2016/5/30 15:38
 * 邮箱：823894716@qq.com
 */
public class JsonParseHelper {
    public static List<ConsultMessageBean> ParseConsultList(JsonObject target) {
        Gson gson = new Gson();
        List<ConsultMessageBean> list = new ArrayList<ConsultMessageBean>();
        JsonArray asJsonArray = target.getAsJsonArray("list");
        Iterator it = asJsonArray.iterator();
        while (it.hasNext()) {
            JsonElement element = (JsonElement) it.next();//JsonElement转换为JavaBean对象
            ConsultMessageBean bean = gson.fromJson(element, ConsultMessageBean.class);
            list.add(bean);
        }
        return list;
    }

    /**
     * 解析出一级评论
     *
     * @param target
     * @return
     */
    public static List<CommentBean> ParseComment(JsonObject target) {
        List<CommentBean> list = new ArrayList<CommentBean>();
        if (target.get("replylist").isJsonNull() == false) {
            Gson gson = new Gson();
            JsonArray asJsonArray = target.getAsJsonArray("replylist");
            if (asJsonArray != null) {
                Iterator it = asJsonArray.iterator();
                while (it.hasNext()) {
                    JsonElement element = (JsonElement) it.next();//JsonElement转换为JavaBean对象
                    CommentBean bean = gson.fromJson(element, CommentBean.class);
                    list.add(bean);
                }
            }

        }
        Log.d("kyo5", "" + list.size());
        return list;
    }

    /**
     * 从评论中解析出二级评论
     *
     * @param list 一级评论列表
     * @return
     */
    public static List<CommentBean> ParseReply(List<CommentBean> list) {
        List<CommentBean> newlist = new ArrayList<CommentBean>();
        if (list.size() > 0) {

            for (CommentBean commentbean : list) {
                newlist.add(commentbean);
            }
            for (int i = 0; i < list.size(); i++) {
                List<String> replylist = new ArrayList<String>();
                String comment = list.get(i).getAuthor() + ": " + list.get(i).getMessage();
                for (int z = i + 1; z < list.size(); z++) {
                    String reply = list.get(z).getMessage();
                    if (reply.contains(comment)) {
                        String name = "";
                        if (list.get(z).getName().length() > 0) {
                            name = list.get(z).getName();
                        } else {
                            name = list.get(z).getAuthor();
                        }
                        reply = name + ": " + reply.replace(comment, "").toString();

                        replylist.add(reply);
                        newlist.remove(list.get(z));
                    }
                }
                if (i <= newlist.size() - 1) {
                    newlist.get(i).setReplylist(replylist);
                }

            }
        }
        return newlist;
    }

    public static List<PicsBean> ParsePictureList(JsonObject target) {
        List<PicsBean> picsBeanList = new ArrayList<PicsBean>();
        if (target.get("pics").isJsonNull() == false) {
            JsonArray picsJsonArray = target.getAsJsonArray("pics");
            if (picsJsonArray != null) {
                Iterator it = picsJsonArray.iterator();
                while (it.hasNext()) {
                    JsonObject jsonObject = (JsonObject) it.next();//JsonElement转换为JavaBean对象
                    PicsBean bean = new PicsBean(jsonObject.get("picurl").getAsString(), jsonObject.get("title").getAsString());
                    picsBeanList.add(bean);
                }
            }
        }
        return picsBeanList;
    }
}
