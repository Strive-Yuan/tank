package com.module.advancedAlgorithm.sort.HeapSort;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 * 加强堆
 */
public class HeapGreater<Inner> {
    //heap
    public ArrayList<Inner> heap;
    public Integer heapSize;
    //索引表，记录元素在数组中的位置
    public Map<Inner, Integer> indexMap;
    private Comparator<? super Inner> comp;

    public HeapGreater(Comparator<? super Inner> comp) {
        this.heap = new ArrayList<>();
        this.heapSize = 0;
        this.indexMap = new HashMap<>();
        this.comp = comp;
    }

    public void push(Inner inner) {
        //加入堆中
        heap.add(inner);
        //记录元素位置，加入索引表
        indexMap.put(inner, heapSize);
        //维护堆结构
        heapInsert();
        //堆长度+1
        heapSize++;
    }

    public Inner poll() {
        if (heap.isEmpty()) {
            System.out.println("无了，无了");
        }
        //在堆中移除此元素
        Inner ans = heap.get(0);
        heap.remove(0);
        //从索引表中移除此元素
        indexMap.remove(ans);
        heapSize--;
        //维护堆结构
        heapIfy(0);
        return ans;
    }

    /**
     * 移除堆中某元素
     */
    public void remove(Inner inner) {

    }

    private void heapIfy(int curIndex) {
        //左子树位置
        int leftIndex = (curIndex * 2) + 1;
        //当存在左子树时
        while (leftIndex < heapSize) {
            //找出左子树和右子树较大的那个,右子树可能不存在
            int maxIndex = leftIndex + 1 < heapSize && comp.compare(heap.get(leftIndex), heap.get(leftIndex + 1)) > 0 ? leftIndex : leftIndex + 1;
            //找出当前节点和较大的子树中较大的那个
            int targetIndex = comp.compare(heap.get(maxIndex), heap.get(curIndex)) > 0 ? maxIndex : curIndex;
            //如果较大的那个是当前节点，直接返回
            if (curIndex == targetIndex) {
                return;
            }
            swap(curIndex, targetIndex);
            curIndex = targetIndex;
            leftIndex = (curIndex * 2) + 1;
        }

    }

    /**
     * 添加元素时，调整维护堆结构
     */
    private void heapInsert() {
        int curIndex = heapSize;
        while (comp.compare(heap.get(curIndex), heap.get((curIndex - 1) / 2)) > 0) {
            swap(curIndex, (curIndex - 1) / 2);
            curIndex = (curIndex - 1) / 2;
        }
    }

    private void swap(int curIndex, int targetIndex) {
        Inner curNode = heap.get(curIndex);
        Inner targetNode = heap.get(targetIndex);
        heap.set(curIndex, targetNode);
        heap.set(targetIndex, curNode);
        indexMap.put(targetNode, curIndex);
        indexMap.put(curNode, targetIndex);
    }
}
