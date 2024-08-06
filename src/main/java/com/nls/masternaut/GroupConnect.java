package com.nls.masternaut;

import com.nls.masternaut.client.HttpClient;

public class GroupConnect {
    private final HttpClient client;

    GroupConnect(HttpClient client) {
        this.client = client;
    }

    public GroupListRequest list() {
        return new GroupListRequest(client);
    }

//    public Group get(String id) {
//        return client.get("group/" + id, null, Group.class);
//    }

    /**
     * No access to tested
     */
    public GroupAddRequest add() {
        return new GroupAddRequest(client);
    }

    /**
     * No access to tested
     */
    public GroupUpdateRequest update(String groupId) {
        return new GroupUpdateRequest(client, groupId);
    }

    /**
     * No access to tested
     */
    public void delete(String groupId) {
        client.delete("group/" + groupId, null, null);
    }

    // Not implemented
    /*
    public List<GroupDriverBehaviorMetric> behaviour(GroupBehaviourMetricRequest request) {
        client.post("driverbehaviourmetrics/group", request, new TypeReference<List<GroupDriverBehaviorMetric>>() {}));
    }
    */

}
