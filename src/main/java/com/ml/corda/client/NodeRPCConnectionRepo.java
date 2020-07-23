package com.ml.corda.client;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author mengl
 */
public class NodeRPCConnectionRepo {
    private static final NodeRPCConnectionRepo instance = new NodeRPCConnectionRepo();

    private NodeRPCConnectionRepo() {
    }

    public static NodeRPCConnectionRepo getInstance() {
        return instance;
    }

    private Map<String, NodeRPCConnection> repo = new ConcurrentHashMap<>();

    public Map<String, NodeRPCConnection> getRepo() {
        return this.repo;
    }

    public void addRPCConnection(String legalName, NodeRPCConnection nodeRPCConnection) {
        this.repo.put(legalName, nodeRPCConnection);
    }
}
