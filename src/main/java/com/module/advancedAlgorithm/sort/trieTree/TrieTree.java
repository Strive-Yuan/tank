package com.module.advancedAlgorithm.sort.trieTree;

import java.util.HashMap;

/**
 * 前缀树
 */
public class TrieTree {
    TrieTreeNode root;

    public TrieTree() {
        this.root = new TrieTreeNode();
    }

    public static void main(String[] args) {
        int arrLen = 100;
        int strLen = 20;
        int testTimes = 100000;
        for (int i = 0; i < testTimes; i++) {
            String[] arr = generateRandomStringArray(arrLen, strLen);
            TrieTree trie1 = new TrieTree();
            Right right = new Right();
            for (int j = 0; j < arr.length; j++) {
                double decide = Math.random();
                if (decide < 0.25) {
                    trie1.insert(arr[j]);
                    right.insert(arr[j]);
                } else if (decide < 0.5) {
                    trie1.delete(arr[j]);
                    right.delete(arr[j]);
                } else if (decide < 0.75) {
                    int ans1 = trie1.search(arr[j]);
                    int ans3 = right.search(arr[j]);
                    if (ans1 != ans3) {
                        System.out.println("search Oops!");
                    }
                } else {
                    int ans1 = trie1.prefixNumber(arr[j]);
                    int ans3 = right.prefixNumber(arr[j]);
                    if (ans1 != ans3) {
                        System.out.println("prefixNumber Oops!  ans1:" + ans1 + "  ans2:" + ans3);
                    }
                }
            }
        }
        System.out.println("finish!");
    }

//    public static void main(String[] args) {
//        String[] strings = {"abc", "ac", "abc", "abcd", "sad", "abcd", "fsfg", "safqqwer", "sad"};
//        TrieTree trieTree = new TrieTree();
//        for (String string : strings) {
//            trieTree.insert(string);
//        }
//        System.out.println(trieTree.prefixNumber("a"));
//        System.out.println("-------------------------------------");
//        Right right = new Right();
//        for (String string : strings) {
//            right.insert(string);
//        }
//        right.box.forEach((k, v) -> System.out.println("k:" + k + "  v:" + v));
//        System.out.println(right.prefixNumber("a"));
//    }

    public void delete(String word) {
        if (word == null) {
            throw new RuntimeException("null...");
        }
        if (search(word) != 0) {
            char[] chars = word.toCharArray();
            TrieTreeNode curNode = root;
            for (char aChar : chars) {
                int index = aChar - 'a';
                if (curNode.nextArr[index] == null) {
                    return;
                }
                curNode = curNode.nextArr[index];
                curNode.pass--;
            }
            curNode.end--;
        }
    }

    /**
     * 所有加入的字符串中，有几个是以pre这个字符串作为前缀的
     */
    public int prefixNumber(String word) {
        if (word == null) {
            throw new RuntimeException("null....");
        }
        char[] chars = word.toCharArray();
        TrieTreeNode curNode = root;
        for (char aChar : chars) {
            int index = aChar - 'a';
            if (curNode.nextArr[index] == null) {
                return 0;
            }
            curNode = curNode.nextArr[index];
        }
        return curNode.pass;
    }

    /**
     * 某个单词加入几次
     */
    public int search(String word) {
        if (word == null) {
            throw new RuntimeException("null...");
        }
        char[] chars = word.toCharArray();
        TrieTreeNode curNode = root;
        for (char aChar : chars) {
            int index = aChar - 'a';
            if (curNode.nextArr[index] == null) {
                return 0;
            }
            curNode = curNode.nextArr[index];
        }
        return curNode.end;
    }

    public void insert(String word) {
        if (word == null) {
            return;
        }
        TrieTreeNode curNode = root;
        char[] chars = word.toCharArray();
        for (char aChar : chars) {
            int curIndex = aChar - 'a';
            if (curNode.nextArr[curIndex] == null) {
                curNode.nextArr[curIndex] = new TrieTreeNode();
            }
            curNode = curNode.nextArr[curIndex];
            curNode.pass++;
        }
        curNode.end++;
    }


    public static class TrieTreeNode {
        public int pass;
        public int end;
        public TrieTreeNode[] nextArr;

        public TrieTreeNode() {
            this.pass = 0;
            this.end = 0;
            this.nextArr = new TrieTreeNode[26];
        }
    }


    // for test
    public static String generateRandomString(int strLen) {
        char[] ans = new char[(int) (Math.random() * strLen) + 1];
        for (int i = 0; i < ans.length; i++) {
            int value = (int) (Math.random() * 6);
            ans[i] = (char) (97 + value);
        }
        return String.valueOf(ans);
    }

    // for test
    public static String[] generateRandomStringArray(int arrLen, int strLen) {
        String[] ans = new String[(int) (Math.random() * arrLen) + 1];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = generateRandomString(strLen);
        }
        return ans;
    }

    public static class Trie1 {
        private Node1 root;

        public Trie1() {
            root = new Node1();
        }

        public static class Node1 {
            public int pass;
            public int end;
            public Node1[] nexts;

            public Node1() {
                pass = 0;
                end = 0;
                nexts = new Node1[26];
            }
        }

        public void insert(String word) {
            if (word == null) {
                return;
            }
            char[] chs = word.toCharArray();
            Node1 node = root;
            node.pass++;
            int index = 0;
            for (int i = 0; i < chs.length; i++) { // 从左往右遍历字符
                index = chs[i] - 'a'; // 由字符，对应成走向哪条路
                if (node.nexts[index] == null) {
                    node.nexts[index] = new Node1();
                }
                node = node.nexts[index];
                node.pass++;
            }
            node.end++;
        }

        public void delete(String word) {
            if (search(word) != 0) {
                char[] chs = word.toCharArray();
                Node1 node = root;
                node.pass--;
                int index = 0;
                for (int i = 0; i < chs.length; i++) {
                    index = chs[i] - 'a';
                    if (--node.nexts[index].pass == 0) {
                        node.nexts[index] = null;
                        return;
                    }
                    node = node.nexts[index];
                }
                node.end--;
            }
        }

        // word这个单词之前加入过几次
        public int search(String word) {
            if (word == null) {
                return 0;
            }
            char[] chs = word.toCharArray();
            Node1 node = root;
            int index = 0;
            for (int i = 0; i < chs.length; i++) {
                index = chs[i] - 'a';
                if (node.nexts[index] == null) {
                    return 0;
                }
                node = node.nexts[index];
            }
            return node.end;
        }

        // 所有加入的字符串中，有几个是以pre这个字符串作为前缀的
        public int prefixNumber(String pre) {
            if (pre == null) {
                return 0;
            }
            char[] chs = pre.toCharArray();
            Node1 node = root;
            int index = 0;
            for (int i = 0; i < chs.length; i++) {
                index = chs[i] - 'a';
                if (node.nexts[index] == null) {
                    return 0;
                }
                node = node.nexts[index];
            }
            return node.pass;
        }
    }

    public static class Right {

        private HashMap<String, Integer> box;

        public Right() {
            box = new HashMap<>();
        }

        public void insert(String word) {
            if (!box.containsKey(word)) {
                box.put(word, 1);
            } else {
                box.put(word, box.get(word) + 1);
            }
        }

        public void delete(String word) {
            if (box.containsKey(word)) {
                if (box.get(word) == 1) {
                    box.remove(word);
                } else {
                    box.put(word, box.get(word) - 1);
                }
            }
        }

        public int search(String word) {
            if (!box.containsKey(word)) {
                return 0;
            } else {
                return box.get(word);
            }
        }

        public int prefixNumber(String pre) {
            int count = 0;
            for (String cur : box.keySet()) {
                if (cur.startsWith(pre)) {
                    count += box.get(cur);
                }
            }
            return count;
        }
    }
}

