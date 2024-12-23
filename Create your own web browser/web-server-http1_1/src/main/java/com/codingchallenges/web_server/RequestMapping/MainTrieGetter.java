package com.codingchallenges.web_server.RequestMapping;

/*
 * 
 * Static object to maintain a universal Trie DS across all the threads.
 * 
 */

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