import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        String command;
        ContactsBook cb = new ContactsBook();
        for (int i = 0 ; i < n ; i++) {
            command = in.next();
            if (command.equals("add"))
                cb.add(in.next());
            else
                System.out.println(cb.find(in.next()));
        }
    }
}

 class ContactsBook {
        Node root = null;
        
        public ContactsBook() {
            root = new Node();
        }
        
        public void add(String contact) {
            Node curNode = root;
            for (int i = 0; i < contact.length() ; i++) {
                Map<Character, Node> map = curNode.getChildrenMap();
                if (!map.containsKey(contact.charAt(i))) {
                    map.put(contact.charAt(i), new Node());
                }
                curNode = map.get(contact.charAt(i));
            }
            curNode.getChildrenMap().put('\0', new Node());
        }
        
        public int find(String word) {
            Node curNode = root;
            for (int i = 0; i < word.length() ; i++) {
                Map<Character, Node> map = curNode.getChildrenMap();
                if (map.containsKey(word.charAt(i)))
                    curNode = map.get(word.charAt(i));
                else
                    return 0;
            }
            return numWords(curNode);
        }
        
        public int numWords(Node n) {
            int sum = 0;
            for (Character c: n.getChildrenMap().keySet()) {
                if (c.equals('\0'))
                    ++sum;
                sum += numWords(n.getChildrenMap().get(c));
            }
            return sum;
        }
    }
    
 class Node {
        Map<Character, Node> childrenMap;
        
        public Node() {
            childrenMap = new HashMap<Character, Node>();
        }
        
        public Map<Character, Node> getChildrenMap() {
            return childrenMap;
        }
    }

