package com.anla.rpc.hellodubbo.dubbo.provider;

import com.anla.rpc.hellodubbo.stub.provider.IndexFacade;
import com.anla.rpc.hellodubbo.stub.vo.IndexVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;

/**
 * @author luoan
 * @version 1.0
 * @date 2019/5/20 14:47
 **/
@Slf4j
@Service(version = "1.0.0")
public class IndexProvider implements IndexFacade {

    @Override
    public IndexVO index(String msg) {
        log.info("index 入参{}", msg);
        IndexVO vo = new IndexVO();
        vo.setMsg("Hello ," + msg + " Welcome to dubbo");
        log.info("index 出参{}", vo);
        return vo;
    }
}
