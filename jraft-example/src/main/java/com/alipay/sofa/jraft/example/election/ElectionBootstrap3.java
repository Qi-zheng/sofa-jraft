/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.alipay.sofa.jraft.example.election;

import com.alipay.sofa.jraft.entity.PeerId;

/**
 *
 * @author jiachun.fjc
 */
public class ElectionBootstrap3 {

    // Start elections by 3 instance. Note that if multiple instances are started on the same machine,
    public static void main(final String[] args) {

        final String dataPath = "/tmp/server3";
        final String groupId = "election_test";
        final String serverIdStr = "127.0.0.1:8083";
        final String initialConfStr = "127.0.0.1:8081,127.0.0.1:8082,127.0.0.1:8083";

        final ElectionNodeOptions electionOpts = new ElectionNodeOptions();
        electionOpts.setDataPath(dataPath);
        electionOpts.setGroupId(groupId);
        electionOpts.setServerAddress(serverIdStr);
        electionOpts.setInitialServerAddressList(initialConfStr);

        final ElectionNode node = new ElectionNode();
        node.addLeaderStateListener(new LeaderStateListener() {

            @Override
            public void onLeaderStart(long leaderTerm) {
                PeerId serverId = node.getNode().getLeaderId();
                String ip = serverId.getIp();
                int port = serverId.getPort();
                System.out.println("[ElectionBootstrap] Leader's ip is: " + ip + ", port: " + port);
                System.out.println("[ElectionBootstrap] Leader start on term: " + leaderTerm);
            }

            @Override
            public void onLeaderStop(long leaderTerm) {
                System.out.println("[ElectionBootstrap] Leader stop on term: " + leaderTerm);
            }
        });
        node.init(electionOpts);
    }
}
