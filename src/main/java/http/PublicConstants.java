package http;

import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;


/**
 * 公共全局常量类
 *
 * @author cheney
 */
public class PublicConstants {
    public static Logger log = Logger.getLogger(PublicConstants.class);
    public static Logger mrLog = Logger.getLogger("MoReport");
    public static Logger cmppRecvLog = Logger.getLogger("CmppRecv");
    public static Logger sgipRecvLog = Logger.getLogger("SgipRecv");
    public static Logger smgpRecvLog = Logger.getLogger("SmgpRecv");
    public static Logger smppRecvLog = Logger.getLogger("SmppRecv");
    public static Logger httpRecvLog = Logger.getLogger("HttpRecv");
    public static String systemProperty;  //系统属性 production 生产环境  sandbox 测试沙箱环境
    public static String memcachedAddr;
    public static int webconsolePort = 5003;
    public static int cmpp20Port = 8855;
    public static int cmpp30Port = 8856;
    public static int sgip12Port = 8857;
    public static int smpp34Port = 8858;
    public static int smgp30Port = 8859;
    public static int httpPort = 8860;
    public static int httpPort2 = 8861;
    public static int httpsPort = 8862;
    public static int meishengPort = 8863;
    public static int httpGbkPort = 8865;
    public static int wjsxPort = 9010;
    public static String jdwsAddr = "http://127.0.0.1:9011/jdserver";
    public static String webServiceAddr = "http://127.0.0.1:9012/SmsWebService.asmx";
    public static String webServiceTaskUrl = "http://172.20.1.168:8280/jersey/clientWeb/sendSms";

    public static long moscanInterval = 3000;
    public static long httpReportInterval = 3000;
    public static long swRefInterval = 3000000;
    public static long cpRefInterval = 3000000;

    public static String smppPacklog = "no";
    public static String smgpPacklog = "no";
    public static String sgipPacklog = "no";
    public static String cmpp20Packlog = "no";
    public static String cmpp30Packlog = "no";
    public static String httpPacklog = "no";
    public static String http2Packlog = "no";
    public static String httpsPacklog = "no";
    public static String warnSvcUrl = "";
    public static int maxSubmitSessionCnt = 10;
    public static int maxSmsTaskProc = 0;

    //是否启动HTTP*协议状态报告推送
    public static String httpReportPush = "yes";
    public static String http2ReportPush = "yes";
    public static String httpsReportPush = "yes";
    public static String sgipReportPush = "yes";

    // 是否启动HTTP*协议上行推送
    public static String httpMoPush = "yes";

    //用户业务主任务池参数
    public static int datCoreSize = 50;
    public static int datMaxSize = 200;
    public static int datQueueSize = 50;

    //用户业务备任务池参数(营销)
    public static int altCoreSize = 20;
    public static int altMaxSize = 20;
    public static int altQueueSize = 10000;

    //批量保存数据任务池参数
    public static int bstCoreSize = 20;
    public static int bstMaxSize = 100;
    public static int bstQueueSize = 50;

    public static String bogusPath = "";

    private static Properties prop1;

    //本地平台地址
    public static String localIP;
    public static String acctService;

    //本平台短信网关编号(分2种协议)
    public static int cmppIsmgId;
    public static String smgpIsmgId;

    //是否采集号段
    public static String collectMSI = "yes";


    public static void initPublicConstants() {
        Properties prop = new Properties();
        FileInputStream fis;
        try {
            fis = new FileInputStream(System.getProperties().getProperty("CONFIG_FILE"));
            prop.load(fis);
            prop1 = prop;
            systemProperty = prop1.getProperty("app.systemProperty");
            memcachedAddr = prop1.getProperty("app.memcachedAddr");
            webconsolePort = Integer.parseInt(prop1.getProperty("app.webconsolePort"));
            cmpp20Port = Integer.parseInt(prop1.getProperty("app.cmpp20Port", "8855"));
            cmpp30Port = Integer.parseInt(prop1.getProperty("app.cmpp30Port", "8856"));
            sgip12Port = Integer.parseInt(prop1.getProperty("app.sgip12Port", "8857"));
            smpp34Port = Integer.parseInt(prop1.getProperty("app.smpp34Port", "8858"));
            smgp30Port = Integer.parseInt(prop1.getProperty("app.smgp30Port", "8859"));
            httpPort = Integer.parseInt(prop1.getProperty("app.httpPort", "8860"));
            httpPort2 = Integer.parseInt(prop1.getProperty("app.httpPort2", "8861"));
            httpsPort = Integer.parseInt(prop1.getProperty("app.httpsPort", "8862"));
            meishengPort = Integer.parseInt(prop1.getProperty("app.meishengPort", "8863"));
            httpGbkPort = Integer.parseInt(prop1.getProperty("app.httpGbkPort", "8865"));
            jdwsAddr = prop1.getProperty("app.jdwsAddr", "http://127.0.0.1:9011/jdserver");
            webServiceAddr = prop1.getProperty("app.webServiceAddr", "http://127.0.0.1:9012/SmsWebService.asmx");
            webServiceTaskUrl = prop1.getProperty("app.webServiceTaskUrl", "");

            moscanInterval = Long.parseLong(prop1.getProperty("app.moscanInterval"));
            httpReportInterval = Long.parseLong(prop1.getProperty("app.httpReportInterval"));
            swRefInterval = Long.parseLong(prop1.getProperty("app.swRefInterval", "300000"));
            cpRefInterval = Long.parseLong(prop1.getProperty("app.cpRefInterval", "300000"));

            smppPacklog = prop1.getProperty("app.smpp.packlog", "no");
            smgpPacklog = prop1.getProperty("app.smgp.packlog", "no");
            sgipPacklog = prop1.getProperty("app.sgip.packlog", "no");
            cmpp20Packlog = prop1.getProperty("app.cmpp20.packlog", "no");
            cmpp30Packlog = prop1.getProperty("app.cmpp30.packlog", "no");
            httpPacklog = prop1.getProperty("app.http.packlog", "no");
            http2Packlog = prop1.getProperty("app.http2.packlog", "no");
            httpsPacklog = prop1.getProperty("app.https.packlog", "no");
            warnSvcUrl = prop1.getProperty("app.warnSvcUrl", "");

            httpReportPush = prop1.getProperty("app.http.reportpush", "yes");
            http2ReportPush = prop1.getProperty("app.http2.reportpush", "yes");
            httpsReportPush = prop1.getProperty("app.https.reportpush", "yes");
            sgipReportPush = prop1.getProperty("app.sgip.reportpush", "yes");

            httpMoPush = prop1.getProperty("app.http.mopush", "yes");

            acctService = prop1.getProperty("app.acctService", "yes");

            datCoreSize = Integer.parseInt(prop1.getProperty("app.datCoreSize", "50"));
            datMaxSize = Integer.parseInt(prop1.getProperty("app.datMaxSize", "200"));
            datQueueSize = Integer.parseInt(prop1.getProperty("app.datQueueSize", "50"));

            altCoreSize = Integer.parseInt(prop1.getProperty("app.altCoreSize", "20"));
            altMaxSize = Integer.parseInt(prop1.getProperty("app.altMaxSize", "20"));
            altQueueSize = Integer.parseInt(prop1.getProperty("app.altQueueSize", "10000"));

            bstCoreSize = Integer.parseInt(prop1.getProperty("app.bstCoreSize", "20"));
            bstMaxSize = Integer.parseInt(prop1.getProperty("app.bstMaxSize", "100"));
            bstQueueSize = Integer.parseInt(prop1.getProperty("app.bstQueueSize", "50"));

            maxSubmitSessionCnt = Integer.parseInt(prop1.getProperty("app.maxSubmitSessionCnt", "10"));
            maxSmsTaskProc = Integer.parseInt(prop1.getProperty("app.maxSmsTaskProc", "0"));

            bogusPath = prop1.getProperty("app.bogusPath", "/home/smsplatform/app/smsserver/cfg");

            localIP = prop1.getProperty("app.localIP", "127.0.0.1");

            cmppIsmgId = Integer.parseInt(prop1.getProperty("app.cmppIsmgId", "59112"));
            smgpIsmgId = prop1.getProperty("app.smgpIsmgId", "591061");

            collectMSI = prop1.getProperty("app.collectMSI", "yes");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        log.info("初始化系统变量成功！");
        mrLog.info("初始化系统变量成功！");
    }

}

