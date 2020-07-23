package com.ml.corda.client.controller;

import com.example.flow.ExampleFlow;
import com.example.state.IOUState;
import com.ml.corda.client.NodeRPCConnection;
import com.ml.corda.client.NodeRPCConnectionRepo;
import com.ml.corda.client.model.CordaOperationResult;
import com.ml.corda.client.util.CordaUtil;
import com.ml.corda.client.util.GSonUtil;
import net.corda.core.contracts.StateAndRef;
import net.corda.core.identity.CordaX500Name;
import net.corda.core.node.NodeInfo;
import net.corda.core.transactions.SignedTransaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * @author mengl
 */
@RestController
public class ExampleFlowController {
    private static final Logger logger = LoggerFactory.getLogger(ExampleFlowController.class);
    public ExampleFlowController() {
    }
    @RequestMapping(value = "/api/example/peers", produces = APPLICATION_JSON_VALUE, method = RequestMethod.POST, consumes = "application/x-www-form-urlencoded")
    @ResponseBody
    private String getPeers(@RequestParam(value = "nodeName", required = true) String nodeName,
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
        List<NodeInfo> nodes = nodeRPCConnection.getProxy().networkMapSnapshot();
        Iterator iterator = nodes.iterator();
        List<CordaX500Name> list = new ArrayList<>();
        while (iterator.hasNext()) {
            NodeInfo nodeInfo = (NodeInfo) iterator.next();
            System.out.println("nodeInfo:" + nodeInfo.getLegalIdentities().get(0).getName());
            logger.info("nodeInfo:" + nodeInfo.getLegalIdentities().get(0).getName());
            list.add(nodeInfo.getLegalIdentities().get(0).getName());
        }
        return GSonUtil.getInstance().object2Json(list);
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
    @RequestMapping(value = "/api/example/ious", produces = APPLICATION_JSON_VALUE, method = RequestMethod.POST, consumes = "application/x-www-form-urlencoded")
    @ResponseBody
    private String getIOUs(@RequestParam(value = "nodeName", required = true) String nodeName,
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
        List<String> ious = new ArrayList<>();
        List<StateAndRef<IOUState>> list = nodeRPCConnection.getProxy().vaultQuery(IOUState.class).getStates();
        for (StateAndRef<IOUState> state : list) {
            logger.info(state.toString());
            ious.add(state.toString());
        }
        return GSonUtil.getInstance().object2Json(ious);
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
    @RequestMapping(value = "/api/example/my-ious", produces = APPLICATION_JSON_VALUE, method = RequestMethod.POST, consumes = "application/x-www-form-urlencoded")
    @ResponseBody
    private String getMyIOUs(@RequestParam(value = "nodeName", required = true) String nodeName,
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
        List<StateAndRef<IOUState>> list = nodeRPCConnection.getProxy().vaultQuery(IOUState.class).getStates();
        List<String> ious = new ArrayList<>();
        for (StateAndRef<IOUState> state : list) {
            logger.info(state.toString());
            ious.add(state.toString());
        }
        return GSonUtil.getInstance().object2Json(ious);
    }

    /****
     *
     * @param nodeName
     * @param host
     * @param username
     * @param password
     * @param rpcPort
     * @param iouValue
     * @param partyName
     * @return
     */
    @RequestMapping(value = "/api/example/create-iou", produces = APPLICATION_JSON_VALUE, method = RequestMethod.POST, consumes = "application/x-www-form-urlencoded")
    @ResponseBody
    private String createIOU(@RequestParam(value = "nodeName", required = true) String nodeName,
                             @RequestParam(value = "host", required = true) String host,
                             @RequestParam(value = "username", required = true) String username,
                             @RequestParam(value = "password", required = true) String password,
                             @RequestParam(value = "rpcPort", required = true) String rpcPort,
                             @RequestParam(value = "iouValue", required = true) int iouValue,
                             @RequestParam(value = "partyName", required = true) String partyName) {
        CordaOperationResult verifyResult = CordaUtil.getInstance().verifyConnectionParameters(nodeName, host, username, password, rpcPort);
        NodeRPCConnection nodeRPCConnection = null;
        if (!verifyResult.isResult()) {
            return GSonUtil.getInstance().object2Json(verifyResult);
        }
        if (iouValue <= 0) {
            logger.error("iouValue should be > 0");
            return GSonUtil.getInstance().object2Json(new CordaOperationResult("iouValue should be > 0", false));
        }
        if (partyName == null || partyName.length() <= 0) {
            logger.error("partyName is null");
            return GSonUtil.getInstance().object2Json(new CordaOperationResult("partyName is null", false));
        }
        if (!NodeRPCConnectionRepo.getInstance().getRepo().containsKey(nodeName)) {
            nodeRPCConnection = new NodeRPCConnection(host, username, password, Integer.parseInt(rpcPort.trim()));
            NodeRPCConnectionRepo.getInstance().addRPCConnection(nodeName, nodeRPCConnection);
            logger.info("NodeRPCConnection created successfully for node:" + nodeName);
        } else {
            nodeRPCConnection = NodeRPCConnectionRepo.getInstance().getRepo().get(nodeName);
            logger.info("Using existing NodeRPCConnection for node:" + nodeName);
        }
        SignedTransaction signedTx = null;
        CordaX500Name partyX500Name = CordaX500Name.parse(partyName);
        try {
            signedTx = nodeRPCConnection.getProxy().startTrackedFlowDynamic(ExampleFlow.Initiator.class, iouValue, nodeRPCConnection.getProxy().wellKnownPartyFromX500Name(partyX500Name)).getReturnValue().get();
            logger.info("signedTx:" + signedTx);
            return signedTx.toString();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return GSonUtil.getInstance().object2Json(signedTx);
    }
}
