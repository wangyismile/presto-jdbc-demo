import java.sql.*;

public class presto_connnector_hive {
    public static void main(String[] args) {
        try {
            //设置用户名密码
            String password = "nnJ80oS14xcc3isrdn10";
            String userName = "dd_edw";
            // 本地代理, 线上服务器,请取消此句
            // HttpUtil.isTest = true;
            //定义一个数据源的链接对象，选择合适的驱动
            Connection con = null;
            Class.forName("com.facebook.presto.jdbc.PrestoDriver");
            // 固定使用host : 172.22.96.76:8888
            //连接串格式：jdbc:jdpresto://presto-server-url/catalog/schema
            con = DriverManager.getConnection("jdbc:jdpresto://bdpadhoc.jd.com:8888/dd_edw/app", userName, password);
            //准备sql
            String sqlString = "select * from fdm.fdm_m99_cluster_hdfs_audit_aggr_info_di limit 3";
            PreparedStatement stmt = con.prepareStatement(sqlString);
            //执行sql
            ResultSet rs = stmt.executeQuery();
            //遍历查询结果
            int line = 1;
            while (rs.next()) {
                System.out.println(line + "  event_time=" + rs.getString(1) + "  mart_name=" + rs.getString(2));
                line++;
            }
            rs.close();
            con.close();
        } catch (Exception e) {
            System.out.println("Presto 连接异常：" + e);
        }
    }

}
