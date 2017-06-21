package com.liudong.System;

/**
 * Created by liudong on 2017/6/21.
 */
public interface SystemLogService {

    int deleteSystemLog(String id);

    int insert(SystemLog record);

    int insertTest(SystemLog record);

    SystemLog selectSystemLog(String id);

    int updateSystemLog(SystemLog record);
}