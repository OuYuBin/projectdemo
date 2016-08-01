/*package uploadfile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import cn.jpush.api.common.connection.IHttpClient.RequestMethod;

 *//**
 * Class Name: FileController Description: TODO 文件上传
 * 
 * @author shiyahuang
 *
 */
/*
 * 
 * @Controller public class FileController { private Logger log =
 * Logger.getLogger(FileController.class.getName()); private static final
 * Configuration config = new Configuration( "quanquan.properties");
 * 
 * @Autowired private FileRepository fileRepository;
 * 
 * @Autowired private FileService fileService;
 * 
 * @RequestMapping("/uploads")
 * 
 * @ResponseBody public GeneralResult uploads(
 * 
 * @RequestParam("file") List<MultipartFile> files, HttpServletRequest request)
 * { List<String> returnStrs = new ArrayList<>(); for (int i = 0; i <
 * files.size(); i++) { FileNode fileNode = new FileNode(); String fileName =
 * files.get(i).getOriginalFilename(); System.out.println("fileName---------->"
 * + fileName);
 * 
 * if (!files.get(i).isEmpty()) { int pre = (int) System.currentTimeMillis();
 * try { String filePath = config.getValue("picFileSavePath"); //
 * 拿到输出流，同时重命名上传的文件 // FileOutputStream os = new FileOutputStream(filePath + new
 * // Date().getTime() + Filename);
 * 
 * File dir = new File(filePath + "/" + SysUtil.getStringDateShort()); String
 * prex = fileName.substring(fileName.lastIndexOf("."), fileName.length());
 * fileName = this.getFileNameToAvoidRepeat(fileName); if (!dir.exists()) {
 * boolean checkdir = dir.mkdirs(); if (!checkdir) { return null; } } String
 * fileId = GloalLabels.IMGSEQ + SysUtil.getUniqueCode() +
 * SysUtil.getStringCode(12); fileNode.setFileId(fileId);
 * fileNode.setFileType(prex); fileNode.setFileName(fileName);
 * fileNode.setFilePath(SysUtil.getStringDateShort()); fileNode.setUpdateTime(""
 * + System.currentTimeMillis()); // fileNode.setFileState(fileState);
 * log.info("file up Path:" + dir.getPath()); log.info("file up fileName:" +
 * fileName); log.info("file up prex:" + prex); File img = new
 * File(dir.getPath() + "/" + fileName);// fileName带有后缀格式 // 拿到上传文件的输入流 //
 * FileInputStream in = (FileInputStream) // files[i].getInputStream(); try {
 * files.get(i).transferTo(img); fileNode.setFileState(1);
 * fileRepository.save(fileNode); returnStrs.add(fileId); } catch
 * (IllegalStateException e) { e.printStackTrace(); returnStrs.add("error");
 * continue; } catch (IOException e) { e.printStackTrace();
 * returnStrs.add("error"); continue; } // 以写字节的方式写文件 // int b = 0; // while ((b
 * = in.read()) != -1) { // os.write(b); // } // os.flush(); // os.close(); //
 * in.close(); // int finaltime = (int) System.currentTimeMillis(); //
 * System.out.println(finaltime - pre);
 * 
 * } catch (Exception e) { e.printStackTrace(); System.out.println("上传出错");
 * continue; } } } return new GeneralResult(true, ErrorCode.SUCCESS.getCode(),
 * "获取成功", returnStrs); }
 * 
 * @RequestMapping("/upload")
 * 
 * @ResponseBody public GeneralResult upload(
 * 
 * @RequestParam(value = "file", required = false) CommonsMultipartFile files,
 * HttpServletRequest request) { List<String> returnStrs = new ArrayList<>();
 * FileNode fileNode = new FileNode(); if (!files.isEmpty()) { String fileName =
 * files.getOriginalFilename(); System.out.println("fileName---------->" +
 * fileName); int pre = (int) System.currentTimeMillis(); try { String filePath
 * = config.getValue("picFileSavePath"); // 拿到输出流，同时重命名上传的文件 // FileOutputStream
 * os = new FileOutputStream(filePath + new // Date().getTime() + Filename);
 * 
 * File dir = new File(filePath + "/" + SysUtil.getStringDateShort()); String
 * prex = fileName.substring(fileName.lastIndexOf("."), fileName.length());
 * fileName = this.getFileNameToAvoidRepeat(fileName); if (!dir.exists()) {
 * boolean checkdir = dir.mkdirs(); if (!checkdir) { return null; } } String
 * fileId = GloalLabels.IMGSEQ + SysUtil.getUniqueCode();
 * fileNode.setFileId(fileId); fileNode.setFileType(prex);
 * fileNode.setFileName(fileName);
 * fileNode.setFilePath(SysUtil.getStringDateShort()); fileNode.setUpdateTime(""
 * + System.currentTimeMillis()); // fileNode.setFileState(fileState);
 * log.info("file up Path:" + dir.getPath()); log.info("file up fileName:" +
 * fileName); log.info("file up prex:" + prex); File img = new
 * File(dir.getPath() + "/" + fileName + prex); // 拿到上传文件的输入流 // FileInputStream
 * in = (FileInputStream) // files[i].getInputStream(); try {
 * files.transferTo(img); fileNode.setFileState(1);
 * fileRepository.save(fileNode); returnStrs.add(fileId); } catch
 * (IllegalStateException e) { e.printStackTrace(); returnStrs.add("error"); }
 * catch (IOException e) { e.printStackTrace(); returnStrs.add("error"); }
 * 
 * } catch (Exception e) { e.printStackTrace(); System.out.println("上传出错"); } }
 * else { log.info("文件不存在"); } return new GeneralResult(true,
 * ErrorCode.SUCCESS.getCode(), "获取成功", returnStrs);
 * 
 * }
 * 
 * @RequestMapping(value = "/getFilesUrl", method = RequestMethod.POST)
 * 
 * @ResponseBody public GeneralResult getFilesUrl(@RequestBody List<String>
 * FileIds, HttpServletRequest request) { List<String> returnStrs = new
 * ArrayList<>(); GeneralResult<List<String>> result = null; List<String>
 * returnfileUrls = null; if (FileIds != null && FileIds.size() > 0) {
 * returnfileUrls = fileService.getFileUrls(FileIds); if (returnfileUrls != null
 * && returnfileUrls.size() > 0) { result = new GeneralResult(true,
 * ErrorCode.SUCCESS.getCode(), "请求成功", returnfileUrls); } else { result = new
 * GeneralResult(true, ErrorCode.SUCCESS.getCode(), "请求成功", null); } return
 * result;
 * 
 * } return new GeneralResult(true, ErrorCode.FAILED.getCode(), "信息有误，请核对",
 * null); }
 * 
 * @RequestMapping(value = "uploadFile", method = RequestMethod.POST)
 * 
 * @ResponseBody public Object uploadFile(HttpServletRequest request,
 * HttpServletResponse response, HttpSession session) throws IOException {
 * String filepath = ""; String uploadFileSavePath =
 * config.getValue("uploadFileSavePath"); System.out.println("uploadFile Path:"
 * + uploadFileSavePath); File file = new File(uploadFileSavePath); if
 * (!file.exists()) {// 文件夹不存在
 * 
 * if (file.mkdirs() == false) {// 创建文件夹失败 return "-1"; } } String
 * namefileString = (String) session.getAttribute("file");
 * System.out.println("file取得时" + namefileString); String paths =
 * fileService.fileUp(request, response, uploadFileSavePath, "file"); if (paths
 * == null) { return "-1"; } else {// 上传成功
 * 
 * if (paths != null) { System.out.println("保存路径:" + paths); filepath = paths;
 * 
 * } }
 * 
 * return 1; }
 * 
 * private String getFileNameToAvoidRepeat(String fileName) { if
 * (fileName.indexOf('.') == -1) {// 无扩展名 fileName = fileName + "(" +
 * SysUtil.getUniqueCode() + ")"; } else {// 有扩展名 fileName =
 * fileName.substring(0, fileName.lastIndexOf('.') - 1) + "(" +
 * SysUtil.getUniqueCode() + ")" +
 * fileName.substring(fileName.lastIndexOf('.')); } return fileName; } }
 */