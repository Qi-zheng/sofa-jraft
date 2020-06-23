package com.alipay.sofa.jraft.example.base;

import com.alipay.sofa.jraft.entity.PeerId;
import com.alipay.sofa.jraft.util.Endpoint;

/**
 * User: seven
 * Date: 2020/6/21
 * Time: 9:00 下午
 * Description:
 * Version: 1.0
 */
public class BaseTest {

    public static void main(String[] args) {
        PeerId peer = new PeerId("localhost", 8080);
        Endpoint addr = peer.getEndpoint(); // 获取节点地址
        int index = peer.getIdx(); // 获取节点序号，目前一直为 0
        System.out.println(addr);
        System.out.println(index);
        String s = peer.toString(); // 结果为 localhost:8080
        System.out.println(peer);
        boolean success = peer.parse(s);
        System.out.println(success);
    }
}
