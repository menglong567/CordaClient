package com.ml.corda.client.controller;

import com.ml.corda.client.NodeRPCConnection;
import com.ml.corda.client.NodeRPCConnectionRepo;
import com.ml.corda.client.model.CordaOperationResult;
import com.ml.corda.client.util.CordaUtil;
import com.ml.corda.client.util.GSonUtil;
import net.corda.core.contracts.ContractState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.TEXT_PLAIN_VALUE;

@RestController
public class StandardController {
    private static final Logger logger = LoggerFactory.getLogger(StandardController.class);
    public StandardController() {
    }

    /****
     *
     * @param nodeName
     * @param host
     * @param username
     * @param password
     * @param rpcPort
     * @return
     */
    @RequestMapping(value = "/servertime", produces = TEXT_PLAIN_VALUE, method = RequestMethod.POST, consumes = "application/x-www-form-urlencoded")
    private String serverTime(@RequestParam(value = "nodeName", required = true) String nodeName,
                              @RequestParam(value = "host", required = true) String host,
                              @RequestParam(value = "username", required = true) String username,
                              @RequestParam(value = "password", required = true) String password,
                              @RequestParam(value = "rpcPort", required = true) String rpcPort) {
        CordaOperationResult verifyResult = CordaUtil.getInstance().verifyConnectionParameters(nodeName, host, username, password, rpcPort);
        NodeRPCConnection nodeRPCConnection = null;
        if (!verifyResult.isResult()) {
            return GSonUtil.getInstance().object2Json(verifyResult);
        }
        if (!NodeRPCConnectionRepo.getInstance().getRepo().containsKey(nodeName)) {
            nodeRPCConnection = new NodeRPCConnection(host, username, password, Integer.parseInt(rpcPort.trim()));
            NodeRPCConnectionRepo.getInstance().addRPCConnection(nodeName, nodeRPCConnection);
            logger.info("NodeRPCConnection created successfully for node:" + nodeName);
        } else {
            nodeRPCConnection = NodeRPCConnectionRepo.getInstance().getRepo().get(nodeName);
            logger.info("Using existing NodeRPCConnection for node:" + nodeName);
        }
        return (LocalDateTime.ofInstant(nodeRPCConnection.getProxy().currentNodeTime(), ZoneId.of("UTC"))).toString();
    }

    /****
     *
     * @param nodeName
     * @param host
     * @param username
     * @param password
     * @param rpcPort
     * @return
     */
    @RequestMapping(value = "/addresses", produces = TEXT_PLAIN_VALUE, method = RequestMethod.POST, consumes = "application/x-www-form-urlencoded")
    private String addresses(@RequestParam(value = "nodeName", required = true) String nodeName,
                             @RequestParam(value = "host", required = true) String host,
                             @RequestParam(value = "username", required = true) String username,
                             @RequestParam(value = "password", required = true) String password,
                             @RequestParam(value = "rpcPort", required = true) String rpcPort) {
        CordaOperationResult verifyResult = CordaUtil.getInstance().verifyConnectionParameters(nodeName, host, username, password, rpcPort);
        NodeRPCConnection nodeRPCConnection = null;
        if (!verifyResult.isResult()) {
            return GSonUtil.getInstance().object2Json(verifyResult);
        }
        if (!NodeRPCConnectionRepo.getInstance().getRepo().containsKey(nodeName)) {
            nodeRPCConnection = new NodeRPCConnection(host, username, password, Integer.parseInt(rpcPort.trim()));
            NodeRPCConnectionRepo.getInstance().addRPCConnection(nodeName, nodeRPCConnection);
            logger.info("NodeRPCConnection created successfully for node:" + nodeName);
        } else {
            nodeRPCConnection = NodeRPCConnectionRepo.getInstance().getRepo().get(nodeName);
            logger.info("Using existing NodeRPCConnection for node:" + nodeName);
        }
        return nodeRPCConnection.getProxy().nodeInfo().getAddresses().toString();
    }

    /****
     *
     * @param nodeName
     * @param host
     * @param username
     * @param password
     * @param rpcPort
     * @return
     */
    @RequestMapping(value = "/identities", produces = TEXT_PLAIN_VALUE, method = RequestMethod.POST, consumes = "application/x-www-form-urlencoded")
    private String identities(@RequestParam(value = "nodeName", required = true) String nodeName,
                              @RequestParam(value = "host", required = true) String host,
                              @RequestParam(value = "username", required = true) String username,
                              @RequestParam(value = "password", required = true) String password,
                              @RequestParam(value = "rpcPort", required = true) String rpcPort) {
        CordaOperationResult verifyResult = CordaUtil.getInstance().verifyConnectionParameters(nodeName, host, username, password, rpcPort);
        NodeRPCConnection nodeRPCConnection = null;
        if (!verifyResult.isResult()) {
            return GSonUtil.getInstance().object2Json(verifyResult);
        }
        if (!NodeRPCConnectionRepo.getInstance().getRepo().containsKey(nodeName)) {
            nodeRPCConnection = new NodeRPCConnection(host, username, password, Integer.parseInt(rpcPort.trim()));
            NodeRPCConnectionRepo.getInstance().addRPCConnection(nodeName, nodeRPCConnection);
            logger.info("NodeRPCConnection created successfully for node:" + nodeName);
        } else {
            nodeRPCConnection = NodeRPCConnectionRepo.getInstance().getRepo().get(nodeName);
            logger.info("Using existing NodeRPCConnection for node:" + nodeName);
        }
        return nodeRPCConnection.getProxy().nodeInfo().getLegalIdentities().toString();
    }

    /****
     *
     * @param nodeName
     * @param host
     * @param username
     * @param password
     * @param rpcPort
     * @return
     */
    @RequestMapping(value = "/platformversion", produces = TEXT_PLAIN_VALUE, method = RequestMethod.POST, consumes = "application/x-www-form-urlencoded")
    private String platformVersion(@RequestParam(value = "nodeName", required = true) String nodeName,
                                   @RequestParam(value = "host", required = true) String host,
                                   @RequestParam(value = "username", required = true) String username,
                                   @RequestParam(value = "password", required = true) String password,
                                   @RequestParam(value = "rpcPort", required = true) String rpcPort) {
        CordaOperationResult verifyResult = CordaUtil.getInstance().verifyConnectionParameters(nodeName, host, username, password, rpcPort);
        NodeRPCConnection nodeRPCConnection = null;
        if (!verifyResult.isResult()) {
            return GSonUtil.getInstance().object2Json(verifyResult);
        }
        if (!NodeRPCConnectionRepo.getInstance().getRepo().containsKey(nodeName)) {
            nodeRPCConnection = new NodeRPCConnection(host, username, password, Integer.parseInt(rpcPort.trim()));
            NodeRPCConnectionRepo.getInstance().addRPCConnection(nodeName, nodeRPCConnection);
            logger.info("NodeRPCConnection created successfully for node:" + nodeName);
        } else {
            nodeRPCConnection = NodeRPCConnectionRepo.getInstance().getRepo().get(nodeName);
            logger.info("Using existing NodeRPCConnection for node:" + nodeName);
        }
        return Integer.toString(nodeRPCConnection.getProxy().nodeInfo().getPlatformVersion());
    }

    /****
     *
     * @param nodeName
     * @param host
     * @param username
     * @param password
     * @param rpcPort
     * @return
     */
    @RequestMapping(value = "/peers", produces = TEXT_PLAIN_VALUE)
    private String peers(@RequestParam(value = "nodeName", required = true) String nodeName,
                         @RequestParam(value = "host", required = true) String host,
                         @RequestParam(value = "username", required = true) String username,
                         @RequestParam(value = "password", required = true) String password,
                         @RequestParam(value = "rpcPort", required = true) String rpcPort) {
        CordaOperationResult verifyResult = CordaUtil.getInstance().verifyConnectionParameters(nodeName, host, username, password, rpcPort);
        NodeRPCConnection nodeRPCConnection = null;
        if (!verifyResult.isResult()) {
            return GSonUtil.getInstance().object2Json(verifyResult);
        }
        if (!NodeRPCConnectionRepo.getInstance().getRepo().containsKey(nodeName)) {
            nodeRPCConnection = new NodeRPCConnection(host, username, password, Integer.parseInt(rpcPort.trim()));
            NodeRPCConnectionRepo.getInstance().addRPCConnection(nodeName, nodeRPCConnection);
            logger.info("NodeRPCConnection created successfully for node:" + nodeName);
        } else {
            nodeRPCConnection = NodeRPCConnectionRepo.getInstance().getRepo().get(nodeName);
            logger.info("Using existing NodeRPCConnection for node:" + nodeName);
        }
        return nodeRPCConnection.getProxy().networkMapSnapshot().stream().map(it -> it.getLegalIdentities().toString()).collect(Collectors.toList()).toString();
    }

    /****
     *
     * @param nodeName
     * @param host
     * @param username
     * @param password
     * @param rpcPort
     * @return
     */
    @RequestMapping(value = "/notaries", produces = TEXT_PLAIN_VALUE, method = RequestMethod.POST, consumes = "application/x-www-form-urlencoded")
    private String notaries(@RequestParam(value = "nodeName", required = true) String nodeName,
                            @RequestParam(value = "host", required = true) String host,
                            @RequestParam(value = "username", required = true) String username,
                            @RequestParam(value = "password", required = true) String password,
                            @RequestParam(value = "rpcPort", required = true) String rpcPort) {
        CordaOperationResult verifyResult = CordaUtil.getInstance().verifyConnectionParameters(nodeName, host, username, password, rpcPort);
        NodeRPCConnection nodeRPCConnection = null;
        if (!verifyResult.isResult()) {
            return GSonUtil.getInstance().object2Json(verifyResult);
        }
        if (!NodeRPCConnectionRepo.getInstance().getRepo().containsKey(nodeName)) {
            nodeRPCConnection = new NodeRPCConnection(host, username, password, Integer.parseInt(rpcPort.trim()));
            NodeRPCConnectionRepo.getInstance().addRPCConnection(nodeName, nodeRPCConnection);
            logger.info("NodeRPCConnection created successfully for node:" + nodeName);
        } else {
            nodeRPCConnection = NodeRPCConnectionRepo.getInstance().getRepo().get(nodeName);
            logger.info("Using existing NodeRPCConnection for node:" + nodeName);
        }
        return nodeRPCConnection.getProxy().notaryIdentities().toString();
    }

    /****
     *
     * @param nodeName
     * @param host
     * @param username
     * @param password
     * @param rpcPort
     * @return
     */
    @RequestMapping(value = "/flows", produces = TEXT_PLAIN_VALUE, method = RequestMethod.POST, consumes = "application/x-www-form-urlencoded")
    private String flows(@RequestParam(value = "nodeName", required = true) String nodeName,
                         @RequestParam(value = "host", required = true) String host,
                         @RequestParam(value = "username", required = true) String username,
                         @RequestParam(value = "password", required = true) String password,
                         @RequestParam(value = "rpcPort", required = true) String rpcPort) {
        CordaOperationResult verifyResult = CordaUtil.getInstance().verifyConnectionParameters(nodeName, host, username, password, rpcPort);
        NodeRPCConnection nodeRPCConnection = null;
        if (!verifyResult.isResult()) {
            return GSonUtil.getInstance().object2Json(verifyResult);
        }
        if (!NodeRPCConnectionRepo.getInstance().getRepo().containsKey(nodeName)) {
            nodeRPCConnection = new NodeRPCConnection(host, username, password, Integer.parseInt(rpcPort.trim()));
            NodeRPCConnectionRepo.getInstance().addRPCConnection(nodeName, nodeRPCConnection);
            logger.info("NodeRPCConnection created successfully for node:" + nodeName);
        } else {
            nodeRPCConnection = NodeRPCConnectionRepo.getInstance().getRepo().get(nodeName);
            logger.info("Using existing NodeRPCConnection for node:" + nodeName);
        }
        return nodeRPCConnection.getProxy().registeredFlows().toString();
    }

    /****
     *
     * @param nodeName
     * @param host
     * @param username
     * @param password
     * @param rpcPort
     * @return
     */
    @RequestMapping(value = "/states", produces = TEXT_PLAIN_VALUE, method = RequestMethod.POST, consumes = "application/x-www-form-urlencoded")
    private String states(@RequestParam(value = "nodeName", required = true) String nodeName,
                          @RequestParam(value = "host", required = true) String host,
                          @RequestParam(value = "username", required = true) String username,
                          @RequestParam(value = "password", required = true) String password,
                          @RequestParam(value = "rpcPort", required = true) String rpcPort) {
        CordaOperationResult verifyResult = CordaUtil.getInstance().verifyConnectionParameters(nodeName, host, username, password, rpcPort);
        NodeRPCConnection nodeRPCConnection = null;
        if (!verifyResult.isResult()) {
            return GSonUtil.getInstance().object2Json(verifyResult);
        }
        if (!NodeRPCConnectionRepo.getInstance().getRepo().containsKey(nodeName)) {
            nodeRPCConnection = new NodeRPCConnection(host, username, password, Integer.parseInt(rpcPort.trim()));
            NodeRPCConnectionRepo.getInstance().addRPCConnection(nodeName, nodeRPCConnection);
            logger.info("NodeRPCConnection created successfully for node:" + nodeName);
        } else {
            nodeRPCConnection = NodeRPCConnectionRepo.getInstance().getRepo().get(nodeName);
            logger.info("Using existing NodeRPCConnection for node:" + nodeName);
        }
        return nodeRPCConnection.getProxy().vaultQuery(ContractState.class).getStates().toString();
    }
}
