package pro.hu.xm.framework;

import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.sms.v20190711.SmsClient;
import com.tencentcloudapi.sms.v20190711.models.SendSmsRequest;
import com.tencentcloudapi.sms.v20190711.models.SendSmsResponse;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @desc 短信发送工具类
 * @author Littiger
 * @title: SMSUtils
 * @description: TODO
 * @date 2021/5/3116:23
 */
public class SendCodeUtils {

    private static final String SMSAPPID = "1400320351";
    private static final String SMSSECREID = "AKID3jdhvTasZ3K9k6ufDVVJzQccbOvCECwN";
    private static final String SMSSECREKEY = "SM2ScXIo8HNEoFRTKtHYbOwENHwQt87r";
    private static final String SMSSIGN = "快学网";


    /**
     * @desc  发送短信
     * @param phoneNumbers 目标电话号码
     * @param valcode 验证码
     * @return boolean
     * @throws TencentCloudSDKException
     */
    public static boolean send(String phoneNumbers,String valcode) throws TencentCloudSDKException {

        String templateId = "881214";

        // 实例化一个认证对象，入参需要传入腾讯云账户密钥对secretId，secretKey。
        Credential cred = new Credential(SMSSECREID, SMSSECREKEY);

        // 实例化一个客户端配置对象，可以指定超时时间等配置
        ClientProfile clientProfile = new ClientProfile();

        // 实例化要请求产品(以sms为例)的client对象
        // 第二个参数是地域信息，可以直接填写字符串ap-guangzhou，或者引用预设的常量
        SmsClient client = new SmsClient(cred, "ap-guangzhou", clientProfile);

        // 实例化一个请求对象，根据调用的接口和实际情况，可以进一步设置请求参数
        SendSmsRequest req = new SendSmsRequest();

        // 短信应用ID: 短信SdkAppid在 [短信控制台] 添加应用后生成的实际SdkAppid，示例如1400006666
        String appid = SMSAPPID;
        req.setSmsSdkAppid(appid);

        // 短信签名内容: 使用 UTF-8 编码，必须填写已审核通过的签名，签名信息可登录 [短信控制台] 查看
        String sign = SMSSIGN;
        req.setSign(sign);

        // 模板id
        req.setTemplateID(templateId);

        // 要发送的手机号
        // String[] phoneNumbers = {"+8621212313123", "+8612345678902",
        // "+8612345678903"};
        String[] phones = {"+86"+phoneNumbers};
        req.setPhoneNumberSet(phones);

        /* 模板参数: 若无模板参数，则设置为空 */
        String[] templateParams = { valcode,"5" };
        req.setTemplateParamSet(templateParams);

        /*
         * 通过 client 对象调用 SendSms 方法发起请求。注意请求方法名与请求对象是对应的 返回的 res 是一个 SendSmsResponse
         * 类的实例，与请求对象对应
         */
        SendSmsResponse res = client.SendSms(req);

        // 输出json格式的字符串回包

        return SendSmsResponse.toJsonString(res).contains("send success");
    }

    /**
     * @desc   生成四位数验证码
     * @return String
     */
    public static String createdCode() {
        String[] beforeShuffle = new String[] { "2", "3", "4", "5", "6", "7", "8", "9" };
        List<String> list = Arrays.asList(beforeShuffle);
        Collections.shuffle(list);// 自动洗牌
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i <= 3; i++) {
            sb.append(list.get(i));
        }

        return sb.toString();
    }

    /**
     * @desc  发送用户回访id信息
     * @param phoneNumber
     * @param surveyId
     * @return
     * @throws TencentCloudSDKException
     */
    public static boolean sendSurveyId(String phoneNumber,String surveyId) throws TencentCloudSDKException {

        String templateId = "978990";

        // 实例化一个认证对象，入参需要传入腾讯云账户密钥对secretId，secretKey。
        Credential cred = new Credential(SMSSECREID, SMSSECREKEY);

        // 实例化一个客户端配置对象，可以指定超时时间等配置
        ClientProfile clientProfile = new ClientProfile();

        // 实例化要请求产品(以sms为例)的client对象
        // 第二个参数是地域信息，可以直接填写字符串ap-guangzhou，或者引用预设的常量
        SmsClient client = new SmsClient(cred, "ap-guangzhou", clientProfile);

        // 实例化一个请求对象，根据调用的接口和实际情况，可以进一步设置请求参数
        SendSmsRequest req = new SendSmsRequest();

        // 短信应用ID: 短信SdkAppid在 [短信控制台] 添加应用后生成的实际SdkAppid，示例如1400006666
        String appid = SMSAPPID;
        req.setSmsSdkAppid(appid);

        // 短信签名内容: 使用 UTF-8 编码，必须填写已审核通过的签名，签名信息可登录 [短信控制台] 查看
        String sign = SMSSIGN;
        req.setSign(sign);

        // 模板id
        req.setTemplateID(templateId);

        // 要发送的手机号
        // String[] phoneNumbers = {"+8621212313123", "+8612345678902",
        // "+8612345678903"};
        String[] phones = {"+86"+phoneNumber};
        req.setPhoneNumberSet(phones);

        /* 模板参数: 若无模板参数，则设置为空 */
        String[] templateParams = { surveyId };
        req.setTemplateParamSet(templateParams);

        /*
         * 通过 client 对象调用 SendSms 方法发起请求。注意请求方法名与请求对象是对应的 返回的 res 是一个 SendSmsResponse
         * 类的实例，与请求对象对应
         */
        SendSmsResponse res = client.SendSms(req);

        // 输出json格式的字符串回包

        return SendSmsResponse.toJsonString(res).contains("send success");
    }

}
