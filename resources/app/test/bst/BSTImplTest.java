package bst;

import static org.junit.Assert.*;

import org.junit.Test;
import java.util.Arrays;

/**
* Teste de Arvore binaria de busca
*
* @author Samuel Santos
* @version 3.0
*/
 
import junit.framework.Assert;
 
import org.junit.Before;
import org.junit.Test;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;
 
public class BSTImplTest {
 
        private final BSTNode<Integer> NIL = new BSTNode();
        private BSTImpl<Integer> tree;
 
        @Before
        public void setUp() {
                tree = new BSTImpl<Integer>();
        }
 
        @Test
        public void testInitialization() {
                Assert.assertTrue(tree.isEmpty());
                Assert.assertFalse(tree.getRoot() == null);
                Assert.assertEquals(0, tree.size());
                Assert.assertEquals(null, tree.getRoot().getData());
                Assert.assertEquals(null, tree.getRoot().getLeft());
                Assert.assertEquals(null, tree.getRoot().getRight());
                Assert.assertEquals(null, tree.getRoot().getParent());
        }
 
        @Test
        public void testInsert(){
                
                Assert.assertEquals(NIL, tree.getRoot());
                Assert.assertEquals(null, tree.getRoot().getParent());
                Assert.assertEquals(null, tree.getRoot().getLeft());
                Assert.assertEquals(null, tree.getRoot().getRight());
                tree.insert(10);
                tree.insert(-10);
                tree.insert(15);
                Assert.assertEquals(null, tree.getRoot().getParent());
                Assert.assertTrue(-10 == tree.getRoot().getLeft().getData());
                Assert.assertTrue(15 == tree.getRoot().getRight().getData());
                Assert.assertEquals(tree.getRoot(), tree.getRoot().getLeft().getParent());
                Assert.assertEquals(NIL, tree.getRoot().getLeft().getLeft());
                Assert.assertEquals(NIL, tree.getRoot().getLeft().getRight());
                Assert.assertEquals(tree.getRoot(), tree.getRoot().getRight().getParent());
                Assert.assertEquals(NIL, tree.getRoot().getRight().getLeft());
                Assert.assertEquals(NIL, tree.getRoot().getRight().getRight());
        }
        
        @Test
        public void testHeight() {
                Assert.assertEquals(-1, tree.height());
                
                tree.insert(10);
                Assert.assertEquals(0, tree.height());
                
                tree.insert(10);
                Assert.assertEquals(0, tree.height());
                
                tree.insert(-10);
                tree.insert(8);
                tree.insert(15);
                
                Assert.assertEquals(2, tree.height());
        }
 
        @Test
        public void testSize() {
                Assert.assertEquals(0, tree.size());
                tree.insert(10);
                Assert.assertEquals(1, tree.size());
                tree.insert(10);
                Assert.assertEquals(1, tree.size());
                tree.insert(-10);
                tree.insert(8);
                tree.insert(15);
                Assert.assertEquals(4, tree.size());
        }
 
        @Test
        public void testSearch() {
                int searchRetorno;
 
                Assert.assertEquals(NIL, tree.search(10));
                tree.insert(10);
                tree.insert(-10);
                tree.insert(8);
                tree.insert(15);
 
                searchRetorno = tree.search(10).getData();
                Assert.assertEquals(10, searchRetorno);
                searchRetorno = tree.search(-10).getData();
                Assert.assertEquals(-10, searchRetorno);
                searchRetorno = tree.search(8).getData();
                Assert.assertEquals(8, searchRetorno);
                searchRetorno = tree.search(15).getData();
                Assert.assertEquals(15, searchRetorno);
        }
 
        @Test
        public void testMaximum() {
                int maximumRetorno;
 
                Assert.assertEquals(null, tree.maximum());
                tree.insert(10);
                tree.insert(-10);
                tree.insert(8);
                tree.insert(15);
 
                maximumRetorno = tree.maximum().getData();
                Assert.assertEquals(15, maximumRetorno);
        }
 
        @Test
        public void testMinimum() {
                int minimumRetorno;
 
                Assert.assertEquals(null, tree.minimum());
                tree.insert(10);
                tree.insert(-10);
                tree.insert(8);
                tree.insert(15);
 
                minimumRetorno = tree.minimum().getData();
                Assert.assertEquals(-10, minimumRetorno);
        }
        
        @SuppressWarnings({ "rawtypes", "unchecked" })
        @Test
        public void testSucessor(){
                int sucessorRetorno;
                BSTNode node;
               
                tree.insert(50);
                tree.insert(25);
                tree.insert(75);
                tree.insert(70);
                tree.insert(80);
                tree.insert(65);
                tree.insert(73);
                tree.insert(78);
                tree.insert(71);
 
                node = tree.sucessor(tree.getRoot().getData());
             
        }
        
        @Test
        public void testPredecessor(){
                int predecessorRetorno;
                BSTNode node;
 
               
                tree.insert(50);
                tree.insert(25);
                tree.insert(75);
                tree.insert(70);
                tree.insert(80);
                tree.insert(65);
                tree.insert(73);
                tree.insert(78);
                tree.insert(71);
             
                
        }
        
        @Test
        public void testRemove(){
                
                int valorNode;
                
                tree.remove(50);
                
                tree.insert(50);
                tree.insert(25);
                tree.insert(75);
                tree.insert(70);
                tree.insert(80);
                tree.insert(65);
                tree.insert(73);
                tree.insert(78);
                tree.insert(71);
                
                tree.remove(50);
                valorNode = tree.getRoot().getData();
                Assert.assertTrue(65 == valorNode);
                Assert.assertTrue(tree.getRoot().getRight().getLeft().getLeft().equals(NIL));
                tree.remove(73);
                valorNode = tree.getRoot().getRight().getLeft().getRight().getData();
                Assert.assertTrue(71 == valorNode);
                Assert.assertTrue(tree.getRoot().getRight().getLeft().getRight().getLeft().equals(NIL));
                tree.remove(25);
                Assert.assertTrue(tree.getRoot().getLeft().equals(NIL));
                
        }
        
        @Test
        public void testPreOrder(){
                Integer[] preOrder = {10, -10, 8, 15};
                Object[] retornoPreOrder;
                
                tree.insert(10);
                tree.insert(-10);
                tree.insert(8);
                tree.insert(15);
                
                retornoPreOrder = tree.preOrder();
                Arrays.equals(preOrder, retornoPreOrder);
                
        }
        
        @Test
        public void testOrder(){
                Integer[] order = {15, 10, 8, -10};
                Object[] retornoOrder;
                
                tree.insert(10);
                tree.insert(-10);
                tree.insert(8);
                tree.insert(15);
                
                retornoOrder = tree.order();
                Arrays.equals(order, retornoOrder);
        }
        
        @Test
        public void testPostOrder(){
                Integer[] postOrder = {8, -10, 15, 10};
                Object[] retornoPostOrder;
                
                tree.insert(10);
                tree.insert(-10);
                tree.insert(8);
                tree.insert(15);
                
                retornoPostOrder = tree.postOrder();
                Arrays.equals(postOrder, retornoPostOrder);
        }
        
 
}