package com.szborqs.mybook.db;


import android.content.Context;


/**
 * description
 *
 * @Author Administrator
 * @Time 2017/1/16 15:46
 */

public class ChatMessageManager {
    private static ChatMessageManager chatMessageManager = null;
    private static DBManager manager = null;

    private ChatMessageManager(Context context) {
        manager = DBManager.getInstance(context);
    }

    public static ChatMessageManager getInstance(Context context) {
        if (chatMessageManager == null) {
            chatMessageManager = new ChatMessageManager(context);
        }
        return chatMessageManager;
    }

/*    public long saveChatMessageItem(SendMessageItem item){
        long influencesRows = -1;
        ContentValues contentValues = new ContentValues();
        contentValues.put("msg_id", item.getMsgId());
        contentValues.put("msg_content", item.getContent());
        if(!SharedMethod.isEmptyString(item.getParam())){
            contentValues.put("msg_param", item.getParam());
        }
        contentValues.put("msg_time", item.getTimeString());
        contentValues.put("my_user_id", CommonPrefs.getCurentUser());
        if(!SharedMethod.isEmptyString(item.getFriendUserId())){
            contentValues.put("friend_id", item.getFriendUserId());
        }
        if(!SharedMethod.isEmptyString(item.getGroupId())){
            contentValues.put("group_id", item.getGroupId());
        }
        contentValues.put("msg_category", item.getMsgCategory());
        contentValues.put("msg_type", item.getMsgType());
        contentValues.put("msg_status", item.getMsgStatus());
        contentValues.put("msg_direction", item.getDirection());
        try{
            SQLiteTemplate st=SQLiteTemplate.getInstance(manager);
            influencesRows=st.insert("im_msg_info",contentValues);
        }catch(Exception e){
            e.printStackTrace();
        }

        return influencesRows;
    }*/

/*    public List<SendMessageItem> getChatListByFriendId(String friendId, int pageNum, int pageSize){
        List<SendMessageItem> result=null;
        int fromIndex = (pageNum - 1) * pageSize;
        SQLiteTemplate st=SQLiteTemplate.getInstance(manager);
        SQLiteTemplate.RowMapper<SendMessageItem> rowMapper=new SQLiteTemplate.RowMapper<SendMessageItem>() {
            @Override
            public SendMessageItem mapRow(Cursor cursor, int index) {
                SendMessageItem item=new SendMessageItem();
                item.setId(cursor.getInt(cursor.getColumnIndex("_id")));
                item.setMsgId(cursor.getString(cursor.getColumnIndex("msg_id")));
                item.setContent(cursor.getString(cursor.getColumnIndex("msg_content")));
                item.setParam(cursor.getString(cursor.getColumnIndex("msg_param")));
                item.setTimeString(cursor.getString(cursor.getColumnIndex("msg_time")));
                item.setMyUserId(cursor.getString(cursor.getColumnIndex("my_user_id")));
                item.setFriendUserId(cursor.getString(cursor.getColumnIndex("friend_id")));
                item.setFriendName(cursor.getString(cursor.getColumnIndex("person_name")));
                item.setFriendHeadUrl(cursor.getString(cursor.getColumnIndex("person_url")));
                item.setMsgCategory(cursor.getInt(cursor.getColumnIndex("msg_category")));
                item.setMsgType(cursor.getInt(cursor.getColumnIndex("msg_type")));
                item.setMsgStatus(cursor.getInt(cursor.getColumnIndex("msg_status")));
                item.setDirection(cursor.getInt(cursor.getColumnIndex("msg_direction")));
                return item;
            }
        };
        String sql="select m._id, m.msg_id, m.msg_content,m.msg_param,m.msg_time,m.my_user_id,m.friend_id,m.msg_category,m.msg_type,m.msg_status,m.msg_direction,i.person_name,i.person_url from im_msg_info m left join im_friend_info i on m.friend_id = i.person_id where m.my_user_id=? and m.friend_id=? and m.msg_category = ? order by m._id desc limit ? , ?";
        result=st.queryForList(rowMapper,sql,new String[] { CommonPrefs.getCurentUser(), friendId, "" + SendMessageItem.CATEGORY_IM,
                "" + fromIndex, "" + pageSize });
        CarLog.e("getChatListByFriendId pageNum:"+pageNum+" pageSize:"+pageSize+" result:"+result.size());
        return result;
    }*/

/*    public List<SendMessageItem> getPickupHistory(int pageNum, int pageSize){
        List<SendMessageItem> result=null;
        int fromIndex = (pageNum - 1) * pageSize;
        SQLiteTemplate st=SQLiteTemplate.getInstance(manager);
        SQLiteTemplate.RowMapper<SendMessageItem> rowMapper=new SQLiteTemplate.RowMapper<SendMessageItem>() {
            @Override
            public SendMessageItem mapRow(Cursor cursor, int index) {
                SendMessageItem item=new SendMessageItem();
                item.setId(cursor.getInt(cursor.getColumnIndex("_id")));
                item.setMsgId(cursor.getString(cursor.getColumnIndex("msg_id")));
                item.setContent(cursor.getString(cursor.getColumnIndex("msg_content")));
                item.setParam(cursor.getString(cursor.getColumnIndex("msg_param")));
                item.setTimeString(cursor.getString(cursor.getColumnIndex("msg_time")));
                item.setMyUserId(cursor.getString(cursor.getColumnIndex("my_user_id")));
                item.setFriendUserId(cursor.getString(cursor.getColumnIndex("friend_id")));
                item.setFriendName(cursor.getString(cursor.getColumnIndex("person_name")));
                item.setFriendHeadUrl(cursor.getString(cursor.getColumnIndex("person_url")));
                item.setMsgCategory(cursor.getInt(cursor.getColumnIndex("msg_category")));
                item.setMsgType(cursor.getInt(cursor.getColumnIndex("msg_type")));
                item.setMsgStatus(cursor.getInt(cursor.getColumnIndex("msg_status")));
                item.setDirection(cursor.getInt(cursor.getColumnIndex("msg_direction")));
                return item;
            }
        };
        String sql="select m._id, m.msg_id, m.msg_content,m.msg_param,m.msg_time,m.my_user_id,m.friend_id,m.msg_category,m.msg_type,m.msg_status,m.msg_direction,i.person_name,i.person_url from im_msg_info m left join im_friend_info i on m.friend_id = i.person_id where m.my_user_id=? and m.msg_category = ? and m.msg_type = ? order by m._id desc limit ? , ?";
        result=st.queryForList(rowMapper,sql,new String[] { CommonPrefs.getCurentUser(), "" + SendMessageItem.CATEGORY_PUSH,"" + SendMessageItem.TYPE_FRIEND_LOCATION,
                "" + fromIndex, "" + pageSize });
        return result;
    }*/

    /*public void updateMsgStatusById(String msgId,int status){
        ContentValues contentValues = new ContentValues();
        contentValues.put("msg_status", status);
        SQLiteTemplate st=SQLiteTemplate.getInstance(manager);
        st.update("im_msg_info",contentValues,"msg_id=?",new String[]{msgId});
    }

    public void delMsgById(String msgId){
        SQLiteTemplate st=SQLiteTemplate.getInstance(manager);
        st.deleteByField("im_msg_info","msg_id",msgId);
    }*/



}
