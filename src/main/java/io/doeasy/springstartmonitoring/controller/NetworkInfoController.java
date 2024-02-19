package io.doeasy.springstartmonitoring.controller;

//import com.alibaba.nacos.api.annotation.NacosInjected;
//import com.alibaba.nacos.api.exception.NacosException;
//import com.alibaba.nacos.api.naming.NamingService;
//import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;

/**
 * @author <a href="mailto:kris.wang@mexc.com">kris.wang</a>
 */
@RestController
public class NetworkInfoController {

    /**
     * 获取当前服务器ip地址，如果是k8s就是pod的地址
     * @return
     */
    @RequestMapping(value = "ipaddr")
    public String ipAddress() throws SocketException {
        List<Inet4Address> addressList = getLocalIp4AddressFromNetworkInterface();
        if(!CollectionUtils.isEmpty(addressList)) {
            return addressList.get(0).toString() + ", " + new Date();
        }
        return "Empty ip address, " + new Date();
    }

    private static List<Inet4Address> getLocalIp4AddressFromNetworkInterface() throws SocketException {
        List<Inet4Address> addressList = new ArrayList<>();
        Enumeration e = NetworkInterface.getNetworkInterfaces();

        if(null == e) {
            return addressList;
        }

        while (e.hasMoreElements()) {
            NetworkInterface n = (NetworkInterface) e.nextElement();
            if(!isValidInterface(n)) {
                continue;
            }
            Enumeration ee = n.getInetAddresses();
            if(null == ee) {
                return addressList;
            }
            while(ee.hasMoreElements()) {
                InetAddress address = (InetAddress) ee.nextElement();
                if (isValidAddress(address)) {
                    addressList.add((Inet4Address) address);
                }
            }
        }
        return addressList;
    }

    /**
     * 判断是否是IPv4，并且内网地址并过滤回环地址.
     * @param address
     * @return
     */
    private static boolean isValidAddress(InetAddress address) {
        return address instanceof Inet4Address && address.isSiteLocalAddress() && !address.isLoopbackAddress();
    }

    /**
     * 过滤回环网卡、点对点网卡、非活动网卡、虚拟网卡并要求网卡名字是eth或ens开头或en开头
     * @param n 网卡
     * @return 如果满足要求则true，否则false
     * @throws SocketException
     */
    private static boolean isValidInterface(NetworkInterface n) throws SocketException {
        return !n.isLoopback() && !n.isPointToPoint() && n.isUp() && !n.isVirtual()
                && (n.getName().startsWith("eth") || n.getName().startsWith("ens") || n.getName().startsWith("en"));
    }

}
