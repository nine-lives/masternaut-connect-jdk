package com.nls.masternaut;

import com.fasterxml.jackson.core.type.TypeReference;
import com.nls.masternaut.client.HttpClient;

import java.util.List;

public class GroupConnect {
    private final HttpClient client;

    GroupConnect(HttpClient client) {
        this.client = client;
    }

    public List<Group> list() {
        return client.get("group", null, new TypeReference<List<Group>>() { });
    }

//    public Group get(String id) {
//        return client.get("group/" + id, null, Group.class);
//    }

    /**
     * No access to tested
     */
    public Group add(GroupAddRequest request) {
        return client.post("group", request, Group.class);
    }

    /**
     * No access to tested
     */
    public Group update(GroupUpdateRequest request) {
        return client.put("group", request, Group.class);
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
