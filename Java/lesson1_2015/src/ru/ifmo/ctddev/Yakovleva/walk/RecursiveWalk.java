package ru.ifmo.ctddev.Yakovleva.walk;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.DirectoryIteratorException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.NotDirectoryException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;


public class RecursiveWalk {

	private static final int FNV_32_INIT = 0x811c9dc5;
	private static final int FNV_32_PRIME = 0x01000193;
	Path firstDirectory;
	Path firstFolder;
	Writer writer;

	public RecursiveWalk(Path dir, Writer writer) {
		this.firstDirectory = dir;
		firstFolder = Paths.get("");
		this.writer = writer;
	}

	int FNV1Hash(byte[] str, int hval) {
		for (int i = 0; i < str.length; i++) {
			hval *= FNV_32_PRIME;
			hval ^= (str[i] & 0xff);
		}
		return hval;
	}

	int getHashFromFile(File file) {
		int hval = FNV_32_INIT;
		int size = 0;
		byte[] cur = new byte[1024];
		try (InputStream reader = new FileInputStream(file)) {
			while ((size = reader.read(cur)) > 0) {
				cur = Arrays.copyOf(cur, size);
				hval = FNV1Hash(cur, hval);
			}
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return hval;
	}

	void printHash(int hash, Path dir) throws IOException {
		writer.write(String.format("%08x", hash) + " " + dir.toString() + "\n");
	}

	void recursiveWalk(Path dir) throws IOException {
		File file = dir.toFile();
		if (!file.exists()) {
			printHash(0, dir);
			System.out.println("File or directory " + dir.toString() + " doesn't exist");
			return;
		}
		if (file.isFile()) {
			int hash = getHashFromFile(file);
			printHash(hash, dir);
		} else if (file.isDirectory()) {
			try (DirectoryStream<Path> dirs = Files.newDirectoryStream(dir)) {
				for (Path curPath : dirs) {
					Path curFile = firstFolder.resolve(curPath);
					recursiveWalk(curFile);
				}
			} catch (NotDirectoryException e) {
				printHash(0, dir);
				System.out.println("Directory " + dir.toString() + " doesn't exist");
				return;
			} catch (IOException e) {
				printHash(0, dir);
				System.out.println(e.getMessage());
				return;
			} catch (DirectoryIteratorException e) {
				System.out.println(e.getMessage());
				return;
			}
		} else {
			System.out.println("So strange file");
		}
		return;
	}

	public void go() throws IOException {
		recursiveWalk(firstDirectory);
	}

	public static void main(String[] args) {
		if (args == null) {
			System.out.println("I want more arguments");
			return;
		}
		if (args.length < 2) {
			System.out.println("I want more arguments");
			return;
		}
		if (args[0] == null || args[1] == null) {
			System.out.println("null is so bad");
			return;
		}
		String inputFile = args[0];
		String outputFile = args[1];
		// String inputFile = "input.in";
		// String outputFile = "output.out";
		String directory = "";
		try (BufferedReader reader = Files.newBufferedReader(Paths.get(inputFile), StandardCharsets.UTF_8)) {
			try (Writer writer = Files.newBufferedWriter(Paths.get(outputFile), StandardCharsets.UTF_8)) { 
				while ((directory = reader.readLine()) != null) {
					new RecursiveWalk(Paths.get(directory), writer).go();
				}
			} catch (IOException e) {
				System.out.println("Writer exception");
			}
		} catch (FileNotFoundException e) {
			System.out.println(e.toString());
		} catch (IOException e) {
			System.out.println("Reader exception");
		}
	}
}

	
