package com.anla.rpc.conditions.hellodubbo.stub.provider;

import com.anla.rpc.conditions.hellodubbo.stub.vo.IndexVO;

/**
 * @author luoan
 * @version 1.0
 * @date 2019/5/20 14:36
 **/
public interface IndexFacade {

    /**
     *
     * @param msg
     * @return
     */
    public IndexVO index(String msg);
}
