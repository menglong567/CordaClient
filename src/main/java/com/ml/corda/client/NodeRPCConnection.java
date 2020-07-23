package com.ml.corda.client;

import net.corda.client.rpc.CordaRPCClient;
import net.corda.client.rpc.CordaRPCConnection;
import net.corda.core.messaging.CordaRPCOps;
import net.corda.core.utilities.NetworkHostAndPort;

public class NodeRPCConnection implements AutoCloseable {
    private final String host;
    private final String username;
    private final String password;
    private final int rpcPort;
    private CordaRPCConnection rpcConnection;
    private CordaRPCOps proxy;

    /**
     * The RPC proxy is configured based on the properties in `application.properties`.
     *
     * @param host     The host of the node we are connecting to.
     * @param rpcPort  The RPC port of the node we are connecting to.
     * @param username The username for logging into the RPC client.
     * @param password The password for logging into the RPC client.
     */
    public NodeRPCConnection(String host, String username, String password, int rpcPort) {
        this.host = host;
        this.username = username;
        this.password = password;
        this.rpcPort = rpcPort;
        initialiseNodeRPCConnection();
    }

    public void initialiseNodeRPCConnection() {
        NetworkHostAndPort rpcAddress = new NetworkHostAndPort(host, rpcPort);
        CordaRPCClient rpcClient = new CordaRPCClient(rpcAddress);
        this.rpcConnection = rpcClient.start(username, password);
        this.proxy = rpcConnection.getProxy();
    }

    public CordaRPCOps getProxy() {
        return proxy;
    }

    @Override
    public void close() throws Exception {
        rpcConnection.notifyServerAndClose();
    }
}
