package com.codingchallenges.web_server.RequestMapping;

public class MainTrieGetter {

        private static Trie root;
    
        private MainTrieGetter() {
        }
    
        public static void setRoot(Trie root) {
            MainTrieGetter.root = root;
        }
    
        public static Trie getRoot() {
            return root;
        }
    
    }