package Lista5.zad2;

import java.util.Arrays;

public class PriorityQueue {
    private int maxSize = 10;
    private int size = 0;
    HeapItem[] heap = new HeapItem[maxSize];

    public void insert(Double priority, Vertex vertex){
        if(size == maxSize - 1){
            resize();
        }
        heap[size] = new HeapItem(priority,vertex);
        fixHeapInsert(size);
        size++;
    }


    public HeapItem remove(){
        if(!isEmpty()){
            HeapItem toReturn = heap[0];
            heap[0] = heap[--size];
            fixHeapPop(0);
            return toReturn;
        }
        return null;
    }
//TODO
//    public void priority(int value, int priority){
//        priority(value,priority,0);
//    }
//    private void priority(int value, int priority,int index){
//        if(index >= size){
//            return;
//        }
//        if(heap[index].getPriority() > priority){
//            return;
//        }
//
//
//
//        priority(value,getLeftChildIndex(index);
//        int right = getRightChildIndex(index);
//    }


    private void fixHeapInsert(int index){
        while(index > 0 && compare(getParentIndex(index),index)){
            swap(getParentIndex(index),index);
            index = getParentIndex(index);
        }
    }

    private void fixHeapPop(int index){
        int left = getLeftChildIndex(index);
        int right = getRightChildIndex(index);
        int smallest;
            if(left <= size && compare(index,left)){
            smallest = left;
        } else {
            smallest = index;
        }
        if(right <= size && compare(smallest,right)){
            smallest = right;
        }
        if(smallest != index){
            swap(index,smallest);
            fixHeapPop(smallest);
        }
    }

    private int getParentIndex(int index){
        return (index-1)/2;
    }

    public int getLeftChildIndex(int index){
        return 2*index+1;
    }

    public int getRightChildIndex(int index){

        return 2*index+2;
    }

    private boolean isEmpty(){
        return size == 0;
    }

    private void resize(){
        maxSize *=2;
        heap = Arrays.copyOf(heap, maxSize);
    }

    private boolean compare(int index1, int index2){
        return heap[index1].getPriority() > heap[index2].getPriority();
    }

    private void swap(int index1, int index2){
        HeapItem temporary = heap[index1];
        heap[index1] = heap[index2];
        heap[index2] = temporary;
    }

    public int getSize() {
        return size;
    }
}
