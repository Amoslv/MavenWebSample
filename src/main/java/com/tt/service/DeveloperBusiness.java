package com.tt.service;

import com.tt.db.DBHelper;
import com.tt.model.DeveloperModel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 进行数据库相关的业务
 */
public class DeveloperBusiness {

    public List<DeveloperModel> getAllDevelopers() {
        List<DeveloperModel> developerList = new ArrayList<>();

        String sql = "select * from tt";
        DBHelper dbHelper = new DBHelper(sql);

        ResultSet resultSet = null;

        try {
            resultSet = dbHelper.preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                String site = resultSet.getString(3);
                String avatar = resultSet.getString(4);
                DeveloperModel developerModel = new DeveloperModel();
                developerModel.setId(id);
                developerModel.setName(name);
                developerModel.setSite(site);
                developerModel.setAvatar(avatar);
                developerList.add(developerModel);
            }

            resultSet.close();
            dbHelper.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return developerList;
    }

    public DeveloperModel getDeveloper(String developerId) {

        String sql = "select * from tt where id =" + developerId;
        DBHelper dbHelper = new DBHelper(sql);
        DeveloperModel developerModel = null;

        try {
            ResultSet resultSet = dbHelper.preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                String site = resultSet.getString(3);
                String avatar = resultSet.getString(4);
                developerModel = new DeveloperModel();
                developerModel.setId(id);
                developerModel.setName(name);
                developerModel.setSite(site);
                developerModel.setAvatar(avatar);
            }
            resultSet.close();
            dbHelper.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return developerModel;
    }

    /**
     * 执行数据库操作
     */
    public boolean execute(DBHelper dbHelper) {

        try {
            dbHelper.preparedStatement.execute();
            dbHelper.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 增加人员
     */
    public boolean addDeveloper(DeveloperModel developerModel) {

        String sql = "INSERT INTO tt(name, site, avatar) VALUES(" +
                "'" + developerModel.getName() + "'," +
                "'" + developerModel.getSite() + "'," +
                "'" + developerModel.getAvatar() + "')";

        System.out.println(sql);

        DBHelper dbHelper = new DBHelper(sql);

        return execute(dbHelper);
    }

    /**
     * 更新人员
     */
    public boolean updateDeveloper(String name, String id) {
        String sql = "UPDATE tt SET name='" + name + "' where id=" + id;
        System.out.println(sql);
        DBHelper dbHelper = new DBHelper(sql);

        try {
            dbHelper.preparedStatement.executeUpdate();
            dbHelper.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * 删除人员
     */
    public boolean deleteDeveloper(String id) {
        String sql = "DELETE FROM tt where id=" + id;
        System.out.println(sql);
        DBHelper dbHelper = new DBHelper(sql);

        return execute(dbHelper);
    }
}
