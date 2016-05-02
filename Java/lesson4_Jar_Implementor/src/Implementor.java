import info.kgeorgiy.java.advanced.implementor.ImplerException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Writer;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.jar.Attributes;
import java.util.jar.JarOutputStream;
import java.util.jar.Manifest;
import java.util.zip.ZipEntry;

import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

public class Implementor implements
info.kgeorgiy.java.advanced.implementor.JarImpler {
	/**
	 * @version 2.0
	 */
	
	/** Output stream */
	static Writer writer;
	
	/** class which need to compile */
	static Class<?> myClass;
	
	/** name of main class */
	static String className;
	
	/**
	 * Constructor from created class and folder, which should create java file
	 * @param arg0 is a class, which should be created
	 * @param arg1 is folder, which should create class
	 * @throws ImplerException if can't extend final class or can't create folder
	 */
	@Override
	public void implement(Class<?> arg0, File arg1) throws ImplerException {
		try {
			if (Modifier.isFinal(arg0.getModifiers())) {
				throw new ImplerException("can't extend final class");
			}
			String pack = "";
			if (arg0.getPackage() != null) {
				pack = arg0.getPackage().getName().replace(".", File.separator);
			}
			File folder = new File(arg1, pack);
			if (!folder.exists() && !folder.mkdirs()) {
				throw new ImplerException("folder hasn't been created");
			}
			myClass = arg0;
			className = myClass.getSimpleName() + "Impl";
			File file = new File(folder, className + ".java");
			writer = Files.newBufferedWriter(Paths.get(file.getAbsolutePath()));
			createJavaCode();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Function create manifest including main class
	 * @param mainClass is main class of jar file
	 * @return ready {@link java.util.jar.Manifest} manifest
	 */
	private Manifest getManifest(String mainClass) {
		Manifest manifest = new Manifest();
		manifest.getMainAttributes().put(Attributes.Name.MANIFEST_VERSION, "1.0");
		manifest.getMainAttributes().put(Attributes.Name.MAIN_CLASS, mainClass);
		return manifest;
	}

	/**
	 * Compiler java file and create .class file in current directory
	 * @param file is file which need to compile
	 * @throws IOException if can't create fileManager 
	 */
	private void compileJavaFile(File file) throws IOException {
		final JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
		StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, null);
		Iterable<? extends JavaFileObject> compilationUnits = fileManager
				.getJavaFileObjectsFromStrings(Arrays.asList(file.getAbsolutePath()));
		JavaCompiler.CompilationTask task = compiler.getTask(null, fileManager, null, null,
				null, compilationUnits);
		task.call();
		fileManager.close();
	}

	/** Write to file stream in jar archive
	 * @param file is file which need to write
	 * @param jarFile is jar archive which contains file to write
	 */
	private void writeToFile(File file, JarOutputStream jarFile) {
		try (FileInputStream cfile = new FileInputStream(new File(file.getParentFile(), className + ".class"))){
			byte[] byteBuffer = new byte[1024];
			int bytesRead = -1;
			while ((bytesRead = cfile.read(byteBuffer)) != -1) {
				jarFile.write(byteBuffer, 0, bytesRead);
			}
			jarFile.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create jar archive in current directory and add to archive .class file 
	 * @param myClass is class which need to create, compile and add to jar archive
	 * @param fileJar is current directory
	 * @throws ImplerException if can't extend final class
	 */
	@Override
	public void implementJar(Class<?> myClass, File fileJar) throws ImplerException {
		if (Modifier.isFinal(myClass.getModifiers())) {
			throw new ImplerException("can't extend final class");
		}
		String pack = "";
		if (myClass.getPackage() != null) {
			pack = myClass.getPackage().getName().replace(".", "/");
		}
		className = myClass.getSimpleName() + "Impl";
		String jarPath = fileJar.getAbsolutePath();
		//			File fileJar = new File(arg1.getParentFile().getParentFile().getAbsolutePath() + arg1.getName());

		try (JarOutputStream jarFile = new JarOutputStream(Files.newOutputStream(Paths.get(jarPath)), 
				getManifest(myClass.getPackage().getName() + "." + className))) {
			File file = new File(fileJar.getParentFile().getAbsolutePath(), className + ".java");
			writer = Files.newBufferedWriter(Paths.get(file.getAbsolutePath()));
			createJavaCode();
			writer.close();
			compileJavaFile(file);
			ZipEntry impl = new ZipEntry(pack + "/" + className + ".class");
			jarFile.putNextEntry(impl);
			writeToFile(file, jarFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * return modifiers of current method or constructor
	 * @param m is a method or constructor
	 * @return {@link java.lang.String} of modifiers
	 */
	private static String getModifiers(int m) {
		return Modifier.toString(m & ~Modifier.TRANSIENT & ~Modifier.ABSTRACT) + " ";
	}

	/**
	 * Return type of method
	 * @param myClass is a {@link java.lang.Class method}
	 * @return {@link java.lang.String} of primitive or Class type
	 */
	private static String getType(Class<?> myClass) {
		String type = "";
		while (myClass.isArray()) {
			type += "[]";
			myClass = myClass.getComponentType();
		}
		return myClass.getTypeName() + type;
	}

	/**
	 * Return parameters of method
	 * @param params is a {@link java.lang.Class method}
	 * @param varArg is boolean value: true if parameters contain varArg, or false otherwise
	 * @return {@link java.lang.String} of parameters with their types separated by comma
	 */
	private static String getParameters(Class<?>[] params, boolean varArg) {
		String s = "";
		for (int i = 0; i < params.length; i++) {
			if (i > 0)
				s += ", ";
			if (i == params.length - 1 && varArg) {
				s += getType(params[i].getComponentType()) + "... param" + i;
				break;
			}
			s += getType(params[i]) + " param" + i;
		}
		return s;
	}
	/**
	 * Return lists of method's exceptions
	 * @param excepts is a {@link java.lang.Class method}
	 * @return {@link java.lang.String} of exceptions separated by comma
	 * if method have exceptions, return value begin with "throws "
	 */
	private static String printExcepts(Class<?>[] excepts) {
		if (excepts.length == 0) {
			return "";
		}
		String s = " throws ";
		for (int i = 0; i < excepts.length; i++) {
			if (i > 0) {
				s += ", ";
			}
			s += excepts[i].getName();
		}
		return s;
	}
	/**
	 * Return default value of method
	 * @param met is a {@link java.lang.Class method}
	 * @return {@link java.lang.String} of default value of return type
	 * Void.TYPE is nothing
	 * Boolean.TYPE is false
	 * Other primitive types is 0
	 * Class type is null
	 */
	private static String valueOfMethod(Method met) {
		String s = "return ";
		// Boolean.TYPE, Character.TYPE, Byte.TYPE, Short.TYPE, Integer.TYPE,
		// Long.TYPE, Float.TYPE, Double.TYPE, Void.TYPE
		if (met.getReturnType().isPrimitive()) {
			if (met.getReturnType() == Boolean.TYPE) {
				s += "false";
			} else if (met.getReturnType().equals(Void.TYPE)) {
				return "";
			} else {
				s += "0";
			}
		} else {
			s += "null";
		}
		return s + ";";
	}
	/**
	 * Write method with annotations, modifiers, name, parameters, return default value
	 * @param m is a {@link java.lang.Class method}
	 * @throws java.io.IOException IOexception
	 */
	private static void printMethod(Method m) throws IOException {
		Annotation[] annotations = m.getDeclaredAnnotations();
		writer.write("\t");
		for (Annotation a : annotations)
			writer.write("@" + a.annotationType().getSimpleName() + " ");
		writer.write("\n");
		writer.write("\t" + getModifiers(m.getModifiers())
				+ getType(m.getReturnType()) + " " + m.getName() + "(");
		writer.write(getParameters(m.getParameterTypes(), m.isVarArgs()));
		writer.write(")");
		writer.write(printExcepts(m.getExceptionTypes()));
		writer.write(" {\n\t\t" + valueOfMethod(m) + "\n\t}\n");
	}
	/**
	 * Return String, which contains method's name and parameters
	 * @param m is a {@link java.lang.Class method}
	 * @return {@link java.lang.String} of method's name and parameters
	 */
	private static String identMethod(Method m) {
		return m.getName() + "("
				+ getParameters(m.getParameterTypes(), m.isVarArgs()) + ")";
	}

	/**
	 * Keep all method's in parents include interfaces and classes and write those, which hasn't implemented yet
	 * @param c is a {@link java.lang.Class method}
	 * @param methods is  {@link java.util.Set} save all watching methods
	 * @throws java.io.IOException IOexception
	 */
	static public void getMethods(Class<?> c, Set<String> methods)
			throws IOException {
		if (c == null) {
			return;
		}
		Method[] meth = c.getDeclaredMethods();
		for (Method m : meth) {
			if (!methods.contains(identMethod(m))) {
				if (Modifier.isAbstract(m.getModifiers())) {
					printMethod(m);
				}
				methods.add(identMethod(m));
			}
		}
		getMethods(c.getSuperclass(), methods);
		for (Class<?> cc : c.getInterfaces()) {
			getMethods(cc, methods);
		}
	}

	/**
	 * This main function creates all output java file
	 * @throws java.io.IOException IOexception
	 * @throws ImplerException if it is impossible to create constructor
	 */
	public static void createJavaCode() throws IOException, ImplerException {
		Package pack = myClass.getPackage();
		if (pack != null) {
			writer.write("package " + pack.getName() + ";\n");
		}
		writer.write("public class " + className + " ");
		if (myClass.isInterface()) {
			writer.write("implements " + myClass.getSimpleName());
		} else {
			writer.write("extends " + myClass.getSimpleName());
		}
		writer.write(" {\n");
		try {
			if (Modifier.isPrivate(myClass.getDeclaredConstructor().getModifiers())) {
				throw new NoSuchMethodException();
			}
		} catch (NoSuchMethodException e) {
			boolean haveC = false;
			for (Constructor<?> c : myClass.getDeclaredConstructors())
				if (!Modifier.isPrivate(c.getModifiers())) {
					writer.write("\t" + getModifiers(c.getModifiers())
							+ className + " (");
					writer.write(getParameters(c.getParameterTypes(),
							c.isVarArgs()));
					writer.write(")");
					writer.write(printExcepts(c.getExceptionTypes()));
					writer.write("{\n\t\tsuper(");
					for (int i = 0; i < c.getParameterCount(); i++) {
						writer.write("param" + i + ((i < c.getParameterCount() - 1)?", ":");"));
					}
					writer.write("\n\t}\n");
					haveC = true;
					break;
				}
			if (!haveC && !myClass.isInterface()) {
				throw new ImplerException("can't create constructor");
			}
		}
		getMethods(myClass, new HashSet<>());
		writer.write("}\n");
	}

	public static void main(String[] args) throws ImplerException {
		try {
			if (args[0].equals("-jar")) {
				myClass = Class.forName(args[1]);
			} else {
				myClass = Class.forName(args[0]);
			}
			className = myClass.getSimpleName() + "Impl";
			writer = Files.newBufferedWriter(Paths.get(className + ".java"));
			createJavaCode();
			writer.close();
			if (args[0].equals("-jar")) {
				Manifest manifest = new Manifest();
				manifest.getMainAttributes().put(Attributes.Name.MANIFEST_VERSION, "1.0");
				manifest.getMainAttributes().put(Attributes.Name.MAIN_CLASS, className);
				JarOutputStream jarFile = new JarOutputStream(Files.newOutputStream(Paths.get(args[2])), manifest);
				final JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
				StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, null);
				Iterable<? extends JavaFileObject> compilationUnits = fileManager
						.getJavaFileObjectsFromStrings(Arrays.asList(className + ".java"));
				JavaCompiler.CompilationTask task = compiler.getTask(null, fileManager, null, null,
						null, compilationUnits);
				task.call();
				fileManager.close();
				ZipEntry impl = new ZipEntry(className + ".class");
				jarFile.putNextEntry(impl);
				try (FileInputStream file = new FileInputStream(className + ".class");){
					byte[] byteBuffer = new byte[1024];
					int bytesRead = -1;
					while ((bytesRead = file.read(byteBuffer)) != -1) {
						jarFile.write(byteBuffer, 0, bytesRead);
					}
					jarFile.flush();
				} 
				jarFile.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
	}
}