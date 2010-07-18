package com.nearinfinity.blur.utils;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.ZooDefs.Ids;

public class ZkUtils {

	public static void mkNodes(String path, ZooKeeper zk) throws KeeperException, InterruptedException {
		String[] split = path.split("/");
		for (int i = 0; i < split.length; i++) {
			StringBuilder builder = new StringBuilder();
			for (int j = 0; j <= i; j++) {
				if (!split[j].isEmpty()) {
					builder.append('/');
					builder.append(split[j]);
				}
			}
			String pathToCheck = builder.toString();
			if (pathToCheck.isEmpty()) {
				continue;
			}
			if (zk.exists(pathToCheck, false) == null) {
				zk.create(pathToCheck, null, Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
			}
		}
	}
}