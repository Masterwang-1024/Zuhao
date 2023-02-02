package com.whl.zuhaowan.contants;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * @ClassName: Constant
 * TODO:类文件简单描述
 * @Author: liuxl
 * @UpdateUser: liuxl
 * @Version: 0.0.1
 */
@Configuration
public class Constant {


    public static String projectId;
    @Value("${pass.project.id}")
    private void setProjectId(String id){
        projectId = id;
    }

    /**
     * 用户名称 key
     */
    public static final String JWT_USER_NAME="jwt-user-name-key";

    /**
     * 角色信息key
     */
    public static final String ROLES_INFOS_KEY="roles-infos-key";

    /**
     * 权限信息key
     */
    public static final String PERMISSIONS_INFOS_KEY="permissions-infos-key";

    /**
     * refresh_token 主动退出后加入黑名单 key
     */
    public static final String JWT_REFRESH_TOKEN_BLACKLIST="jwt-refresh-token-blacklist_";

    /**
     * access_token 主动退出后加入黑名单 key
     */
    public static final String JWT_ACCESS_TOKEN_BLACKLIST="jwt-access-token-blacklist_";

    /**
     * 正常token
     */
    public static final String ACCESS_TOKEN="authorization";
    /**
     * 刷新token
     */
    public static final String REFRESH_TOKEN="refresh_token";

    /**
     * 标记用户是否已经被锁定
     */
    public static final String ACCOUNT_LOCK_KEY="account-lock-key_";

    /**
     * 标记用户是否已经删除
     */
    public static final String DELETED_USER_KEY="deleted-user-key_";

    /**
     * 主动去刷新 token key(适用场景 比如修改了用户的角色/权限去刷新token)
     */
    public static final String JWT_REFRESH_KEY="jwt-refresh-key_";
    /**
     * 标记新的access_token
     */
    public static final String JWT_REFRESH_IDENTIFICATION="jwt-refresh-identification_";

    /**
     * 部门编码key
     */
    public static final String DEPT_CODE_KEY="dept-code-key_";

    /**
     * 用户权鉴缓存 key
     */
    public static final String IDENTIFY_CACHE_KEY="shiro-cache:com.extrotec.saas.shiro.CustomRealm.authorizationCache:";

    /* ------------------paas平台接口地址集-----------------------------*/
    public static  String PAAS_NAMESPACE;
    public static final String PAAS_TOKEN = "paas-token-app-industry";
    public static final String PAAS_IMAGE_REPOSITORY_URL="docker-user.extrotec.com:30080/";
    /**登录接口**/
    public static final String PAAS_LOGIN_URL="http://paas.extrotec.com:30080/api/auth/v3/auth/tokens";
    public static final String PAAS_LOGIN_URL_STRP2="http://paas.extrotec.com:30080/api/keystone/v3/auth/tokens";
    /**数据集创建接口**/
    public static String PAAS_DATASET_CREATE_URL;
    /**公共数据集**/
    public static String PASS_COMMONT_DATASET_URL;
    /**单节点规格**/
    public static String PASS_FLAVOR_TYPE_URL;
    /**标注数据集**/
    public static String PAAS_MARKSET_CREATE_URL;
    /**模型管理**/
    public static String PAAS_MODEL_CREATE_URL;
    /**训练任务管理**/
    public static String PAAS_TRAIN_TASK_URL;
    /**公共镜像分页查询**/
    public static final String PAAS_COMMON_IMAGE_URL="http://paas.extrotec.com:30080/api/harbor/api/repositories?page_size=10&sort=-created_at&project_id=2&page=";
    /**公共镜像列表查询**/
    public static final String PAAS_COMMON_IMAGE_LIST_URL="http://paas.extrotec.com:30080/api/harbor/api/repositories?label_id=4&sort=name&project_id=2";
    /**镜像版本号**/
    public static final String PAAS_COMMON_IMAGE_VERSION_URL="http://paas.extrotec.com:30080/api/harbor/api/repositories/";
    /**文件上传接口**/
    public static  String PASS_FILE_UPLOAD_URL ;
    /*新增算法*/
    public static String PASS_CREATE_AN_ALGORITHM ;
    /*更新算法*//*删除算法*/
    public static String PASS_UPDATE_AN_ALGORITHM ;
    /*上传算法url*/
    public static String PASS_UPLOAD_AN_ALGORITHM ;
    /*新增规则集*/
    public static String PASS_ADD_AN_RULESET ;

    public static String PASS_ADD_AN_RULE;
    /*获取公共算法*/
    public static String GET_PASS_SHARED_ALGORITHM;
    /**训练任务日志**/
    public static String PAAS_TRAIN_LOG;
    /****/
    public static String PAAS_TASK_STATUS;
    /**
     * @Description 处理中状态
     **/
    public final static String PENDING ="Pending";
    /**
     * @Description 正常
     **/
    public final static String BOUND = "Bound";

    public final static String LABEL ="http://paas.extrotec.com:30080/api/label/v1/namespace/";
    public final static String COMPUTE ="http://paas.extrotec.com:30080/api/compute/v2/namespace/";

    /**
     * @ModifyTime 2021/08/30
     * url需要根据配置文件生成，需要初始化方法初始化变量值，
     * 上面的url设计projectId的需要重新初始化
     **/
    @PostConstruct
    public void init(){
        PAAS_NAMESPACE=projectId;
        PAAS_DATASET_CREATE_URL=COMPUTE+projectId+"/dataset";
        PASS_COMMONT_DATASET_URL=COMPUTE+projectId+"/shared-dataset";
        PASS_FLAVOR_TYPE_URL=COMPUTE+projectId+"/flavor?public=true&limit=0";
        PAAS_MARKSET_CREATE_URL=LABEL+projectId+"/datasets";
        PAAS_MODEL_CREATE_URL=COMPUTE+projectId+"/savedmodel";
        PAAS_TRAIN_TASK_URL=COMPUTE+projectId+"/batch-trainingjob";

        PASS_FILE_UPLOAD_URL ="http://paas.extrotec.com:30080/api/storage/v2/namespace/"+projectId+"/volume/";
        PASS_CREATE_AN_ALGORITHM =COMPUTE+projectId+"/algorithm";
        PASS_UPDATE_AN_ALGORITHM =COMPUTE+projectId+"/algorithm/";
        PASS_UPLOAD_AN_ALGORITHM ="http://paas.extrotec.com:30080/api/storage/v2/namespace/"+projectId+"/volume/";
        PASS_ADD_AN_RULESET =LABEL+projectId+"/rulesets";
        PASS_ADD_AN_RULE =LABEL+projectId+"/rulesets/";
        GET_PASS_SHARED_ALGORITHM =COMPUTE+projectId+"/shared-algorithm";

        PAAS_TRAIN_LOG = "http://paas.extrotec.com:30080/api/efk/v1/namespace/"+projectId+"/log";
        PAAS_TASK_STATUS = "http://paas.extrotec.com:30080/api/compute/v2/namespace/"+projectId+"/trainingjob";

    }
    public static String RSA_PUBLIC = "MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAJzsJM71CVVk85yWGhRTvBmPYPhdXVzvVW8jVsoE430Q" +
            "tJNUGvNt9s7ZSr0GnOFvF++zZrnK5Zi064X/a6qNwPsCAwEAAQ==";
    public static String RSA_PRIVATE = "MIIBUwIBADANBgkqhkiG9w0BAQEFAASCAT0wggE5AgEAAkEAnOwkzvUJVWTznJYaFFO8GY9g+F1d" +
            "XO9VbyNWygTjfRC0k1Qa8232ztlKvQac4W8X77NmucrlmLTrhf9rqo3A+wIDAQABAkA3EawNMPLY" +
            "k/fmEMsQLR6KvcVjA6jbD2ak/PrdJIANCDwWy66y0SD4+0nbSUfwhUL/9Ab+ChKSeN+powe7xZyx" +
            "AiEA7UzWYG9lb/8Cn2nqK4SiK8CZk84T4xWPJae44SNh98MCIQCpSc+JC11sqgNFywuTtB7LvCbN" +
            "nE5iLS2yVsVrGLQ2aQIgJxkX4T+5aOCNYBKlYJIz3h2XsECuCiZNNZkS3xVjWN0CIFDwT1+wpVxc" +
            "9O+bos9d4RN7w/mub/OUsjKANid8Sj1xAiAtQfTq9nisr51BuSNtXocViyH46Q1ZpaX6kXAfVO7Y" +
            "2A==";
}
