package com.ml.corda.client.util;

import com.ml.corda.client.model.CordaOperationResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CordaUtil {
    private static Logger LOGGER = LoggerFactory.getLogger(CordaUtil.class);
    private static CordaUtil instance = new CordaUtil();
    private CordaUtil() {
    }

    public static CordaUtil getInstance() {
        return instance;
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
    public CordaOperationResult verifyConnectionParameters(String nodeName, String host, String username, String password, String rpcPort) {
        if (nodeName == null || nodeName.trim().isEmpty()) {
            LOGGER.error("nodeName is null");
            return new CordaOperationResult("nodeName is null", false);
        }
        if (host == null || host.trim().isEmpty()) {
            LOGGER.error("host is null");
            return new CordaOperationResult("host is null", false);
        }
        if (username == null || username.trim().isEmpty()) {
            LOGGER.error("username is null");
            return new CordaOperationResult("username is null", false);
        }
        if (password == null || password.trim().isEmpty()) {
            LOGGER.error("password is null");
            return new CordaOperationResult("password is null", false);
        }
        if (rpcPort == null || rpcPort.trim().isEmpty()) {
            LOGGER.error("rpcPort is null");
            return new CordaOperationResult("rpcPort is null", false);
        }
        if (!CommonUtil.getInstance().isInteger(rpcPort.trim())) {
            LOGGER.error(rpcPort + " is not a valid number");
            return new CordaOperationResult(rpcPort + " is not a valid number", false);
        }
        return new CordaOperationResult("varifyConnectionParameters successfully", true);
    }
}
