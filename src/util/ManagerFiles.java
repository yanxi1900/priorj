package util;

/*
* PriorJ: JUnit Test Case Prioritization.
* 
* Copyright (C) 2012-2013  Samuel T. C. Santos
*
* This program is free software: you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation, either version 3 of the License, or
* (at your option) any later version.
*
* This program is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
* GNU General Public License for more details.
* 
* You should have received a copy of the GNU General Public License
* along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import dao.XStreamRead;

/**
 * ManagerFile is an class which manager files, move files, copy files, save
 * files, delete files and other operations.
 * 
 * @author Samuel T. C. Santos
 * @version 1.0
 * 
 */
public class ManagerFiles {

	/**
	 * create a directory
	 * 
	 * @param directoryName
	 *            the directory name.
	 */
	public static void createDirectory(String directoryName) {

		File file = new File(directoryName);
		file.mkdir();
	}

	/**
	 * Verify if the directory or file informed exist.
	 * 
	 * @param filename or filename
	 *            the file or directory name.
	 * 
	 * @return true or false
	 */
	public static boolean existFileOrDirectory(String filename) {

		File f = new File(filename);

		return f.exists();
	}

	/**
	 * Delete all files include the directory root.
	 * 
	 * @param path
	 *            an path.
	 */
	public static void deleteAll(String path) {
		deleteAll(new File(path));
	}

	/**
	 * Delete all files include the directory root.
	 * 
	 * @param path
	 *            an path.
	 */
	public static void deleteAll(File file) {
		if (file.isFile()) {
			file.delete();
		} else {
			File[] files = file.listFiles();
			for (File f : files)
				deleteAll(f);
			file.delete();
		}
	}

	/**
	 * Delete all files only inside the directory.
	 * 
	 * @param file
	 *            an file type directory.
	 */
	public static void deleteAllOnlyInside(File file) {
		if (file.isFile()) {
			file.delete();
		} else {
			File[] files = file.listFiles();
			for (File f : files)
				deleteAll(f);
		}
	}
	
	public static void deleteAllOnlyInside(String filename) {
		deleteAllOnlyInside(new File(filename));
	}
	
	

	/**
	 * Return a list with the content of the every files.
	 * 
	 * @param pathDirectory
	 *            the directory path, example \home\doc\project\suites 
	 *            Don't ends with separator.
	 * 
	 * @return an list of contents the files.
	 * 
	 * @throws Exception
	 *             error when try access files.
	 */
	public static List<String> openAllFiles(String pathDirectory) throws Exception {

		File f = new File(pathDirectory);
		List<String> contents = new ArrayList<String>();

		if (f.exists()) {

			File[] files = f.listFiles();

			if (files.length > 0) {
				String content = "";
				for (int i = 0; i < files.length; i++) {
					content = ReadFile.read(pathDirectory + Settings.SEPARATOR + files[i].getName());
					contents.add(content);
				}

			}
		}

		return contents;
	}

	/**
	 * List all names of the files into the informed directory.
	 * 
	 * @param pathDirectory
	 *            the path of an directory.
	 * 
	 * @return an list of names files.
	 * 
	 * @throws Exception
	 */
	public static List<String> listFilesNames(String pathDirectory) throws Exception {

		File f = new File(pathDirectory);
		List<String> filenames = new ArrayList<String>();

		if (f.exists()) {

			File[] files = f.listFiles();

			if (files.length > 0) {
				String content = "";
				for (int i = 0; i < files.length; i++) {
					filenames.add(files[i].getName());
				}

			}
		}

		return filenames;
	}

	/**
	 * Copy all file from directory to another.
	 * 
	 * @param pathOrigin
	 *            the origin directory.
	 * @param pathDest
	 *            the destination directory.
	 */
	public static void copyFileAll(String pathOrigin, String pathDest) {

		File orig = new File(pathOrigin);
		File dest = new File(pathDest);

		try {
			CopyFile.copyAll(orig, dest, true);
		} catch (IOException ex) {
			System.out.println(ex.getMessage());
		}
	}

	/**
	 * open an file or more files into the directory passed.
	 * 
	 * @param local
	 *            the directory.
	 * @param filename
	 *            the name of the file.
	 * 
	 * @return 
	 * 		A list of strings.
	 */
	public static List<String> openFile(String local, String filename) {
		try {
			XStreamRead reader = new XStreamRead(local + Settings.SEPARATOR + filename);
			List<String> methods = (List<String>) reader.read();

			return methods;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}

	}

	/**
	 * Get an pseudo name to techniques.
	 * 
	 * @param techniqueId
	 *          the id of the technique.
	 * 
	 * @return 
	 * 			A pseudo name.
	 */
	public static String alias(int techniqueId) {

		String techniqueNames = "";

		switch (techniqueId) {
		case 1:
			techniqueNames = "TSC";
			break;
		case 2:
			techniqueNames = "TMC";
			break;
		case 3:
			techniqueNames = "ASC";
			break;
		case 4:
			techniqueNames = "AMC";
			break;
		case 5:
			techniqueNames = "CBT";
			break;
		case 6:
			techniqueNames = "Random";
			break;
		case 7:
			techniqueNames = "CBA";
			break;
		case 8:
			techniqueNames = "RBA";
			break;

		default:
			techniqueNames = "Unknow";
			break;
		}

		return techniqueNames;
	}

	/**
	 * This method remove a file.
	 * 
	 * @param path
	 * 		path to file
	 */
	public static void deleteFile(String path) {
		// TODO Auto-generated method stub
		File f = new File(path);
		
		if (f.exists())
			f.delete();
	}

}

