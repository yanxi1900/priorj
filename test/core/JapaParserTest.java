package core;

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
import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import japa.parser.ASTHelper;
import japa.parser.JavaParser;
import japa.parser.ParseException;
import japa.parser.ast.CompilationUnit;
import japa.parser.ast.PackageDeclaration;
import japa.parser.ast.body.BodyDeclaration;
import japa.parser.ast.body.ClassOrInterfaceDeclaration;
import japa.parser.ast.body.MethodDeclaration;
import japa.parser.ast.body.ModifierSet;
import japa.parser.ast.body.TypeDeclaration;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import util.Settings;

public class JapaParserTest {

	CompilationUnit cu;

	String path = "";

	@Before
	public void setUp() throws Exception {
		cu = new CompilationUnit();

		path = Settings.INSTRUMENT_BLOCKS + Settings.SEPARATOR + "ForIf.java";

	}

	@After
	public void tearDown() throws Exception {
		cu = null;
	}

	// this test check an file
	@Test
	public void testInit() {
		File f = new File(path);
		assertTrue(f.exists());
	}

	// this test create a package declaration
	@Test
	public void testCompilationUnitsetPackageDeclaration() {

		CompilationUnit cu = new CompilationUnit();

		cu.setPackage(new PackageDeclaration(ASTHelper
				.createNameExpr("java.parser.test")));

		assertEquals("java.parser.test", cu.getPackage().getName().toString());
	}

	// this test create a class with name ForIfClass
	@Test
	public void testCompilationUnitInterfaceOrClassDeclaration() {

		ClassOrInterfaceDeclaration type = new ClassOrInterfaceDeclaration(
				ModifierSet.PUBLIC, false, "ForIfClass");
		ASTHelper.addTypeDeclaration(cu, type);

		assertEquals("ForIfClass", cu.getTypes().get(0).getName().toString());
	}

	// this test create a method to a class
	// this test create a class with name ForIfClass
	@Test
	public void testCompilationUnitMethodDeclaration() {

		ClassOrInterfaceDeclaration type = new ClassOrInterfaceDeclaration(
				ModifierSet.PUBLIC, false, "ForIfClass");
		ASTHelper.addTypeDeclaration(cu, type);

		// create a method
		MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC,
				ASTHelper.VOID_TYPE, "main");

		method.setModifiers(ModifierSet.addModifier(method.getModifiers(),
				ModifierSet.STATIC));

		ASTHelper.addMember(type, method);

		// get the list of members.
		List<BodyDeclaration> members = type.getMembers();

		List<MethodDeclaration> methods = new ArrayList<MethodDeclaration>();

		for (BodyDeclaration m : members) {
			if (m instanceof MethodDeclaration) {
				methods.add((MethodDeclaration) m);
			}
		}

		MethodDeclaration methodM = methods.get(0);

		assertEquals("main", methodM.getName().toString());

		assertTrue(methods.size() == 1);

	}

}
