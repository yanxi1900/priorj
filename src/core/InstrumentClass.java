package core;

import japa.parser.ASTHelper;
import japa.parser.JavaParser;
import japa.parser.ast.CompilationUnit;
import japa.parser.ast.body.BodyDeclaration;
import japa.parser.ast.body.ClassOrInterfaceDeclaration;
import japa.parser.ast.body.ConstructorDeclaration;
import japa.parser.ast.body.FieldDeclaration;
import japa.parser.ast.body.MethodDeclaration;
import japa.parser.ast.body.ModifierSet;
import japa.parser.ast.body.TypeDeclaration;
import japa.parser.ast.expr.AssignExpr;
import japa.parser.ast.expr.FieldAccessExpr;
import japa.parser.ast.expr.NameExpr;
import japa.parser.ast.expr.ThisExpr;
import japa.parser.ast.stmt.BlockStmt;
import japa.parser.ast.stmt.ExpressionStmt;
import japa.parser.ast.stmt.ForStmt;
import japa.parser.ast.stmt.ForeachStmt;
import japa.parser.ast.stmt.IfStmt;
import japa.parser.ast.stmt.Statement;
import japa.parser.ast.stmt.SwitchEntryStmt;
import japa.parser.ast.stmt.SwitchStmt;
import japa.parser.ast.stmt.TryStmt;
import japa.parser.ast.stmt.WhileStmt;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class InstrumentClass {

    private String nameField = "watchPriorJApp";

    private String pathFile;

    private String nameFile;

    public InstrumentClass(String pathFile, String nameFile) {
        this.pathFile = pathFile;
        this.nameFile = nameFile;
    }

    public String getPathFile() {
        
        return pathFile;
    }

    public void setPathFile(String pathFile) {
        
        this.pathFile = pathFile;
    }

    public String getNameFile() {
        
        return nameFile;
    }

    public void setNameFile(String nameFile) {
        
        this.nameFile = nameFile;
    }

    public void setNameField(String nameField) {
        
        nameField = nameField;
    }

    public String getNameField() {
        
        return nameField;
    }

    public void instrumentationRun() throws Exception {
        
        FileInputStream in = new FileInputStream(getPathFile() + getNameFile());
        
        CompilationUnit compilation;
        
        compilation = JavaParser.parse(in);
        
        in.close();
        
        instrumentarClasse(compilation);
        
        writeFile(getPathFile(), getNameFile(), compilation.toString());
    }

    private void instrumentarClasse(CompilationUnit compilation) {
        
        FieldDeclaration vigia = ASTHelper.createFieldDeclaration(ModifierSet.STATIC, ASTHelper.BOOLEAN_TYPE, getNameField());
        
        ClassOrInterfaceDeclaration classe = null;
        
        List<TypeDeclaration> types = compilation.getTypes();
        
        for (TypeDeclaration typeDeclaration : types) {
            
            if (typeDeclaration instanceof ClassOrInterfaceDeclaration && !((ClassOrInterfaceDeclaration) typeDeclaration).isInterface()) {
                classe = (ClassOrInterfaceDeclaration) typeDeclaration;
            }
        }
        
        if (classe == null) return;
        
        List<BodyDeclaration> membros = classe.getMembers();
        
        if (membros.contains(vigia)) {
            
            return;
        }
        
        for (BodyDeclaration bodyDeclaration : membros) {
            
            if (bodyDeclaration instanceof MethodDeclaration) {
                instrumentaMetodo((MethodDeclaration) bodyDeclaration);
            }else if(bodyDeclaration instanceof ConstructorDeclaration){
            	instrumentaConstrututor((ConstructorDeclaration) bodyDeclaration);
            }
            else if (bodyDeclaration instanceof ClassOrInterfaceDeclaration){
            	
            	ClassOrInterfaceDeclaration clazz = (ClassOrInterfaceDeclaration) bodyDeclaration;
            	
                List<BodyDeclaration> members =  clazz.getMembers();
                
                for (BodyDeclaration member : members) {
                	if (member instanceof MethodDeclaration ){
                        instrumentaMetodo((MethodDeclaration) member);
                	}
                	else if (member instanceof ConstructorDeclaration){
                		instrumentaConstrututor((ConstructorDeclaration) member);
                	}
                }
            
            }
        }
        
        ASTHelper.addMember(classe, vigia);
    }

   private void instrumentaConstrututor(ConstructorDeclaration contrutor) {
        
        BlockStmt linhas = contrutor.getBlock();
        
        if (linhas == null) return;
        
        List<Statement> stmts = linhas.getStmts();
        
        FieldAccessExpr acessoAtributo = new FieldAccessExpr(new ThisExpr(null), getNameField());
        
        NameExpr valorExpressao = new NameExpr(acessoAtributo.getField());
        
        AssignExpr expressao = new AssignExpr(valorExpressao, valorExpressao, AssignExpr.Operator.assign);
        
        Statement watchStatement = new ExpressionStmt(expressao);
        
        List<Statement> novaLista = new ArrayList<Statement>();
        
        if (stmts != null) {
            
            for (int i = 0; i < stmts.size(); i++) {
                Statement stm = stmts.get(i);
                if(i != 0 || !(stmts.get(i).toString().contains("super") || stmts.get(i).toString().contains("this")))
                	novaLista.add(watchStatement);
                stm = instrumentarBody(stm, watchStatement);
                novaLista.add(stm);
            }
            
            linhas.setStmts(novaLista);
        }
    }
    
    private void instrumentaMetodo(MethodDeclaration metodo) {
        
        BlockStmt linhas = metodo.getBody();
        
        if (linhas == null) return;
        
        List<Statement> stmts = linhas.getStmts();
        
        FieldAccessExpr acessoAtributo = new FieldAccessExpr(new ThisExpr(null), getNameField());
        
        NameExpr valorExpressao = new NameExpr(acessoAtributo.getField());
        
        AssignExpr expressao = new AssignExpr(valorExpressao, valorExpressao, AssignExpr.Operator.assign);
        
        Statement watchStatement = new ExpressionStmt(expressao);
        
        List<Statement> novaLista = new ArrayList<Statement>();
        
        if (stmts != null) {
            
            for (int i = 0; i < stmts.size(); i++) {
                Statement stm = stmts.get(i);
                novaLista.add(watchStatement);
                stm = instrumentarBody(stm, watchStatement);
                novaLista.add(stm);
            }
            
            linhas.setStmts(novaLista);
        }
    }

    private Statement instrumentarBody(Statement stm, Statement watch) {
        
        if (stm == null) {
            
            return null;
        } else if (stm instanceof BlockStmt) {
            
            List<Statement> novaLista = new ArrayList<Statement>();
            
            BlockStmt novoBloco = (BlockStmt) stm;
            if(novoBloco.getStmts() != null){
            	for (Statement stat : novoBloco.getStmts()) {
            		novaLista.add(watch);
            		novaLista.add(stat);
            	}          	
            }
            
            novoBloco.setStmts(novaLista);
            
            return novoBloco;
        } else if (stm instanceof IfStmt) {
            
            IfStmt state = (IfStmt) stm;
            
            state.setThenStmt(instrumentarBody(state.getThenStmt(), watch));
            
            state.setElseStmt(instrumentarBody(state.getElseStmt(), watch));
            
            return state;
        } else if (stm instanceof WhileStmt) {
            
            WhileStmt state = (WhileStmt) stm;
            
            state.setBody(instrumentarBody(state.getBody(), watch));
            
            return state;
        } else if (stm instanceof ForStmt) {
            
            ForStmt state = (ForStmt) stm;
            
            state.setBody(instrumentarBody(state.getBody(), watch));
            
            return state;
        } else if (stm instanceof ForeachStmt) {
            
            ForeachStmt state = (ForeachStmt) stm;
            
            state.setBody(instrumentarBody(state.getBody(), watch));
            
            return state;
        } else if (stm instanceof TryStmt) {
            
            TryStmt state = (TryStmt) stm;
            
            state.setTryBlock((BlockStmt) instrumentarBody(state.getTryBlock(), watch));
            
            state.setFinallyBlock((BlockStmt) instrumentarBody(state.getFinallyBlock(), watch));
            
            return state;
        } else {
            
            return stm;
        }
    }

    private void writeFile(String dir, String nameFile, String data) throws IOException {
        
        File path = new File(dir);
        
        if (!path.exists()) path.mkdir();
        
        FileOutputStream out = new FileOutputStream(new File(dir + nameFile));
        
        out.write(data.getBytes());
        
        out.close();
    }

}
