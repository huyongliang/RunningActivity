package firework.hyl.running.common.util;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Random;

/**
 * @author HuYongliang
 * @version 1.0
 */
public class FileUtils {

	private String ip;

	public FileUtils() {
		super();
	}

	public FileUtils(String ip) {
		super();
		this.ip = ip;
	}

	/**
	 * 根据指定的datePattern返回时间戳
	 * 
	 * @param datePattern
	 *            SimpleDateFormat使用的正则表达式
	 * @return 时间戳
	 */
	public static String getTimeStamp(String datePattern) {
		String temp = null;
		SimpleDateFormat sdf = new SimpleDateFormat(datePattern);
		temp = sdf.format(new java.util.Date());
		return temp;
	}

	/**
	 * @see #getTimeStamp(String)
	 * @return 时间戳:getTimeStamp("yyyyMMddHHmmssSSS");
	 */
	public static String getTimeStamp() {
		return getTimeStamp("yyyyMMddHHmmssSSS");
	}

	/**
	 * 返回IP地址+时间戳+randomCount位随机数组成的字符串
	 * 
	 * @param randomCount
	 *            随机数的位数 若randomCount<0，则randomCount=3 若randomCount==0，则不生成随机数部分
	 * @return IP地址+时间戳+randomCount位随机数组成的字符串
	 */
	public String getIpTimestampRandom(int randomCount) {
		StringBuilder sb = new StringBuilder();
		if (this.ip != null) {
			if (this.ip.contains(".")) {
				String str[] = this.ip.split("\\.");
				for (String s : str) {
					sb.append(fillZero(s, 3));
				}
			}
		}
		sb.append(getTimeStamp());
		Random r = new Random();
		int count = randomCount >= 0 ? randomCount : 3;

		for (int i = 0; i < count; i++)
			sb.append(r.nextInt(10));
		return sb.toString();
	}

	public static String fillZero(String str, int len) {
		StringBuilder sb = new StringBuilder();
		sb.append(str);
		while (sb.length() < len)
			sb.insert(0, '0');
		return sb.toString();
	}

	/**
	 * 删除文件夹
	 * 
	 * @param folderPath
	 *            指定的文件绝对路径
	 */
	public static void deleteFolder(String folderPath) {
		deleteAllFiles(folderPath);
		String filePath = folderPath;
		filePath = filePath.toString();
		java.io.File myFilePath = new java.io.File(filePath);
		myFilePath.delete();
	}

	/**
	 * 
	 * 删除指定文件夹下所有文件及文件夹 <br>
	 * 
	 * @param folderPath
	 *            指定的文件夹的完整绝对路径
	 * @return false---出现异常或<code>folderPath</code>下没有文件(文件夹)<br>
	 *         true---删除成功
	 */
	public static boolean deleteAllFiles(String folderPath) {

		boolean flag = false;
		java.io.File file = new File(folderPath);
		if (!file.exists()) {
			return false;
		}
		if (!file.isDirectory()) {
			return false;
		}

		String[] tempList = file.list();
		java.io.File tempFile = null;
		for (int i = 0; i < tempList.length; i++) {
			if (folderPath.endsWith(java.io.File.separator))
				tempFile = new File(folderPath + tempList[i]);
			else
				tempFile = new File(folderPath + File.separator + tempList[i]);

			if (tempFile.isFile())
				tempFile.delete();

			if (tempFile.isDirectory()) {
				deleteAllFiles(folderPath + File.separator + tempList[i]);
				deleteFolder(folderPath + File.separator + tempList[i]);
				flag = true;
			}

		}
		return flag;
	}

	/**
	 * 获取扩展名 <br>
	 * 例子:
	 * 
	 * <pre>
	 * FileHelper.getExtName(&quot;data.txt&quot;, false);// txt
	 * FileHelper.getExtName(&quot;data.txt&quot;, true);// .txt
	 * FileHelper.getExtName(&quot;data&quot;, true);// null
	 * FileHelper.getExtName(&quot;&quot;, true);// null
	 * FileHelper.getExtName(null, true);// null
	 * </pre>
	 * 
	 * 
	 * @param fileName
	 *            文件名
	 * @param withDot
	 *            是否返回扩展名前的点
	 * @return 文件的扩展名<br>
	 *         返回<code>null<code> 如果fileName为null、""或者fileName不含点(.)
	 */
	public static String getExtName(String fileName, boolean withDot) {
		if (fileName == null || "".equals(fileName))
			return null;
		if (fileName.indexOf(".") == -1)
			return null;
		if (withDot)
			return fileName.substring(fileName.lastIndexOf("."));
		else
			return fileName.substring(fileName.lastIndexOf(".") + 1);
	}

	/**
	 * 根据指定的dirName建立文件夹
	 * 
	 * @param dirName
	 *            文件夹路径
	 */
	public static void makeDir(String dirName) {
		File file = new File(dirName);
		if (!file.exists() && !file.isDirectory()) {
			file.mkdir();
		}
	}
}
