package com.tt;

import com.tt.model.CommonModel;
import com.tt.model.DeveloperModel;
import com.tt.service.DeveloperBusiness;
import com.tt.utils.ConstantUtils;
import com.tt.utils.GsonUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * 增删改查接口
 */
@WebServlet(name = "DeveloperServlet", urlPatterns = {ConstantUtils.ALL_DEVELOPERS_URL,
        ConstantUtils.QUERY_DEVELOPER_URL, ConstantUtils.ADD_DEVELOPER_URL,
        ConstantUtils.UPDATE_DEVELOPER_URL, ConstantUtils.DELETE_DEVELOPER_URL})
//@WebServlet(name = "DeveloperServlet", urlPatterns = "/developer")
public class DeveloperServlet extends HttpServlet {

    private DeveloperBusiness developerBusiness = new DeveloperBusiness();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("doGet_url" + request.getRequestURI());

       /* response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<h3>hello world amos</h3>");*/

        request.setCharacterEncoding("UTF-8");
        //设置响应头内容类型
        response.setContentType("text/json;charset=UTF-8");
        String url = request.getRequestURI();
        /*if (url.equals(ConstantUtils.ALL_DEVELOPERS_URL)) {
            getAllDevelopers(request, response);
        }else if (url.equals(ConstantUtils.QUERY_DEVELOPER_URL)) {
            getDeveloper(request, response);
        }else if (url.equals(ConstantUtils.ADD_DEVELOPER_URL)) {
            addDeveloper(request, response);
        }else if (url.equals(ConstantUtils.UPDATE_DEVELOPER_URL)) {
            updateDeveloper(request, response);
        }else if (url.equals(ConstantUtils.DELETE_DEVELOPER_URL)){
            doDelete(request, response);
        }*/
        switch (url) {
            case ConstantUtils.ALL_DEVELOPERS_URL:
                getAllDevelopers(request, response);
                break;
            case ConstantUtils.QUERY_DEVELOPER_URL:
                getDeveloper(request, response);
                break;
            case ConstantUtils.ADD_DEVELOPER_URL:
                addDeveloper(request, response);
                break;
            case ConstantUtils.UPDATE_DEVELOPER_URL:
                updateDeveloper(request, response);
                break;
            case ConstantUtils.DELETE_DEVELOPER_URL:
                doDelete(request, response);
                break;
            default:
                break;
        }

    }

    /**
     * 获取所有人员数据
     */
    private void getAllDevelopers(HttpServletRequest request, HttpServletResponse response) throws IOException {

        //设置逻辑实现
        PrintWriter printWriter = response.getWriter();

        List<DeveloperModel> developerList = developerBusiness.getAllDevelopers();

        CommonModel commonModel = new CommonModel();

        if (developerList.size() > 0) {
            commonModel.setSuccess();
            commonModel.setData(developerList);
        } else {
            commonModel.setFail();
        }
        printWriter.println(GsonUtils.bean2Json(commonModel));
        printWriter.flush();
        printWriter.close();
    }

    /**
     * 获取单个人员数据
     */
    private void getDeveloper(HttpServletRequest request, HttpServletResponse response) throws IOException {

        PrintWriter printWriter = response.getWriter();
        //接受参数
        String id = request.getParameter("id");
        System.out.println("getDeveloper_id: " + id);
        DeveloperModel developerModel = developerBusiness.getDeveloper(id);

        CommonModel commonModel = new CommonModel();

        if (developerModel == null) {
            commonModel.setFail();
        } else {
            commonModel.setSuccess();
            commonModel.setData(developerModel);
        }

        printWriter.println(GsonUtils.bean2Json(commonModel));
        printWriter.flush();
        printWriter.close();

    }

    /**
     * 增加人员
     */
    private void addDeveloper(HttpServletRequest request, HttpServletResponse response) throws IOException {

        PrintWriter printWriter = response.getWriter();

        //设置数据
        String name = request.getParameter("name");
        System.out.println("addDeveloper_name: " + name);
        String site = request.getParameter("site");
        String avatar = request.getParameter("avatar");

        CommonModel commonModel = new CommonModel();
        DeveloperModel developerModel = new DeveloperModel();
        developerModel.setName(name);
        developerModel.setSite(site);
        developerModel.setAvatar(avatar);

        boolean insertSuccess = developerBusiness.addDeveloper(developerModel);

        System.out.println("addDeveloper_insertSuccess: " + insertSuccess);

        if (insertSuccess) {
            commonModel.setSuccess();
        }else {
            commonModel.setFail();
        }

        printWriter.println(GsonUtils.bean2Json(commonModel));
       /* printWriter.flush();
        printWriter.close();*/

    }

    /**
     * 更新人员
     */
    private void updateDeveloper(HttpServletRequest request, HttpServletResponse response) throws IOException {

        PrintWriter printWriter = response.getWriter();

        //得到数据
        String id = request.getParameter("id");
        String name = request.getParameter("name");

        System.out.println("updateDeveloper_name: " + name);

        CommonModel commonModel = new CommonModel();
        boolean updateSuccess = developerBusiness.updateDeveloper(name, id);
        if (updateSuccess) {
            commonModel.setSuccess();
        }else {
            commonModel.setFail();
        }

        printWriter.println(GsonUtils.bean2Json(commonModel));

    }

}
