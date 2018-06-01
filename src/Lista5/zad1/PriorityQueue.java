package Lista5.zad1;

import java.util.Arrays;

public class PriorityQueue {
    private int maxSize = 10;
    private int size = 0;
    HeapItem[] heap = new HeapItem[maxSize];

    public void insert(int value, int priority){
        if(size == maxSize - 1){
            resize();
        }
        heap[size] = new HeapItem(value,priority);
        fixHeapInsert(size);
        size++;
    }

    public void Empty(){
        if(isEmpty()){
            System.out.println("1");
        } else {
            System.out.println("0");
        }
    }

    public void top(){
        if(isEmpty()){
            System.out.println();
        } else {
            System.out.println(heap[0].getValue());
        }
    }

    public HeapItem pop(){
        top();
        return remove();
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

    public void priority(int value, int priority){
        for (int index = 0; index < size;index++) {
            HeapItem item = heap[index];
            if(item.getValue().equals(value) && item.getPriority() < priority){
                item.setPriority(priority);
                fixHeapPop(index);
            }
        }
    }
    public void print(){
        StringBuilder buffer = new StringBuilder();
        for(int index = 0; index < size; index ++)
            buffer.append("(")
                    .append(heap[index].getValue())
                    .append(",")
                    .append(heap[index].getPriority())
                    .append(") ");
        System.out.println(buffer.toString());
    }

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
