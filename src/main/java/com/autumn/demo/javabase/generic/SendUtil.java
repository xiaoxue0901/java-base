package com.autumn.demo.javabase.generic;

import com.autumn.demo.javabase.bean.FindUser;
import com.autumn.demo.javabase.bean.Head;
import lombok.extern.slf4j.Slf4j;

/**
 * @author xql132@zcsmart.com
 * @date 2020/12/25
 * @time 17:41
 * @description 泛型方法
 */
@Slf4j
public class SendUtil {

    private BeanFactory beanFactory;

    public SendUtil(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    /**
     * 泛型方法
     *
     * @param req
     * @param repClass
     * @param <REQ>
     * @param <REP>
     * @return
     */
    public <REQ, REP> RespVO<REP> send(REQ req, Class<REP> repClass) {
        log.info("打印请求信息:{}", req);
        // 业务操作
        REP resp = (REP) beanFactory.getBean(repClass);
        RespVO<REP> respVO = new RespVO<>();
        respVO.setCode(0);
        respVO.setMessage("suc");
        respVO.setData(resp);
        return respVO;
    }

    /**
     * 通配符
     * @param target
     * @return
     */
    public Object send(Class<?> target) {
        try {
            return target.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取request
     * @param reqClass
     * @param <REQ>
     * @return
     */
  public <REQ extends Head> REQ getReq(Class<? extends Head> reqClass) {
      REQ req = null;
      try {
          req = (REQ)reqClass.newInstance();
      } catch (InstantiationException e) {
          e.printStackTrace();
      } catch (IllegalAccessException e) {
          e.printStackTrace();
      }
      return req;
  }

  public <RESP extends Head> RESP getResp(Class<? super FindUser> respClass) {
      try {
          return (RESP) respClass.newInstance();
      } catch (InstantiationException e) {
          e.printStackTrace();
      } catch (IllegalAccessException e) {
          e.printStackTrace();
      }
      return null;
  }

}
